package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * Klasa  obslugujaca logowanie
 */
public class Login {


    @FXML
    private Button exitbtn;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private Button loginbtn;

    @FXML
    private Label logintxt;

    @FXML
    private PasswordField passinput;

    @FXML
    private Label passtxt;

    @FXML
    private Button registerbtn;

    @FXML
    private TextField userinput;

    @FXML
    private Label usertxt;

    /**
     * Metoda odpowiadajaca za akcje wykonywana po wcisnieciu przycisku "Rejestruj"
     * @param event
     * @throws IOException
     */
    public void userRegister(ActionEvent event) throws IOException {
        System.out.println(userinput.getText());
        System.out.println(passinput.getText());
        JavaPostgreSql.writeToDatabase(userinput.getText(), passinput.getText());

    }

    /**
     * Metoda odpowiadajaca za akcje wykonywana po wcisnieciu przycisku "Wyjdz"
     * @param event
     */
    public void exitOnAction(ActionEvent event){
        Stage stage = (Stage) exitbtn.getScene().getWindow();
        stage.close();
    }

    /**
     * Metoda odpowiadajaca za akcje wykonywana po wcisnieciu przycisku "Zaloguj"
     * @param event
     */
    public void userLogin(ActionEvent event){
        if(userinput.getText().isBlank()==false && passinput.getText().isBlank()==false){
           validateLogin();
        }else {
            loginMessageLabel.setText("Spróbuj zalogować się ponownie");
        }
    }

    /**
     * Metoda odpowiadajaca za logowanie sie za pomoca bazy danych
     */
    public void validateLogin(){
        JavaPostgreSql connectNow = new JavaPostgreSql();
        Connection connectDB = connectNow.getConnection();
        String verifyLogin = "SELECT count(1) FROM users WHERE username ='" +userinput.getText()+"' AND password ='"+passinput.getText()+"'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1)==1){
                    loginMessageLabel.setText("Witaj!");
                    Main.setRoot("menu");
                }else {
                    loginMessageLabel.setText("Spróbuj zalogować się ponownie");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
