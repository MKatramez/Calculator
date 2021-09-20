package com.example.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.Character.isDigit;

@Service
public class CalculatorService {

    private final CalculatorRepository calculatorRepository;

    @Autowired
    public CalculatorService(CalculatorRepository calculatorRepository){
        this.calculatorRepository = calculatorRepository;
    }

    public double analyseCalculation(String calculation){

        StringBuilder newCalc = new StringBuilder();
        StringBuilder leftString = new StringBuilder();
        StringBuilder rightString = new StringBuilder();

        if (calculation != null && !calculation.equals("")){
            //String input filter just numbers will be saved
            for (int i = 0;i<calculation.length(); i++){
                if(isDigit(calculation.charAt(i)) || 'u' == calculation.charAt(i) || '-' == calculation.charAt(i)
                        || '*' == calculation.charAt(i) || '/' == calculation.charAt(i)){
                    newCalc.append(calculation.charAt(i));
                }else{
                    throw new CalculatorException();
                }
            }
            //left and right from symbol "222+222"
            for (int i=0; i < newCalc.length();i++){
                if (newCalc.charAt(i) == 'u'|| newCalc.charAt(i) == '-'
                        || newCalc.charAt(i) == '*' || newCalc.charAt(i) == '/') {

                    for (int y = 0; y < i; y++) {
                        leftString.append(newCalc.charAt(y));
                    }
                    for (int e = i+1; e < newCalc.length(); e++) {
                        rightString.append(newCalc.charAt(e));
                    }

                    double numL = Integer.parseInt(leftString.toString());
                    double numR = Integer.parseInt(rightString.toString());

                    if ('u' == newCalc.charAt(i)) {
                        return numL + numR;
                    }
                    if ('-' == newCalc.charAt(i)) {
                        return numL - numR;
                    }
                    if ('*' == newCalc.charAt(i)) {
                        return numL * numR;
                    }
                    if ('/' == newCalc.charAt(i)) {
                        return numL / numR;
                    }
                }
            }
        }else{
            throw new CalculatorException();
        }
        return Integer.parseInt(newCalc.toString());

    }

    public List<Calculator> getCalculations() {
        return calculatorRepository.findAll();
    }

    public void addNewCalculation(Calculator calculator) {
        Optional<Calculator> calcOptional = calculatorRepository
                .findCalculationById(calculator.getId());
        if (calcOptional.isPresent()) {
            throw new IllegalStateException("Calculation is already in DB");
        }
        calculatorRepository.save(calculator);
    }

}
