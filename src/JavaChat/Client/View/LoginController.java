package JavaChat.Client.View;

import JavaChat.Client.Service.LoginService;
import JavaChat.Common.Pojo.User;
import JavaChat.Common.Utils.RegularExpression;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Vector;

public class LoginController {
    @FXML
    private Button RetrievePassword_Button;
    @FXML
    private Button LoginClose_Button;
    @FXML
    private Button Register_Button;
    @FXML
    private Button Login_Button;
    @FXML
    private TextField AccountNumber_TextField;
    @FXML
    private TextField AccountPassword_TextField;
    @FXML
    private Label LoginErrorTip_Label;
    private Boolean LoginKey;
    @FXML
    private Label AccountErrorTip_Label;
    private Boolean AccountKey;
    @FXML
    private Label PasswordErrorTip_Label;
    private Boolean PasswordKey;
    @FXML
    private Button Login1_Button;
    @FXML
    private Button Login2_Button;
    @FXML
    private Button Login3_Button;


    public void Login1(){
        AccountNumber_TextField.setText("00685312");
        AccountPassword_TextField.setText("123456");
    }
    public void Login2(){
        AccountNumber_TextField.setText("99296512");
        AccountPassword_TextField.setText("123456");
    }
    public void Login3(){
        AccountNumber_TextField.setText("48260608");
        AccountPassword_TextField.setText("123456");
    }
    public void Login4(){
        AccountNumber_TextField.setText("58359808");
        AccountPassword_TextField.setText("123456");
    }
    public void initialize(){
        AccountKey = false;
        PasswordKey =false;
    }
    public void KeyReleasedPasswordReleased_PasswordField() {
        if(RegularExpression.CheckAccountPassword(AccountPassword_TextField.getText())){
            PasswordKey = true;
            PasswordErrorTip_Label.setText("");
        }else{
            PasswordKey = false;
            PasswordErrorTip_Label.setText("????????????????????????");
        }
    }
    public void KeyReleasedAccountReleased_PasswordField() {
        if(RegularExpression.CheckAccountNumber(AccountNumber_TextField.getText())){
            AccountKey = true;
            AccountErrorTip_Label.setText("");
        }else{
            AccountKey = false;
            AccountErrorTip_Label.setText("????????????????????????");
        }
    }
    public void But_ColorEnter(){
        Login_Button.setStyle(
                "-fx-background-color: #00f1ea;"
        );
    }
    public void But_ColorExited(){
        Login_Button.setStyle(
                "-fx-background-color: #48bcff;"
        );
    }
    //??????????????????
    public  void  CreatRegister(ActionEvent event){
        Stage stage =(Stage) Login_Button.getScene().getWindow();
        stage.close();
        try {
            RegisterView.start(new Stage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //????????????
    public  void closely(ActionEvent event){
        Stage stage =(Stage) Login_Button.getScene().getWindow();
        stage.close();
    }
    //??????????????????
    public  void  CreatRetrievePassword (ActionEvent event){
        Stage stage =(Stage) Login_Button.getScene().getWindow();
        stage.close();
        try {
            ForgetView.start(new Stage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //????????????
    public  void  CreatMainCore (ActionEvent event) throws Exception{
        if(RegularExpression.CheckAccountNumber(AccountNumber_TextField.getText())&&RegularExpression.CheckAccountPassword(AccountPassword_TextField.getText())){
            AccountKey = true;
            PasswordKey = true;
        }
       if(!AccountKey||!PasswordKey){
           return;
       }

        //????????????
        User user = new User();
        user.setAccountNumber(AccountNumber_TextField.getText());
        user.setAccountPassword(AccountPassword_TextField.getText());
        if(LoginService.Login(user)){
            //??????
            System.out.println("????????????:" + AccountNumber_TextField.getText()+"!");
            //???Maincore????????????????????????(???????????????????????????????????????????????????id?????????)
            MainCoreController.setAccountNumber(AccountNumber_TextField.getText());
            //????????????????????????
            Stage stage =(Stage) Login_Button.getScene().getWindow();
            stage.close();
            try {
                MainCoreView.start(new Stage());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("????????????!");
        }else{
            //??????????????????
            System.out.println("????????????!");
        }
    }



}
