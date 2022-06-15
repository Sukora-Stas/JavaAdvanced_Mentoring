package application;

import bank.api.IBank;
import cloud.bank.api.Bank_Impl;
import cloud.service.impl.Service_Impl;
import dto.BankCard;
import dto.BankCardType;
import dto.User;
import service.api.IService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {

        System.out.println("Initialize");
        IBank bank = new Bank_Impl();
        IService service = new Service_Impl();
        User user = new User("Stas", "Sukora", LocalDate.of(1997, 5, 16));
        BankCard creditCard = bank.createBankCard(user, BankCardType.CREDIT);
        System.out.println("Make subscription");
        service.subscribe(creditCard);

        System.out.println("-----------------------------");
        System.out.println("Verify");
        service.getAllUsers().forEach(System.out::println);
        service.getSubscriptionByBankCardNumber(creditCard.getNumber())
                .ifPresentOrElse(System.out::println, () -> System.out.println("Nothing found :("));

    }
}
