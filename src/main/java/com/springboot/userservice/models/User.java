package com.springboot.userservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@JsonDeserialize(as = User.class)
public class User extends BaseModel{
    private String email;
    private String password;
    @ManyToMany
    @JsonIgnore
    private Set<Role> roles =new HashSet<>();
}
