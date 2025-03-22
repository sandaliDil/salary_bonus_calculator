package com.example.salary_bonus_calculatordemo.repository;

import com.example.salary_bonus_calculatordemo.database.DatabaseConnection;
import com.example.salary_bonus_calculatordemo.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    public List<Employee> findByBranchIdAndPosition(int branchId) {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employee WHERE branch_id = ? ";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, branchId);


            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Employee employee = new Employee(
                            resultSet.getInt("id"),
                            resultSet.getString("user_name"),
                            resultSet.getString("position"),
                            resultSet.getInt("branch_id"),
                            resultSet.getBoolean("attendance") // Added attendance
                    );
                    employees.add(employee);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT id, branch_id, user_name, position, attendance FROM employee";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Employee employee = new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("user_name"),
                        resultSet.getString("position"),
                        resultSet.getInt("branch_id"),
                        resultSet.getBoolean("attendance") // Added attendance
                );
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

}
