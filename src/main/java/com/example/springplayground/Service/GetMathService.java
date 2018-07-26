package com.example.springplayground.Service;

import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;

@Component
public class GetMathService {

    public String calculate(String operation, String x, String y){
        if(operation.equalsIgnoreCase("add")) return String.valueOf(Integer.parseInt(x) + Integer.parseInt(y)) ;
        if(operation.equalsIgnoreCase("subtract")) return String.valueOf(Integer.parseInt(x) - Integer.parseInt(y));
        if(operation.equalsIgnoreCase("multiply")) return String.valueOf(Integer.parseInt(x) * Integer.parseInt(y));
        if(operation.equalsIgnoreCase("divide")) return String.valueOf(Integer.parseInt(x) / Integer.parseInt(y));
        return "nothing is working";
    }

    public String getSum(LinkedMultiValueMap<String,String> paramMap) {
        Integer sum = 0;
        List<String> inputValues = paramMap.get("n");
        for(String value : inputValues){
            sum += Integer.parseInt(value);
        }
        return String.valueOf(sum);
    }
}
