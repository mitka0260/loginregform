package ee.bcs.testappfordeploy.loginregform.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    //в базе данных должен быть хеш-код (от сайта bcrypt). Если так, то encoder использовать не надо

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return User
                .withUsername(username)
                .password(userRepository.getUserPassword(username))
                //.password(passwordEncoder.encode(userRepository.getUserPassword(username)))
                .roles("USER")
                .build();
    }
}