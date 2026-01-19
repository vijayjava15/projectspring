package com.learn.websocket.user;

import com.learn.websocket.exception.ResponseUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public Object registerUser(User user) {
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if(optionalUser.isPresent()){
         return ResponseUtility.BADREQUEST(null, "User Already exist with this username     " +   user.getUsername());
        }
        userRepository.save(user);
        return ResponseUtility.OK(null, "User registered sucessfully    " +   user.getUsername());
    }

    @Override
    public Object login(User user) {
        StringBuilder errorMsg = new StringBuilder();
        if(user.getEmail().isBlank()  || user.getEmail() == null ){
            errorMsg.append("email is blank");
        }
        if(user.getPassword().isBlank() || user.getPassword() == null ){
            errorMsg.append("password is blank");
        }

        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if(optionalUser.isEmpty()){
            errorMsg.append("email is wrong");
        }
        User userInfo = optionalUser.get();

        if (!userInfo.getPassword().equals(user.getPassword())){
            errorMsg.append("password wrong ");
        }

        if (errorMsg.length()>0){
            return ResponseUtility.BADREQUEST(null , errorMsg.toString());
        }

        return ResponseUtility.OK(userInfo ,"user logged in sucessFully ");
    }


}
