package com.example.springplayground.Controller;

import com.example.springplayground.Controller.QueryStringController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(QueryStringController.class)
public class QueryStringControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void testGetIndividualParams() throws Exception {
        this.mvc.perform(get("/individual-example?filter=1987"))
                .andExpect(status().isOk())
                .andExpect(content().string("Filter is : 1987"));
    }

    @Test
    public void testGetCustomizedQueryParams() throws Exception {
        this.mvc.perform(get("/customized?sort-by=name&sort-dir=inc"))
                .andExpect(status().isOk())
                .andExpect(content().string("sort-by value: name and sort-dir value: inc"));
    }

    @Test
    public void testGetMapParams() throws Exception {
        this.mvc.perform(get("/map-example?sort-by=name&sort-dir=inc"))
                .andExpect(status().isOk())
                .andExpect(content().string("{sort-by=name, sort-dir=inc}"));

    }

    @Test
    public void testGetTaskInfo() throws Exception {
        this.mvc.perform(get("/taskInfo?sortBy=name&owner=Sally"))
                .andExpect(status().isOk())
                .andExpect(content().string("sortBy is name; owner is Sally"));

    }

}