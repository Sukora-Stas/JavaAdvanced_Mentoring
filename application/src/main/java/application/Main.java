package application;

import bank.api.IBank;
import cloud.bank.api.Bank_Impl;
import cloud.service.impl.Service_Impl;
import dto.BankCard;
import dto.BankCardType;
import dto.User;
import service.api.IService;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {

        System.out.println("Initialize");
        IBank bank = new Bank_Impl();
        IService service = new Service_Impl();
        var users = initUsers();
        var bankCards = new ArrayList<BankCard>();
        users.forEach(user -> {
            System.out.println(user);
            try {
                var bankCard = bank.createBankCard(user, BankCardType.CREDIT);
                bankCards.add(bankCard);
                System.out.println("Make subscription");

                service.subscribe(bankCard);

                //for test Subscription by condition
                if (bankCard.getUser().getName().equals("Stas")) {
                    service.getSubscriptionByBankCardNumber(bankCard.getNumber())
                            .get()
                            .setStartDate(LocalDate.of(2022, Month.FEBRUARY, 24));
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("-----------------------------");
        System.out.println("Verify");
        bankCards.forEach(bankCard -> {
            System.out.println(bankCard.getUser());
            service.getSubscriptionByBankCardNumber(bankCard.getNumber())
                    .ifPresentOrElse(System.out::println, () -> System.out.println("Nothing found :("));
        });

        System.out.println("-----------------------------");
        System.out.println("AVG");
        System.out.println(service.getAverageUsersAge());
        System.out.println("-----------------------------");
        System.out.println("Is Payable");

        service.getAllUsers()
                .forEach(user ->
                        System.out.println(user + " - Is payable: "
                                + IService.isPayableUser(user)));

        System.out.println("-----------------------------");
        System.out.println("Subscription by condition is not match");
        service.getAllSubscriptionsByCondition(subscription ->
                        !subscription.getStartDate().equals(LocalDate.now()))
                .forEach(System.out::println);
    }

    private static List<User> initUsers() {
        var list = new ArrayList<User>();
        list.add(new User("Stas", "Sukora", LocalDate.of(1997, Month.MAY, 16)));
        list.add(new User("Jon", "Bos", LocalDate.of(1990, Month.JANUARY, 1)));
        list.add(new User("Mikola", "Proshuto", LocalDate.of(1967, Month.JULY, 6)));
        list.add(new User("Nick", "Vuinich", LocalDate.of(2005, Month.APRIL, 8)));
        list.add(new User("Stefan", "Tibalda", LocalDate.of(1990, Month.AUGUST, 25)));
        list.add(new User("Pablo", "Escobar", LocalDate.of(1995, Month.OCTOBER, 30)));
        return list;
    }
}
