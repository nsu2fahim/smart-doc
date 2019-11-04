/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fahim.smartdoc;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author glitch
 */
public class FXMLController3 implements Initializable {

    @FXML
    private Label fieldSymptoms;
    @FXML
    private Label fieldMessage;
    public String variables[] = new String[50];
    public int count = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleSymptopmsAction(ActionEvent event) {
        
        String symptom = ((Button) event.getSource()).getText();
        String oldText = fieldSymptoms.getText();
        String newText = oldText + symptom + " ";
        fieldSymptoms.setText(newText);
        
    }

    @FXML
    private void handleSubmitAction(ActionEvent event) throws IOException {
        if(!"".equals(fieldSymptoms.getText())){
            getSymptoms();
        
            ((Node) (event.getSource())).getScene().getWindow().hide();
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ResultPage.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage stage = new Stage();
        stage.setTitle("Result");
        stage.setScene(scene);
        stage.show();
        }
        else{
            fieldMessage.setText("Please select your symptoms first");
        }
    }

    @FXML
    private void handleClearFieldAction(ActionEvent event) {
        fieldSymptoms.setText("");
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
    
    private void getSymptoms(){
        String s1=fieldSymptoms.getText();
        String[] words=s1.split("\\s");
        count = 0;
        
        int v=0;
        for(String w:words){
        variables[v]=w;
        
        count++;
        v++;
    }

    }
    
}
