package com.example.faptebune;

import com.example.faptebune.domain.Orase;
import com.example.faptebune.domain.Persoana;
import com.example.faptebune.service.Service;
import com.example.faptebune.utils.observer.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AutentificareController {
    private final ObservableList<Persoana> persoanaModel = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Persoana, Long> userColumn;
    @FXML
    private TableView<Persoana> persoanaTableView;
    @FXML
    private Button loginButton;
    @FXML
    private Button inregistrareButton;
    @FXML
    private TextField numeTF;
    @FXML
    private TextField prenumeTF;
    @FXML
    private TextField usernameTF;
    @FXML
    private TextField parolaTF;
    @FXML
    private TextField stradaTF;
    @FXML
    private TextField numarTF;
    @FXML
    private TextField telefonTF;
    @FXML
    private ComboBox<Orase> orasComboBox;
    Service service;
    public void initialize(Service service) {
        this.service = service;
        initModel();

        for(Orase oras :Orase.values()){
            orasComboBox.getItems().add(oras);
        }

        userColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        persoanaTableView.setItems(persoanaModel);
    }

    private void initModel() {
        Iterable<Persoana> persoanas = service.getAllPersoane();
        List<Persoana> persoanaList = StreamSupport.stream(persoanas.spliterator(), false).collect(Collectors.toList());
        persoanaModel.setAll(persoanaList);
    }

    public void onInregistrare(ActionEvent actionEvent) {
        String nume = numeTF.getText();
        String prenume = prenumeTF.getText();
        String username = usernameTF.getText();
        String parola = parolaTF.getText();
        Orase oras = orasComboBox.getValue();
        String strada = stradaTF.getText();
        String nrStrada = numarTF.getText( );
        String telefon = telefonTF.getText();


        this.service.inregistrare(nume,prenume,username,parola,oras,strada, nrStrada, telefon);

        numeTF.clear();
        prenumeTF.clear();
        usernameTF.clear();
        parolaTF.clear();
        stradaTF.clear();
        numarTF.clear();
        telefonTF.clear();
        orasComboBox.valueProperty().set(null);
        initialize(service);
    }

    public void onLogin(ActionEvent actionEvent) {
        Persoana persoana = persoanaTableView.getSelectionModel().getSelectedItem();
        FXMLLoader ajutorLoader = new FXMLLoader(HelloApplication.class.getResource("ajutor-view.fxml"));
        Scene ajutorScene;

        try {
            ajutorScene = new Scene(ajutorLoader.load(), 1200, 800);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage ajutorStage = new Stage();
        AjutorController ajutorController = ajutorLoader.getController();
        ajutorController.initialize(service,persoana.getUsername());
        ajutorStage.setTitle("Ajutoare");
        ajutorStage.setScene(ajutorScene);
        ajutorStage.show();
    }
}