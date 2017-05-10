package com.jpmg;

import java.math.*;
import java.nio.file.*;
import java.time.*;
import java.util.*;

import com.jpmg.common.*;
import com.jpmg.core.*;

public class Main {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Incorrect number of arguments. Please refer to README.md file.");
            System.exit(1);
        }

        if(isInvalidFilePath(args[0])) {
            System.out.println("Given file path(s) is (/are) either incorrect or inaccessible. Please confirm and re-run.");
            System.exit(1);
        }

        String stockDataFile = args[0];

        MarketReport market = MarketReport.getSalesEngine();

        boolean initialized = market.initialize(stockDataFile);
        if(!initialized) {
            System.out.println("Stock initialization failed. Check console. Inform developer.");
            System.exit(1);
        }

        Map<LocalDate,Map<String,BigDecimal>> lReport = market.amountProcess();
        if(lReport == null) {
            System.out.println("Instructions reports' process failed. Check console. Inform developer.");
            System.exit(1);
        }
        
        
        market.entityRanking();
        System.out.println("Amount in USD settled' per day (incoming/outgoing)");
        market.printingAmounts(lReport);
        System.out.println("Ranking of entities");
        List<Instruction> lIns = market.entityRanking();
        market.printingSettled(lIns);
        
    }

    private static boolean isInvalidFilePath(String filePath) {
        try {
            Path path = Paths.get(filePath);

            if(!Files.exists(path) || Files.notExists(path)) { //either does not exist or status is unknown
                return true;
            }

            if(!Files.isRegularFile(path)) { //an executable or directory
                return true;
            }

            if(!Files.isReadable(path)) { //not allowed to read (at least at this moment)
                return true;
            }
        } catch (InvalidPathException | NullPointerException exception) {
            return true; //raised an exception
        }

        return false;
    }
}
