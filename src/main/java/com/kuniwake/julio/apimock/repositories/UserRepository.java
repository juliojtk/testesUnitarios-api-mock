package com.kuniwake.julio.apimock.repositories;

import com.kuniwake.julio.apimock.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
