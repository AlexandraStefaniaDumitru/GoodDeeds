package com.example.faptebune;

import com.example.faptebune.domain.Nevoie;
import com.example.faptebune.domain.Persoana;
import com.example.faptebune.repository.Repository;
import com.example.faptebune.repository.database.NevoieDBRepository;
import com.example.faptebune.repository.database.PersoanaDBRepository;
import com.example.faptebune.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Repository<Long, Persoana> persoanaRepository = new PersoanaDBRepository();
        Repository<Long, Nevoie> nevoieRepository = new NevoieDBRepository();
        Service service = new Service(persoanaRepository, nevoieRepository);

        FXMLLoader autentificareLoader = new FXMLLoader(HelloApplication.class.getResource("autentificare-view.fxml"));
        Scene autentificareScene = new Scene(autentificareLoader.load(), 1000, 600);
        AutentificareController autentificareController = autentificareLoader.getController();
        autentificareController.initialize(service);
        stage.setTitle("Autentificare");
        stage.setScene(autentificareScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}