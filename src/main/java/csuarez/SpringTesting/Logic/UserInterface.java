package csuarez.SpringTesting.Logic;

import csuarez.SpringTesting.Entities.Users;

public interface UserInterface {
    String createUser(Users user);
    Users getByUsername(String username);
}
