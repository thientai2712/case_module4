package com.cg.controller.rest;

import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import com.cg.service.user.IUserService;
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

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody UserDTO userDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }

        userDTO.setId(0L);
        userDTO.getLocationRegion().setId(0L);

        User newUser = userService.save(userDTO.toUser());
        return  new ResponseEntity<>(newUser.toUserDTO(), HttpStatus.CREATED);
    }
}
