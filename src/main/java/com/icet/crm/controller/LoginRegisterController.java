package com.icet.crm.controller;

import com.icet.crm.dto.UserDTO;
import com.icet.crm.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/register")
@RequiredArgsConstructor
@CrossOrigin

public class LoginRegisterController {

    @Autowired
    final RegisterService registerService;

    @PostMapping("/addUser")
    public void registerUser(@RequestBody UserDTO userDTO) {
        registerService.registerUser(userDTO);
    }

    @GetMapping("/login/{email}")
    public UserDTO isLogin(@PathVariable String email){
        return registerService.isLogin(email);
    }
}
