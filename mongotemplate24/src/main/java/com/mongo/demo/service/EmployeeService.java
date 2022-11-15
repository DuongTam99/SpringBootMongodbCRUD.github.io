package com.mongo.demo.service;

import com.mongo.demo.model.Employee;
import com.mongo.demo.repository.EmployeeRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {
@Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee emp) {
        emp.setJoiningDate(new Date());
        return employeeRepository.insert(emp);
    }

    public List<Employee> getAll() {
        return employeeRepository.find();

    }

    public long update(Employee emp) {
        return employeeRepository.update(emp, emp.getId());
    }

    public long delete(Employee emp) {
        return employeeRepository.delete(emp);
    }

    public List<Employee> getBySalary(int salary) {
        return employeeRepository.findBySalary(salary);
    }

    public List<Employee> getByfirstName(String firstName) {
return employeeRepository.getByfirstName(firstName);

    }


    public Document countSalary() {
        return employeeRepository.countSalary();
    }
}