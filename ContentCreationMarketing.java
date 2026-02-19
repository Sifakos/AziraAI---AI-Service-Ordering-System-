package PackageService;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class ContentCreationMarketing implements iAIService {

    public void AIServiceOrderProcess(Scanner scanner, Cart shopping_cart) {

        String[][] marketing_specifications = new String[4][3]; // Specifications for the Marketing & Content order

        for (int i = 0; i < marketing_specifications.length; i++) {
            switch (i) {
                case 0:
                    System.out.println("=== CAMPAIGN IDENTITY ===");
                    for (int j = 0; j < 3; j++) {
                        switch (j) {
                            case 0:
                                System.out.print("What is the target audience for this content ?: ");
                                marketing_specifications[0][0] = scanner.nextLine();
                                break;
                            case 1:
                                System.out.print("What is the main marketing objective (e.g., brand awareness, lead gen) ?: ");
                                marketing_specifications[0][1] = scanner.nextLine();
                                break;
                            case 2:
                                System.out.print("What is the primary industry or niche ?: ");
                                marketing_specifications[0][2] = scanner.nextLine();
                                break;
                        }
                    }
                    break;
                case 1:
                    System.out.println("=== CONTENT SPECIFICATIONS ===");
                    for (int j = 0; j < 3; j++) {
                        switch (j) {
                            case 0:
                                System.out.print("What type of content is required (e.g., blog posts, social media, video scripts) ?: ");
                                marketing_specifications[1][0] = scanner.nextLine();
                                break;
                            case 1:
                                marketing_specifications[1][1] = null;
                                break;
                            case 2:
                                System.out.print("Upload your brand guidelines or reference material (PDF, Word, Images) ?: ");
                                JFileChooser FileChooser = new JFileChooser();
                                int file_result = FileChooser.showOpenDialog(null);
                                if (file_result == JFileChooser.APPROVE_OPTION) {
                                    File selected_file = FileChooser.getSelectedFile();
                                    try {
                                        byte[] FileBytes = java.nio.file.Files.readAllBytes(selected_file.toPath()); // 1. Reading the bytes of the file
                                        String EncodedFile = java.util.Base64.getEncoder().encodeToString(FileBytes); // 2. Transformation in Base 64 String to store the file
                                        marketing_specifications[1][2] = EncodedFile;
                                        System.out.println("File uploaded successfully: " + selected_file.getName());
                                    } catch (Exception e) {
                                        System.out.println("Error reading file: " + e.getMessage());
                                        marketing_specifications[1][2] = null;
                                    }
                                } else {
                                    System.out.println("No file selected");
                                    marketing_specifications[1][2] = null;
                                }
                                break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("=== TONE & DISTRIBUTION ===");
                    for (int j = 0; j < 3; j++) {
                        switch (j) {
                            case 0:
                                System.out.print("Describe the desired brand voice (e.g., authoritative, witty, empathetic) ?: ");
                                marketing_specifications[2][0] = scanner.nextLine();
                                break;
                            case 1:
                                System.out.print("On which platforms will this content be published ?: ");
                                marketing_specifications[2][1] = scanner.nextLine();
                                break;
                            case 2:
                                marketing_specifications[2][2] = null;
                                break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("=== SEO & COMPLIANCE ===");
                    for (int j = 0; j < 3; j++) {
                        switch (j) {
                            case 0:
                                System.out.print("List any primary keywords or SEO requirements: ");
                                marketing_specifications[3][0] = scanner.nextLine();
                                break;
                            case 1:
                                System.out.print("What content types/topics are strictly prohibited (Safety/Compliance) ?: ");
                                marketing_specifications[3][1] = scanner.nextLine();
                                break;
                            case 2:
                                marketing_specifications[3][2] = null;
                                break;
                        }
                    }
                    break;
            }
        }

        double marketing_cost = GenerateOrderCost(marketing_specifications); // Calculation of the order's cost/price
        int marketing_development_days = GenerateOrderDevelopmentTime(marketing_specifications); // Calculation of the order's development days
        System.out.println("=== CONTENT CREATION ORDER SPECIFICATIONS ===");
        System.out.println("Order Cost: $" + marketing_cost);
        // Preparation of development date  
        LocalDate today_date = LocalDate.now();
        LocalDate development_data = today_date.plusDays(marketing_development_days);
        DateTimeFormatter date_formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Preperation Date: " + development_data.format(date_formatter));
        
        shopping_cart.AddOrderLine(marketing_specifications, marketing_cost, marketing_development_days, 2); 
    }

    public double GenerateOrderCost(String[][] specs) {
        double base_order_cost = 350;
        double extra_order_cost = 0;

        // [0][0]: Target Audience
        if (specs[0][0] != null) {
            String audience = specs[0][0].toLowerCase();
            if (audience.contains("corporate") || audience.contains("b2b") || audience.contains("enterprise")) {
                extra_order_cost += 85.0;
            }
            if (audience.contains("medical") || audience.contains("healthcare") || audience.contains("scientific")) {
                extra_order_cost += 160.0;
            }
            if (audience.contains("teenagers") || audience.contains("gen-z") || audience.contains("youth")) {
                extra_order_cost += 50.0;
            }
        }

        // [0][1]: Main Objective
        if (specs[0][1] != null) {
            String objective = specs[0][1].toLowerCase();
            if (objective.contains("leads") || objective.contains("sales") || objective.contains("conversion")) {
                extra_order_cost += 95.0;
            }
            if (objective.contains("brand awareness") || objective.contains("reach")) {
                extra_order_cost += 45.0;
            }
            if (objective.contains("education") || objective.contains("instructional")) {
                extra_order_cost += 75.0;
            }
        }

        // [0][2]: Industry Niche
        if (specs[0][2] != null) {
            String niche = specs[0][2].toLowerCase();
            if (niche.contains("tech") || niche.contains("software") || niche.contains("it")) {
                extra_order_cost += 115.0;
            }
            if (niche.contains("finance") || niche.contains("crypto") || niche.contains("banking")) {
                extra_order_cost += 175.0;
            }
            if (niche.contains("lifestyle") || niche.contains("fashion") || niche.contains("travel")) {
                extra_order_cost += 65.0;
            }
        }

        // [1][0]: Content Format
        if (specs[1][0] != null) {
            String type = specs[1][0].toLowerCase();
            if (type.contains("video") || type.contains("animation") || type.contains("scripting")) {
                extra_order_cost += 140.0;
            }
            if (type.contains("blog") || type.contains("article") || type.contains("long-form")) {
                extra_order_cost += 70.0;
            }
            if (type.contains("social media") || type.contains("copy") || type.contains("ads")) {
                extra_order_cost += 55.0;
            }
        }

        // [1][2]: Guidelines File Analysis
        if (specs[1][2] != null) {
            extra_order_cost += 80.0; // Base fee for file processing
            int fileLength = specs[1][2].length();
            if (fileLength > 120000) {
                extra_order_cost += 55.0; // Medium guide
            }
            if (fileLength > 1100000) {
                extra_order_cost += 190.0; // Large brand manual
            }
        }

        // [2][0]: Brand Voice Style
        if (specs[2][0] != null) {
            String voice = specs[2][0].toLowerCase();
            if (voice.contains("humorous") || voice.contains("witty") || voice.contains("funny")) {
                extra_order_cost += 65.0;
            }
            if (voice.contains("authoritative") || voice.contains("expert") || voice.contains("professional")) {
                extra_order_cost += 105.0;
            }
            if (voice.contains("empathetic") || voice.contains("kind") || voice.contains("storytelling")) {
                extra_order_cost += 85.0;
            }
        }

        // [2][1]: Publication Channels
        if (specs[2][1] != null) {
            String plat = specs[2][1].toLowerCase();
            if (plat.contains("instagram") || plat.contains("tiktok") || plat.contains("youtube")) {
                extra_order_cost += 75.0;
            }
            if (plat.contains("linkedin") || plat.contains("professional networks")) {
                extra_order_cost += 85.0;
            }
            if (plat.contains("omnichannel") || plat.contains("all platforms") || plat.contains("multi")) {
                extra_order_cost += 150.0;
            }
        }

        // [3][0]: SEO & Keywords
        if (specs[3][0] != null) {
            String keywords = specs[3][0].toLowerCase();
            if (keywords.length() > 5) {
                extra_order_cost += 110.0; // Base SEO setup
            }
            if (keywords.split(",").length > 8) {
                extra_order_cost += 60.0; // Heavy keyword research
            }
        }

        // [3][1]: Safety & Compliance Guardrails
        if (specs[3][1] != null) {
            String safety = specs[3][1].toLowerCase();
            if (safety.contains("legal") || safety.contains("medical") || safety.contains("disclaimer")) {
                extra_order_cost += 140.0;
            }
            if (safety.contains("sensitive") || safety.contains("controversial")) {
                extra_order_cost += 110.0;
            }
            if (safety.contains("copyright") || safety.contains("trademark")) {
                extra_order_cost += 70.0;
            }
        }

        double total_order_cost = base_order_cost + extra_order_cost;
        return total_order_cost;
    }

    public int GenerateOrderDevelopmentTime(String[][] specs) {
        int base_development_days = 4;
        int extra_development_days = 0;

        // [0][0]: Audience Analysis time
        if (specs[0][0] != null) {
            String audience = specs[0][0].toLowerCase();
            if (audience.contains("corporate") || audience.contains("medical")) {
                extra_development_days += 3;
            }
        }

        // [0][2]: Industry Knowledge Research
        if (specs[0][2] != null) {
            String niche = specs[0][2].toLowerCase();
            if (niche.contains("finance") || niche.contains("tech")) {
                extra_development_days += 4;
            }
            if (niche.contains("crypto")) {
                extra_development_days += 6;
            }
        }

        // [1][0]: Content Production time
        if (specs[1][0] != null) {
            String format = specs[1][0].toLowerCase();
            if (format.contains("video") || format.contains("animation")) {
                extra_development_days += 9;
            }
            if (format.contains("blog") || format.contains("article")) {
                extra_development_days += 3;
            }
        }

        // [1][2]: Document Parsing time
        if (specs[1][2] != null) {
            extra_development_days += 2;
            if (specs[1][2].length() > 900000) {
                extra_development_days += 3;
            }
        }

        // [2][0]: Tone Fine-tuning
        if (specs[2][0] != null && specs[2][0].toLowerCase().contains("humorous")) {
            extra_development_days += 3;
        }

        // [2][1]: Platform Adaptation
        if (specs[2][1] != null && (specs[2][1].contains(",") || specs[2][1].contains("multi"))) {
            extra_development_days += 5;
        }

        // [3][0]: SEO Implementation
        if (specs[3][0] != null && specs[3][0].length() > 40) {
            extra_development_days += 3;
        }

        // [3][1]: Compliance Checking
        if (specs[3][1] != null) {
            String safety = specs[3][1].toLowerCase();
            if (safety.contains("legal") || safety.contains("medical")) {
                extra_development_days += 5;
            }
        }

        int total_development_days = base_development_days + extra_development_days;
        return (total_development_days);
    }
}
