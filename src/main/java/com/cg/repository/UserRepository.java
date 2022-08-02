package com.cg.repository;

import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User getByUsername(String username);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    @Query("SELECT new com.cg.model.dto.UserDTO (" +
            "u.id, " +
            "u.urlImage, " +
            "u.fullName, " +
            "u.username, " +
            "u.password, " +
            "u.phone, " +
            "u.locationRegion" +
            ") " +
            "FROM User AS u"
    )
    List<UserDTO> findAllUserDTO();

    @Query("SELECT new com.cg.model.dto.UserDTO (" +
            "u.id, " +
            "u.fullName, " +
            "u.username, " +
            "u.password, " +
            "u.phone, " +
            "u.locationRegion" +
            ") " +
            "FROM User AS u " +
            "WHERE u.id = :id"
    )
    UserDTO getUserDTOById();


    @Query("SELECT NEW com.cg.model.dto.UserDTO (u.id, u.username) FROM User u WHERE u.username = ?1")
    Optional<UserDTO> findUserDTOByUsername(String username);
}
