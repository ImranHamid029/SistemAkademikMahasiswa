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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static sistemakademikmahasiswa.HalamanLoginController.user;

/**
 * FXML Controller class
 *
 * @author imran sukron hamid
 */
public class MenuMKController implements Initializable {
    Connection conn = null;
    ResultSet rsm = null;
    PreparedStatement pstm = null;
    @FXML
    private TextField idd;
       @FXML
    private Label Tugas;

    @FXML
    private TextField i;

    @FXML
    private Label informasi;

    @FXML
    private TextField m;

    @FXML
    private Label materi;

    @FXML
    private Label matkul;

    @FXML
    private TextField t;
    @FXML
    void toDosen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MenuDosen.fxml"));
                Scene scene = new Scene(root);  
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }
    @FXML
    void addinformasi(ActionEvent event) throws IOException {
        String insert = "INSERT INTO `menudosen`  VALUES ('" + m.getText() + "','" + t.getText() + "','" + i.getText() +  "','"+ idd.getText() +  "')";
        try {
            if (CONN.createStatement().executeUpdate(insert) > 0) {
                JOptionPane.showMessageDialog(null, "Data Berhasil Masuk");
                Parent root = FXMLLoader.load(getClass().getResource("MenuDosen.fxml"));
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
   


    private final Connection CONN;

    public MenuMKController() {
        this.CONN = DBMHelper.getConnection();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
     tampilaninformasi();
     informasi();
     }

    public void tampilaninformasi(){
        conn = DBMHelper.getConnection();
        String sql="Select * from menudosen where Id_Dosen= ? ";
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, user);
            rsm=pstm.executeQuery();
            if(rsm.next()){
                String mat =rsm.getString("Materi");
                String tgs=rsm.getString("Tugas");
                String inf = rsm.getString("informasi");
                
                
                materi.setText(mat);
                Tugas.setText(tgs);
                informasi.setText(inf);
                
                
                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    
    
    public void informasi(){
        conn = DBMHelper.getConnection();
        String sqlm="Select * from matakuliah where Id_Dosen = ?";
        try{
            pstm = conn.prepareStatement(sqlm);
            pstm.setString(1, user);
            rsm=pstm.executeQuery();
            if(rsm.next()){
                String infouser =rsm.getString("Nama_MK");
                
                matkul.setText(infouser);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    
   
    }
    

