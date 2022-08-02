package com.cg.service.user;

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

    Boolean existsByUsername(String username);

    Boolean existsByEmailAndIdIsNot(String username, Long id);

    User getByUsername(String username);

    Optional<User> findByUsername(String username);

    Optional<UserDTO> findUserDTOByUsername(String username);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
