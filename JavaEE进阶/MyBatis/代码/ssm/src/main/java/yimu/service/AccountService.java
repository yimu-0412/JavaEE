package yimu.service;

import yimu.domain.Account;

import java.io.IOException;
import java.util.List;

/**
 * @author
 * @Program
 * @create 2022-03-24-16:24
 */
public interface AccountService {

    public void save(Account account) throws IOException;

    public List<Account> findAll();
}
