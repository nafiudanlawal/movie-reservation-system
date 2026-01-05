package com.nafiu.moviereservationservice.auth.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    Integer id;

    String username;

    String password;

    @ManyToOne
    Role role;
}
