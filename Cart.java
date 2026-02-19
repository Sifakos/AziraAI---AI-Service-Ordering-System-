package PackageService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Cart {
    
    private double cart_cost;
    private ArrayList<AIOrder>[] ai_orders = new ArrayList[4];

    public double GetCartCost() {

        return (this.cart_cost);
    }

    public void OpenCart() {

        for (int i = 0; i < this.ai_orders.length; i ++) {
            this.ai_orders[i] = new ArrayList<AIOrder>();
        }
    }

    public void AddOrderLine(String[][] order_specifications, double order_cost, int development_time, int index) {

        this.cart_cost += order_cost; // Update total shopping cart cost
        this.ai_orders[index].add(new AIOrder(order_specifications, order_cost, development_time)); // Add order to shopping cart
    }

    public void ViewCart() {

        System.out.println("=== YOUR CART ===");
        System.out.println("Total cart price: $" + this.cart_cost);
        for (int i = 0; i < this.ai_orders.length; i ++) {
            switch (i) {
                case 0:
                    if (!this.ai_orders[0].isEmpty()) {
                        System.out.println("=== A.I. AGENTS ===");
                    }
                    break;
                case 1:
                    if (!this.ai_orders[1].isEmpty()) {
                        System.out.println("=== WORKFLOW AUTOMATIONS ===");
                    }
                    break;
                case 2:
                    if (!this.ai_orders[2].isEmpty()) {
                        System.out.println("=== CONTENT CREATION & MARKETING ===");
                    }
                    break;
                case 3:
                    if (!this.ai_orders[0].isEmpty()) {
                        System.out.println("=== DATA ANALYSIS ===");
                    }
                    break;
            }
            for (int j = 0; j < this.ai_orders[i].size(); j ++) {
                System.out.println(this.ai_orders[i].get(j));
            }
        }
    }

    public void DeleteOrder(Scanner scanner) throws InvalidAIServiceException, InvalidOrderNumberException {

        System.out.println("A.I. Agents (1) | Workflow Automation (2) | Content Creation - Marketing (3) | Data Analysis (4) | Exit (5)");
        System.out.print("Select A.I. service (1-5): ");
        int ai_service = scanner.nextInt();
        if (ai_service < 1 || ai_service > 5) {
            throw new InvalidAIServiceException("Invalid A.I. service selected | Valid services (1-5)");
        } else {
            while (ai_service != 5) {
                switch (ai_service) {
                    case 1:
                        if (this.ai_orders[0].isEmpty()) {
                            System.out.println("There are no A.I. agents orders");
                        } else if (!this.ai_orders[0].isEmpty()) {
                            for (int i = 0; i < this.ai_orders[0].size(); i ++) {
                                System.out.println("A.I. Agent order No" + (i + 1) + this.ai_orders[0].get(i));
                            }
                            System.out.print("Select A.I. agent order number: ");
                            int order_number = scanner.nextInt();
                            if (order_number < 1 || order_number > this.ai_orders[0].size()) {
                                throw new InvalidOrderNumberException("Invalid A.I. agent order number");
                            } else {
                                double order_price = this.ai_orders[0].get(order_number - 1).GetOrderCost();
                                this.cart_cost -= order_price;
                                this.ai_orders[0].remove(order_number - 1);
                            }
                        }
                        break;
                    case 2:
                        if (this.ai_orders[1].isEmpty()) {
                            System.out.println("There are no workflow automations orders");
                        } else if (!this.ai_orders[1].isEmpty()) {
                            for (int i = 0; i < this.ai_orders[1].size(); i ++) {
                                System.out.println("Workflow automations order No" + (i + 1) + this.ai_orders[1].get(i));
                            }
                            System.out.print("Select workflow automations order number: ");
                            int order_number = scanner.nextInt();
                            if (order_number < 1 || order_number > this.ai_orders[1].size()) {
                                throw new InvalidOrderNumberException("Invalid workflow automations order number");
                            } else {
                                double order_price = this.ai_orders[1].get(order_number - 1).GetOrderCost();
                                this.cart_cost -= order_price;
                                this.ai_orders[1].remove(order_number - 1);
                            }
                        }
                        break;
                    case 3:
                        if (this.ai_orders[2].isEmpty()) {
                            System.out.println("There are no content creation & marketing orders");
                        } else if (!this.ai_orders[2].isEmpty()) {
                            for (int i = 0; i < this.ai_orders[2].size(); i ++) {
                                System.out.println("Content creation & marketing order No" + (i + 1) + this.ai_orders[2].get(i));
                            }
                            System.out.print("Select content creation & marketing order number: ");
                            int order_number = scanner.nextInt();
                            if (order_number < 1 || order_number > this.ai_orders[2].size()) {
                                throw new InvalidOrderNumberException("Invalid content creation & marketing order number");
                            } else {
                                double order_price = this.ai_orders[2].get(order_number - 1).GetOrderCost();
                                this.cart_cost -= order_price;
                                this.ai_orders[2].remove(order_number - 1);
                            }
                        }
                        break;
                    case 4:
                        if (this.ai_orders[3].isEmpty()) {
                            System.out.println("There are no data analysis orders");
                        } else if (!this.ai_orders[3].isEmpty()) {
                            for (int i = 0; i < this.ai_orders[3].size(); i ++) {
                                System.out.println("Data analysis order No" + (i + 1) + this.ai_orders[3].get(i));
                            }
                            System.out.print("Select data analysis order number: ");
                            int order_number = scanner.nextInt();
                            if (order_number < 1 || order_number > this.ai_orders[3].size()) {
                                throw new InvalidOrderNumberException("Invalid data analysis order number");
                            } else {
                                double order_price = this.ai_orders[3].get(order_number - 1).GetOrderCost();
                                this.cart_cost -= order_price;
                                this.ai_orders[3].remove(order_number - 1);
                            }
                        }
                        break;
                    }
                    System.out.println("A.I. Agents (1) | Workflow Automation (2) | Content Creation - Marketing (3) | Data Analysis (4) | Exit (5)");
                    System.out.print("Select A.I. service (1-5): ");
                    ai_service = scanner.nextInt();
            }   
        }
    }

    public void ClearCart() {

        this.cart_cost = 0;
        for (int i = 0; i < this.ai_orders.length; i ++) {
            for (int j = 0; j < this.ai_orders[i].size(); j ++) {
                this.ai_orders[i].remove(j);
            }
        }
    }

    public void Checkout(Scanner scanner, Client client_data) {

        System.out.println("Total cart price: $" + this.cart_cost);
        
        // Client's card data for the purchase of the cart
        long card_number = client_data.GetBankingData().CardNumberInput(scanner);
        int cvv = client_data.GetBankingData().CVVInput(scanner);
        String expiration_date = client_data.GetBankingData().ExpirationDateInput(scanner);
        boolean valid_purchase = client_data.GetBankingData().PurchaseCart(this.cart_cost);
        if (valid_purchase == false) {
            System.out.println("The purchase of the cart was not sucessfull because of low balance");
        } else if (valid_purchase == true) {
            client_data.GetBankingData().SetCardNumber(card_number);
            client_data.GetBankingData().SetCVV(cvv);
            client_data.GetBankingData().SetExpirationDate(expiration_date);
            // Client's contact data
            String name = client_data.GetContactData().NameInput(scanner);
            String email = client_data.GetContactData().EMailInput(scanner);
            long phone_number = client_data.GetContactData().PhoneNumberInput(scanner);
            client_data.GetContactData().SetName(name);
            client_data.GetContactData().SetEMail(email);
            client_data.GetContactData().SetPhoneNumber(phone_number);
            // Client's shipping data
            String delivery_address = client_data.GetShippingData().DeliveryAddressInput(scanner);
            String city = client_data.GetShippingData().CityInput(scanner);
            int postal_code = client_data.GetShippingData().PostalCodeInput(scanner);
            client_data.GetShippingData().SetDeliveryAddress(delivery_address);
            client_data.GetShippingData().SetCity(city);
            client_data.GetShippingData().SetPostalCode(postal_code);
            GenerateInvoice(client_data); // Invoice generation
            ClearCart();
        }
    }

    public void GenerateInvoice(Client client_data) {
        
        // Get current date and time for the invoice
        LocalDateTime date_now = LocalDateTime.now();
        DateTimeFormatter date_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String invoice_id = "INV-" + (System.currentTimeMillis() % 100000);

        System.out.println("\n==================================================");
        System.out.println("                OFFICIAL INVOICE                 ");
        System.out.println("==================================================");
        System.out.println("Invoice ID:   " + invoice_id);
        System.out.println("Date/Time:    " + date_now.format(date_formatter));
        System.out.println("--------------------------------------------------");
        
        // Client Information from ContactData
        System.out.println("BILL TO:");
        System.out.println("Name:         " + client_data.GetContactData().GetName());
        System.out.println("Email:        " + client_data.GetContactData().GetEMail());
        System.out.println("Phone:        " + client_data.GetContactData().GetPhoneNumber());
        System.out.println("--------------------------------------------------");

        // Shipping Information from ShippingData
        System.out.println("SHIP TO:");
        System.out.println("Address:      " + client_data.GetShippingData().GetDeliveryAddress());
        System.out.println("City:         " + client_data.GetShippingData().GetCity());
        System.out.println("Postal Code:  " + client_data.GetShippingData().GetPostalCode());
        System.out.println("--------------------------------------------------");

        // Order Details from Cart
        ViewCart(); 

        System.out.println("--------------------------------------------------");
        
        // Payment Status and Masked Card Details from BankingData
        System.out.println("PAYMENT SUMMARY:");
        System.out.println("Status:       PAID");
        
        // Masking card number for security (showing only last 4 digits)
        String full_card_number = String.valueOf(client_data.GetBankingData().GetCardNumber());
        String masked_card_number = "****-****-****-" + full_card_number.substring(Math.max(0, full_card_number.length() - 4));
        
        System.out.println("Method:       Credit Card (" + masked_card_number + ")");
        System.out.println("TOTAL AMOUNT: $" + GetCartCost());
        
        System.out.println("==================================================");
        System.out.println("         Thank you for your business!            ");
        System.out.println("==================================================\n");
    }
}
