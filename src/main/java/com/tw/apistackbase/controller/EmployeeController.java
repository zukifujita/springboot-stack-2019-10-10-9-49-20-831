package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private List<Employee> employeeList = new ArrayList<>();
    private final Logger log = Logger.getLogger(this.getClass().getName());

    @GetMapping(path = "/search", produces = "appli cation/json")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeList);
    }

    @GetMapping(path = "/search/{id}", produces = "application/json")
    public ResponseEntity<Stream<Employee>> getEmployeesById(@PathVariable Integer id) {
        return ResponseEntity.ok(employeeList.stream().filter(element -> element.getId().equals(id)));
    }

    @PostMapping(path = "/add", consumes = "application/json")
    public ResponseEntity<List<Employee>> createEmployee(@RequestBody Employee employeePost) {
        employeeList.add(employeePost);
        return ResponseEntity.ok(employeeList);
    }

    @PutMapping(path = "/change/{id}")
    public ResponseEntity<List<Employee>> putEmployee(@PathVariable Integer id, @RequestBody Employee employeePut) {
        Employee employee = employeeList.stream().filter(element -> element.getId().equals(id)).findFirst().orElse(null);

        employee.setName(employeePut.getName());
        employee.setAge(employeePut.getAge());
        employee.setGender(employeePut.getGender());

        return ResponseEntity.ok(employeeList);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<List<Employee>> deleteEmployee(@PathVariable Integer id) {
        Employee employee = employeeList.stream().filter(element -> element.getId().equals(id)).findFirst().orElse(null);
        employeeList.remove(employee);
        return ResponseEntity.ok(employeeList);
    }
}
