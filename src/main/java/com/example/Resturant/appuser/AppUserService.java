package com.example.Resturant.appuser;

import com.example.Resturant.registration.token.ConfirmationToken;
import com.example.Resturant.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MESSAGE = "User with email %s not found";
    private final AppUserRepo appUserRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    /*
    @Autowired
    public AppUserService(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }
    */


    /*
    public List<AppUser> findAllCustomers() {
        return appUserRepo.findAll();
    }
    */

    public AppUser findAppUser(Long id) {
        return appUserRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("cant find customer with id " + id));
    }

/*
    public AppUser createAppUser(AppUser appUser) {
        return appUserRepo.save(appUser);
    }


    public AppUser updateCustomer(AppUser appUser) { return appUserRepo.save(appUser); }

    public void deleteCustomer(Long id) { appUserRepo.deleteById(id); }
    */

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepo.findAppUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, email)));
    }

    public String signUpUser(AppUser appUser) {
        boolean userExists = appUserRepo.findAppUserByEmail(appUser.getEmail()).isPresent();

        if (userExists) {
            throw new IllegalStateException("email is already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);

        appUserRepo.save(appUser);


        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        // TODO : send Email

        return token;
    }

    public int enableAppUser(String email) {
        return appUserRepo.enableAppUser(email);
    }
}
