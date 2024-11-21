package com.icet.crm.repository;

import com.icet.crm.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface RegisterRepository extends CrudRepository<UserEntity,Long> {


    Object findByEmail(String email);
}
