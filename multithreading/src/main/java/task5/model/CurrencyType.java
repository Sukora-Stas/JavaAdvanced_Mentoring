package task5.model;

import java.math.BigDecimal;

public enum CurrencyType {
    USD(new BigDecimal("1")),
    RUB(new BigDecimal("0.014")),
    EUR(new BigDecimal("1.16")),
    BYN(new BigDecimal("0.0088"));

    private final BigDecimal courseInDollars;

    CurrencyType(BigDecimal courseInDollars) {
        this.courseInDollars = courseInDollars;
    }

    public BigDecimal getCourseInDollars() {
        return courseInDollars;
    }
}
