package ut.edu.koii.config;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String adminPassword = encoder.encode("admin_password");
        String userPassword = encoder.encode("user_password");
        System.out.println("Encoded admin password: " + adminPassword);
        System.out.println("Encoded user password: " + userPassword);
    }
}
