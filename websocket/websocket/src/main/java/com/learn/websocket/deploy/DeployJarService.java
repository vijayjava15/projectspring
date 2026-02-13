package com.learn.websocket.deploy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DeployJarService {

    void deploy() throws IOException, InterruptedException {

        // goto directory
        List<String> cmd = new ArrayList<>();
        cmd.add("git status");
        cmd.add("cd /appl/server/backend/code/projectSpring/websocket/websocket");
        cmd.add(" mvn clean package ");
        cmd.add(" sudo mv  /appl/server/backend/code/projectSpring/websocket/websocket/target/websocket-0.0.1-SNAPSHOT.jar  /appl/server/backend/");
        cmd.add("cd /appl/server/backend/");
        cmd.add(" nohup java -jar websocket-0.0.1-SNAPSHOT.jar");

        ProcessBuilder pb = new ProcessBuilder(cmd);

        pb.redirectErrorStream(true); // merge stderr + stdout
        Process process = pb.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        int exitCode = process.waitFor();
        System.out.println("Exit code: " + exitCode);

    }

}
