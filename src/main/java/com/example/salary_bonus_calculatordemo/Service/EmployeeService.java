package com.example.salary_bonus_calculatordemo.Service;

import com.example.salary_bonus_calculatordemo.model.Employee;
import com.example.salary_bonus_calculatordemo.repository.EmployeeRepository;

import java.util.List;

public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService() {
        this.employeeRepository = new EmployeeRepository();
    }

    public List<Employee> getEmployeesByBranchAndPosition(int branchId) {
        return employeeRepository.findByBranchIdAndPosition(branchId);
    }
}
