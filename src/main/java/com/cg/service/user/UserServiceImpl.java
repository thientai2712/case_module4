package com.cg.service.user;

import com.cg.exception.EmailExistsException;
import com.cg.model.LocationRegion;
import com.cg.model.User;
import com.cg.model.UserPrinciple;
import com.cg.model.dto.UserDTO;
import com.cg.repository.LocationRegionRepository;
import com.cg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRegionRepository locationRegionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }

//    @Override
//    public User save(User user) {
//        LocationRegion locationRegion = locationRegionRepository.save(user.getLocationRegion());
//        user.setLocationRegion(locationRegion);
//        return userRepository.save(user);
//    }

    @Override
    public List<UserDTO> findAllUserDTO() {
        return userRepository.findAllUserDTO();
    }

    @Override
    public List<UserDTO> findAllUserDTOdeleteFalse() {
        return userRepository.findAllUserDTOdeleteFalse();
    }

    @Override
    public Boolean existsById(Long id) {
        return userRepository.existsById(id);
    }



    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Boolean existByPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }

    @Override
    public Boolean existsByEmailAndIdIsNot(String username, Long id) {
        return userRepository.existsByEmailAndIdIsNot(username,id);
    }

    @Override
    public Boolean existsByPhoneAndIdIsNot(String phone, Long id) {
        return userRepository.existsByPhoneAndIdIsNot(phone, id);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> findUserDTOByEmail(String email) {
        return userRepository.findUserDTOByEmail(email);
    }


    @Override
    public UserDetails loadUserByEmail(String email) throws EmailExistsException {
        return null;
    }

    @Override
    public User save(User user) {
        LocationRegion locationRegion = locationRegionRepository.save(user.getLocationRegion());
        user.setLocationRegion(locationRegion);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }



    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void softDelete(User user) {
        user.setDeleted(true);
        userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(email);
        }
        return UserPrinciple.build(userOptional.get());
//        return (UserDetails) userOptional.get();
    }

}
