package dto;

public abstract class BankCard {

    public BankCard(String number, User user) {
        this.number = number;
        this.user = user;
    }

    private String number;
    private User user;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
