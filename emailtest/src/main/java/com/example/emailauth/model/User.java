package com.example.emailauth.model;
import jakarta.persistence.*;
import java.util.UUID;
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private String token;
    private boolean verified = false;

    public User() {}

    public User(String email, String name) {
        this.email = email;
        this.name = name;
        this.token = UUID.randomUUID().toString();
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public boolean isVerified() {
        return verified;
    }
    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
