package com.icet.crm.service;

import com.icet.crm.dto.UserDTO;

public interface RegisterService {

    void registerUser(UserDTO userDTO);
    UserDTO isLogin(String email);
}
