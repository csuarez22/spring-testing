package csuarez.SpringTesting.Logic;

import csuarez.SpringTesting.Entities.DTOs.AuthRequest;
import csuarez.SpringTesting.Entities.DTOs.AuthResponse;
import csuarez.SpringTesting.Entities.DTOs.RefreshRequest;
import csuarez.SpringTesting.Entities.DTOs.RegisterRequest;
import csuarez.SpringTesting.Entities.RefreshToken;
import csuarez.SpringTesting.Entities.User;
import csuarez.SpringTesting.Exceptions.UserException;
import csuarez.SpringTesting.Logic.Interface.AuthInterface;
import csuarez.SpringTesting.Repositories.RefreshTokenRepo;
import csuarez.SpringTesting.Repositories.UserRepo;
import csuarez.SpringTesting.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AuthLogic implements AuthInterface {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RefreshTokenRepo tokenRepo;

    @Override
    public AuthResponse register(RegisterRequest request) {
        try {
            if (userRepo.findByUsername(request.getUsername()) != null) {
                throw UserException.usernameTaken(request.getUsername());
            }
            User user = new User(
                    request.getUsername(),
                    passwordEncoder.encode(request.getPassword()),
                    request.getEmail(),
                    request.getAddress(),
                    request.getDateOfBirth()
            );

            String refreshToken = jwtUtil.generateRefreshToken(user);

            userRepo.save(user);
            tokenRepo.save(new RefreshToken(user.getUsername(), refreshToken));

            return new AuthResponse(
                    jwtUtil.generateAccessToken(user),
                    refreshToken
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        try {
            //throws exception if user isn't found or can't be authenticated for some reason
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

//            authenticate() returns 3 things
//            auth.getPrincipal()    -> the logged-in user object — in your case, your User entity
//            auth.getCredentials()  -> the password (usually nulled out after auth for security)
//            auth.getAuthorities()  -> the roles/permissions, e.g. [ROLE_ADMIN]

            UserDetails userDetails = (UserDetails) auth.getPrincipal(); //obtain user object
            String refreshToken = jwtUtil.generateRefreshToken(userDetails);

            //store refreshToken in database
            tokenRepo.save(new RefreshToken(userDetails.getUsername(), refreshToken));
            return new AuthResponse(
                    jwtUtil.generateAccessToken(userDetails),
                    refreshToken
            );
        } catch (Exception e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }

    @Override
    public AuthResponse refreshToken(RefreshRequest request) {
        try {
            String refreshToken = request.getRefreshToken();

            if (!jwtUtil.isRefreshTokenValid(refreshToken)) {
                throw UserException.refreshTokenExpired();
            }

            String username = jwtUtil.extractUsername(refreshToken);
            RefreshToken storedToken = tokenRepo.findById(username)
                    .orElseThrow(UserException::refreshTokenExpired);

            if (!storedToken.getToken().equals(refreshToken)) {
                throw UserException.refreshTokenExpired();
            }

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            return new AuthResponse(
                    jwtUtil.generateAccessToken(userDetails),
                    refreshToken
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
