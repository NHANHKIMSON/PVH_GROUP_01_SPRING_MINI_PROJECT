package org.example.pvh_group_01_spring_mini_project.controllers;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.example.pvh_group_01_spring_mini_project.jwt.JwtService;
import org.example.pvh_group_01_spring_mini_project.models.dto.request.AuthRequest.AuthLoginRequest;
import org.example.pvh_group_01_spring_mini_project.models.dto.request.AuthRequest.AuthRegisterRequest;
import org.example.pvh_group_01_spring_mini_project.models.dto.response.ApiRespones;
import org.example.pvh_group_01_spring_mini_project.models.dto.response.AuthResponse;
import org.example.pvh_group_01_spring_mini_project.models.entity.UserApp;
import org.example.pvh_group_01_spring_mini_project.service.UserAppService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/auths")
@RequiredArgsConstructor
public class AuthController {
    private final UserAppService userAppService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthLoginRequest request) throws Exception {
        authenticate(request.getEmail(), request.getPassword());
        final UserDetails userDetails = userAppService.loadUserByUsername(request.getEmail());
        final String token = jwtService.generateToken(userDetails);
        AuthResponse authResponse = new AuthResponse(token);
        ApiRespones<AuthResponse> respones=ApiRespones.<AuthResponse>builder()
                .success(true)
                .message("Suceess for login")
                .status(HttpStatus.OK)
                .payload(authResponse)
                .timestamps(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(respones);
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRegisterRequest request){
        ApiRespones<UserApp> respones=ApiRespones.<UserApp>builder()
                .success(true)
                .message(("User registered successfully"))
                .status(HttpStatus.ACCEPTED)
                .payload(userAppService.registerProfile(request))
                .timestamps(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(respones);
    }
}
