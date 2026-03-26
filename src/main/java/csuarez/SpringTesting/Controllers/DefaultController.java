package csuarez.SpringTesting.Controllers;

//imports
import csuarez.SpringTesting.Logic.DefaultLogic;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/default")
public class DefaultController {
    @Autowired
    private DefaultLogic service;

    @GetMapping("/returnDefault")
    public ResponseEntity<?> getReturnDefault() {
        return new ResponseEntity<>(service.returnDefault(), HttpStatus.OK);
    }
}