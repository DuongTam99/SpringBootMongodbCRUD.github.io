package com.mongo.demo.controller;

import com.mongo.demo.model.Employee;
import com.mongo.demo.service.EmployeeService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/")
    public ResponseEntity save(@RequestBody Employee emp) {
        Employee savedEmployee = employeeService.save(emp);
        Map<String, Object> responseBody = new HashMap<>();
        if (savedEmployee == null) {

            responseBody.put("status", 0);
            responseBody.put("message", "Can not Post employee");
            return ResponseEntity.status(400).body(responseBody);
        }

        responseBody.put("status", 1);
        responseBody.put("data", savedEmployee);
        return ResponseEntity.status(200).body(responseBody);

    }

    @GetMapping("/")
    public ResponseEntity getALL() {
        List<Employee> findAllEmployee = employeeService.getAll();
        Map<String, Object> responseBody = new HashMap<>();
        if (findAllEmployee == null) {

            responseBody.put("status", 0);
            responseBody.put("message", "employee not null");
            return ResponseEntity.status(400).body(responseBody);
        }

        responseBody.put("status", 1);
        responseBody.put("data", findAllEmployee);
        return ResponseEntity.status(200).body(responseBody);
    }

    @PutMapping("/")
    public ResponseEntity update(@RequestBody Employee emp) {
        long statusUpdate = employeeService.update(emp);
        Map<String, Object> responseBody = new HashMap<>();
        if (statusUpdate == 123) {

            responseBody.put("status", 0);
            responseBody.put("message", "employee not null");
            return ResponseEntity.status(400).body(responseBody);
        }

        responseBody.put("status", 1);
        responseBody.put("data", statusUpdate);
        return ResponseEntity.status(200).body(responseBody);
    }

    @DeleteMapping("/")
    public ResponseEntity delete(@RequestBody Employee emp) {
        Long countDelete = employeeService.delete(emp);
        Map<String, Object> responseBody = new HashMap<>();
        if (countDelete == null) {

            responseBody.put("status", 0);
            responseBody.put("message", "employee not null");
            return ResponseEntity.status(400).body(responseBody);
        }

        responseBody.put("status", 1);
        responseBody.put("data", countDelete);
        return ResponseEntity.status(200).body(responseBody);
    }

    @GetMapping("/salary")
    public ResponseEntity getBySalary(@PathParam("salary") int salary) {
        List<Employee> employee = employeeService.getBySalary(salary);


        Map<String, Object> responseBody = new HashMap<>();
        if (employee == null) {

            responseBody.put("status", 0);
            responseBody.put("message", "employee not null");
            return ResponseEntity.status(400).body(responseBody);
        }

        responseBody.put("status", 1);
        responseBody.put("data", employee);
        return ResponseEntity.status(200).body(responseBody);
    }

    @GetMapping("/firstname")
    public ResponseEntity getByfirstName(@PathParam("firstname") String firstName) {
        List<Employee> employee = employeeService.getByfirstName(firstName);
        Map<String, Object> responseBody = new HashMap<>();
        if (employee == null) {

            responseBody.put("status", 0);
            responseBody.put("message", "employee not null");
            return ResponseEntity.status(400).body(responseBody);
        }

        responseBody.put("status", 1);
        responseBody.put("data", employee);
        return ResponseEntity.status(200).body(responseBody);

    }

    @GetMapping("/countSalary")
    public ResponseEntity countSalary() {
        Document doc = employeeService.countSalary();
        Map<String, Object> responseBody = new HashMap<>();
        if (doc == null) {

            responseBody.put("status", 0);
            responseBody.put("message", "employee not null,cannot count salary");
            return ResponseEntity.status(400).body(responseBody);
        }

        responseBody.put("status", 1);
        responseBody.put("data", doc);
        return ResponseEntity.status(200).body(responseBody);


    }


}




