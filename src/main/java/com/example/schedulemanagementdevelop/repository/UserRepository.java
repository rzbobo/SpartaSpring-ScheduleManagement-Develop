package com.example.schedulemanagementdevelop.repository;

import com.example.schedulemanagementdevelop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {



    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String username);
    Optional<User> findIdByEmailAndPassword(String email, String password);



    default User findUserByUsernameOrElseThrow(String username) {
        return findUserByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist username = " + username));
    }






}

/*
ㄻㄴㅇㄻㄴㅇㄻㄴㅇㄻㄴㅇ ㄹ ㅁㄴㅇㄻㄴㅇㄹ ㅁㄴㅇ
Optional<User> findUserByUsernameOrElseThrow(String username);

default User findUserByUsernameOrElseThrow(String username) {
    return findUserByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist username = " + username));
}



Optional<User> findByUsernameOrElseThrow(String username);

default User findByUsernameOrElseThrow(String username) {
    return findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist username = " + username));
}


 */


//'findUserByUsernameOrElseThrow(String)'
//'findUserByUsernameOrElseThrow(String)'