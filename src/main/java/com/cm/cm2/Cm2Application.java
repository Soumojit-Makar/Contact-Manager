package com.cm.cm2;

import com.cm.cm2.entities.User;
import com.cm.cm2.helper.AppCon;
import com.cm.cm2.repsitories.UserRepo;
import com.cm.cm2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class Cm2Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Cm2Application.class, args);
    }
    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setName("admin");
        user.setEmail("admin@gmail.com");
        user.setPassword(passwordEncoder.encode("soumo"));

        user.setRoleList(List.of(AppCon.rUser));
        user.setEmailVefied(true);
        user.setEnable(true);
        user.setAbout("This is dummy user created initially");
        user.setPhoneVerified(true);

        userRepo.findByEmail("admin@gmail.com").ifPresentOrElse(user1 -> {},() -> {
            userRepo.save(user);
            System.out.println("user created");
        });


    }
}
