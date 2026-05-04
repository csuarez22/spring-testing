package csuarez.SpringTesting.Logic.Interface;

import csuarez.SpringTesting.Entities.User;

public interface UserInterface {
    String createUser(User user);
    User getByUsername(String username);
}
