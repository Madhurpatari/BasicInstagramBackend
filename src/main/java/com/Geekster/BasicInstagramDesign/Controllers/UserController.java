package com.Geekster.BasicInstagramDesign.Controllers;

import com.Geekster.BasicInstagramDesign.Models.SignInRequest;
import com.Geekster.BasicInstagramDesign.Models.SignUpRequest;
import com.Geekster.BasicInstagramDesign.Models.UpdateUserRequest;
import com.Geekster.BasicInstagramDesign.Models.User;
import com.Geekster.BasicInstagramDesign.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest signUpRequest) {
        return userService.signUp(signUpRequest);
    }
    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody SignInRequest signInRequest){
        return userService.signIn(signInRequest);
    }

    //Update user details by email
    @PutMapping("/{email}")
    public ResponseEntity<String> updateUserDetails(@PathVariable String email, @RequestBody UpdateUserRequest updateUserRequest) {
        try {
            User existingUser = userService.getUserByEmail(email);

            if (existingUser == null) {
                return new ResponseEntity<>("User not found..!!", HttpStatus.NOT_FOUND);
            }

            // Update the user details with the values from the request
            existingUser.setFirstName(updateUserRequest.getFirstName());
            existingUser.setLastName(updateUserRequest.getLastName());
            existingUser.setAge(updateUserRequest.getAge());
            existingUser.setEmail(updateUserRequest.getEmail());
            existingUser.setPhoneNumber(updateUserRequest.getPhoneNumber());

            User updatedUser = userService.updateUser(existingUser);

            if (updatedUser != null) {
                return new ResponseEntity<>("User details updated successfully",HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to update user details",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while updating user details",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
