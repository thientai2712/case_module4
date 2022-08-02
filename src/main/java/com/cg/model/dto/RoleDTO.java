package com.cg.model.dto;

import com.cg.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RoleDTO {

    @NotNull(message = "The role is required")
    private Long id;

    private String code;

    private String name;

    public Role toRoleDTO() {
        return new Role()
                .setId(id)
                .setCode(code)
                .setName(name);
    }
}
