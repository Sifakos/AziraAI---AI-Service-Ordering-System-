package PackageService;

import java.util.Scanner;

public class ContactData {
    
    private String name; // Client's full name
    private String email; // Client's email
    private long phone_number; // Client's phone number

    public void SetName(String name) {

        this.name = name;
    }

    public void SetEMail(String email) {

        this.email = email;
    }

    public void SetPhoneNumber(long phone_number) {

        this.phone_number = phone_number;
    }

    public String GetName() {

        return (this.name);
    }

    public String GetEMail() {

        return (this.email);
    }

    public long GetPhoneNumber() {
        
        return (this.phone_number);
    }

    public String NameInput(Scanner scanner) {

        String name_input;
        do {
            System.out.print("Name: ");
            name_input = scanner.nextLine();
            if (!name_input.matches("^[A-Z][a-z]+(?:[\\s-][A-Z][a-z]+)+$")) {
                System.out.println("Invalid name");
            }
        } while (!name_input.matches("^[A-Z][a-z]+(?:[\\s-][A-Z][a-z]+)+$"));
        return (name_input);
    }

    public String EMailInput(Scanner scanner) {

        String email_input;
        do {
            System.out.print("E-Mail: ");
            email_input = scanner.nextLine();
            if (!email_input.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]{2,}$")) {
                System.out.println("Invalid e-mail");
            }
        } while (!email_input.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]{2,}$"));
        return (email_input);
    }

    public long PhoneNumberInput(Scanner scanner) {

        long phone_number_input;
        do {
            System.out.print("Phone number: ");
            phone_number_input = scanner.nextLong();
            if (phone_number_input < 1000000000 || phone_number_input > 999999999) {
                System.out.println("Invalid phone number");
            }
        } while (phone_number_input < 1000000000 || phone_number_input > 999999999);
        return (phone_number_input);
    }
}
