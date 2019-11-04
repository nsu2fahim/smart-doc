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

public class FXMLController1 implements Initializable {
    
    private Label label;
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;
    @FXML
    private Label fieldMessage;
    private Connection connection;
    private PreparedStatement pstmt;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            this.connection = dbConnection.getConnection();
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void handleLoginAction(ActionEvent event) throws IOException, SQLException {
        if(check()){
        
        ((Node) (event.getSource())).getScene().getWindow().hide();
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/SymptomsPage.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage stage = new Stage();
        stage.setTitle("Select symptoms");
        stage.setScene(scene);
        stage.show();
        }
        else{
        
            fieldMessage.setText("Username or password is incorrect!");
        }
    }

    @FXML
    private void handleReturnAction(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FirstPage.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage stage = new Stage();
        stage.setTitle("Smart Doctor");
        stage.setScene(scene);
        stage.show();
    }
    
    private boolean check() throws SQLException{
        
        String sql = "SELECT * FROM login WHERE username=? AND password=?";
        pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, username.getText());
        pstmt.setString(2, password.getText());
        ResultSet rs = pstmt.executeQuery();
        boolean login = rs.next();
        pstmt.close();
        rs.close();
        return login;
        
        
    }
}
