package com.springboot.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
public class Session extends BaseModel{
    private String token;
    @ManyToOne
    private User user;
    private Date expiringAt;
    @Enumerated(EnumType.ORDINAL)
    private SessionStatus sessionStatus;
}
