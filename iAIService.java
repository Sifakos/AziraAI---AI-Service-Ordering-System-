package PackageService;

import java.util.Scanner;

public interface iAIService {
    
    public void AIServiceOrderProcess(Scanner scanner, Cart shopping_cart); // Access the ordering process for the A.I. service the client desires

    public double GenerateOrderCost(String[][] ai_agent_specifications); // Calculate order cost for the A.I. service the client desires

    public int GenerateOrderDevelopmentTime(String[][] ai_agent_specifications); // Calculate order completion time for the A.I. service the client desires
}
