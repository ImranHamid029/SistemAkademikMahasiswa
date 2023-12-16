/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemakademikmahasiswa;

import DataBaseMahasiswa.DBMHelper;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author imran sukron hamid
 */
public class HalamanDaftarAkunController implements Initializable {
    @FXML
    private ComboBox Pilih_jeniskelamin;
    
    @FXML
    private ComboBox pilihprogramstudi;
    
    @FXML
    private TextField i_riwayat;

    

    @FXML
    private TextField in_nama;

    @FXML
    private TextField in_npm;

    @FXML
    private TextField in_pass;

    
    @FXML
    void ToHalamanLogin(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("HalamanLogin.fxml"));
    Scene scene = new Scene(root);  
    Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    
    
    private final Connection CONN;

    public HalamanDaftarAkunController() {
        this.CONN = DBMHelper.getConnection();
    }
    
    @FXML
    public void Login(ActionEvent event) throws Exception {      
    Parent root = FXMLLoader.load(getClass().getResource("HalamanLogin.fxml"));
    Scene scene = new Scene(root);  
    Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
     
    public void addMahasiswa(ActionEvent event) throws Exception {
        String insert = "INSERT INTO `mahasiswa` VALUES ('" + in_npm.getText() + "','" + in_nama.getText() + "','" + Pilih_jeniskelamin.getValue() + "','" + i_riwayat.getText() + "','" + in_pass.getText() + "','" + pilihprogramstudi.getValue() + "')";
        try {
            if (CONN.createStatement().executeUpdate(insert) > 0) {
                JOptionPane.showMessageDialog(null, "Data Berhasil Masuk");
                Parent root = FXMLLoader.load(getClass().getResource("HalamanLogin.fxml"));
                Scene scene = new Scene(root);  
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else {
                JOptionPane.showMessageDialog(null, "Data Gagal Masuk");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HalamanDaftarAkunController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }


        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
     populatepilihprogramstudi();
     pilihjeniskelamin();
   
    }
    private void pilihjeniskelamin(){
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/universitaskonoha","root","")) {
            String sql = "SELECT jeniskelamin FROM jk";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String jeniskelamin = resultSet.getString("jeniskelamin");
                        Pilih_jeniskelamin.getItems().add(jeniskelamin);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void populatepilihprogramstudi() {
        
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/universitaskonoha","root","")) {
            String sql = "SELECT Nama_ProgramStudi FROM programstudi";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String programStudi = resultSet.getString("Nama_ProgramStudi");
                        pilihprogramstudi.getItems().add(programStudi);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     private void saveMahasiswaData() {
        String selectedProgramStudi = (String) pilihprogramstudi.getValue();
        String selectedJenisKelamin = (String) Pilih_jeniskelamin.getValue();
        
    }
    
}
