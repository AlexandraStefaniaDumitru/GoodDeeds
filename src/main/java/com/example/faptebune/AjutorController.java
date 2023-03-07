package com.example.faptebune;

import com.example.faptebune.domain.Nevoie;
import com.example.faptebune.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AjutorController {

    private final ObservableList<Nevoie> nevoieModel = FXCollections.observableArrayList();

    private final ObservableList<Nevoie> fapteModel = FXCollections.observableArrayList();
    Service service;
    @FXML
    private Button inregistreazaButton;
    @FXML
    private TextField titluTF;
    @FXML
    private TextField descriereTF;
    @FXML
    private DatePicker deadlineDatePicker;
    @FXML
    private TableView<Nevoie> fapteTableView;
    @FXML
    private TableColumn<Nevoie, Long> idFaptaColumn;
    @FXML
    private TableColumn<Nevoie, String> titluFaptaColumn;
    @FXML
    private TableColumn<Nevoie, String> descriereFaptaColumn;
    @FXML
    private TableColumn<Nevoie, LocalDateTime> deadlineFaptaColumn;
    @FXML
    private TableColumn<Nevoie, Long> omNevoieFaptaColumn;
    @FXML
    private Button rezolvaButton;
    @FXML
    private TableColumn<Nevoie, Long> idColumn;
    @FXML
    private TableView<Nevoie> nevoiTableView;
    @FXML
    private TableColumn<Nevoie, String> titluColumn;
    @FXML
    private TableColumn<Nevoie, String> descriereColumn;
    @FXML
    private TableColumn<Nevoie, LocalDateTime> deadlineColumn;
    @FXML
    private TableColumn<Nevoie, Long> omNevoieColumn;
    @FXML
    private TableColumn<Nevoie, Long> omSalvatorColumn;
    @FXML
    private TableColumn<Nevoie, String> statusColumn;
    private String username;

    public void initialize(Service service, String username) {
        this.service = service;
        this.username = username;
        initModel(username);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titluColumn.setCellValueFactory(new PropertyValueFactory<>("titlu"));
        descriereColumn.setCellValueFactory(new PropertyValueFactory<>("descriere"));
        deadlineColumn.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        omNevoieColumn.setCellValueFactory(new PropertyValueFactory<>("om_in_nevoie"));
        omSalvatorColumn.setCellValueFactory(new PropertyValueFactory<>("om_salvator"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        nevoiTableView.setItems(nevoieModel);

        idFaptaColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titluFaptaColumn.setCellValueFactory(new PropertyValueFactory<>("titlu"));
        descriereFaptaColumn.setCellValueFactory(new PropertyValueFactory<>("descriere"));
        deadlineFaptaColumn.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        omNevoieFaptaColumn.setCellValueFactory(new PropertyValueFactory<>("om_in_nevoie"));
        fapteTableView.setItems(fapteModel);
    }

    private void initModel(String username) {
        Iterable<Nevoie> nevoi = service.getAllNevoiByPersoana(username);
        List<Nevoie> nevoieList = StreamSupport.stream(nevoi.spliterator(), false).collect(Collectors.toList());
        nevoieModel.setAll(nevoieList);

        Iterable<Nevoie> fapte = service.getFapteByUser(username);
        List<Nevoie> fapteList = StreamSupport.stream(fapte.spliterator(), false).collect(Collectors.toList());
        fapteModel.setAll(fapteList);

    }

    public void onRezolva(ActionEvent actionEvent) {
        Nevoie nevoie = nevoiTableView.getSelectionModel().getSelectedItem();
        if (nevoie.getOm_salvator() >= 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("DENY HELP");
            alert.setHeaderText(null);
            alert.setContentText("The selected need cannot be assigned. Hero already found!");
            alert.showAndWait();
        } else if (this.service.rezolvaNevoie(nevoie, username) != null) {
            initialize(service, username);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("CONFIRM HELP");
            alert.setHeaderText(null);
            alert.setContentText("The selected need have been assigned. You are a hero!");
            alert.showAndWait();
        }
    }

    public void onInregistreaza(ActionEvent actionEvent) {
        String titlu = titluTF.getText();
        String descriere = descriereTF.getText();
        LocalDate date = deadlineDatePicker.getValue();
        this.service.addNevoie(username,titlu,descriere,date);
    }
}