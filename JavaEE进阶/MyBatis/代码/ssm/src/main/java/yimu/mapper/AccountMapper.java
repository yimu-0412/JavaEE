package yimu.mapper;

import yimu.domain.Account;

import java.util.List;

/**
 * @author
 * @Program
 * @create 2022-03-24-16:23
 */
public interface AccountMapper {

    public void save(Account account);

    public List<Account> findAll();
}
