package ee.bcs.testappfordeploy.loginregform.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public void createAccountDB(String iban) {
        bankRepository.createAccountDB(iban);
    }

    public int getAccountBalanceDB(String iban) {
        return bankRepository.getAccountBalanceDB(iban);
    }

    public void depositMoneyDB(String iban, int howMuch) {
        if (howMuch > 0) {
            Integer currentBalance = bankRepository.getAccountBalanceDB(iban);
            int newBalance = currentBalance + howMuch;
            bankRepository.setAccountBalanceDB(iban, newBalance);
        }
    }

    public void withDrawMoneyDB(String iban, int howMuch) {
        if (howMuch > 0) {
            Integer amount = bankRepository.getAccountBalanceDB(iban);

            if (amount >= howMuch) {
                amount = amount - howMuch;
                bankRepository.setAccountBalanceDB(iban, amount);
            }
        }
    }

    public void transferMoneyDB(String ibanFrom, String ibanTo, Integer howMuch) {
        if (howMuch < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "amount should be larger than 0");
        }
        Integer amountOnIbanFrom = bankRepository.getAccountBalanceDB(ibanFrom);

        if (amountOnIbanFrom >= howMuch) {
            amountOnIbanFrom = amountOnIbanFrom - howMuch;
            bankRepository.setAccountBalanceDB(ibanFrom, amountOnIbanFrom);

            Integer amountOnIbanTo = bankRepository.getAccountBalanceDB(ibanTo);
            amountOnIbanTo += howMuch;
            bankRepository.setAccountBalanceDB(ibanTo, amountOnIbanTo);
        }
    }

}