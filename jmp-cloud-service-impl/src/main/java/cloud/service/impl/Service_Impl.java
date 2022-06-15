package cloud.service.impl;

import dto.BankCard;
import dto.Subscription;
import dto.User;
import service.api.IService;

import java.time.LocalDate;
import java.util.*;

public class Service_Impl implements IService {

    private final Map<User, List<BankCard>> userStorage = new HashMap<>();
    private final List<Subscription> subscriptions = new ArrayList<>();

    @Override
    public void subscribe(BankCard bankCard) {
        var user = bankCard.getUser();
        if (!userStorage.containsKey(user)) {
            userStorage.put(user, new ArrayList<>());
        }
        userStorage.get(user).add(bankCard);
        subscriptions.add(new Subscription(bankCard.getNumber(), LocalDate.now()));
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String s) {
        return subscriptions.stream()
                .filter(subscription -> subscription.getBankcard().equals(s))
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(userStorage.keySet());
    }
}
