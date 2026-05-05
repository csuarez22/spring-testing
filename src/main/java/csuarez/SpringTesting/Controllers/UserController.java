package csuarez.SpringTesting.Controllers;

import csuarez.SpringTesting.Entities.User;
import csuarez.SpringTesting.Exceptions.UserException;
import csuarez.SpringTesting.Logic.UserLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserLogic userService;

    @GetMapping("/findUser/{username}")
    public ResponseEntity<?> createNewUser(@PathVariable String username) {
        return new ResponseEntity<>(userService.getByUsername(username), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        try {
            return ResponseEntity.ok(userService.logout());
        }
        catch (UserException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
