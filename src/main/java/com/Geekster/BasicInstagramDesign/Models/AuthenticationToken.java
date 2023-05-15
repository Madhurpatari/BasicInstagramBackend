package com.Geekster.BasicInstagramDesign.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class AuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    private LocalDate tokenCreationDate;

    @OneToOne
    private User user;

    //Parameterized Constructor for patient
    public AuthenticationToken(User user){
        this.user = user;
        this.tokenCreationDate = LocalDate.now();
        this.token = UUID.randomUUID().toString(); //will generate random token for user in string format
    }
}