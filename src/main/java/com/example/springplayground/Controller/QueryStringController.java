package com.example.springplayground.Controller;

import com.example.springplayground.Model.TaskInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class QueryStringController {

    @GetMapping("/individual-example")
    public String getIndividualParams(@RequestParam String filter) {
        return String.format("Filter is : %s", filter);
    }

    @GetMapping("/customized")
    public String getCustomizedParams(@RequestParam("sort-by") String sortBy, @RequestParam(value = "sort-dir") String sortDir) {
        return String.format("sort-by value: %s and sort-dir value: %s", sortBy, sortDir);
    }

    @GetMapping("/vehicle")
    public String myCoolMethodOne(@RequestParam(required = false) String type) {
        return type;
    }

    @GetMapping("/other")
    public String myCoolMethodTwo(@RequestParam(value = "type", defaultValue = "car") String type) {
        return type;
    }

    @GetMapping("/map-example")
    public String getMapParams(@RequestParam Map querystring) {
        return querystring.toString();
    }

    @GetMapping("/taskInfo")
    public String getTaskInfo(TaskInfo taskinfo) {
        return String.format("sortBy is %s; owner is %s", taskinfo.getSortBy(), taskinfo.getOwner());
    }
}
