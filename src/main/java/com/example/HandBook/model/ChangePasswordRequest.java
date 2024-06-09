package com.example.HandBook.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRequest {
    String uid;
    String newPassword;
}
