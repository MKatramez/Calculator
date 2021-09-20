package com.example.calculator;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/")
public class CalculatorController {

    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorController(CalculatorService calculatorService){
        this.calculatorService = calculatorService;
    }

    @GetMapping
    public List<Calculator> getCalculation(){
        return calculatorService.getCalculations();
    }

    @GetMapping(path = "calculate")
    @ResponseBody
    public double calculation(@RequestParam(value = "calculation", defaultValue = "")String calculation){
        return calculatorService.analyseCalculation(calculation);
    }

    @PostMapping(path = "save/")
    public void registerNewCalculation(@RequestBody Calculator calculation){
        calculatorService.addNewCalculation(calculation);
    }

    @PostMapping(path = "saveAndCalculate")
    @ResponseBody
    public double saveAndCalculation(
            @RequestParam(value = "id", defaultValue = "0")Long id
            ,@RequestParam(value = "calculation", defaultValue = "")String calculation){
        calculatorService.addNewCalculation(new Calculator(id,calculation));
        return calculatorService.analyseCalculation(calculation);
    }

}
