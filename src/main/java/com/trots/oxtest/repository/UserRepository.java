package com.trots.oxtest.repository;

import com.trots.oxtest.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("select distinct u from UserEntity u left join fetch u.roles where u.email = :email")
    UserEntity findUserByEmailWithRoles(String email);
}
