package com.matestodolocura.continuousdelivery.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DtoTwoNumbers {
    private final String number1;
    private final String number2;
}



