package cloud.bank.api;

import bank.api.IBank;
import dto.*;

import java.util.UUID;

public class Bank_Impl implements IBank {


    @Override
    public BankCard createBankCard(User user, BankCardType bankCardType) throws IllegalAccessException {
        if (BankCardType.CREDIT == bankCardType) {
            return new CreditBankCard(UUID.randomUUID().toString(), user);
        } else if (BankCardType.DEBIT == bankCardType) {
            return new DebitBankCard(UUID.randomUUID().toString(), user);
        } else {
            throw new IllegalAccessException("Wrong bank card type: " + bankCardType);
        }
    }
}
