package ee.bcs.testappfordeploy.loginregform.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BankRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void createAccountDB(String iban) {
        String sql = "INSERT INTO account (account_nr, balance) VALUES (:account_nr, 0)";
        Map paramMap = new HashMap<>();
        paramMap.put("account_nr", iban);
        jdbcTemplate.update(sql, paramMap);
    }

    public int getAccountBalanceDB(String iban) {
        String sql = "SELECT balance FROM account WHERE account_nr = :iban";
        HashMap paramMap = new HashMap();
        paramMap.put("iban", iban);
        return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

    public void setAccountBalanceDB(String iban, int amount) {
        String sql = "UPDATE account SET balance = :amount WHERE account_nr = :a";
        Map paramMap = new HashMap();
        paramMap.put("amount", amount);
        paramMap.put("a", iban);
        jdbcTemplate.update(sql, paramMap);
    }

}