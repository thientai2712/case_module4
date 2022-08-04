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
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDTO {

    private Long id;

    @Size(min = 5, max = 30, message = "The length of Full Name must be between 5 and 30 characters")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotBlank(message = "The email is required")
    @Size(min = 5, max = 50, message = "The length of email must be between 5 and 50 characters")
    @Email(message = "The email address is invalid")
    @Column(nullable = false, unique = true)
    private String email;

    @Size(max = 30, message = "Maximum password length 30 characters")
//    @Size(min = 6, message = "Minimum password length 30 characters")
    private String password;

    @Pattern(regexp = "^[0][1-9][0-9]{8,9}$", message = "Phone is not valid")
    private String phone;

    @Valid
    private RoleDTO role;

    @Column(name = "url_image",nullable = false)
    private String urlImage;

    @Valid
    private LocationRegionDTO locationRegion;

    public UserDTO(Long id, String email) {
        this.id = id;
        this.email = email;
    }
    public User toUser(){
        return new User()
                .setId(id)
                .setUrlImage(urlImage)
                .setFullName(fullName)
                .setEmail(email)
                .setPassword("123")
                .setPhone(phone)
                .setRole(role.toRoleDTO())
                .setLocationRegion(locationRegion.toLocationRegion());

    }

    public UserDTO(Long id, String fullName, String email, String password, String phone, LocationRegion locationRegion) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.locationRegion = locationRegion.toLocationRegionDTO();
    }

    public UserDTO(Long id,String urlImage, String fullName, String email, String password, String phone, LocationRegion locationRegion) {
        this.id = id;
        this.urlImage = urlImage;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.locationRegion = locationRegion.toLocationRegionDTO();
    }
}
