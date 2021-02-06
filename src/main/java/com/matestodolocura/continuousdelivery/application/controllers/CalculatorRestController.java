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

    @GetMapping(path = "/add")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String add(@RequestBody DtoTwoNumbers dtoTwoNumbers){
        return calculatorAction.add(dtoTwoNumbers);
    }
}
