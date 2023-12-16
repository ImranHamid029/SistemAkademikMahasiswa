/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemakademikmahasiswa;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author imran sukron hamid
 */
public class MenuDosenController implements Initializable {

    @FXML
    private Label info;

    @FXML
    private Button infodosen;

    @FXML
    void Menuinformasi(ActionEvent event) {

    }
    @FXML
    void ToKonsul(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Konsultasi.fxml"));
        Scene scene = new Scene(root);  
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void ToMenuMK(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MenuMK.fxml"));
        Scene scene = new Scene(root);  
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
