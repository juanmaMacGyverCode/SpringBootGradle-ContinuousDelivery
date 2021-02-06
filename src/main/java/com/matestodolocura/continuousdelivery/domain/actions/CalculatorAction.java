package com.matestodolocura.continuousdelivery.domain.actions;

import com.matestodolocura.continuousdelivery.application.dto.DtoTwoNumbers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculatorAction {

    public String add(DtoTwoNumbers dtoTwoNumbers){
        return String.valueOf(Integer.parseInt(dtoTwoNumbers.getNumber1()) + Integer.parseInt(dtoTwoNumbers.getNumber2()));
    }
}

