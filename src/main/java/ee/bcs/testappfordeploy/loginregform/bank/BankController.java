package ee.bcs.testappfordeploy.loginregform.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BankController {

    @Autowired
    private BankService bankService;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //localhost:8080/bank/createAccountDB?iban=EE123
    @GetMapping("bank/createAccountDB")
    public void createAccountDB(@RequestParam("iban") String iban) {
        bankService.createAccountDB(iban);
    }

    @GetMapping("bank/getAccountBalanceDB")
    public int getAccountBalanceDB(@RequestParam("iban") String iban) {
        return bankService.getAccountBalanceDB(iban);
    }

    @GetMapping("bank/depositMoneyDB")
    public void depositMoneyDB(@RequestParam("iban") String iban,
                               @RequestParam("howMuch") int howMuch) {
        bankService.depositMoneyDB(iban, howMuch);
    }

    @GetMapping("bank/withDrawMoneyDB")
    public void withDrawMoneyDB(@RequestParam("iban") String iban,
                                @RequestParam("howMuch") int howMuch) {
        bankService.withDrawMoneyDB(iban, howMuch);
    }

    @GetMapping("bank/transferMoneyDB")
    public void transferMoneyDB(@RequestParam("ibanFrom") String ibanFrom,
                                @RequestParam("ibanTo") String ibanTo,
                                @RequestParam("howMuch") Integer howMuch) {
        bankService.transferMoneyDB(ibanFrom, ibanTo, howMuch);
    }

    @GetMapping("bank/accountsDB")
    public List<Client> showAllClients() {
        String sql = "SELECT * FROM account";
        Map paraMap = new HashMap();
        return namedParameterJdbcTemplate.query(sql, paraMap, new AccountRowMapper());
    }

    private class AccountRowMapper implements RowMapper<Client> {
        @Override
        public Client mapRow(ResultSet resultSet, int i) throws SQLException {
            Client qwerty = new Client();
            qwerty.account_nr = resultSet.getString("account_nr");
            qwerty.balance = resultSet.getInt("balance");
            return qwerty;
        }
    }





}