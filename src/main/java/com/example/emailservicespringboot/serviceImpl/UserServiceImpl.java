package com.example.emailservicespringboot.serviceImpl;//package com.example.emailservice.serviceImpl;
import com.example.emailservicespringboot.model.Confirmation;
import com.example.emailservicespringboot.model.User;
import com.example.emailservicespringboot.repository.ConfirmationRepository;
import com.example.emailservicespringboot.repository.UserRepository;
import com.example.emailservicespringboot.service.EmailService;
import com.example.emailservicespringboot.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ConfirmationRepository confirmationRepository;
    private final EmailService emailService;

    public UserServiceImpl(UserRepository userRepository, ConfirmationRepository confirmationRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.confirmationRepository = confirmationRepository;
        this.emailService = emailService;
    }

    @Override
    public User saveUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        user.setEnabled(false);
        userRepository.save(user);
        Confirmation confirmation = new Confirmation(user);
        confirmationRepository.save(confirmation);
        //emailService.sendSimpleMessage(user.getFirstName(), user.getEmail(), confirmation.getToken());
        emailService.sendMimeMessageWithAttachment(user.getFirstName(), user.getEmail(), confirmation.getToken());
        //emailService.sendMimeMessageWithEmbeddedImages(user.getFirstName(), user.getEmail(), confirmation.getToken());







        return user;
    }

    @Override
    public Boolean verifyToken(String token) {
        Confirmation confirmation = confirmationRepository.findByToken(token);
        User user = userRepository.findByEmailIgnoreCase(confirmation.getUser().getEmail());
        user.setEnabled(true);
        userRepository.save(user);
            return Boolean.TRUE;
    }
}
