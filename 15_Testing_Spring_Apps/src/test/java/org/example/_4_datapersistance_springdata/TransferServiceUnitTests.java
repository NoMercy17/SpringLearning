package org.example._4_datapersistance_springdata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Repository;

import javax.swing.*;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // to use the @Mock and @InjectMocks
public class TransferServiceUnitTests {

    @Mock
    private AccountRepository repository;
    @InjectMocks
    private TransferService transferService;

    @Test
    @DisplayName("Test the amount is transferred from one account to another without any errors.")
    public void moneyTransferHappyFlow(){
        // Create the fake repo using mock
//        AccountRepository repository = mock(AccountRepository.class);
//
//        TransferService transferService = new TransferService(repository);


        Account sender = new Account();
        Account receiver = new Account();

        sender.setId(1);
        sender.setAmount(new BigDecimal(1000));
        receiver.setId(2);
        receiver.setAmount(new BigDecimal(2000));

        given(repository.findById(sender.getId())).willReturn(Optional.of(sender));

        given(repository.findById(receiver.getId())).willReturn(Optional.of(receiver));

        transferService.transferMoney(sender.getId(), receiver.getId(), new BigDecimal(100));

        verify(repository).changeAmount(1, new BigDecimal(900));
        verify(repository).changeAmount(2, new BigDecimal(2100));
    }

    @Test
    public void moneyTransferDestinationAccountNotFound(){
        Account sender = new Account();
        Account receiver = new Account();

        sender.setId(1);
        sender.setAmount(new BigDecimal(1000));

        given(repository.findById(1L))
                .willReturn(Optional.of(sender));

        given(repository.findById(2L))
                .willReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, ()-> transferService.transferMoney(1,2, new BigDecimal(100)));

        // e use the verify() method with the never() conditional to assert that the changeAmount() method hasn’t been called.
        verify(repository, never()).changeAmount(1, new BigDecimal(900));
    }
}
