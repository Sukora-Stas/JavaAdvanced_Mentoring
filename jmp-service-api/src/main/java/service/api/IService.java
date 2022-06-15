package service.api;

import dto.BankCard;
import dto.Subscription;
import dto.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface IService {

    void subscribe(BankCard bankCard);

    Optional<Subscription> getSubscriptionByBankCardNumber(String s);

    List<User> getAllUsers();

    default double getAverageUsersAge() {
        LocalDate now = LocalDate.now();
        return getAllUsers().stream()
                .mapToLong(user -> ChronoUnit.YEARS.between(user.getBirthday(), now))
                .average()
                .orElse(0);
    }

    static boolean isPayableUser(User user) {
        return ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now()) > 18;
    }

    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate);
}
