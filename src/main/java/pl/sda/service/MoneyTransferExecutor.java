package pl.sda.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.model.AccountEntity;
import pl.sda.repository.AccountRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class MoneyTransferExecutor {


    private final AccountRepository accountRepository;

    @Transactional
    public synchronized void send(String sederAccountNumber, String receiverAccountNumber, BigDecimal value) {
        AccountEntity senderAccount = accountRepository.findFirstByAccountNumber(sederAccountNumber);
        AccountEntity receiverAccount = accountRepository.findFirstByAccountNumber(receiverAccountNumber);

        if (senderAccount.getState().compareTo(value) >= 0) {
            senderAccount.substractMoney(value);
            receiverAccount.addMoney(value);
        }
    }


}
