package service.api;

import dto.BankCard;
import dto.Subscription;
import dto.User;

import java.util.List;
import java.util.Optional;

public interface IService {

    void subscribe(BankCard bankCard);

    Optional<Subscription> getSubscriptionByBankCardNumber(String s);

    List<User> getAllUsers();
}
