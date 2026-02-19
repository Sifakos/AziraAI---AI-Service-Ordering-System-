package PackageService;

public class Client {
    
    private ContactData contact_data; // Client's personal details
    private BankingData banking_data; // Client's banking details
    private ShippingData shipping_data; // Delivery details

    public Client(ContactData contact_data, BankingData banking_data, ShippingData shipping_data) {

        this.contact_data = contact_data;
        this.banking_data = banking_data;
        this.shipping_data = shipping_data;
    }

    public ContactData GetContactData() {

        return (this.contact_data);
    }

    public BankingData GetBankingData() {

        return (this.banking_data);
    }

    public ShippingData GetShippingData() {
        
        return (this.shipping_data);
    }
}
