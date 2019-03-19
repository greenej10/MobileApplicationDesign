package net.ddns.bivor.group14_inclass07;

import java.time.LocalDate;
import java.util.Date;

public class Expense {

    String name, category, amount;
    Date date;

    public Expense() {
    }

    public Expense(String name, String category, String amount, Date date) {
        this.name = name;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }
}
