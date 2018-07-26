package com.example.springplayground.Service;

import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;
import java.util.Map;

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

    public String getArea(Map<String,String> paramMap) {
        String type = paramMap.get("type");
        String finalString = "";

        if(type.equalsIgnoreCase("circle")) {
            Integer radius = Integer.parseInt(paramMap.get("radius"));
            Double pi = Math.PI;
            Double area = pi*radius*radius;
            finalString = String.format("Area of a circle with a radius of %d is %.5f", radius, area);
        }

        if(type.equalsIgnoreCase("rectangle")){
            int width = Integer.parseInt(paramMap.get("width"));
            int height = Integer.parseInt(paramMap.get("height"));
            finalString = String.format("Area of a %dx%d rectangle is %d", width,height, width*height);
        }
        return finalString;
    }
}
