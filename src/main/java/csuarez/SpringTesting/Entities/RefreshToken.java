package csuarez.SpringTesting.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "refresh_token")
public class RefreshToken {

    @Id
    private String username;
    private String token;

    public RefreshToken() {
    }

    public RefreshToken(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}