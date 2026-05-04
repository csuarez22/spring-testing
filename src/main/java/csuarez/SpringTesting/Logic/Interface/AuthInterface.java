package csuarez.SpringTesting.Logic.Interface;

import csuarez.SpringTesting.Entities.DTOs.AuthRequest;
import csuarez.SpringTesting.Entities.DTOs.AuthResponse;
import csuarez.SpringTesting.Entities.DTOs.RefreshRequest;
import csuarez.SpringTesting.Entities.DTOs.RegisterRequest;

public interface AuthInterface {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(AuthRequest request);
    AuthResponse refreshToken(RefreshRequest request);
}
