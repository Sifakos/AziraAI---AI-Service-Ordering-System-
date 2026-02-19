package PackageService;

import java.util.Scanner;

public class Home {

    public static void main(String[] args) throws InvalidCompanyOptionException, InvalidAIServiceException, InvalidOrderNumberException {

        Scanner scanner = new Scanner(System.in);

        Client client_data = new Client(null, null, null); // Client details
        Cart shopping_cart = new Cart(); // Shopping cart

        // A.I. Services offered by the company
        AIAgent ai_agents = new AIAgent();
        WorkflowAutomation workflow_automations = new WorkflowAutomation();
        ContentCreationMarketing content_creation_marketing = new ContentCreationMarketing();
        DataAnalysis data_analysis = new DataAnalysis();

        // Necessary functions to start the application
        shopping_cart.OpenCart();

        // Display options menu
        System.out.println("Welcome to AziraAI Customer Service!");
        System.out.println("Automation Services (A.I.) (1) | Shopping Cart (2) | Exit (3)");
        System.out.print("Select option (1-3): ");
        int option = scanner.nextInt();
        if (option < 1 || option > 3) {
            throw new InvalidCompanyOptionException("Invalid option selected | Valid options (1-3)");
        } else {
            while (option != 3) {
                switch (option) {
                    case 1:
                        System.out.println("A.I. Agents (1) | Workflow Automation (2) | Content Creation - Marketing (3) | Data Analysis (4) | Exit (5)");
                        System.out.print("Select A.I. service (1-5): ");
                        int ai_service = scanner.nextInt();
                        if (ai_service < 1 || ai_service > 5) {
                            throw new InvalidAIServiceException("Invalid A.I. service selected | Valid services (1-5)");
                        } else {
                            while (ai_service != 5) {
                                switch (ai_service) {
                                    case 1:
                                        ai_agents.AIServiceOrderProcess(scanner, shopping_cart); // Process order for A.I. Agents service
                                        break;
                                    case 2:
                                        workflow_automations.AIServiceOrderProcess(scanner, shopping_cart); // Process order for Workflow Automation service
                                        break;
                                    case 3:
                                        content_creation_marketing.AIServiceOrderProcess(scanner, shopping_cart); // Process order for Content Creation - Marketing service
                                        break;
                                    case 4:
                                        data_analysis.AIServiceOrderProcess(scanner, shopping_cart); // Process order for Data Analysis service
                                        break;
                                }
                                System.out.println("A.I. Agents (1) | Workflow Automation (2) | Content Creation - Marketing (3) | Data Analysis (4) | Exit (5)");
                                System.out.print("Select A.I. service (1-5): ");
                                ai_service = scanner.nextInt();
                            }   
                        }
                        break;
                    case 2:
                        System.out.println("View Cart (1) | Delete Order (2) | Clear Cart (3) | Checkout (4) | Exit (5)");
                        System.out.print("Select shopping cart option (1-5): ");
                        int cart_option = scanner.nextInt();
                        if (cart_option < 1 || cart_option > 5) {
                            throw new InvalidCompanyOptionException("Invalid shopping cart option selected | Valid options (1-5)");
                        } else {
                            while (cart_option != 5) {
                                switch (cart_option) {
                                    case 1:
                                        shopping_cart.ViewCart(); // Display orders in the shopping cart
                                        break;
                                    case 2:
                                        shopping_cart.DeleteOrder(scanner); // Delete an order from the shopping cart
                                        break;
                                    case 3:
                                        shopping_cart.ClearCart(); // Clear the shopping cart
                                        break;
                                    case 4:
                                        shopping_cart.Checkout(scanner, client_data); // Complete order / Checkout
                                        break;
                                }
                                System.out.println("View Cart (1) | Delete Order (2) | Clear Cart (3) | Checkout (4) | Exit (5)");
                                System.out.print("Select shopping cart option (1-5): ");
                                cart_option = scanner.nextInt();
                            }   
                        }
                        break;
                }
            }
        }
    }
}