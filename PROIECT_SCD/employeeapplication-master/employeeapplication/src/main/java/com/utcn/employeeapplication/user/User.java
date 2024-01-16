package com.utcn.employeeapplication.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@Setter
@Getter

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    private String name;
    private String username;
    private String password;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private Date accountCreatedAt;
    private String occupation;

}
