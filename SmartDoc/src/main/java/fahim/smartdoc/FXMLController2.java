/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fahim.smartdoc;

import fahim.smartdoc.database.dbConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author glitch
 */
public class FXMLController2 implements Initializable {

    @FXML
    private TextField fieldEmail;
    @FXML
    private TextField fieldName;
    @FXML
    private TextField fieldAge;
    @FXML
    private TextField fieldSex;
    private String email,name,age,sex,username,password;
    @FXML
    private PasswordField fieldPassword;
    @FXML
    private TextField fieldUsername;
    private Connection connection;
    private PreparedStatement pstmt;
    @FXML
    private Label fieldMessage;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            this.connection = dbConnection.getConnection();
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void handleSubmitAction(ActionEvent event) throws IOException, SQLException {
        

    if ((!"".equals(fieldUsername.getText()))&&(!"".equals(fieldPassword.getText()))&&(!"".equals(fieldEmail.getText()))){
        if(!check()){
        email = fieldEmail.getText();
        name = fieldName.getText();
        age = fieldAge.getText();
        sex = fieldSex.getText();
        username = fieldUsername.getText();
        password = fieldPassword.getText();
        
        updateDatabase1();
        updateDatabase();
        
        ((Node) (event.getSource())).getScene().getWindow().hide();
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginPage.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage stage = new Stage();
        stage.setTitle("Login Window");
        stage.setScene(scene);
        stage.show();
        }
        else{
            fieldMessage.setText("User exists with this username, try a different one");
        }
    }
    else {
            fieldMessage.setText("Email,username and password must not be empty");
            }
    }
        
    
        
       
    private void updateDatabase() throws SQLException{
    
        pstmt = this.connection.prepareStatement("INSERT INTO login (username, password) VALUES (? , ?)");
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        pstmt.executeUpdate();

        pstmt.close();
        
    
    }
    
    private void updateDatabase1() throws SQLException{
        
        pstmt = this.connection.prepareStatement("INSERT INTO signup (fullName , age , sex , email) VALUES (? , ? , ? , ?)");
        pstmt.setString(1, name);
        pstmt.setString(2, age);
        pstmt.setString(3, sex);
        pstmt.setString(4, email);
        pstmt.executeUpdate();
        
        pstmt.close();
        
        
    }
        
    

    @FXML
    private void handleClearAction(ActionEvent event) {
        fieldEmail.setText("");
        fieldName.setText("");
        fieldAge.setText("");
        fieldSex.setText("");
    }

    @FXML
    private void handleReturnAction(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FirstPage.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage stage = new Stage();
        stage.setTitle("Login Window");
        stage.setScene(scene);
        stage.show();
    }
    
    private boolean check() throws SQLException{
        String sql = "SELECT * FROM login WHERE username=?";
        pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, fieldUsername.getText());
        boolean existingUsername;
        try (ResultSet rs = pstmt.executeQuery()) {
            existingUsername = rs.next();
            pstmt.close();
            rs.close();
        }
        return existingUsername;
    }
}
