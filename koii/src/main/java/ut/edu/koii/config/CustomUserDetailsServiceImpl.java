package ut.edu.koii.config;

import ut.edu.koii.repository.UserRepository;

public class CustomUserDetailsServiceImpl extends CustomUserDetailsService {
    public CustomUserDetailsServiceImpl(UserRepository userRepository) {
        super(userRepository);
    }
}
