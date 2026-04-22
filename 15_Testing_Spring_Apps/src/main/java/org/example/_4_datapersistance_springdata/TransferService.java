package org.example._4_datapersistance_springdata;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferService {
    private final AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void transferMoney(long idSender, long idReceiver, BigDecimal amount) {
        Account sender = accountRepository.findById(idSender).orElseThrow(
                () -> new AccountNotFoundException("Sender account not found")
        );

        Account receiver = accountRepository.findById(idReceiver).orElseThrow(
                () -> new AccountNotFoundException("Receiver account not found")
        );

        BigDecimal senderNewAmount = sender.getAmount().subtract(amount);
        BigDecimal receiverNewAmount = receiver.getAmount().add(amount);

        accountRepository.changeAmount(idSender, senderNewAmount);
        accountRepository.changeAmount(idReceiver, receiverNewAmount);
    }

    public List<Account> findAccountsByName(String name){
        return accountRepository.findAccountsByName(name);
    }

    public List<Account> findAllAccounts(){
        return accountRepository.findAllAccounts();
    }
}
