package org.example.pvh_group_01_spring_mini_project.controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.example.pvh_group_01_spring_mini_project.jwt.JwtService;
import org.example.pvh_group_01_spring_mini_project.models.dto.request.AuthRequest.AuthLoginRequest;
import org.example.pvh_group_01_spring_mini_project.models.dto.request.AuthRequest.AuthRegisterRequest;
import org.example.pvh_group_01_spring_mini_project.models.dto.response.ApiRespones;
import org.example.pvh_group_01_spring_mini_project.models.dto.response.AuthResponse;
import org.example.pvh_group_01_spring_mini_project.models.dto.response.ResendOtpResponse;
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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/auths")
//@RequiredArgsConstructor
public class AuthController {
    private final UserAppService userAppService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(UserAppService userAppService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userAppService = userAppService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
     @Operation(summary = "Login user")
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthLoginRequest request) throws Exception {
        authenticate(request.getEmail(), request.getPassword());
        final UserDetails userDetails = userAppService.loadUserByUsername(request.getEmail());

//        System.out.println("this is user " + userDetails);

        final String token = jwtService.generateToken(userDetails);
        AuthResponse authResponse = new AuthResponse(token);
        ApiRespones<AuthResponse> respones=ApiRespones.<AuthResponse>builder()
                .success(true)
                .message("Login Successful Authentication generated")
                .status(HttpStatus.OK)
                .payload(authResponse)
                .timestamps(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(respones);
    }
     @Operation(summary = "Register new User")
    @PostMapping("/register")
    public ResponseEntity<ApiRespones<UserApp>> register(@RequestBody AuthRegisterRequest authRegisterRequest) {
        ApiRespones<UserApp> respones = ApiRespones.<UserApp>builder()
                .success(true)
                .message("User registered successfully! Please verify your email to complete the registration.")
                .status(HttpStatus.CREATED)
                .payload(userAppService.registerProfile(authRegisterRequest))
                .timestamps(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(respones, HttpStatus.CREATED);
    }
    @Operation(summary = "Resend verification OTP")
    @PostMapping("/resend")
    public ResponseEntity<?> resendOtp( @RequestParam String email){
        UserApp foundEmail = userAppService.checkEmail(email);
        if(foundEmail != null){
            ResendOtpResponse<UserApp> respones = ResendOtpResponse.<UserApp>builder()
                    .success(true)
                    .message("Verification OTP successfully resent to your email.")
                    .status(HttpStatus.CREATED)
                    .timestamps(LocalDateTime.now())
                    .build();
            return new ResponseEntity<>(respones, HttpStatus.CREATED);
        }else {
            ResendOtpResponse<UserApp> respones = ResendOtpResponse.<UserApp>builder()
                    .success(false)
                    .message("The email address provided is not registered. Please check and try again.")
                    .status(HttpStatus.NOT_FOUND)
                    .timestamps(LocalDateTime.now())
                    .build();
            return new ResponseEntity<>(respones, HttpStatus.NOT_FOUND);
        }

    }
    @Operation(summary = "Verify email with OTP")
    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser( @RequestParam String email, @RequestParam String otp){
        try {
            userAppService.verify(email, otp);
            return new ResponseEntity<>("User verified successfully",HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
