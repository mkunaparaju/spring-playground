package com.example.springplayground.Service;

import com.example.springplayground.Model.Area;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class GetMathServiceTest {

    private GetMathService mathService;
    private LinkedMultiValueMap<String, String> valueMap;
    private Area area;

    @Before
    public void setUp(){
        mathService = new GetMathService();
        valueMap = new LinkedMultiValueMap<String, String>();
        area = new Area();
    }

    @Test
    public void testCalculate_whenAdd_thenReturnCorrectValue() {
        String actual = mathService.calculate("add", "4", "6");
        String expected = "10";

        assertEquals("Addition of 4 + 6 = 10", expected,actual);
    }

    @Test
    public void testCalculate_whenSub_thenReturnCorrectValue() {
        String actual = mathService.calculate("subtract", "8", "6");
        String expected = "2";

        assertEquals("Subtraction of 8 - 6 = 2", expected,actual);
    }

    @Test
    public void testCalculate_whenMult_thenReturnCorrectValue() {
        String actual = mathService.calculate("multiply", "4", "6");
        String expected = "24";

        assertEquals("Multiplication of 4 * 6 = 24", expected,actual);

    }

    @Test
    public void testCalculate_whenDiv_thenReturnCorrectValue() {
        String actual = mathService.calculate("divide", "24", "6");
        String expected = "4";

        assertEquals("Division of 24 * 6 = 4", expected,actual);

    }

    @Test
    public void testCalculate_whenInvalidInput_thenReturnInvalid() {
        String actual = mathService.calculate("div", "24", "6");
        String expected = "nothing is working";

        assertEquals("Invalid Input", expected,actual);
    }


    @Test
    public void testGetSum_whenInput_thenReturnSumInput() {
        valueMap.add("n","4");
        valueMap.add("n","5");
        valueMap.add("n","6");

        String actual = mathService.getSum(valueMap);
        String expected = "15";

        assertEquals("Sum of 4+5+6 = 15", expected,actual);
    }

    @Test
    public void testGetArea_whenCircleInput_thenReturnArea() {
        area.setType("circle");
        area.setRadius("4");

        String actual = mathService.getArea(area);
        String expected = "Area of a circle with a radius of 4 is 50.26548";

        assertEquals("Area of circle", expected,actual);
    }

    @Test
    public void testGetArea_whenRectangleInput_thenReturnArea() {
        area.setType("rectangle");
        area.setWidth("4");
        area.setHeight("7");

        String actual = mathService.getArea(area);
        String expected = "Area of a 4x7 rectangle is 28";

        assertEquals("Area of rectangle", expected,actual);
    }
}