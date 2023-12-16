/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemakademikmahasiswa;

import DataBaseMahasiswa.DBMHelper;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static sistemakademikmahasiswa.HalamanLoginController.user;


public class HalamanInformasiMahasiswaController implements Initializable {
    Connection conn = null;
    ResultSet rsm = null;
    PreparedStatement pstm = null;
    
    @FXML
    private Label jk;

    @FXML
    private Hyperlink Kembali;
            
    @FXML
    private Label nama;

    @FXML
    private Label npm;

    @FXML
    private Label pass;

    @FXML
    private Label prodi;

    @FXML
    private Label r;
    
    
    public void tampilaninformasi(){
        conn = DBMHelper.getConnection();
        String sqlm="Select * from mahasiswa where NPM = ?";
        try{
            pstm = conn.prepareStatement(sqlm);
            pstm.setString(1, user);
            rsm=pstm.executeQuery();
            if(rsm.next()){
                String NpmUser =rsm.getString("NPM");
                String NamaUser=rsm.getString("Nama_Mahasiswa");
                String Jk = rsm.getString("JenisKelamin");
                String rw = rsm.getString("RiwayatPendidikan");
                
                String Prodi = rsm.getString("Nama_ProgramStudi");
                
                
                npm.setText(NpmUser);
                nama.setText(NamaUser);
                jk.setText(Jk);
                r.setText(rw);
                
                prodi.setText(Prodi);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
     @FXML
    void ToMenuMahasiswa(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MenuMahasiswa.fxml"));
        Scene scene = new Scene(root);  
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tampilaninformasi();
    }    
    
}
