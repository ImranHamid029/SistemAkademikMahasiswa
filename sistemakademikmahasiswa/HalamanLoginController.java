/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemakademikmahasiswa;

import DataBaseMahasiswa.DBMHelper;
import java.awt.Image;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
    
    

public class HalamanLoginController implements Initializable {
    public static String user;
    
     @FXML
    private TextField passwordlogin;

    @FXML
    private TextField userlogin;
    
    @FXML
    public void daftarAkun(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("HalamanDaftarAkun.fxml"));
        Scene scene = new Scene(root);  
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    Connection conn = null;
    ResultSet rsm = null;
    ResultSet rsd = null;
    PreparedStatement pstm = null;
    PreparedStatement pstd = null;

    @FXML
    private void ma(ActionEvent event)throws Exception{
        user = userlogin.getText();
        conn = DBMHelper.getConnection();
        String sqlm="Select * from mahasiswa where NPM = ? and PasswordUser = ? ";
        String sqld="Select * from dosen where Id_Dosen = ? and PasswordDosen = ? ";
        
        try{
            pstm = conn.prepareStatement(sqlm);
            pstm.setString(1, userlogin.getText());
            pstm.setString(2,passwordlogin.getText());
            rsm=pstm.executeQuery();
            pstd=conn.prepareStatement(sqld);
            pstd.setString(1, userlogin.getText());
            pstd.setString(2,passwordlogin.getText());
            rsd=pstd.executeQuery();
            
            if(rsm.next()){
              JOptionPane.showMessageDialog(null,"Password Benar");
        Parent root = FXMLLoader.load(getClass().getResource("MenuMahasiswa.fxml"));
        Scene scene = new Scene(root);  
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
            }else if(rsd.next()){
              JOptionPane.showMessageDialog(null,"Password Benar");
        Parent root = FXMLLoader.load(getClass().getResource("MenuDosen.fxml"));
        Scene scene = new Scene(root);  
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
            }else{
              JOptionPane.showMessageDialog(null,"Password Salah");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    
    
}
