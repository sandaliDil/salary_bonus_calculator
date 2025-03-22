package com.example.salary_bonus_calculatordemo.Service;

import com.example.salary_bonus_calculatordemo.database.DatabaseConnection;
import com.example.salary_bonus_calculatordemo.model.Branch;
import com.example.salary_bonus_calculatordemo.repository.BranchRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BranchService {
    private final BranchRepository branchRepository;

    public BranchService() {
        this.branchRepository = new BranchRepository();
    }

    public List<Branch> getAllBranches() {
        return branchRepository.getAllBranches();
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
