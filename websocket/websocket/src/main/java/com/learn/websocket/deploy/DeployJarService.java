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

        cmd.add("cd /home/bzf/backend/websocket/websocket");
        cmd.add(" mvn clean package ");
        cmd.add(" sudo mv  jar  /home/bzf/backendPublish/  ");
        cmd.add(" java -jar jarName");

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
