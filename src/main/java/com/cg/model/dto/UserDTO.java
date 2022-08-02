package com.cg.model.dto;

import com.cg.model.LocationRegion;
import com.cg.model.Role;
import com.cg.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "user_name", nullable = false)
    private String username;


    private String password;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleDTO role;

    @Column(name = "url_image",nullable = false)
    private String urlImage;

     @Valid
    private LocationRegionDTO locationRegion;

    public UserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }
    public User toUser(){
        return new User()
                .setId(id)
                .setUrlImage(urlImage)
                .setFullName(fullName)
                .setUsername(username)
                .setPassword(password)
                .setPhone(phone)
                .setRole(role.toRoleDTO())
                .setLocationRegion(locationRegion.toLocationRegion());

    }

    public UserDTO(Long id, String fullName, String username, String password, String phone, LocationRegion locationRegion) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.locationRegion = locationRegion.toLocationRegionDTO();
    }

    public UserDTO(Long id,String urlImage, String fullName, String username, String password, String phone, LocationRegion locationRegion) {
        this.id = id;
        this.urlImage = urlImage;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.locationRegion = locationRegion.toLocationRegionDTO();
    }
}
