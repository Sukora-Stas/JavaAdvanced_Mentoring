package bank.api;

import dto.BankCard;
import dto.BankCardType;
import dto.User;

public interface IBank {

    BankCard createBankCard(User user, BankCardType bankCardType) throws IllegalAccessException;
}
