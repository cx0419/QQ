package JavaChat.Client.View;

import JavaChat.Client.Service.FriendApplicationService;
import JavaChat.Client.Service.GroupApplicationService;
import JavaChat.Common.Pojo.FriendApplication;
import JavaChat.Common.Pojo.GroupApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class GroupApplicationItemController {
    @FXML
    private ImageView HeadPortrait;
    @FXML
    private Button Agree_Button;
    @FXML
    private Button Reject_Button;
    @FXML
    private Label State_Label;
    @FXML
    private Label GroupNumber_Label;
    @FXML
    private Rectangle Cover_Rectangle;
    @FXML
    private Label Sender_Label;

    private GroupApplication groupApplication;

    public void Agree_ButtonColorEnter(){
        Agree_Button.setStyle(
                "-fx-background-color: #63eab4;"
        );
    }
    public void Agree_ButtonColorExited(){
        Agree_Button.setStyle(
                "-fx-background-color: #37ff00;"
        );
    }
    public void Reject_ButtonColorEnter(){
        Reject_Button.setStyle(
                "-fx-background-color: #63eab4;"
        );
    }
    public void Reject_ButtonColorExited(){
        Reject_Button.setStyle(
                "-fx-background-color: #ec521e;"
        );
    }
    public void AgreeGroupApplication(){
        System.out.println("同意");
        GroupApplicationService.AgreeGroupApplication(groupApplication.getGroupApplicationSender(),groupApplication.getGroupNumber());
        Agree_Button.setDisable(true);
        Reject_Button.setDisable(true);
    }
    public void RejectGroupApplication(){
        GroupApplicationService.RejectGroupApplication(groupApplication.getGroupApplicationSender(),groupApplication.getGroupNumber());
        System.out.println("拒绝");
        Agree_Button.setDisable(true);
        Reject_Button.setDisable(true);
    }
    public Image getHeadPortrait() {
        return HeadPortrait.getImage();
    }

    public void setHeadPortrait(Image headPortrait) {
        HeadPortrait.setImage(headPortrait);
    }

    public String getState() {
        return State_Label.getText();
    }

    public void setState(String state) {
        State_Label.setText(state);
    }

    public String getGroupNumber_Label() {
        return GroupNumber_Label.getText();
    }

    public void setGroupNumber_Label(String name) {
        GroupNumber_Label.setText(name);
    }

    public GroupApplication getGroupApplication() {
        return groupApplication;
    }

    public void setGroupApplication(GroupApplication groupApplication) {
        this.groupApplication = groupApplication;
    }

    public void SetCoverRectangleVisibility(boolean flag){
        Cover_Rectangle.setVisible(flag);
    }
    public String getSender_Label() {
        return Sender_Label.getText();
    }

    public void setSender_Label(String Sender) {
        Sender_Label.setText(Sender);
    }

}
