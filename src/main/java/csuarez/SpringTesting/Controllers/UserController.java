package csuarez.SpringTesting.Controllers;

import csuarez.SpringTesting.Entities.User;
import csuarez.SpringTesting.Logic.UserLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserLogic service;

    @PostMapping("/create")
    public ResponseEntity<?> createNewUser(@RequestBody User user) {
        return new ResponseEntity<>(service.createUser(user), HttpStatus.OK);
    }

    @GetMapping("/findUser/{username}")
    public ResponseEntity<?> createNewUser(@PathVariable String username) {
        return new ResponseEntity<>(service.getByUsername(username), HttpStatus.OK);
    }
}
