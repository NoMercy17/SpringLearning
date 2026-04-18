package org.example._3_transactions_spring;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class TransferService {
    private final AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    //@Transactional // instead we can annotate the class itself, making it entirely transactional
    // if the class has multiple methods, it applies to all
    public void transferMoney(long idSender, long idReceiver, BigDecimal amount) {

        // get the correct accounts
        Account sender = accountRepository.findAccountById(idSender);
        Account receiver = accountRepository.findAccountById(idReceiver);

        BigDecimal senderNewAmount = sender.getAmount().subtract(amount);
        BigDecimal receiverNewAmount = receiver.getAmount().add(amount);

        accountRepository.changeAmount(idSender, senderNewAmount);
        accountRepository.changeAmount(idReceiver, receiverNewAmount);


        throw new RuntimeException("OH smth went terribly wrong!");
    }

    public List<Account> getAccounts() {
        return accountRepository.findAllAccounts();
    }


}
