package com.ulasgergerli.virtucart.VirtuCart.Auth;

import com.ulasgergerli.virtucart.VirtuCart.Config.JwtService;
import com.ulasgergerli.virtucart.VirtuCart.User.Role;
import com.ulasgergerli.virtucart.VirtuCart.User.User;
import com.ulasgergerli.virtucart.VirtuCart.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getFirstName())
                .surname(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CUSTOMER)
                .build();

        userRepository.save(user);

        var token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail());

        var token = jwtService.generateToken(user.orElseThrow(() -> new RuntimeException("User not found")));

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

}
