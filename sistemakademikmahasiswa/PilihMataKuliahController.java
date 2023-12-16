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
public class PilihMataKuliahController implements Initializable {
    
    @FXML
    private Button btnadd;
    @FXML
    private TextField idm;
    
    @FXML
    private TextField idmk;
    
    @FXML
    private TableColumn <matakuliah, String> dosen;

    @FXML
    private TableColumn<matakuliah, String> Id;

    @FXML
    private TableColumn<matakuliah, String> jadwal;

    @FXML
    private TableColumn<matakuliah, String> matkul;

    @FXML
    private TableColumn<matakuliah, String> ruang;

    @FXML
    private TableColumn<matakuliah, String> sks;
    @FXML
    private TableView<matakuliah> tvdata;
    
    @FXML
    private ComboBox milihh;
    
    @FXML
    private Label userr;

    @FXML
    void ToMenuMahasiswa(ActionEvent event) throws IOException {       
        Parent root = FXMLLoader.load(getClass().getResource("MenuMahasiswa.fxml"));
        Scene scene = new Scene(root);  
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
   
        

        
    public ObservableList<matakuliah> getmatakuliah(){
        ObservableList<matakuliah> matkul = FXCollections.observableArrayList();
        Connection conn = DBMHelper.getConnection();
        String query = "select matakuliah.Id_MK,matakuliah.Nama_MK, matakuliah.Jadwal, matakuliah.SKS,dosen.Nama_Dosen,ruangan.Nama_Ruangan from matakuliah inner join dosen on dosen.Id_Dosen  = matakuliah.Id_Dosen INNER JOIN ruangan on ruangan.Nama_Ruangan = matakuliah.Nama_Ruangan ";
  

  try {
     PreparedStatement pss = conn.prepareStatement(query);
    //pss.setString(0, user);
     ResultSet rs = pss.executeQuery();
    matakuliah temp;
    while (rs.next()) {
      temp = new matakuliah(rs.getString("Id_MK"),rs.getString("Nama_MK"),rs.getInt("SKS"),rs.getString("Jadwal"),rs.getString("Nama_Ruangan"),rs.getString("Nama_Dosen"));
      matkul.add(temp);
        }
    } catch (SQLException ex) {
    ex.printStackTrace();
      }
    return matkul;
    } 
    
    @Override
     public void initialize(URL url, ResourceBundle rb){
     showsemua();
     pilihmk();
     }
     
     void showsemua(){
       ObservableList<matakuliah> list = getmatakuliah();
       Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        matkul.setCellValueFactory(new PropertyValueFactory<>("mk"));
        sks.setCellValueFactory(new PropertyValueFactory<>("s"));
        jadwal.setCellValueFactory(new PropertyValueFactory<>("j"));
        ruang.setCellValueFactory(new PropertyValueFactory<>("r"));
        dosen.setCellValueFactory(new PropertyValueFactory<>("d"));
        tvdata.setItems(list);
        
   }
     
    private final Connection CONN;

    public PilihMataKuliahController() {
        this.CONN = DBMHelper.getConnection();
    }
     
     @FXML
     void addJadwal(ActionEvent event) throws IOException{
         String insert = "INSERT INTO `enroll`(`NPM`,`Id_MK`) VALUES ('" + idm.getText() + "','" + milihh.getValue() + "')";
        try {
            
            if (CONN.createStatement().executeUpdate(insert) > 0) {
                JOptionPane.showMessageDialog(null, "Data Berhasil Masuk");
                Parent root = FXMLLoader.load(getClass().getResource("MenuMahasiswa.fxml"));
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
     
     
     
     
     private void pilihmk() {
        
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/universitaskonoha","root","")) {
            String sql = "SELECT Id_MK FROM matakuliah";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String jmk = resultSet.getString("Id_MK");
                        milihh.getItems().add(jmk);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     private void savejadwal() {
        String selectedmk = (String) milihh.getValue();
        
        
    }
}
     

