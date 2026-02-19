package PackageService;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class AIAgent implements iAIService {
    

    public void AIServiceOrderProcess(Scanner scanner, Cart shopping_cart) {

        String[][] ai_agent_specifications = new String[4][3]; // Specifications for the A.I. Agent order

        for (int i = 0; i < ai_agent_specifications.length; i ++) {
            switch (i) {
                case 0:
                    System.out.println("=== THE ID OF THE A.I. AGENT ===");
                    for (int j = 0; j < 3; j ++) {
                        switch (j) {
                            case 0:
                                System.out.print("What is the name of the department which needs assistance ?: ");
                                ai_agent_specifications[0][0] = scanner.nextLine();
                                break;
                            case 1:
                                System.out.print("What will be the title position of the A.I. agent ?: ");
                                ai_agent_specifications[0][1] = scanner.nextLine();
                                break;
                            case 2:
                                System.out.print("What will be its main tasks ?: ");
                                ai_agent_specifications[0][2] = scanner.nextLine();
                                break;
                        }
                    }
                    break;
                case 1:
                    System.out.println("=== THE WORK TASKS OF THE A.I. AGENT ===");
                    for (int j = 0; j < 3; j ++) {
                        switch (j) {
                            case 0:
                                System.out.print("What are the reasons why the tasks which need A.I. assistance  are getting delayed ?: ");
                                ai_agent_specifications[1][0] = scanner.nextLine();
                                break;
                            case 1:
                                ai_agent_specifications[1][1] = null;
                                break;
                            case 2:
                                System.out.print("Is there any file (PDF, Excel, Word) that the A.I. agent needs to get adviced before completing tasks ?: ");
                                JFileChooser FileChooser = new JFileChooser();
                                int file_result = FileChooser.showOpenDialog(null);
                                if (file_result == JFileChooser.APPROVE_OPTION) {
                                    File selected_file = FileChooser.getSelectedFile();
                                    try {
                                        byte[] FileBytes = java.nio.file.Files.readAllBytes(selected_file.toPath()); // 1. Reading the bytes of the file
                                        String EncodedFile = java.util.Base64.getEncoder().encodeToString(FileBytes); // 2. Transformation in Base 64 String to store the file
                                        ai_agent_specifications[1][2] = EncodedFile;
                                        System.out.println("File uploaded successfully: " + selected_file.getName());
                                    } catch (Exception e) {
                                        System.out.println("Error reading file: " + e.getMessage());
                                        ai_agent_specifications[1][2] = null;
                                    }
                                } else {
                                    System.out.println("No file selected");
                                    ai_agent_specifications[1][2] = null;
                                }
                                break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("=== PERSONALITY & COMMUNICATION ===");
                    for (int j = 0; j < 3; j ++) {
                        switch (j) {
                            case 0:
                                System.out.print("How would you like the A.I. agent to communicate with the clients or employees ?");
                                ai_agent_specifications[2][0] = scanner.nextLine();
                                break;
                            case 1:
                                System.out.print("In what channels will the A.I. agent 'live' in ?: ");
                                ai_agent_specifications[2][1] = scanner.nextLine();
                                break;
                            case 2:
                                ai_agent_specifications[2][2] = null;
                                break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("=== CONNECTION WITH THE 'REAL WORLD' ===");
                    for (int j = 0; j < 3; j ++) {
                        switch (j) {
                            case 0:
                                System.out.print("Where will all the information be stored ?: ");
                                ai_agent_specifications[3][0] = scanner.nextLine();
                                break;
                            case 1:
                                System.out.print("In what circumnstances will the A.I. agent stop performing immediately?: ");
                                ai_agent_specifications[3][1] = scanner.nextLine();
                                break;
                            case 2:
                                ai_agent_specifications[3][2] = null;
                                break;
                        }
                    }
                    break;
            }
        }
        double ai_agent_cost = GenerateOrderCost(ai_agent_specifications); // Calculation of the order's cost/price
        int ai_agent_development_days = GenerateOrderDevelopmentTime(ai_agent_specifications); // Calculation of the order's development days
        System.out.println("=== A.I. AGENT ORDER SPECIFICATIONS ===");
        System.out.println("Order Cost: $" + ai_agent_cost);
        // Preparation of development date  
        LocalDate today_date = LocalDate.now();
        LocalDate development_data = today_date.plusDays(ai_agent_development_days);
        DateTimeFormatter date_formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Preperation Date:" + development_data.format(date_formatter));
        shopping_cart.AddOrderLine(ai_agent_specifications, ai_agent_cost, ai_agent_development_days, 0);
    }

    public double GenerateOrderCost(String[][] ai_agent_specifications) {
        
        double base_order_cost = 500;
        double extra_order_cost = 0;

        // [0][0]: Department
        if (ai_agent_specifications[0][0] != null) {
            String department = ai_agent_specifications[0][0].toLowerCase();
            if (department.contains("sales") || department.contains("commerce") || department.contains("retail")) {
                extra_order_cost += 60.0;
            }
            if (department.contains("it") || department.contains("technology") || department.contains("software") || department.contains("devops")) {
                extra_order_cost += 120.0;
            }
            if (department.contains("legal") || department.contains("compliance") || department.contains("law")) {
                extra_order_cost += 180.0;
            }
            if (department.contains("medical") || department.contains("healthcare") || department.contains("hospital")) {
                extra_order_cost += 250.0;
            }
            if (department.contains("finance") || department.contains("banking") || department.contains("accounting")) {
                extra_order_cost += 160.0;
            }
            if (department.contains("marketing") || department.contains("advertising") || department.contains("social media")) {
                extra_order_cost += 70.0;
            }
        }

        // [0][1]: Job Title
        if (ai_agent_specifications[0][1] != null) {
            String job_title = ai_agent_specifications[0][1].toLowerCase();
            if (job_title.contains("manager") || job_title.contains("supervisor") || job_title.contains("lead")) {
                extra_order_cost += 90.0;
            }
            if (job_title.contains("ceo") || job_title.contains("executive") || job_title.contains("director") || job_title.contains("chief")) {
                extra_order_cost += 200.0;
            }
            if (job_title.contains("analyst") || job_title.contains("engineer") || job_title.contains("specialist")) {
                extra_order_cost += 110.0;
            }
            if (job_title.contains("assistant") || job_title.contains("secretary") || job_title.contains("clerk")) {
                extra_order_cost += 40.0;
            }
            if (job_title.contains("consultant") || job_title.contains("advisor")) {
                extra_order_cost += 130.0;
            }
        }

        // [0][2]: Main Tasks
        if (ai_agent_specifications[0][2] != null) {
            String tasks = ai_agent_specifications[0][2].toLowerCase();
            if (tasks.contains("coding") || tasks.contains("programming") || tasks.contains("debugging")) {
                extra_order_cost += 150.0;
            }
            if (tasks.contains("research") || tasks.contains("data mining") || tasks.contains("investigation")) {
                extra_order_cost += 100.0;
            }
            if (tasks.contains("translation") || tasks.contains("multilingual") || tasks.contains("localization")) {
                extra_order_cost += 85.0;
            }
            if (tasks.contains("content creation") || tasks.contains("copywriting") || tasks.contains("editing")) {
                extra_order_cost += 55.0;
            }
            if (tasks.contains("customer support") || tasks.contains("ticketing") || tasks.contains("troubleshooting")) {
                extra_order_cost += 75.0;
            }
            if (tasks.contains("scheduling") || tasks.contains("planning") || tasks.contains("automation")) {
                extra_order_cost += 95.0;
            }
        }

        // [1][0]: Delay Reasons
        if (ai_agent_specifications[1][0] != null) {
            String delay_reasons = ai_agent_specifications[1][0].toLowerCase();
            if (delay_reasons.contains("workload") || delay_reasons.contains("volume") || delay_reasons.contains("heavy")) {
                extra_order_cost += 50.0;
            }
            if (delay_reasons.contains("complex") || delay_reasons.contains("complicated") || delay_reasons.contains("difficult")) {
                extra_order_cost += 100.0;
            }
            if (delay_reasons.contains("manual") || delay_reasons.contains("repetitive") || delay_reasons.contains("boring")) {
                extra_order_cost += 40.0;
            }
            if (delay_reasons.contains("human error") || delay_reasons.contains("mistakes") || delay_reasons.contains("accuracy")) {
                extra_order_cost += 80.0;
            }
            if (delay_reasons.contains("slow") || delay_reasons.contains("latency") || delay_reasons.contains("speed")) {
                extra_order_cost += 60.0;
            }
        }

        // [1][2]: Encoded File Analysis
        if (ai_agent_specifications[1][2] != null) {
            extra_order_cost += 100.0; // Base fee for file processing
            int fileLength = ai_agent_specifications[1][2].length();
            if (fileLength > 100000) {
                extra_order_cost += 50.0; // Medium file
            }
            if (fileLength > 1000000) {
                extra_order_cost += 200.0; // Very large file
            }
        }

        // [2][0]: Communication Style
        if (ai_agent_specifications[2][0] != null) {
            String style = ai_agent_specifications[2][0].toLowerCase();
            if (style.contains("formal") || style.contains("professional") || style.contains("polite")) {
                extra_order_cost += 40.0;
            }
            if (style.contains("friendly") || style.contains("warm") || style.contains("casual")) {
                extra_order_cost += 20.0;
            }
            if (style.contains("humorous") || style.contains("funny") || style.contains("witty")) {
                extra_order_cost += 60.0;
            }
            if (style.contains("empathetic") || style.contains("understanding") || style.contains("kind")) {
                extra_order_cost += 70.0;
            }
            if (style.contains("technical") || style.contains("expert") || style.contains("precise")) {
                extra_order_cost += 90.0;
            }
        }

        // [2][1]: Channels
        if (ai_agent_specifications[2][1] != null) {
            String channels = ai_agent_specifications[2][1].toLowerCase();
            if (channels.contains("web") || channels.contains("site") || channels.contains("browser")) {
                extra_order_cost += 50.0;
            }
            if (channels.contains("whatsapp") || channels.contains("telegram") || channels.contains("viber")) {
                extra_order_cost += 150.0;
            }
            if (channels.contains("slack") || channels.contains("teams") || channels.contains("discord")) {
                extra_order_cost += 120.0;
            }
            if (channels.contains("email") || channels.contains("outlook") || channels.contains("gmail")) {
                extra_order_cost += 40.0;
            }
            if (channels.contains("mobile") || channels.contains("ios") || channels.contains("android")) {
                extra_order_cost += 180.0;
            }
            if (channels.contains("api") || channels.contains("integration") || channels.contains("webhook")) {
                extra_order_cost += 200.0;
            }
        }

        // [3][0]: Storage
        if (ai_agent_specifications[3][0] != null) {
            String storage = ai_agent_specifications[3][0].toLowerCase();
            if (storage.contains("sql") || storage.contains("database") || storage.contains("relational")) {
                extra_order_cost += 110.0;
            }
            if (storage.contains("cloud") || storage.contains("aws") || storage.contains("azure") || storage.contains("google")) {
                extra_order_cost += 170.0;
            }
            if (storage.contains("nosql") || storage.contains("mongodb") || storage.contains("firebase")) {
                extra_order_cost += 130.0;
            }
            if (storage.contains("excel") || storage.contains("csv") || storage.contains("sheets")) {
                extra_order_cost += 40.0;
            }
            if (storage.contains("blockchain") || storage.contains("ledger") || storage.contains("secure")) {
                extra_order_cost += 350.0;
            }
        }

        // [3][1]: Stop Conditions (Safety)
        if (ai_agent_specifications[3][1] != null) {
            String safety = ai_agent_specifications[3][1].toLowerCase();
            if (safety.contains("offensive") || safety.contains("toxic") || safety.contains("insult")) {
                extra_order_cost += 70.0;
            }
            if (safety.contains("illegal") || safety.contains("unethical") || safety.contains("crime")) {
                extra_order_cost += 150.0;
            }
            if (safety.contains("private") || safety.contains("confidential") || safety.contains("gdpr")) {
                extra_order_cost += 120.0;
            }
            if (safety.contains("error") || safety.contains("crash") || safety.contains("fail")) {
                extra_order_cost += 50.0;
            }
            if (safety.contains("budget") || safety.contains("expensive") || safety.contains("limit")) {
                extra_order_cost += 90.0;
            }
        }
        double total_order_cost = base_order_cost + extra_order_cost;
        return(total_order_cost);
    }

    public int GenerateOrderDevelopmentTime(String[][] ai_agent_specifications) {
        
        int base_development_days = 5;
        int extra_development_days = 0;

        // [0][0]: Department (Complexity based on the field)
        if (ai_agent_specifications[0][0] != null) {
            String department = ai_agent_specifications[0][0].toLowerCase();
            if (department.contains("sales") || department.contains("marketing")) {
                extra_development_days += 2;
            }
            if (department.contains("it") || department.contains("finance")) {
                extra_development_days += 5;
            }
            if (department.contains("legal") || department.contains("medical")) {
                extra_development_days += 8;
            }
        }

        // [0][1]: Job Title (Responsibility/Decision-making level)
        if (ai_agent_specifications[0][1] != null) {
            String job_title = ai_agent_specifications[0][1].toLowerCase();
            if (job_title.contains("manager") || job_title.contains("analyst")) {
                extra_development_days += 3;
            }
            if (job_title.contains("ceo") || job_title.contains("executive") || job_title.contains("director")) {
                extra_development_days += 7;
            }
            if (job_title.contains("assistant")) {
                extra_development_days += 1;
            }
        }

        // [0][2]: Main Tasks (Technical implementation difficulty)
        if (ai_agent_specifications[0][2] != null) {
            String tasks = ai_agent_specifications[0][2].toLowerCase();
            if (tasks.contains("coding") || tasks.contains("programming")) {
                extra_development_days += 10;
            }
            if (tasks.contains("research") || tasks.contains("data mining")) {
                extra_development_days += 6;
            }
            if (tasks.contains("translation")) {
                extra_development_days += 4;
            }
            if (tasks.contains("customer support")) {
                extra_development_days += 3;
            }
            if (tasks.contains("automation")) {
                extra_development_days += 8;
            }
        }

        // [1][0]: Delay Reasons (Analysis of problems to be solved)
        if (ai_agent_specifications[1][0] != null) {
            String delay_reasons = ai_agent_specifications[1][0].toLowerCase();
            if (delay_reasons.contains("complex") || delay_reasons.contains("complicated")) {
                extra_development_days += 5;
            }
            if (delay_reasons.contains("human error") || delay_reasons.contains("accuracy")) {
                extra_development_days += 4;
            }
            if (delay_reasons.contains("volume") || delay_reasons.contains("heavy")) {
                extra_development_days += 2;
            }
        }

        // [1][2]: Encoded File Analysis (Training on provided files)
        if (ai_agent_specifications[1][2] != null) {
            extra_development_days += 3; // Basic time for file analysis
            int fileLength = ai_agent_specifications[1][2].length();
            if (fileLength > 100000) {
                extra_development_days += 2; // Medium-sized file
            }
            if (fileLength > 1000000) {
                extra_development_days += 5; // Very large file
            }
        }

        // [2][0]: Communication Style (Fine-tuning the tone/voice)
        if (ai_agent_specifications[2][0] != null) {
            String style = ai_agent_specifications[2][0].toLowerCase();
            if (style.contains("humorous") || style.contains("witty")) {
                extra_development_days += 4;
            }
            if (style.contains("empathetic")) {
                extra_development_days += 3;
            }
            if (style.contains("technical") || style.contains("precise")) {
                extra_development_days += 5;
            }
        }

        // [2][1]: Channels (Platform integration)
        if (ai_agent_specifications[2][1] != null) {
            String channels = ai_agent_specifications[2][1].toLowerCase();
            if (channels.contains("whatsapp") || channels.contains("slack") || channels.contains("teams")) {
                extra_development_days += 6;
            }
            if (channels.contains("mobile") || channels.contains("ios") || channels.contains("android")) {
                extra_development_days += 10;
            }
            if (channels.contains("api") || channels.contains("webhook")) {
                extra_development_days += 8;
            }
            if (channels.contains("web")) {
                extra_development_days += 2;
            }
        }

        // [3][0]: Storage (Database implementation)
        if (ai_agent_specifications[3][0] != null) {
            String storage = ai_agent_specifications[3][0].toLowerCase();
            if (storage.contains("sql") || storage.contains("database")) {
                extra_development_days += 4;
            }
            if (storage.contains("cloud") || storage.contains("aws") || storage.contains("azure")) {
                extra_development_days += 6;
            }
            if (storage.contains("blockchain")) {
                extra_development_days += 15;
            }
        }

        // [3][1]: Stop Conditions (Safety Layer & Guardrails)
        if (ai_agent_specifications[3][1] != null) {
            String safety = ai_agent_specifications[3][1].toLowerCase();
            if (safety.contains("illegal") || safety.contains("unethical")) {
                extra_development_days += 7;
            }
            if (safety.contains("private") || safety.contains("gdpr")) {
                extra_development_days += 5;
            }
            if (safety.contains("error") || safety.contains("fail")) {
                extra_development_days += 2;
            }
        }

        int total_development_days = base_development_days + extra_development_days;
        return (total_development_days);
    }
}
