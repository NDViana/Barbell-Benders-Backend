package Projects.BarbellBenders.service;

import Projects.BarbellBenders.util.JwtUtil;
import Projects.BarbellBenders.model.User;
import Projects.BarbellBenders.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class UserService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(JwtUtil jwtUtil, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public String register(String username, String password, String email) {
        if (userRepository.findByUsername(username).isPresent()) {
            return "Username already exists";
        }

        String hashedPassword = passwordEncoder.encode(password);
        User user = new User(username, hashedPassword, email);
        userRepository.save(user);
        return "User registered successfully";
    }


    public String login(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) return "User not found";
        User user = optionalUser.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "Invalid password";
        }

        return jwtUtil.generateToken(new HashMap<>(), user);
    }

}
