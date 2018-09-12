package com.example.springplayground.Controller;

import com.example.springplayground.Config.SecurityConfig;
import com.example.springplayground.crud.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeesController.class)
@Import({SecurityConfig.class, EmployeeRepository.class})
public class EmployeesControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    EmployeeRepository employeeRepository;
    @Before
    public void before() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList());
    }
    @Test
    public void testAccessAdmin() throws Exception {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList());
        RequestBuilder request = get("/admin/employees").with(user("boss").roles("ADMIN"));
        mvc.perform(request).andExpect(status().isOk());
    }

    @Test
    public void testAccessUser() throws Exception {
        RequestBuilder request = get("/admin/employees")
                .with(user("boss").roles("USER"));
        mvc.perform(request).andExpect(status().isForbidden());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    public void testAccessAdminMock() throws Exception {
        RequestBuilder request = get("/admin/employees");
        mvc.perform(request).andExpect(status().isOk());
    }
    @Test
    public void testAnonymousUsersUnAuthorized() throws Exception {
        RequestBuilder request = get("/admin/employees").with(anonymous());
        mvc.perform(request).andExpect(status().isUnauthorized());
    }
}