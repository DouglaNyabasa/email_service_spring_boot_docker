package com.example.emailservicespringboot.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "users")
public class User {


    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private String email;
    private String password;
    private String firstName;
    private boolean isEnabled;
}
