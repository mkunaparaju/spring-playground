package com.example.springplayground.Controller;

import com.example.springplayground.Service.GetMathService;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/volume/{length}/{width}/{height}")
    public String getVolume(@PathVariable Integer length, @PathVariable Integer width, @PathVariable Integer height){
        return String.valueOf(length*height*width);
    }

}
