package com.javasetup.springboot.repository.db2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javasetup.springboot.entity.db2.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
