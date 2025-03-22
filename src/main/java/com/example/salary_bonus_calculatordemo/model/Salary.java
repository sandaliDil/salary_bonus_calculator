package com.example.salary_bonus_calculatordemo.model;

import javafx.beans.binding.BooleanExpression;

import java.time.LocalDate;

public class Salary {

        private int id;
        private int userId;
        private int branchId;
        private LocalDate salaryDate;
        private double bonus;

        // Constructor
        public Salary(int id, int userId, int branchId, LocalDate salaryDate, double salary) {
            this.id = id;
            this.userId = userId;
            this.branchId = branchId;
            this.salaryDate = salaryDate;
            this.bonus = bonus;
        }

        // Default Constructor
        public Salary() {}



        // Getters and Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getBranchId() {
            return branchId;
        }

        public void setBranchId(int branchId) {
            this.branchId = branchId;
        }

        public LocalDate getSalaryDate() {
            return salaryDate;
        }

        public void setSalaryDate(LocalDate salaryDate) {
            this.salaryDate = salaryDate;
        }

        public double getBonus() {
            return bonus;
        }

        public void setBonus(double bonus) {
            this.bonus = bonus;
        }

        @Override
        public String toString() {
            return "Salary{" +
                    "id=" + id +
                    ", userId=" + userId +
                    ", branchId=" + branchId +
                    ", salaryDate=" + salaryDate +
                    ", bonus=" + bonus +
                    '}';
        }



}
