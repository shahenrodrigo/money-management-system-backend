package com.icet.crm.service;

import com.icet.crm.dto.UserDTO;
import com.icet.crm.entity.UserEntity;
import com.icet.crm.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService{

    private final RegisterRepository registerRepository;
    private final ModelMapper mapper;

    @Override
    public void registerUser(UserDTO userDTO) {
        registerRepository.save(mapper.map(userDTO, UserEntity.class));
    }

    @Override
    public UserDTO isLogin(String email) {
        return mapper.map(registerRepository.findByEmail(email),UserDTO.class);
    }

}
