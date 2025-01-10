package com.example.dto;

import com.example.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtDTO {
    private String id;
    private String username;
    private UserRole role;

    public JwtDTO(String id, String userName, UserRole role) {
        this.id = id;
        this.username = userName;
        this.role = role;
    }

    public JwtDTO(String id) {
        this.id = id;
    }
}
