package com.example.salary_bonus_calculatordemo.controller;

import com.example.salary_bonus_calculatordemo.Service.BranchService;
import com.example.salary_bonus_calculatordemo.Service.EmployeeService;
import com.example.salary_bonus_calculatordemo.model.Employee;
import com.example.salary_bonus_calculatordemo.model.Salary;
import com.example.salary_bonus_calculatordemo.repository.EmployeeRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.CheckBoxTableCell;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;

import java.awt.Label;
import java.time.LocalDate;
import java.util.List;

public class CalculateController {

    @FXML
    private ComboBox<String> branchComboBox;

    private BranchService branchService;

    @FXML
    private TableColumn<Salary, String> bonusColumn;

    @FXML
    private TextField incomeTextField;

    @FXML
    private TableView<Employee> employeeTableView;

    @FXML
    private TableColumn<Employee, Integer> idColumn;

    @FXML
    private TableColumn<Employee, String> userNameColumn;

    @FXML
    private TableColumn<Employee, String> positionColumn;

    @FXML
    private TableColumn<Employee, Void> positionSelectionColumn;
    @FXML
    private Button calculateButton;

    @FXML
    private TableColumn<Employee, Boolean> attendanceColumn; // New column for Attendance

    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepo;
    private ObservableList<Employee> employeeList;

    public CalculateController() {
        this.employeeService = new EmployeeService();
        this.employeeRepo = new EmployeeRepository();
    }

    public void initialize() {
        branchService = new BranchService();
        setupTable();
        populateBranchComboBox();
        filterEmployeesByBranch();
        calculateButton.setOnAction(event -> calculateBonus());
    }

    private void populateBranchComboBox() {
        List<Employee> employees = employeeRepo.getAllEmployees();

        ObservableList<String> branchNames = FXCollections.observableArrayList();
        for (Employee employee : employees) {
            String branchName = "Branch " + employee.getBranchId();
            if (!branchNames.contains(branchName)) {
                branchNames.add(branchName);
            }
        }
        branchComboBox.setItems(branchNames);
    }

    private void filterEmployeesByBranch() {
        branchComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                int selectedBranchId = Integer.parseInt(newValue.replace("Branch ", ""));
                List<Employee> filteredEmployees = employeeRepo.findByBranchIdAndPosition(selectedBranchId);
                employeeList.setAll(filteredEmployees);
            }
        });
    }


    private void setupTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));

        attendanceColumn.setCellValueFactory(cellData -> cellData.getValue().attendanceProperty());
        attendanceColumn.setCellFactory(CheckBoxTableCell.forTableColumn(attendanceColumn));

        employeeTableView.setEditable(true);

        // Position selection column setup
        positionSelectionColumn.setCellFactory(tc -> new TableCell<Employee, Void>() {
            private final CheckBox cashierCheckBox = new CheckBox("Cashier");
            private final CheckBox employeeCheckBox = new CheckBox("Employee");
            private final HBox hBox = new HBox(10, cashierCheckBox, employeeCheckBox);

            {
                cashierCheckBox.setOnAction(event -> {
                    if (cashierCheckBox.isSelected()) {
                        employeeCheckBox.setSelected(false);
                        updateAttendance(true);
                    }
                });

                employeeCheckBox.setOnAction(event -> {
                    if (employeeCheckBox.isSelected()) {
                        cashierCheckBox.setSelected(false);
                        updateAttendance(false);
                    }
                });
            }

            private void updateAttendance(boolean isPresent) {
                Employee employee = getTableView().getItems().get(getIndex());
                if (employee != null) {
                    employee.setAttendance(isPresent);
                }
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(hBox);
                    Employee employee = getTableView().getItems().get(getIndex());
                    if (employee != null) {
                        cashierCheckBox.setSelected(employee.isAttendance());
                        employeeCheckBox.setSelected(!employee.isAttendance());
                    }
                }
            }
        });


        // Load Employees
        employeeList = FXCollections.observableArrayList(employeeRepo.getAllEmployees());
        employeeTableView.setItems(employeeList);

    }


    @FXML
    private void calculateBonus() {

        if (incomeTextField.getText().isEmpty()) {
            showAlert("Error", "Please enter total income.");
            return;
        }

        try {
            double totalIncome = Double.parseDouble(incomeTextField.getText());
            double bonusPool = totalIncome * 0.01; // 1% of total income

            // Count employees who are present
            long presentCount = employeeList.stream()
                    .filter(Employee::isAttendance)
                    .count();

            if (presentCount == 0) {
                showAlert("Info", "No employees are present.");
                return;
            }

            double bonusPerEmployee = bonusPool / (presentCount + 1);

            // Insert bonus into the Salary table
            for (Employee employee : employeeList) {
                if (employee.isAttendance()) {
                    double bonus = "Cashier".equals(employee.getPosition()) ? bonusPerEmployee * 2 : bonusPerEmployee;
                    double roundedBonus = Math.floor(bonus / 50) * 50; // Round down to nearest multiple of 50

                    // Create a Salary object and store it
                    Salary salaryRecord = new Salary();
                    salaryRecord.setUserId(employee.getId());
                    salaryRecord.setBranchId(employee.getBranchId());
                    salaryRecord.setSalaryDate(LocalDate.now());
                    salaryRecord.setBonus(roundedBonus);

                    // Save to database (implement saveSalary method)
                    saveSalary(salaryRecord);
                }
            }

            showAlert("Success", "Bonus calculated and stored successfully!");

        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid income value.");
        }
    }

    private void saveSalary(Salary salaryRecord) {
    }


    // Helper method to show alerts
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
