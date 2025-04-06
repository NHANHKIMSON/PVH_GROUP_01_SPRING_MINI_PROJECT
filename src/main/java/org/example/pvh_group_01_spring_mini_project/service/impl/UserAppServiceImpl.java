package org.example.pvh_group_01_spring_mini_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.pvh_group_01_spring_mini_project.models.dto.request.AuthRequest.AuthRegisterRequest;

import org.example.pvh_group_01_spring_mini_project.models.entity.UserApp;
import org.example.pvh_group_01_spring_mini_project.repository.UserAppRepository;
import org.example.pvh_group_01_spring_mini_project.service.UserAppService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserAppServiceImpl implements UserAppService {
    private final UserAppRepository userAppRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Override
    public UserApp registerProfile(AuthRegisterRequest authRegisterRequest) {
        authRegisterRequest.setPassword(passwordEncoder.encode(authRegisterRequest.getPassword()));
        UserApp existingUser = userAppRepository.findByEmail(authRegisterRequest.getEmail());

        if (existingUser != null && existingUser.getIsVerified()) {
            throw new RuntimeException("User Already Registered");
        }

        UserApp users = UserApp.builder()
                .username(authRegisterRequest.getUsername())
                .email(authRegisterRequest.getEmail())
                .password(authRegisterRequest.getPassword())
                .build();
        String otp = generateOTP();
        users.setOtp(otp);
        users.setCreatedAt(LocalDateTime.now());

        if (existingUser != null) {
            users.setCreatedAt(LocalDateTime.now());
            sendVerificationEmail(users.getEmail(), otp);
            return userAppRepository.updateOtp(users);
        } else {
            sendVerificationEmail(users.getEmail(), otp);
//            UserApp userApp = userAppRepository.save(users);
            return userAppRepository.save(users);
        }

//        Users savedUser = usersRepository.save(users);

//        sendVerificationEmail(users.getEmail(), otp);

//        System.out.println("users : " + users);

//        ApiRespones<UserApp> response = ApiRespones.<UserApp>builder()
//                .status(HttpStatus.OK)
//                .success(true)
//                .build();
//        return new U<>(response,HttpStatus.OK);
//        return users;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("this is user : " + userAppRepository.getProfileByEmail(email));
        return userAppRepository.getProfileByEmail(email);
    }
    @Override
    public UserApp checkEmail(String email) {
        UserApp userApp = userAppRepository.findByEmail(email);
        if (userApp.getEmail().equals(email)) {
            String otp = generateOTP();
            userApp.setOtp(otp);
            userApp.setCreatedAt(LocalDateTime.now());
            sendVerificationEmail(email, otp);
            return userAppRepository.updateOtp(userApp);
        } else {
            throw new RuntimeException("Email Not Found");
        }
    }

    @Override
    public void verify(String email, String otp) {
        UserApp users = userAppRepository.findByEmail(email);


        if (users == null) {
            throw new RuntimeException("User not found");
        } else if (users.getIsVerified()) {
            throw new RuntimeException("User is already verified");
        }
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime otpExpiryTime = users.getCreatedAt().plusSeconds(120);

        if (now.isAfter(otpExpiryTime)) {
            throw new RuntimeException("OTP has expired");
        } else if (otp.equals(users.getOtp())) {
            users.setIsVerified(true);
            userAppRepository.update(users);
        } else {
            throw new RuntimeException("Internal Server error");
        }
    }


    private String generateOTP() {
        Random random = new Random();
        int otpValue = 100000 + random.nextInt(900000);
        return String.valueOf(otpValue);
    }

    private void sendVerificationEmail(String email, String otp) {
        String subject = "Email verification😏";
        String body = "Nis verification otp is: << " + otp + " >> dak oy lern lern 2 minutes expired!😡jam tea ke send oy yii";
        emailService.sendEmail(email, subject, body);
    }
}
