package com.jpmg.core;

import java.io.*;
import java.math.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;
import java.util.*;
import java.util.stream.*;

import com.jpmg.common.*;
import com.jpmg.common.Currency;

public class MarketReport {
    private static MarketReport marketEngine = new MarketReport();

    private List<Instruction> lInstruction;

    private MarketReport() {
    }

    public static MarketReport getSalesEngine() {
        return marketEngine;
    }
    /**
     * Method in charge of initializing the main listing 
     * @param instructionFile
     * @return
     */
    public boolean initialize(String instructionFile) {
            List<List<String>> intrucs = new ArrayList<List<String>>();
            lInstruction = new ArrayList<Instruction>();
            Locale.setDefault(Locale.ENGLISH);
	        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("dd MMM yyyy");
            try (Stream<String> instructions = Files.lines(Paths.get(instructionFile))) {
    			intrucs = instructions.map(instruction -> Arrays.asList(instruction.split(","))).skip(1).collect(Collectors.toList());
    			intrucs.forEach(ins -> {
    				LocalDate insDate = LocalDate.parse(ins.get(4),dTF);	
    				LocalDate settDate = LocalDate.parse(ins.get(5),dTF);
    				

    		        Instruction instruction =
    				new Instruction(new Entity(ins.get(0).toUpperCase()), 
    					BuySellFlag.valueOf(BuySellFlag.class,ins.get(1).toUpperCase().trim()), 
    					BigDecimal.valueOf(Double.parseDouble(ins.get(2))),
    					new Currency(ins.get(3)), 
    					insDate,
    					settDate,
    					Integer.parseInt(ins.get(6)), 
    					BigDecimal.valueOf(Double.parseDouble(ins.get(7))));
    		        
    		        instruction.setAdjSettlementDate(workingDayAdjuster(settDate, ins.get(3).toString()));
    		        instruction.setAmountTrade(instruction.getPricePUnit().multiply(new BigDecimal(instruction.getiUnits()).multiply(instruction.getbAgreed())));
    		        lInstruction.add(instruction);
            });
    			
    		} catch (IOException e) {
    			e.printStackTrace();
    			System.out.println("There is no enough fields to process.");
    		}
            

        return true;
    }
    /**
     * 
     * @param localDate
     * @param currency
     * @return
     */
    private LocalDate workingDayAdjuster(LocalDate localDate, String currency) {
    	DayOfWeek firstDayOfWeekEnd;
    	DayOfWeek secondDayOfWeekEnd;
    	 if(currency.equals("AED") || currency.equals("SAR")){
    		 firstDayOfWeekEnd = DayOfWeek.FRIDAY;
    		 secondDayOfWeekEnd = DayOfWeek.SATURDAY;
    	 }else{
    		 firstDayOfWeekEnd = DayOfWeek.SATURDAY;
    		 secondDayOfWeekEnd = DayOfWeek.SUNDAY;
    	 }
		 TemporalAdjuster workingDayAdjuster = TemporalAdjusters.ofDateAdjuster(ld -> {
		     DayOfWeek dayOfWeek = localDate.getDayOfWeek();
		     if (dayOfWeek == firstDayOfWeekEnd) {
		         return ld.plusDays(2);
		     } else if (dayOfWeek == secondDayOfWeekEnd) {
		         return ld.plusDays(1);
		     }
		     return ld.plusDays(0);
		 });
		 return localDate.with(workingDayAdjuster);
     }



    public Map<LocalDate,Map<String,BigDecimal>> amountProcess(){
    	Map<LocalDate,Map<String,BigDecimal>> report = new HashMap<LocalDate,Map<String,BigDecimal>>();
    	
    	lInstruction.forEach(i ->{
    		Map<String,BigDecimal> newEntry = new HashMap<String, BigDecimal>();
    		report.computeIfPresent(i.getAdjSettlementDate(), (key,value)-> buySellFlagProcessor(value,i.getBsf().toString(),i.getAmountTrade()));
    		newEntry.put(i.getBsf().toString(), i.getAmountTrade());
    		report.putIfAbsent(i.getAdjSettlementDate(), newEntry );
    	});
    	
    	return report;
    }
    /**
     * 
     * @return
     */
    public List<Instruction> entityRanking(){
    	 List<Instruction> lins = lInstruction.stream().sorted(Comparator.comparing(Instruction::getBsf).thenComparing(Instruction::getAmountTrade).reversed()).collect(Collectors.toList());
    	return lins;
    }
    /**
     * 
     * @param existing
     * @param bsf
     * @param amount
     * @return
     */
    private Map<String,BigDecimal> buySellFlagProcessor(Map<String,BigDecimal> existing, String bsf, BigDecimal amount){
    	existing.computeIfPresent(bsf, (key,value)-> value.add(amount));
    	existing.putIfAbsent(bsf, amount );
    	return existing;
    }
    
    public void printingSettled(List<Instruction> lins){
    	lins.forEach(e -> System.out.println("Id:"+ e.getEntity().getName()+", B/S:"+e.getBsf()+", Amount:"+e.getAmountTrade()));
    }
    
    public void printingAmounts(Map<LocalDate,Map<String,BigDecimal>> lAmounts){
    	
    	lAmounts.forEach((k,v) ->{
//    		System.out.print(k);
    		v.forEach((key,value) -> System.out.println(k+"  - "+key+" - "+value));
    	});
    }
}