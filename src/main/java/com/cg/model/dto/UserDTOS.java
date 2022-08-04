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
public class UserDTOS {

    private Long id;

    @Size(min = 5, max = 30, message = "The length of Full Name must be between 5 and 30 characters")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotBlank(message = "The email is required")
    @Size(min = 5, max = 50, message = "The length of email must be between 5 and 50 characters")
    @Email(message = "The email address is invalid")
    @Column(nullable = false, unique = true)
    private String email;

    @Pattern(regexp = "^[0][1-9][0-9]{8,9}$", message = "Phone is not valid")
    private String phone;

    @Column(name = "url_image",nullable = false)
    private String urlImage;

    @Valid
    private LocationRegionDTO locationRegion;

    public UserDTOS(Long id, String email) {
        this.id = id;
        this.email = email;
    }
    public User toUser(){
        return new User()
                .setId(id)
                .setFullName(fullName)
                .setEmail(email)
                .setPhone(phone)
                .setUrlImage(urlImage)
                .setLocationRegion(locationRegion.toLocationRegion());

    }

}
