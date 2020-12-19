package ee.bcs.testappfordeploy.loginregform.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public String getUserPassword (String username) {
        String sql = "SELECT encodedpassword FROM users WHERE username = :username";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("username", username);
        return jdbcTemplate.queryForObject(sql, paramMap,  String.class);
    }


//i wrtitew a comment to new upload
    public void registerUser(String username, String encodedPassword) {
        String sql = "INSERT INTO users (username, encodedpassword) VALUES (:username, :password)";
        Map paramMap = new HashMap<>();
        paramMap.put("username", username);
        paramMap.put("password", encodedPassword);
        jdbcTemplate.update(sql, paramMap);
    }

}