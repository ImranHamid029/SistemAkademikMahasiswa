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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static sistemakademikmahasiswa.HalamanLoginController.user;

/**
 * FXML Controller class
 *
 * @author imran sukron hamid
 */
public class MenuMahasiswaController implements Initializable {
   
    

    @FXML
    private Label info;

    @FXML
    private Button informasimahasiswa;

    @FXML
    private TableColumn <jadwal,String>J;
    
    @FXML
    private TableColumn <jadwal,String>MK;
    @FXML
    private TableColumn <jadwal,String>R;
    @FXML
    private TableColumn <jadwal,String>S;
    @FXML
    private TableColumn <jadwal,String>D;
    @FXML
    private TableView <jadwal>jadwalmhs;
    
    Connection conn = null;
    ResultSet rsm = null;
    PreparedStatement pstm = null;
    
    public void mkpilihan(){
        
    }
    
    @FXML
    void Menuinformasi(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HalamanInformasiMahasiswa.fxml"));
        Scene scene = new Scene(root);  
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ToHalamanLogin(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("HalamanLogin.fxml"));
    Scene scene = new Scene(root);  
    Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    
    public void informasi(){
        conn = DBMHelper.getConnection();
        String sqlm="Select * from mahasiswa where NPM = ?";
        try{
            pstm = conn.prepareStatement(sqlm);
            pstm.setString(1, user);
            rsm=pstm.executeQuery();
            if(rsm.next()){
                String infouser =rsm.getString("Nama_Mahasiswa");
                
                info.setText(infouser);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    @FXML
    void ToPilihMK(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("PilihMataKuliah.fxml"));
        Scene scene = new Scene(root);  
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        informasi();
        showsemua();
    }   
    
    
    
    
    
    
    
    
    
    public ObservableList<jadwal> getjadwal() {
    ObservableList<jadwal> matkul = FXCollections.observableArrayList();
    Connection conn = DBMHelper.getConnection();
    String query = "SELECT matakuliah.Nama_MK, matakuliah.Jadwal, matakuliah.SKS, dosen.Nama_Dosen, ruangan.Nama_Ruangan FROM matakuliah INNER JOIN dosen ON dosen.Id_Dosen = matakuliah.Id_Dosen INNER JOIN ruangan ON ruangan.Nama_Ruangan = matakuliah.Nama_Ruangan INNER JOIN enroll ON enroll.Id_MK = matakuliah.Id_MK WHERE enroll.NPM = ?;";
                    
                   
                   

    try {
        PreparedStatement pss = conn.prepareStatement(query);
        pss.setString(1, user);
        ResultSet rs = pss.executeQuery();
        jadwal temp;
        while (rs.next()) {
            temp = new jadwal(rs.getString("Nama_MK"), rs.getString("Jadwal"), rs.getInt("SKS"), rs.getString("Nama_Dosen"), rs.getString("Nama_Ruangan"));
            matkul.add(temp);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return matkul;
}

void showsemua() {
    ObservableList<jadwal> list = getjadwal();
    MK.setCellValueFactory(new PropertyValueFactory<>("mkul"));
    J.setCellValueFactory(new PropertyValueFactory<>("jdl"));
    S.setCellValueFactory(new PropertyValueFactory<>("sks"));
    D.setCellValueFactory(new PropertyValueFactory<>("dos"));
    R.setCellValueFactory(new PropertyValueFactory<>("ruangan"));
    jadwalmhs.setItems(list);
}

    
}

    