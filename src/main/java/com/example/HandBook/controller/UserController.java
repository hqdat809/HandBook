package com.example.HandBook.controller;

import com.example.HandBook.model.ChangePasswordRequest;
import com.example.HandBook.model.LoginRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody LoginRequest loginRequest) {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(loginRequest.getEmail())
                .setPassword(loginRequest.getPassword());

        try {
            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            return new ResponseEntity<>(userRecord, HttpStatus.OK);
        } catch (FirebaseAuthException e) {

            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        try {
            UserRecord.UpdateRequest request = new UserRecord.UpdateRequest(changePasswordRequest.getUid())
                    .setPassword(changePasswordRequest.getNewPassword());

            UserRecord userRecord = FirebaseAuth.getInstance().updateUser(request);
            return new ResponseEntity<>(userRecord, HttpStatus.OK);
        } catch (FirebaseAuthException e) {
            System.out.println("Error updating user: " + e.getMessage());
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{uid}")
    public ResponseEntity<?> getUser(@PathVariable String uid) {
        try {
            return new ResponseEntity<>(FirebaseAuth.getInstance().getUser(uid), HttpStatus.OK);
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}