package com.example.salary_bonus_calculatordemo.repository;

import com.example.salary_bonus_calculatordemo.database.DatabaseConnection;
import com.example.salary_bonus_calculatordemo.model.Branch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchRepository {


    public List<Branch> getAllBranches() {
        List<Branch> branches = new ArrayList<>();
        String query = "SELECT id, branch_name FROM branch";  // Ensure table and column names match your DB

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Process the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String branchName = resultSet.getString("branch_name");

                Branch branch = new Branch(id, branchName);
                branches.add(branch);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception
        }

        return branches;
    }

    public int getBranchIdByName(String branchName) {
        String query = "SELECT id FROM branch WHERE branch_name = ?";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, branchName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Return -1 if branch is not found
    }


}
