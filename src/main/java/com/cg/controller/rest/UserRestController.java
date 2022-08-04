package com.cg.controller.rest;

import com.cg.exception.DataInputException;
import com.cg.exception.EmailExistsException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import com.cg.model.dto.UserDTOS;
import com.cg.service.user.IUserService;
import com.cg.service.user.UserServiceImpl;
import com.cg.util.AppUtil;
import jdk.jfr.internal.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private AppUtil appUtil;

    @Autowired
    private IUserService userService;

    @GetMapping()
    public ResponseEntity<?> findAllUser(){
        List<UserDTO> userDTOS = userService.findAllUserDTO();

        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable long id){
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()){
            throw new ResourceNotFoundException("Invalid customer ID");
        }
        return new ResponseEntity<>(userOptional.get().toUserDTO(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody UserDTO userDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }

        userDTO.setId(0L);
        userDTO.getLocationRegion().setId(0L);

        Boolean exitsEmail = userService.existsByEmail(userDTO.getEmail());

        if (exitsEmail){
            throw new EmailExistsException("Email already exits");
        }

        User newUser = userService.save(userDTO.toUser());

        return  new ResponseEntity<>(newUser.toUserDTO(), HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<?> doUpdate(@Validated @RequestBody UserDTOS userDTOS, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }

        Optional<User> currentUser = userService.findById(userDTOS.getId());

        userDTOS.getLocationRegion().setId(currentUser.get().getLocationRegion().getId());

        Boolean existId = userService.existsById(userDTOS.getId());

        if (!existId) {
            throw new ResourceNotFoundException("Customer ID invalid");
        }

//        Boolean existEmail =userService.existsByEmailAndIdIsNot(userDTOS.getEmail(), userDTOS.getId());
//
//        if (existEmail){
//            throw new DataInputException("Email is exist");
//        }

//        userDTOS.getLocationRegion().setId(0L);

        currentUser.get().setFullName(userDTOS.getFullName());
        currentUser.get().setEmail(userDTOS.getEmail());
        currentUser.get().setLocationRegion(userDTOS.getLocationRegion().toLocationRegion());
        currentUser.get().setPhone(userDTOS.getPhone());
        currentUser.get().setUrlImage("user.png");





        User updatedUser = userService.save(currentUser.get());

        return new ResponseEntity<>(updatedUser.toUserDTOS(), HttpStatus.ACCEPTED);
    }


}
