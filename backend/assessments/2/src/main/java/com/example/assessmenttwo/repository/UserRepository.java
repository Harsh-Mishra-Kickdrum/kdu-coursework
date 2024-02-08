package com.example.assessmenttwo.repository;
import com.example.assessmenttwo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Repository interface for {@link User} entity.
 * It extends {@link JpaRepository} to leverage standard CRUD functionalities.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long > {

    @Transactional
    User findByName(String name);

    @Transactional
    User findByID(Long id);
}
