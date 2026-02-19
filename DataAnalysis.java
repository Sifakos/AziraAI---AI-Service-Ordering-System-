package PackageService;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class DataAnalysis implements iAIService {

    public void AIServiceOrderProcess(Scanner scanner, Cart shopping_cart) {

        String[][] data_analysis_specifications = new String[4][3]; // Specifications for the Data Analysis order

        for (int i = 0; i < data_analysis_specifications.length; i++) {
            switch (i) {
                case 0:
                    System.out.println("=== PROJECT SCOPE ===");
                    for (int j = 0; j < 3; j++) {
                        switch (j) {
                            case 0:
                                System.out.print("What is the primary objective of this analysis (e.g., forecasting, clustering) ?: ");
                                data_analysis_specifications[0][0] = scanner.nextLine();
                                break;
                            case 1:
                                System.out.print("What is the main business question you want to answer ?: ");
                                data_analysis_specifications[0][1] = scanner.nextLine();
                                break;
                            case 2:
                                System.out.print("Which industry sector does this data belong to ?: ");
                                data_analysis_specifications[0][2] = scanner.nextLine();
                                break;
                        }
                    }
                    break;
                case 1:
                    System.out.println("=== DATA SOURCE & INPUT ===");
                    for (int j = 0; j < 3; j++) {
                        switch (j) {
                            case 0:
                                System.out.print("What is the current format of your data (e.g., JSON, SQL, CSV) ?: ");
                                data_analysis_specifications[1][0] = scanner.nextLine();
                                break;
                            case 1:
                                data_analysis_specifications[1][1] = null;
                                break;
                            case 2:
                                System.out.print("Upload a sample dataset or data dictionary (PDF, Excel, CSV) ?: ");
                                JFileChooser FileChooser = new JFileChooser();
                                int file_result = FileChooser.showOpenDialog(null);
                                if (file_result == JFileChooser.APPROVE_OPTION) {
                                    File selected_file = FileChooser.getSelectedFile();
                                    try {
                                        byte[] FileBytes = java.nio.file.Files.readAllBytes(selected_file.toPath()); // 1. Reading the bytes of the file
                                        String EncodedFile = java.util.Base64.getEncoder().encodeToString(FileBytes); // 2. Transformation in Base 64 String to store the file
                                        data_analysis_specifications[1][2] = EncodedFile;
                                        System.out.println("File uploaded successfully: " + selected_file.getName());
                                    } catch (Exception e) {
                                        System.out.println("Error reading file: " + e.getMessage());
                                        data_analysis_specifications[1][2] = null;
                                    }
                                } else {
                                    System.out.println("No file selected");
                                    data_analysis_specifications[1][2] = null;
                                }
                                break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("=== VISUALIZATION & REPORTING ===");
                    for (int j = 0; j < 3; j++) {
                        switch (j) {
                            case 0:
                                System.out.print("How would you like the results to be presented (e.g., Dashboard, PDF Report) ?: ");
                                data_analysis_specifications[2][0] = scanner.nextLine();
                                break;
                            case 1:
                                System.out.print("Which visualization tools do you prefer (e.g., PowerBI, Tableau, Matplotlib) ?: ");
                                data_analysis_specifications[2][1] = scanner.nextLine();
                                break;
                            case 2:
                                data_analysis_specifications[2][2] = null;
                                break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("=== PROCESSING & PRIVACY ===");
                    for (int j = 0; j < 3; j++) {
                        switch (j) {
                            case 0:
                                System.out.print("Where is the data currently hosted (e.g., On-premise, AWS, Azure) ?: ");
                                data_analysis_specifications[3][0] = scanner.nextLine();
                                break;
                            case 1:
                                System.out.print("What are the specific data privacy or anonymization requirements ?: ");
                                data_analysis_specifications[3][1] = scanner.nextLine();
                                break;
                            case 2:
                                data_analysis_specifications[3][2] = null;
                                break;
                        }
                    }
                    break;
            }
        }

        double analysis_cost = GenerateOrderCost(data_analysis_specifications); // Calculation of the order's cost/price
        int analysis_development_days = GenerateOrderDevelopmentTime(data_analysis_specifications); // Calculation of the order's development days
        System.out.println("=== DATA ANALYSIS ORDER SPECIFICATIONS ===");
        System.out.println("Order Cost: $" + analysis_cost);
        // Preparation of development date  
        LocalDate today_date = LocalDate.now();
        LocalDate development_data = today_date.plusDays(analysis_development_days);
        DateTimeFormatter date_formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Preperation Date: " + development_data.format(date_formatter));
        
        shopping_cart.AddOrderLine(data_analysis_specifications, analysis_cost, analysis_development_days, 3); // 3 representing Data Analysis type
    }

    public double GenerateOrderCost(String[][] specs) {
        double base_order_cost = 600;
        double extra_order_cost = 0;

        // [0][0]: Objective
        if (specs[0][0] != null) {
            String objective = specs[0][0].toLowerCase();
            if (objective.contains("forecasting") || objective.contains("predictive")) {
                extra_order_cost += 150.0;
            }
            if (objective.contains("clustering") || objective.contains("segmentation")) {
                extra_order_cost += 100.0;
            }
            if (objective.contains("cleaning") || objective.contains("etl")) {
                extra_order_cost += 80.0;
            }
        }

        // [0][1]: Business Question Complexity
        if (specs[0][1] != null && specs[0][1].length() > 50) {
            extra_order_cost += 60.0;
        }

        // [0][2]: Industry Sector
        if (specs[0][2] != null) {
            String industry = specs[0][2].toLowerCase();
            if (industry.contains("finance") || industry.contains("banking")) {
                extra_order_cost += 140.0;
            }
            if (industry.contains("medical") || industry.contains("healthcare")) {
                extra_order_cost += 200.0;
            }
            if (industry.contains("retail") || industry.contains("commerce")) {
                extra_order_cost += 70.0;
            }
        }

        // [1][0]: Data Format
        if (specs[1][0] != null) {
            String format = specs[1][0].toLowerCase();
            if (format.contains("sql") || format.contains("database")) {
                extra_order_cost += 90.0;
            }
            if (format.contains("json") || format.contains("xml") || format.contains("unstructured")) {
                extra_order_cost += 120.0;
            }
        }

        // [1][2]: Dataset File Analysis
        if (specs[1][2] != null) {
            extra_order_cost += 110.0; 
            int fileLength = specs[1][2].length();
            if (fileLength > 150000) {
                extra_order_cost += 70.0; 
            }
            if (fileLength > 1500000) {
                extra_order_cost += 250.0; 
            }
        }

        // [2][0]: Presentation Method
        if (specs[2][0] != null) {
            String pres = specs[2][0].toLowerCase();
            if (pres.contains("dashboard") || pres.contains("interactive")) {
                extra_order_cost += 180.0;
            }
            if (pres.contains("real-time") || pres.contains("live")) {
                extra_order_cost += 220.0;
            }
        }

        // [2][1]: Visualization Tools
        if (specs[2][1] != null) {
            String tool = specs[2][1].toLowerCase();
            if (tool.contains("powerbi") || tool.contains("tableau")) {
                extra_order_cost += 130.0;
            }
            if (tool.contains("custom") || tool.contains("d3.js")) {
                extra_order_cost += 160.0;
            }
        }

        // [3][0]: Hosting Environment
        if (specs[3][0] != null) {
            String host = specs[3][0].toLowerCase();
            if (host.contains("aws") || host.contains("cloud") || host.contains("azure")) {
                extra_order_cost += 110.0;
            }
            if (host.contains("on-premise") || host.contains("local server")) {
                extra_order_cost += 150.0;
            }
        }

        // [3][1]: Privacy & Compliance
        if (specs[3][1] != null) {
            String privacy = specs[3][1].toLowerCase();
            if (privacy.contains("gdpr") || privacy.contains("compliance")) {
                extra_order_cost += 140.0;
            }
            if (privacy.contains("anonymization") || privacy.contains("encryption")) {
                extra_order_cost += 120.0;
            }
        }

        return base_order_cost + extra_order_cost;
    }

    public int GenerateOrderDevelopmentTime(String[][] specs) {
        int base_development_days = 7;
        int extra_development_days = 0;

        // [0][0]: Objective Complexity
        if (specs[0][0] != null) {
            String obj = specs[0][0].toLowerCase();
            if (obj.contains("forecasting") || obj.contains("predictive")) {
                extra_development_days += 8;
            }
        }

        // [0][2]: Industry Knowledge
        if (specs[0][2] != null && specs[0][2].toLowerCase().contains("medical")) {
            extra_development_days += 5;
        }

        // [1][0]: Data Parsing Complexity
        if (specs[1][0] != null && specs[1][0].toLowerCase().contains("unstructured")) {
            extra_development_days += 6;
        }

        // [1][2]: File Processing
        if (specs[1][2] != null) {
            extra_development_days += 3;
            if (specs[1][2].length() > 1000000) {
                extra_development_days += 5;
            }
        }

        // [2][0]: Dashboard Development
        if (specs[2][0] != null && specs[2][0].toLowerCase().contains("dashboard")) {
            extra_development_days += 7;
        }

        // [2][1]: Tool Integration
        if (specs[2][1] != null && specs[2][1].toLowerCase().contains("custom")) {
            extra_development_days += 6;
        }

        // [3][0]: Infrastructure Setup
        if (specs[3][0] != null && specs[3][0].toLowerCase().contains("on-premise")) {
            extra_development_days += 4;
        }

        // [3][1]: Anonymization Logic
        if (specs[3][1] != null && specs[3][1].toLowerCase().contains("anonymization")) {
            extra_development_days += 5;
        }

        return base_development_days + extra_development_days;
    }
}
