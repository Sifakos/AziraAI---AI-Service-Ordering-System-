package PackageService;

import java.util.Scanner;

public class ShippingData {

    private String delivery_address; // Delivery address
    private String city; // Delivery city
    private int postal_code; // Delivery postal code

    public void SetDeliveryAddress(String delivery_address) {

        this.delivery_address = delivery_address;
    }

    public void SetCity(String city) {

        this.city = city;
    }

    public void SetPostalCode(int postal_code) {

        this.postal_code = postal_code;
    }

    public String GetDeliveryAddress() {

        return (this.delivery_address);
    }

    public String GetCity() {

        return (this.city);
    }

    public int GetPostalCode() {
        
        return (this.postal_code);
    }

    public String DeliveryAddressInput(Scanner scanner) {

        String delivery_address_input;
        do { 
            System.out.print("Delivery address: ");
            delivery_address_input = scanner.nextLine();
            if (!delivery_address_input.matches("^[A-Za-z0-9\\s,.-]{5,100}$")) {
                System.out.println("Invalid delivery address");
            }
        } while (!delivery_address_input.matches("^[A-Za-z0-9\\s,.-]{5,100}$"));
        return (delivery_address_input);
    }

    public String CityInput(Scanner scanner) {

        String city_input;
        do { 
            System.out.print("City: ");
            city_input = scanner.nextLine();
            if (!city_input.matches("^[A-Z][a-zA-Z\\s-]{1,30}$")) {
                System.out.println("Invalid city");
            }
        } while (!city_input.matches("^[A-Z][a-zA-Z\\s-]{1,30}$"));
        return (city_input);
    }

    public int PostalCodeInput(Scanner scanner) {

        int postal_code_input;
        do { 
            System.out.print("Postal code: ");
            postal_code_input = scanner.nextInt();
            if (postal_code_input < 10000 || postal_code_input > 99999) {
                System.out.println("Invalid postal code");
            }
        } while (postal_code_input < 10000 || postal_code_input > 99999);
        return (postal_code_input);
    }
}
