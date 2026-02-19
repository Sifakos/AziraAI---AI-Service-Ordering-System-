package PackageService;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class WorkflowAutomation implements iAIService {

    public void AIServiceOrderProcess(Scanner scanner, Cart shopping_cart) {

        String[][] workflow_specifications = new String[4][3]; // Specifications for the Workflow Automation order

        for (int i = 0; i < workflow_specifications.length; i++) {
            switch (i) {
                case 0:
                    System.out.println("=== WORKFLOW IDENTIFICATION ===");
                    for (int j = 0; j < 3; j++) {
                        switch (j) {
                            case 0:
                                System.out.print("Which business process do you want to automate ?: ");
                                workflow_specifications[0][0] = scanner.nextLine();
                                break;
                            case 1:
                                System.out.print("What is the primary goal (e.g., speed, accuracy, cost reduction) ?: ");
                                workflow_specifications[0][1] = scanner.nextLine();
                                break;
                            case 2:
                                System.out.print("Which software/tools are currently used in this process ?: ");
                                workflow_specifications[0][2] = scanner.nextLine();
                                break;
                        }
                    }
                    break;
                case 1:
                    System.out.println("=== PROCESS COMPLEXITY & DATA ===");
                    for (int j = 0; j < 3; j++) {
                        switch (j) {
                            case 0:
                                System.out.print("Briefly describe the manual steps currently involved: ");
                                workflow_specifications[1][0] = scanner.nextLine();
                                break;
                            case 1:
                                workflow_specifications[1][1] = null;
                                break;
                            case 2:
                                System.out.print("Upload a process map or documentation file (PDF, Excel, Word) ?: ");
                                JFileChooser FileChooser = new JFileChooser();
                                int file_result = FileChooser.showOpenDialog(null);
                                if (file_result == JFileChooser.APPROVE_OPTION) {
                                    File selected_file = FileChooser.getSelectedFile();
                                    try {
                                        byte[] FileBytes = java.nio.file.Files.readAllBytes(selected_file.toPath()); // 1. Reading the bytes of the file
                                        String EncodedFile = java.util.Base64.getEncoder().encodeToString(FileBytes); // 2. Transformation in Base 64 String to store the file
                                        workflow_specifications[1][2] = EncodedFile;
                                        System.out.println("File uploaded successfully: " + selected_file.getName());
                                    } catch (Exception e) {
                                        System.out.println("Error reading file: " + e.getMessage());
                                        workflow_specifications[1][2] = null;
                                    }
                                } else {
                                    System.out.println("No file selected");
                                    workflow_specifications[1][2] = null;
                                }
                                break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("=== INTEGRATION & TRIGGERS ===");
                    for (int j = 0; j < 3; j++) {
                        switch (j) {
                            case 0:
                                System.out.print("What event should trigger the automation ?: ");
                                workflow_specifications[2][0] = scanner.nextLine();
                                break;
                            case 1:
                                System.out.print("Specify required integrations (APIs, Webhooks, etc.): ");
                                workflow_specifications[2][1] = scanner.nextLine();
                                break;
                            case 2:
                                workflow_specifications[2][2] = null;
                                break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("=== MONITORING & ERROR HANDLING ===");
                    for (int j = 0; j < 3; j++) {
                        switch (j) {
                            case 0:
                                System.out.print("Where should the execution logs and data be stored ?: ");
                                workflow_specifications[3][0] = scanner.nextLine();
                                break;
                            case 1:
                                System.out.print("What are the critical safety/error conditions to monitor ?: ");
                                workflow_specifications[3][1] = scanner.nextLine();
                                break;
                            case 2:
                                workflow_specifications[3][2] = null;
                                break;
                        }
                    }
                    break;
            }
        }
        double workflow_cost = GenerateOrderCost(workflow_specifications); // Calculation of the order's cost/price
        int workflow_development_days = GenerateOrderDevelopmentTime(workflow_specifications); // Calculation of the order's development days
        System.out.println("=== WORKFLOW AUTOMATION ORDER SPECIFICATIONS ===");
        System.out.println("Order Cost: $" + workflow_cost);
        // Preparation of development date  
        LocalDate today_date = LocalDate.now();
        LocalDate development_date = today_date.plusDays(workflow_development_days);
        DateTimeFormatter date_formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Preperation Date: " + development_date.format(date_formatter));
        shopping_cart.AddOrderLine(workflow_specifications, workflow_cost, workflow_development_days, 1); 
    }

    public double GenerateOrderCost(String[][] specs) {
        double base_order_cost = 450;
        double extra_order_cost = 0;

        // [0][0]: Business Process Type
        if (specs[0][0] != null) {
            String process = specs[0][0].toLowerCase();
            if (process.contains("finance") || process.contains("accounting")) {
                extra_order_cost += 140.0;
            }
            if (process.contains("hr") || process.contains("recruitment")) {
                extra_order_cost += 80.0;
            }
            if (process.contains("supply chain") || process.contains("logistics")) {
                extra_order_cost += 120.0;
            }
            if (process.contains("customer service")) {
                extra_order_cost += 90.0;
            }
        }

        // [0][1]: Strategic Goal
        if (specs[0][1] != null) {
            String goal = specs[0][1].toLowerCase();
            if (goal.contains("accuracy") || goal.contains("compliance")) {
                extra_order_cost += 100.0;
            }
            if (goal.contains("speed") || goal.contains("latency")) {
                extra_order_cost += 70.0;
            }
            if (goal.contains("cost")) {
                extra_order_cost += 50.0;
            }
        }

        // [0][2]: Legacy & Modern Tools
        if (specs[0][2] != null) {
            String tools = specs[0][2].toLowerCase();
            if (tools.contains("sap") || tools.contains("salesforce") || tools.contains("oracle")) {
                extra_order_cost += 210.0;
            }
            if (tools.contains("excel") || tools.contains("legacy")) {
                extra_order_cost += 60.0;
            }
            if (tools.contains("custom") || tools.contains("proprietary")) {
                extra_order_cost += 150.0;
            }
        }

        // [1][0]: Complexity of manual steps
        if (specs[1][0] != null) {
            String steps = specs[1][0].toLowerCase();
            if (steps.contains("many") || steps.contains("complex") || steps.contains("heavy")) {
                extra_order_cost += 130.0;
            }
            if (steps.contains("simple") || steps.contains("repetitive")) {
                extra_order_cost += 40.0;
            }
        }

        // [1][2]: Process Documentation File Analysis
        if (specs[1][2] != null) {
            extra_order_cost += 110.0; // Base fee for processing logic docs
            int fileLength = specs[1][2].length();
            if (fileLength > 150000) {
                extra_order_cost += 60.0; // Medium-sized map
            }
            if (fileLength > 1200000) {
                extra_order_cost += 220.0; // Highly detailed system documentation
            }
        }

        // [2][0]: Automation Triggers
        if (specs[2][0] != null) {
            String trigger = specs[2][0].toLowerCase();
            if (trigger.contains("schedule") || trigger.contains("time")) {
                extra_order_cost += 50.0;
            }
            if (trigger.contains("webhook") || trigger.contains("real-time")) {
                extra_order_cost += 130.0;
            }
            if (trigger.contains("email") || trigger.contains("outlook")) {
                extra_order_cost += 60.0;
            }
        }

        // [2][1]: Connection Methods (APIs)
        if (specs[2][1] != null) {
            String integrations = specs[2][1].toLowerCase();
            if (integrations.contains("api") || integrations.contains("rest")) {
                extra_order_cost += 180.0;
            }
            if (integrations.contains("database") || integrations.contains("sql")) {
                extra_order_cost += 110.0;
            }
            if (integrations.contains("cloud") || integrations.contains("azure")) {
                extra_order_cost += 140.0;
            }
        }

        // [3][0]: Data Storage Location
        if (specs[3][0] != null) {
            String storage = specs[3][0].toLowerCase();
            if (storage.contains("blockchain") || storage.contains("secure ledger")) {
                extra_order_cost += 300.0;
            }
            if (storage.contains("s3") || storage.contains("blob")) {
                extra_order_cost += 90.0;
            }
            if (storage.contains("local")) {
                extra_order_cost += 30.0;
            }
        }

        // [3][1]: Safety Layer & Error Handling
        if (specs[3][1] != null) {
            String safety = specs[3][1].toLowerCase();
            if (safety.contains("failover") || safety.contains("redundant")) {
                extra_order_cost += 160.0;
            }
            if (safety.contains("alert") || safety.contains("notification")) {
                extra_order_cost += 50.0;
            }
            if (safety.contains("rollback")) {
                extra_order_cost += 110.0;
            }
        }

        double total_order_cost = base_order_cost + extra_order_cost;
        return total_order_cost;
    }

    public int GenerateOrderDevelopmentTime(String[][] specs) {
        int base_development_days = 6;
        int extra_development_days = 0;

        // [0][0]: Business Process (Field complexity)
        if (specs[0][0] != null) {
            String process = specs[0][0].toLowerCase();
            if (process.contains("finance") || process.contains("legal")) {
                extra_development_days += 6;
            }
            if (process.contains("logistics") || process.contains("it")) {
                extra_development_days += 4;
            }
        }

        // [0][1]: Strategic Goals (Precision requirement)
        if (specs[0][1] != null && specs[0][1].toLowerCase().contains("accuracy")) {
            extra_development_days += 3;
        }

        // [0][2]: Current Tools (Software compatibility depth)
        if (specs[0][2] != null) {
            String tools = specs[0][2].toLowerCase();
            if (tools.contains("sap") || tools.contains("oracle")) {
                extra_development_days += 8;
            }
            if (tools.contains("custom")) {
                extra_development_days += 5;
            }
        }

        // [1][0]: Manual Steps (Logic mapping time)
        if (specs[1][0] != null && specs[1][0].toLowerCase().contains("complex")) {
            extra_development_days += 7;
        }

        // [1][2]: Documentation Processing (Parsing process maps)
        if (specs[1][2] != null) {
            extra_development_days += 3;
            if (specs[1][2].length() > 1000000) {
                extra_development_days += 4;
            }
        }

        // [2][0]: Trigger Implementation logic
        if (specs[2][0] != null && specs[2][0].toLowerCase().contains("webhook")) {
            extra_development_days += 4;
        }

        // [2][1]: API/Integration Complexity
        if (specs[2][1] != null) {
            String integr = specs[2][1].toLowerCase();
            if (integr.contains("api")) {
                extra_development_days += 7;
            }
            if (integr.contains("database")) {
                extra_development_days += 3;
            }
        }

        // [3][0]: Storage Architecture
        if (specs[3][0] != null && specs[3][0].toLowerCase().contains("blockchain")) {
            extra_development_days += 12;
        }

        // [3][1]: Safety Layer (Error prevention development)
        if (specs[3][1] != null) {
            String safety = specs[3][1].toLowerCase();
            if (safety.contains("failover") || safety.contains("rollback")) {
                extra_development_days += 6;
            }
        }

        int total_development_days = base_development_days + extra_development_days;
        return (total_development_days);
    }
}
