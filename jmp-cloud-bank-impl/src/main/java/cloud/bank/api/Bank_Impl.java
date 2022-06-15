package cloud.bank.api;

import bank.api.IBank;
import dto.*;

import java.util.UUID;

public class Bank_Impl implements IBank {

    /* private final Map<BankCardType, Function<User, BankCard>> factories;

     public Bank_Impl() {
         var map = new ConcurrentHashMap<BankCardType, Function<User, BankCard>>();
         map.put(BankCardType.CREDIT, new CreditBankCard());
         map.put(BankCardType.DEBIT, new DebitBankCard());
         this.factories = map;
     }
 */
    @Override
    public BankCard createBankCard(User user, BankCardType bankCardType) throws IllegalAccessException {

       /* return factories
                .getOrDefault(bankCardType, u -> {throw new UnsupportedOperationException("Card type is not supported: " + bankCardType);})
                .apply(user);*/

        if (BankCardType.CREDIT == bankCardType) {
            return new CreditBankCard(UUID.randomUUID().toString(), user);
        } else if (BankCardType.DEBIT == bankCardType) {
            return new DebitBankCard(UUID.randomUUID().toString(), user);
        } else {
            throw new IllegalAccessException("Wrong bank card type: " + bankCardType);
        }
    }
}
