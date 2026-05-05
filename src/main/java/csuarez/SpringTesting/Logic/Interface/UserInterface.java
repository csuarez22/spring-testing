package csuarez.SpringTesting.Logic.Interface;

import csuarez.SpringTesting.Entities.User;

public interface UserInterface {
    User getByUsername(String username);
    String logout();
}
