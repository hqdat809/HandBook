package com.example.HandBook.controller;

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
    public ResponseEntity<UserRecord> createUser(@RequestBody LoginRequest loginRequest) {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(loginRequest.getEmail())
                .setPassword(loginRequest.getPassword());

        try {
            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            return new ResponseEntity<>(userRecord, HttpStatus.OK);
        } catch (FirebaseAuthException e) {
            return null;
        }
    }

    @GetMapping("/{uid}")
    public ResponseEntity<UserRecord> getUser(@PathVariable String uid) {
        try {
            return new ResponseEntity<>(FirebaseAuth.getInstance().getUser(uid), HttpStatus.OK);
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return null;
        }
    }
}