package com.Geekster.BasicInstagramDesign.Services;

import com.Geekster.BasicInstagramDesign.Models.*;
import com.Geekster.BasicInstagramDesign.Repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private AuthenticationService authenticationService;

    //Sign-up as a new user
    public ResponseEntity<String> signUp(SignUpRequest signUpRequest) {
        //Step 1.check if User already exist or not
        User user = userRepository.findFirstByEmail(signUpRequest.getEmail());

        //Step 2.if user is not equals null ,this means user with this email already exists in the database
        if (user != null) {
            throw new IllegalStateException("User with this email already exist..try sign-in");
        }
        //Step 3.Encryption of password
        String hashedPassword = DigestUtils.md5DigestAsHex(signUpRequest.getPassword().getBytes());

        //Step 4.save the user as a new instagram user
        user = new User(
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getAge(),
                signUpRequest.getEmail(),
                hashedPassword,
                signUpRequest.getPhoneNumber()
        );
        //Step 5. save instagram user in database
        userRepository.save(user);

        //Step 6.Create and save token corresponds to this user
        AuthenticationToken token = new AuthenticationToken(user);
        authenticationService.saveToken(token);

        return new ResponseEntity<>("User registered successfully..Thank you!!", HttpStatus.OK);
    }

    //Sign in as an instagram user
    public ResponseEntity<String> signIn(SignInRequest signInRequest) {

        //Step 1.Get user email and check if it's already there in the database or not
        User user =userRepository.findFirstByEmail(signInRequest.getEmail());

        //Step 2.if user is null it means there is no user registered with this email
        if(user == null){
            throw new IllegalStateException("No user is registered with this email..try sign-up!!");
        }
        //Step 3.Encrypt the user password to check if this encrypted password is exists in the database
        String encryptedPass = DigestUtils.md5DigestAsHex(signInRequest.getPassword().getBytes());

        //Step 4.Match it with already existing passwords
        boolean isPasswordValid = encryptedPass.equals(user.getPassword());

        //Step 5.if it is not valid throw exception
        if(!isPasswordValid){
            throw new IllegalStateException("User Invalid..try sign-in again!!");
        }
        //Step 6. figure out the token
        AuthenticationToken authenticationToken = authenticationService.getToken(user);

        return new ResponseEntity<>(authenticationToken.getToken(), HttpStatus.ACCEPTED);
    }

   

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public User updateUser(User existingUser) {
        try{
            return userRepository.save(existingUser);
        }catch (Exception e) {
            return null;
        }
    }
}
