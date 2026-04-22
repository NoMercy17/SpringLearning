package org.example._4_datapersistance_springdata;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {

    // The magic of translating a method’s name into a query
    //List<Account> findAccountsByName(String name);


    @Query("SELECT * FROM account WHERE name= :name")
    List<Account> findAccountsByName(String name);

    @Query("SELECT * FROM account")
    List<Account> findAllAccounts();



    // and when modifying
    @Modifying
    @Query("UPDATE account SET amount= :amount WHERE id = :id")
    void changeAmount(long id, BigDecimal amount);


}
