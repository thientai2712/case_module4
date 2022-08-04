package com.cg.service.user;

import com.cg.exception.EmailExistsException;
import com.cg.model.dto.UserDTO;
import com.cg.service.IGeneralService;
import com.cg.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {

    List<UserDTO> findAllUserDTO();

    Boolean existsById(Long id);

    Boolean existsByEmail(String email);

    Boolean existByPhone (String phone);

    Boolean existsByEmailAndIdIsNot(String email, Long id);

    Boolean existsByPhoneAndIdIsNot(String phone, Long id);

    User getByEmail(String email);

    Optional<User> findByEmail(String email);

    Optional<UserDTO> findUserDTOByEmail(String email);

    UserDetails loadUserByEmail(String email) throws EmailExistsException;
}
