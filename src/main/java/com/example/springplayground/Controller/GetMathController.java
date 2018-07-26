package com.example.springplayground.Controller;

import com.example.springplayground.Service.GetMathService;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class GetMathController {

    GetMathService getMathService;

    public GetMathController(){
        getMathService = new GetMathService();
    }

    @GetMapping("/pi")
    public String getPi(){
        return "3.141592653589793";
    }

    @GetMapping("/calculate")
    public String getCalculation(@RequestParam String operation, String x, String y)  {
       return getMathService.calculate(operation, x, y);
    }

    @GetMapping("/sum")
    public String getSum(@RequestParam LinkedMultiValueMap<String, String> paramMap){

        return getMathService.getSum(paramMap);
    }
}
