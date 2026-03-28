package csuarez.SpringTesting.Logic;

import csuarez.SpringTesting.Entities.User;
import csuarez.SpringTesting.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class UserLogic implements UserInterface {
    @Autowired
    private UserRepo userRepo;

    @Override
    public String createUser(User user) {
        user.setDateOfBirth(LocalDate.now());
        userRepo.save(user);
        return "User created.";
    }

    @Override
    public User getByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
