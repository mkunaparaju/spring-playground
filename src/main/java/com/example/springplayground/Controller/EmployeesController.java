package com.example.springplayground.Controller;

import com.example.springplayground.Model.Employee;
import com.example.springplayground.Model.Views;
import com.example.springplayground.crud.EmployeeRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeesController {
    private EmployeeRepository employeeRepository;
    public EmployeesController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @GetMapping("/admin/employees")
    @JsonView(Views.AllView.class)
    public Iterable<Employee> getAdminEmployees() {
        return employeeRepository.findAll();
    }
    @GetMapping("/employees")
    @JsonView(Views.CompactView.class)
    public Iterable<Employee> getEmployees() {
        return employeeRepository.findAll();
    }
}