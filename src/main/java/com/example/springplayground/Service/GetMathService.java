package com.example.springplayground.Service;

import com.example.springplayground.Model.Area;
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

    public String getArea(Area paramBody) {
        String type = paramBody.getType();
        String finalString = "";

        if (type.equalsIgnoreCase("circle")){
            if (null == paramBody.getRadius() ) return "INVALID";
            if (null != paramBody.getHeight() || null != paramBody.getWidth() ) return "INVALID";

            Integer radius = Integer.parseInt(paramBody.getRadius());
            Double pi = Math.PI;
            Double area = pi*radius*radius;

            finalString = String.format("Area of a circle with a radius of %d is %.5f", radius, area);

        }

        else if(type.equalsIgnoreCase("rectangle")){
            if (null == paramBody.getHeight() || null == paramBody.getWidth() ) return "INVALID";
            if (null != paramBody.getRadius() ) return "INVALID";

            int width = Integer.parseInt(paramBody.getWidth());
            int height = Integer.parseInt(paramBody.getHeight());

            finalString = String.format("Area of a %dx%d rectangle is %d", width,height, width*height);
        }

        return finalString;
    }
}
