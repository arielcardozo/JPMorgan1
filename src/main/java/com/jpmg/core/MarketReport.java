package com.jpmg.core;

import java.io.*;
import java.math.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.stream.*;

import com.jpmg.common.*;
import com.jpmg.common.Currency;

public class MarketReport {
    private static MarketReport marketEngine = new MarketReport();

    private List<Instruction> lInstruction;
    private static final long MESSAGE_PROCESSING_CAPACITY = 50;

    private MarketReport() {
//        this.register = new Register();
    }

    public static MarketReport getSalesEngine() {
        return marketEngine;
    }

    public boolean initialize(String instructionFile) {
            List<List<String>> intrucs = new ArrayList<List<String>>();
            lInstruction = new ArrayList<Instruction>();
            BuySellFlag.valueOf(BuySellFlag.class,"B");
            
            Locale.setDefault(Locale.ENGLISH);
	        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("dd MMM yyyy");
	        
            try (Stream<String> instructions = Files.lines(Paths.get(instructionFile))) {
    			intrucs = instructions.map(instruction -> Arrays.asList(instruction.split(","))).skip(1).collect(Collectors.toList());
    			intrucs.forEach(ins -> lInstruction.add(new Instruction(new Entity(ins.get(0).toUpperCase()), 
    					BuySellFlag.valueOf(BuySellFlag.class,ins.get(1).toUpperCase().trim()), 
    					BigDecimal.valueOf(Double.parseDouble(ins.get(2))),
    					new Currency(ins.get(3)), 
    					LocalDate.parse(ins.get(4),dTF),
    					LocalDate.parse(ins.get(5),dTF),
    					Integer.parseInt(ins.get(6)), 
    					BigDecimal.valueOf(Double.parseDouble(ins.get(7))))));
    			
    		} catch (IOException e) {
    			e.printStackTrace();
    			System.out.println("Stock update failed. Please confirm stock data.");
    		}
            

        return true;
    }

    
//    public List<Message> parse(String notificationsFile) {
//        List<Message> messages = null;
//        ObjectMapper mapper = new ObjectMapper();
//
//        try {
//            messages = mapper.readValue(new File(notificationsFile), new TypeReference<List<Message>>(){});
//            
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return messages;
//    }
//
//    public boolean process(List<Message> messages) {
//    	int processedMessages = 0;
//        StringBuilder adjustmentsLog = new StringBuilder();
//
//       
//        for(Message message : messages) {
//            boolean recordsUpdated = register.updateRecords(message);
//            if(!recordsUpdated) {
//                return false;
//            }
//
//            processedMessages++;
//
//            if(message instanceof AdjustmentMessage) {
//                adjustmentsLog.append("Product (");
//                adjustmentsLog.append(message.getType());
//                adjustmentsLog.append(") was adjusted (operation: ");
//                adjustmentsLog.append(((AdjustmentMessage) message).getOperationType());
//                adjustmentsLog.append(") by a value of ");
//                adjustmentsLog.append(message.getSellingPrice());
//                adjustmentsLog.append(" at approximately ");
//                adjustmentsLog.append(new Date());
//                adjustmentsLog.append(".\n");
//            }
//
//            if(processedMessages % 10 == 0) {
//                System.out.println("\n*** Intermediate Processed Sales' Record ***");
//                register.printSalesReport();
//            }
//
//            if(processedMessages == MESSAGE_PROCESSING_CAPACITY) {
//                System.out.println("\nOwing to limited processing capacity, only a total of "
//                        + MESSAGE_PROCESSING_CAPACITY + " messages can and were processed. Processing stopped.");
//                break;
//            }
//        }
//
//        /* printing one last time because any processed and recorded "reminder"
//            (14 notifications % 10 = 4 not reflected otherwise)
//           sales won't get displayed on console otherwise.
//         */
//        System.out.println("\n*** Final Processed Sales' Record ***");
//        register.printSalesReport();
//
//        if(adjustmentsLog.length() != 0) {
//            System.out.println("\n*** Adjustment Log ***");
//            System.out.println(adjustmentsLog.toString());
//        }
//
//        return true;
//    }
}