package com.rokwonkk.springboot.cruddemo.rest;


import com.rokwonkk.springboot.cruddemo.dao.EmployeeDAO;
import com.rokwonkk.springboot.cruddemo.entity.Employee;
import com.rokwonkk.springboot.cruddemo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private final EmployeeService service;

    // quick and dirty: inject employee dao ( use constructor injection )
    public EmployeeRestController(EmployeeService service) {
        this.service = service;
    }

    // expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return service.findAll();
    }

    // add mapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employteeId}")
    public Employee getEmployeeById(@PathVariable int employeeId) {
        Employee employee = service.findById(employeeId);
        if (employee == null) {
            throw new RuntimeException("Employee id not found: " + employeeId);
        }
        return employee;
    }

    // add mapping for POST /employees - add new employee
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        employee.setId(0);

        return service.save(employee);
    }

    // add mapping for PUT /employees - update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return service.save(employee);
    }

    // add mapping for DELETE /employees/{employeeId} - delete employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee deleteEmployee = service.findById(employeeId);
        if(deleteEmployee == null) {
            throw new RuntimeException("Employee id not found: " + employeeId);
        }
        service.deleteById(employeeId);
        return "Deleted employee id: " + employeeId;
    }
}









