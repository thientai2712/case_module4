package com.cg.repository;

import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import com.cg.model.dto.UserDTOS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User getByEmail(String email);


//    @Query("SELECT " +
//            "u.id, " +
//            "u.urlImage, " +
//            "u.fullName, " +
//            "u.email, " +
//            "u.password, " +
//            "u.phone, " +
//            "u.locationRegion" +
//            "FROM User AS u"
//    )
    Optional<User> findByEmail(String email);

    Optional<User> findUserById (Long id);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

    Boolean existsByEmailAndIdIsNot(String username, Long id);

    Boolean existsByPhoneAndIdIsNot(String phone, Long id);

    @Query("SELECT new com.cg.model.dto.UserDTO (" +
            "u.id, " +
            "u.urlImage, " +
            "u.fullName, " +
            "u.email, " +
            "u.password, " +
            "u.phone, " +
            "u.role, " +
            "u.locationRegion" +
            ") " +
            "FROM User AS u"
    )
    List<UserDTO> findAllUserDTO();

    @Query("SELECT new com.cg.model.dto.UserDTO (" +
            "u.id, " +
            "u.fullName, " +
            "u.email, " +
            "u.password, " +
            "u.phone, " +
            "u.locationRegion" +
            ") " +
            "FROM User AS u " +
            "WHERE u.id = :id"
    )
    UserDTO getUserDTOById();



    @Query("SELECT NEW com.cg.model.dto.UserDTO (u.id, u.email) FROM User u WHERE u.email = ?1")
    Optional<UserDTO> findUserDTOByEmail(String email);

}
