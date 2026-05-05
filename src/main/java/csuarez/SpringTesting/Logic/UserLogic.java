package csuarez.SpringTesting.Logic;

import csuarez.SpringTesting.Entities.User;
import csuarez.SpringTesting.Logic.Interface.UserInterface;
import csuarez.SpringTesting.Repositories.RefreshTokenRepo;
import csuarez.SpringTesting.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserLogic implements UserInterface {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RefreshTokenRepo tokenRepo;

    @Override
    public User getByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public String logout() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        tokenRepo.deleteById(username);
        return "User correctly logged out.";
    }
}