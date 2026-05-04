package csuarez.SpringTesting.Controllers;

import csuarez.SpringTesting.Entities.DTOs.AuthRequest;
import csuarez.SpringTesting.Entities.DTOs.AuthResponse;
import csuarez.SpringTesting.Entities.DTOs.RegisterRequest;
import csuarez.SpringTesting.Entities.User;
import csuarez.SpringTesting.Repositories.UserRepo;
import csuarez.SpringTesting.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails)));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userRepo.findByUsername(request.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken.");
        }
        User user = new User(
                request.getUsername(),
                request.getFirstname(),
                request.getLastname(),
                request.getDateOfBirth(),
                request.getEmail(),
                request.getAddress(),
                passwordEncoder.encode(request.getPassword())
        );
        userRepo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse(jwtUtil.generateToken(user)));
    }
}
