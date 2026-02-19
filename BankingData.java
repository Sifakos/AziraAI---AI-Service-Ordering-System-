package PackageService;

import java.util.Scanner;

public class BankingData {
    
    private long card_number; // Client's card number
    private int cvv; // Client's security code
    private String expiration_date; // Client's card expiration date 
    private double balance = 32193.34; // Client's bank balance

    public void SetCardNumber(long card_number) {

        this.card_number = card_number;
    }

    public void SetCVV(int cvv) {

        this.cvv = cvv;
    }

    public void SetExpirationDate(String expiration_date) {

        this.expiration_date = expiration_date;
    }

    public long GetCardNumber() {

        return (this.card_number);
    }

    public int GetCVV() {

        return (this.cvv);
    }

    public String GetExpirationDate() {

        return (this.expiration_date);
    }

    public double GetBalance() {
        
        return (this.balance);
    }

    public long CardNumberInput(Scanner scanner) {

        long card_number_input;
        do {
            System.out.print("Card number: ");
            card_number_input = scanner.nextLong();
            if (card_number_input < 1000000000000000L  || card_number_input > 9999999999999999L) {
                System.out.println("Invalid card number");
            }
        } while (card_number_input < 1000000000000000L  || card_number_input > 9999999999999999L);
        return (card_number_input);
    }

    public int CVVInput(Scanner scanner) {

        int cvv_input;
        do {
            System.out.print("CVV: ");
            cvv_input = scanner.nextInt();
            if (cvv_input < 100 || cvv_input > 999) {
                System.out.println("Invalid CVV");
            }
        } while (cvv_input < 100 || cvv_input > 999);
        return (cvv_input);
    }

    public String ExpirationDateInput(Scanner scanner) {

        String expiration_date_input;
        do { 
            System.out.print("Card's expiration date: ");
            expiration_date_input = scanner.nextLine();
            if (!expiration_date_input.matches("^(0[1-9]|1[0-2])/[0-9]{2}$")) {
                System.out.println("Invalid card's expiration date");
            }
        } while (!expiration_date_input.matches("^(0[1-9]|1[0-2])/[0-9]{2}$"));
        return (expiration_date_input);
    }

    public boolean PurchaseCart(double cart_price) {

        boolean purchase_cart = false;
        if (this.balance < cart_price) {
            purchase_cart = false;
        } else if (this.balance >= cart_price) {
            purchase_cart = true;
            this.balance -= cart_price;
        }
        return (purchase_cart);
    }
}
