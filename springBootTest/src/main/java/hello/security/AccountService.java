package hello.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sren on 17-1-18.
 */
@RestController
@RequestMapping("/account")
public class AccountService {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AccountRepository accountRepository;

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseBody
    public Account getAccountByName(@PathVariable("name") String name){
        Account account = accountRepository.findByUsername(name);
        log.debug("get the account: "+account);
        return account;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Account addAccount(Account account){
        Account save = accountRepository.save(account);
        log.debug("save a account: "+save);
        return save;
    }
}
