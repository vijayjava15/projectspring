package com.learn.websocket.user;

import com.learn.websocket.exception.ResponseUtility;
import com.learn.websocket.security.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    private static final Logger log = LoggerFactory .getLogger(UserServiceImpl.class);

    @Override
    public Object registerUser(User user) {
        if (user == null) {
            return ResponseUtility.BADREQUEST(null, "User payload is required");
        }
        StringBuilder errorMsg = new StringBuilder();
        if (StringUtils.isBlank(user.getUsername())) {
            errorMsg.append("username is blank");
        }
        if (StringUtils.isBlank(user.getEmail())) {
            if (errorMsg.length() > 0) {
                errorMsg.append(" , ");
            }
            errorMsg.append("email is blank");
        }
        if (StringUtils.isBlank(user.getPassword())) {
            if (errorMsg.length() > 0) {
                errorMsg.append(" , ");
            }
            errorMsg.append("password is blank");
        }

        if (errorMsg.length() > 0) {
            return ResponseUtility.BADREQUEST(null, errorMsg.toString());
        }

        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if (optionalUser.isPresent()) {
            return ResponseUtility.BADREQUEST(null, "User Already exist with this username     " + user.getUsername());
        }
        Optional<User> optionalEmail = userRepository.findByEmail(user.getEmail());
        if (optionalEmail.isPresent()) {
            return ResponseUtility.BADREQUEST(null, "User Already exist with this email     " + user.getEmail());
        }
        userRepository.save(user);
        return ResponseUtility.OK(null, "User registered sucessfully    " + user.getUsername());
    }

    @Override
    public Object login(User user) {
        StringBuilder errorMsg = new StringBuilder();
        if (user == null) {
            return ResponseUtility.BADREQUEST(null, "User payload is required");
        }
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            errorMsg.append("email is blank");
        }
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            errorMsg.append("password is blank");
        }

        if (errorMsg.length() > 0) {
            return ResponseUtility.BADREQUEST(null, errorMsg.toString());
        }

        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isEmpty()) {
            return ResponseUtility.BADREQUEST(null, "email is wrong");
        }
        User userInfo = optionalUser.get();

        if (!userInfo.getPassword().equals(user.getPassword())) {
            return ResponseUtility.BADREQUEST(null, "password wrong ");
        }

        //generate token need separate method in future
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        String token = JwtUtil.generateToken(userInfo.getUsername(), claims);
        userInfo.setToken(token);
        userRepository.save(userInfo);
        return ResponseUtility.OK(userInfo, "user logged in sucessFully ");
    }

    @Override
    public boolean verifyToken(String token) {
        
        if (StringUtils.isBlank(token)) {
            return false;
        }
        
        log.info(token);
        Optional<User> optionalUser = userRepository.findByToken(token);
        if (optionalUser.isEmpty()) {
            return false;
        }
        User user = optionalUser.get();
        try {
            String usernameFromToken = JwtUtil.getUsername(token);
           log.info("usernameFromToken       :"  +   usernameFromToken);
            if (!StringUtils.equals(usernameFromToken, user.getUsername())) {
                return false;
            }
            return !JwtUtil.isTokenExpired(token);
        } catch (JwtException | IllegalArgumentException ex) {
            ex.printStackTrace();
            return false;
        }
    }


}
