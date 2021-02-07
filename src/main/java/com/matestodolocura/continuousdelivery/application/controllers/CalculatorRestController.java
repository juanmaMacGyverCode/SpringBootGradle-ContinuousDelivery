package com.matestodolocura.continuousdelivery.application.controllers;

import com.matestodolocura.continuousdelivery.application.dto.DtoTwoNumbers;
import com.matestodolocura.continuousdelivery.domain.actions.CalculatorAction;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/calculator")
public class CalculatorRestController {

    private final CalculatorAction calculatorAction;

    @GetMapping(path = "/add/num1/{number1}/num2/{number2}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String addWithPathVariable(@PathVariable String number1, @PathVariable String number2){
        DtoTwoNumbers dtoTwoNumbers = new DtoTwoNumbers(number1, number2);
        return calculatorAction.add(dtoTwoNumbers);
    }

    @GetMapping(path = "/add")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String addWithRequestParam(@RequestParam String number1, @RequestParam String number2){
        return calculatorAction.add(new DtoTwoNumbers(number1, number2));
    }

    @PostMapping(path = "/add")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String addWithRequestBody(@RequestBody DtoTwoNumbers dtoTwoNumbers){
        return calculatorAction.add(dtoTwoNumbers);
    }
}
