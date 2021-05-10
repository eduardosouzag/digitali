package com.digitali.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.digitali.api.entity.User;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUserName(String username);

    @Query(nativeQuery = true, value = "UPDATE USER u SET ACTIVE=false WHERE u.ID = :userId")
    void deactivated(@Param("userId") int userId);

    @Query(nativeQuery = true, value = "SELECT u FROM USER u WHERE u.DOCUMENT= '%:document%'")
    Optional<User> findUsersByDocument( @Param("document") String document);

    @Query(nativeQuery = true, value = "SELECT u FROM USER u WHERE u.DOCUMENT = :document")
    Optional<User> findUserByDocument(@Param("document") String document);

}
