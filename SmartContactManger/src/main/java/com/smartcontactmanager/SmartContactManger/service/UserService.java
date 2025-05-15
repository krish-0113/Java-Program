package com.smartcontactmanager.SmartContactManger.service;

import com.smartcontactmanager.SmartContactManger.entity.User;

public interface UserService {
    boolean registerUser(User user);
    User loginUser(String email, String password);  // ✅ Fix method signature
}
