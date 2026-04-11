package com.spring26.section2.group13.movieproductionhouse.akkhorik.controller;


import com.spring26.section2.group13.movieproductionhouse.akkhorik.model.*;
import com.spring26.section2.group13.movieproductionhouse.akkhorik.service.AlertHelper;
import com.spring26.section2.group13.movieproductionhouse.akkhorik.service.DataService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class ExecutiveController {

    @FXML private TableView<ProjectPitch> pitchesTable;
    @FXML private TableView<Budget> budgetsTable;
    @FXML private TextArea outputArea;

    public DataService dataService = new DataService();
    private ObservableList<ProjectPitch> pitchList = FXCollections.observableArrayList();
    private ObservableList<Budget> budgetList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        setupPitchesTableColumns();
        setupBudgetsTableColumns();
        refreshData();
    }

    private void setupPitchesTableColumns() {
        TableColumn<ProjectPitch, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<ProjectPitch, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<ProjectPitch, String> producerCol = new TableColumn<>("Producer");
        producerCol.setCellValueFactory(new PropertyValueFactory<>("producer"));
        TableColumn<ProjectPitch, Double> budgetCol = new TableColumn<>("Budget Est.");
        budgetCol.setCellValueFactory(new PropertyValueFactory<>("estimatedBudget"));
        TableColumn<ProjectPitch, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        pitchesTable.getColumns().setAll(idCol, titleCol, producerCol, budgetCol, statusCol);
    }

    private void setupBudgetsTableColumns() {
        TableColumn<Budget, String> projCol = new TableColumn<>("Project ID");
        projCol.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        TableColumn<Budget, Double> totalCol = new TableColumn<>("Total Budget");
        totalCol.setCellValueFactory(new PropertyValueFactory<>("totalBudget"));
        TableColumn<Budget, Double> spentCol = new TableColumn<>("Spent");
        spentCol.setCellValueFactory(new PropertyValueFactory<>("spent"));
        TableColumn<Budget, Double> remainCol = new TableColumn<>("Remaining");
        remainCol.setCellValueFactory(new PropertyValueFactory<>("remaining"));
        TableColumn<Budget, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        budgetsTable.getColumns().setAll(projCol, totalCol, spentCol, remainCol, statusCol);
    }

    @FXML
    private void reviewPitches() {
        ProjectPitch selected = pitchesTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            AlertHelper.showWarning("No Pitch Selected", "Please select a pitch to review.");
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Review Pitch");
        dialog.setHeaderText(selected.getTitle() + " by " + selected.getProducer());

        TextArea synopsisArea = new TextArea(selected.getSynopsis());
        synopsisArea.setEditable(false);
        synopsisArea.setPrefRowCount(3);
        synopsisArea.setWrapText(true);

        TextArea commentsArea = new TextArea();
        commentsArea.setPromptText("Enter comments...");
        Spinner<Integer> ratingSpinner = new Spinner<>(1, 10, 5);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Synopsis:"), 0, 0);
        grid.add(synopsisArea, 1, 0);
        grid.add(new Label("Rating (1-10):"), 0, 1);
        grid.add(ratingSpinner, 1, 1);
        grid.add(new Label("Comments:"), 0, 2);
        grid.add(commentsArea, 1, 2);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            selected.setRating(ratingSpinner.getValue());
            selected.setComments(commentsArea.getText());
            selected.setStatus("Reviewed");
            dataService.updatePitch(selected);
            outputArea.setText("Pitch reviewed. Producer notified.");
            refreshPitches();
        }
    }

    @FXML
    private void approveBudget() {
        ProjectPitch selected = pitchesTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            AlertHelper.showWarning("No Project Selected", "Please select a project from the table.");
            return;
        }

        Dialog<Budget> dialog = new Dialog<>();
        dialog.setTitle("Approve Budget");
        dialog.setHeaderText("Project: " + selected.getTitle());

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField castField = new TextField(String.valueOf(selected.getEstimatedBudget() * 0.3));
        TextField crewField = new TextField(String.valueOf(selected.getEstimatedBudget() * 0.2));
        TextField locationsField = new TextField(String.valueOf(selected.getEstimatedBudget() * 0.15));
        TextField vfxField = new TextField(String.valueOf(selected.getEstimatedBudget() * 0.2));
        TextField marketingField = new TextField(String.valueOf(selected.getEstimatedBudget() * 0.15));

        grid.add(new Label("Cast:"), 0, 0);
        grid.add(castField, 1, 0);
        grid.add(new Label("Crew:"), 0, 1);
        grid.add(crewField, 1, 1);
        grid.add(new Label("Locations:"), 0, 2);
        grid.add(locationsField, 1, 2);
        grid.add(new Label("VFX/Post:"), 0, 3);
        grid.add(vfxField, 1, 3);
        grid.add(new Label("Marketing:"), 0, 4);
        grid.add(marketingField, 1, 4);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                try {
                    double cast = Double.parseDouble(castField.getText());
                    double crew = Double.parseDouble(crewField.getText());
                    double locations = Double.parseDouble(locationsField.getText());
                    double vfx = Double.parseDouble(vfxField.getText());
                    double marketing = Double.parseDouble(marketingField.getText());

                    Budget budget = new Budget(UUID.randomUUID().toString().substring(0,8), selected.getId());
                    budget.addLineItem("Cast", cast);
                    budget.addLineItem("Crew", crew);
                    budget.addLineItem("Locations", locations);
                    budget.addLineItem("VFX/Post", vfx);
                    budget.addLineItem("Marketing", marketing);
                    budget.setStatus("Pending");

                    return budget;
                } catch (NumberFormatException e) {
                    AlertHelper.showError("Invalid Input", "All fields must be numeric.");
                    return null;
                }
            }
            return null;
        });

        Optional<Budget> result = dialog.showAndWait();
        result.ifPresent(budget -> {
            dataService.saveBudget(budget);
            selected.setApprovedBudget(budget.getTotalBudget());
            selected.setStatus("Greenlit");
            dataService.updatePitch(selected);
            outputArea.setText(String.format("Budget approved: $%.2f total", budget.getTotalBudget()));
            refreshBudgets();
        });
    }

    @FXML
    private void monitorProduction() {
        List<ProjectPitch> activeProjects = dataService.loadAllPitches().stream()
                .filter(p -> p.getStatus().equals("Greenlit"))
                .collect(Collectors.toList());

        if (activeProjects.isEmpty()) {
            outputArea.setText("No active productions found.");
            return;
        }

        StringBuilder sb = new StringBuilder("=== Active Productions ===\n\n");
        for (ProjectPitch p : activeProjects) {
            Budget budget = dataService.loadAllBudgets().stream()
                    .filter(b -> b.getProjectId().equals(p.getId()))
                    .findFirst().orElse(null);

            sb.append(String.format("Project: %s\n", p.getTitle()));
            sb.append(String.format("Producer: %s\n", p.getProducer()));
            if (budget != null) {
                sb.append(String.format("Budget: $%.2f | Spent: $%.2f | Remaining: $%.2f\n",
                        budget.getTotalBudget(), budget.getSpent(), budget.getRemaining()));
            }
            sb.append("---\n");
        }
        outputArea.setText(sb.toString());
    }

    @FXML
    private void analyzeTrends() {
        List<Film> films = dataService.loadFilms();
        List<ProjectPitch> pitches = dataService.loadAllPitches();

        StringBuilder sb = new StringBuilder("=== Market Trends Analysis ===\n\n");

        // Genre distribution
        sb.append("Genre Popularity (Active Projects):\n");
        pitches.stream()
                .collect(Collectors.groupingBy(ProjectPitch::getGenre, Collectors.counting()))
                .forEach((genre, count) -> sb.append(String.format("  %s: %d projects\n", genre, count)));

        sb.append("\nFilm Library by Rating:\n");
        films.stream()
                .collect(Collectors.groupingBy(Film::getRating, Collectors.counting()))
                .forEach((rating, count) -> sb.append(String.format("  %s: %d films\n", rating, count)));

        double avgBudget = pitches.stream().mapToDouble(ProjectPitch::getEstimatedBudget).average().orElse(0);
        sb.append(String.format("\nAverage Project Budget: $%.2f\n", avgBudget));

        outputArea.setText(sb.toString());
    }

    @FXML
    private void approveTalentDeals() {
        List<TalentDeal> deals = dataService.loadTalentDeals().stream()
                .filter(d -> d.getStatus().equals("Pending"))
                .collect(Collectors.toList());

        if (deals.isEmpty()) {
            AlertHelper.showInfo("No Pending Deals", "No talent deals pending approval.");
            return;
        }

        ChoiceDialog<TalentDeal> dialog = new ChoiceDialog<>(deals.get(0), deals);
        dialog.setTitle("Approve Talent Deal");
        dialog.setHeaderText("Select a deal to review");
        dialog.setContentText("Deal:");

        Optional<TalentDeal> result = dialog.showAndWait();
        result.ifPresent(deal -> {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Approve Talent Deal");
            confirm.setHeaderText("Deal for: " + deal.getTalentName() + " (" + deal.getRole() + ")");
            confirm.setContentText(String.format("Base Salary: $%.2f\nBackend Points: %.1f%%\nBreak-even Impact: $%.2f\n\nApprove this deal?",
                    deal.getBaseSalary(), deal.getBackendPoints(), deal.calculateBreakEvenImpact()));

            Optional<ButtonType> confirmResult = confirm.showAndWait();
            if (confirmResult.isPresent() && confirmResult.get() == ButtonType.OK) {
                deal.setStatus("Approved");
                dataService.updateTalentDeal(deal);
                outputArea.setText("Talent deal approved for " + deal.getTalentName());
            }
        });
    }

    @FXML
    private void manageMilestones() {
        List<ProjectPitch> greenlit = dataService.loadAllPitches().stream()
                .filter(p -> p.getStatus().equals("Greenlit"))
                .collect(Collectors.toList());

        if (greenlit.isEmpty()) {
            outputArea.setText("No greenlit projects to set milestones for.");
            return;
        }

        ChoiceDialog<ProjectPitch> dialog = new ChoiceDialog<>(greenlit.get(0), greenlit);
        dialog.setTitle("Manage Milestones");
        dialog.setHeaderText("Select a project");
        dialog.setContentText("Project:");

        Optional<ProjectPitch> result = dialog.showAndWait();
        result.ifPresent(project -> {
            Dialog<ButtonType> milestoneDialog = new Dialog<>();
            milestoneDialog.setTitle("Set Milestone");
            milestoneDialog.setHeaderText("Project: " + project.getTitle());

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            TextField milestoneField = new TextField();
            DatePicker datePicker = new DatePicker();

            grid.add(new Label("Milestone:"), 0, 0);
            grid.add(milestoneField, 1, 0);
            grid.add(new Label("Target Date:"), 0, 1);
            grid.add(datePicker, 1, 1);

            milestoneDialog.getDialogPane().setContent(grid);
            milestoneDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            Optional<ButtonType> milestoneResult = milestoneDialog.showAndWait();
            if (milestoneResult.isPresent() && milestoneResult.get() == ButtonType.OK) {
                outputArea.setText(String.format("Milestone '%s' set for %s on %s",
                        milestoneField.getText(), project.getTitle(), datePicker.getValue()));
            }
        });
    }

    @FXML
    private void reviewTestScreening() {
        List<ProjectPitch> projects = dataService.loadAllPitches().stream()
                .filter(p -> p.getStatus().equals("Greenlit") || p.getStatus().equals("Post-Production"))
                .collect(Collectors.toList());

        if (projects.isEmpty()) {
            outputArea.setText("No projects available for test screening.");
            return;
        }

        ChoiceDialog<ProjectPitch> dialog = new ChoiceDialog<>(projects.get(0), projects);
        dialog.setTitle("Test Screening Review");
        dialog.setHeaderText("Select a project");
        dialog.setContentText("Project:");

        Optional<ProjectPitch> result = dialog.showAndWait();
        result.ifPresent(project -> {
            // Simulate test screening results
            double rating = 5 + Math.random() * 4; // 5-9
            int recommend = 50 + (int)(Math.random() * 40); // 50-90%

            StringBuilder sb = new StringBuilder("=== Test Screening Results ===\n");
            sb.append("Project: ").append(project.getTitle()).append("\n");
            sb.append(String.format("Overall Rating: %.1f/10\n", rating));
            sb.append(String.format("Recommend %: %d%%\n", recommend));

            String[] criticisms = {"Pacing too slow in Act 2", "Character motivation unclear",
                    "Ending needs work", "Sound mixing issues"};
            String criticism = criticisms[(int)(Math.random() * criticisms.length)];
            sb.append("Top Criticism: ").append(criticism).append("\n\n");

            TextInputDialog directiveDialog = new TextInputDialog();
            directiveDialog.setTitle("Executive Directive");
            directiveDialog.setHeaderText("Enter directive for post-production team");
            directiveDialog.setContentText("Directive:");

            Optional<String> directiveResult = directiveDialog.showAndWait();
            directiveResult.ifPresent(directive -> {
                sb.append("Executive Directive: ").append(directive);
                outputArea.setText(sb.toString());
            });
        });
    }

    @FXML
    private void evaluateROI() {
        List<ProjectPitch> completedProjects = dataService.loadAllPitches().stream()
                .filter(p -> p.getStatus().equals("Greenlit") || p.getStatus().equals("Released"))
                .collect(Collectors.toList());

        if (completedProjects.isEmpty()) {
            outputArea.setText("No completed projects to evaluate.");
            return;
        }

        ChoiceDialog<ProjectPitch> dialog = new ChoiceDialog<>(completedProjects.get(0), completedProjects);
        dialog.setTitle("ROI Evaluation");
        dialog.setHeaderText("Select a project for post-mortem analysis");
        dialog.setContentText("Project:");

        Optional<ProjectPitch> result = dialog.showAndWait();
        result.ifPresent(project -> {
            double revenue = dataService.getTotalRevenueForFilm(project.getId());
            double cost = project.getApprovedBudget() > 0 ? project.getApprovedBudget() : project.getEstimatedBudget();
            double profit = revenue - cost;
            double roi = cost > 0 ? (profit / cost) * 100 : 0;

            StringBuilder sb = new StringBuilder("=== POST-MORTEM ROI ANALYSIS ===\n");
            sb.append("Project: ").append(project.getTitle()).append("\n");
            sb.append(String.format("Total Production Cost: $%.2f\n", cost));
            sb.append(String.format("Total Revenue (Est.): $%.2f\n", revenue));
            sb.append(String.format("Net Profit/Loss: $%.2f\n", profit));
            sb.append(String.format("ROI: %.2f%%\n", roi));

            if (profit > 0) {
                sb.append("\nVerdict: PROFITABLE - Sequel Potential");
            } else {
                sb.append("\nVerdict: LOSS - Review marketing strategy");
            }

            outputArea.setText(sb.toString());
        });
    }

    @FXML
    private void refreshData() {
        refreshPitches();
        refreshBudgets();
        outputArea.clear();
    }

    private void refreshPitches() {
        pitchList.setAll(dataService.loadAllPitches());
        pitchesTable.setItems(pitchList);
    }

    private void refreshBudgets() {
        budgetList.setAll(dataService.loadAllBudgets());
        budgetsTable.setItems(budgetList);
    }

    @FXML
    private void backToMain() {
        Stage stage = (Stage) pitchesTable.getScene().getWindow();
        stage.close();
    }
}