package JavaChat.Client.View;

import JavaChat.Client.Service.FriendApplicationService;
import JavaChat.Client.Service.GroupApplicationService;
import JavaChat.Common.Pojo.FriendApplication;
import JavaChat.Common.Pojo.GroupApplication;
import JavaChat.Common.Transfer.FriendApplicationState;
import JavaChat.Common.Transfer.GroupApplicationState;
import JavaChat.Common.Utils.FileUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.Vector;

public class GroupApplicationController {
    @FXML
    private Button Close_Button;
    @FXML
    private ListView<GroupApplication> GroupApplication_ListView;
    @FXML
    private Label ErrorTip_Label;


    public static ObservableList<GroupApplication> GroupApplication_ObservableList = FXCollections.observableArrayList();

    public void CloseGroupApplication(){
        Stage stage =(Stage) Close_Button.getScene().getWindow();
        stage.close();
        GroupApplicationView.loginStage = null;
    }

    public void initialize(){
        GroupApplication_ListView.setFixedCellSize(129);
        GroupApplication_ListView.setItems(GroupApplication_ObservableList);
        GroupApplication_ListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<GroupApplication>() {
            @Override
            public void changed(ObservableValue<? extends GroupApplication> observable, GroupApplication oldValue, GroupApplication newValue) {
            }
        });
        GroupApplication_ListView.setCellFactory(new Callback<ListView<GroupApplication>, ListCell<GroupApplication>>() {
            @Override
            public ListCell<GroupApplication> call(ListView<GroupApplication> param) {
                ListCell listCell = new ListCell<GroupApplication>(){
                    @Override
                    protected void updateItem(GroupApplication item, boolean empty) {
                        super.updateItem(item, empty);
                        if(!empty){
                            //setStyle("-fx-background-color:rgba(0,0,0,0)");
                            HBox hBox=new HBox();
                            FXMLLoader fxmlLoader=new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("fxml/GroupApplicationItem.fxml"));
                            try {
                                hBox.getChildren().add(fxmlLoader.load());
                            } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println("??????????????????");
                            }
                            GroupApplicationItemController groupApplicationItemController = fxmlLoader.getController();
                            String UserAccountNumber = MainCoreController.getAccountNumber();
                            String  Result = "";
                            groupApplicationItemController.setGroupApplication(item);
                            groupApplicationItemController.setSender_Label("?????????:"+item.getGroupApplicationSender());
                            groupApplicationItemController.setGroupNumber_Label("??????:"+item.getGroupNumber());
                            //??????????????????????????????????????????
                            if(UserAccountNumber.equals(item.getGroupApplicationSender())){
                                groupApplicationItemController.SetCoverRectangleVisibility(false);
                                switch(item.getGroupApplicationState()){
                                    case AGREE:
                                        groupApplicationItemController.SetCoverRectangleVisibility(true);
                                        Result = "??????????????????"+item.getProcessor()+"?????????";
                                        break;
                                    case UNTREATED:
                                        groupApplicationItemController.SetCoverRectangleVisibility(true);
                                        Result = "????????????????????????????????????"+item.getGroupApplicationSender()+"????????????";
                                        break;
                                    case REJECT:
                                        groupApplicationItemController.SetCoverRectangleVisibility(true);
                                        Result = "????????????"+item.getProcessor()+"??????";
                                        break;
                                }
                            }else{
                                switch((GroupApplicationState)item.getGroupApplicationState()){
                                    case AGREE:
                                        groupApplicationItemController.SetCoverRectangleVisibility(true);
                                        Result = item.getProcessor()+"????????????"+item.getGroupApplicationSender()+"????????????";
                                        break;
                                    case UNTREATED:
                                        groupApplicationItemController.SetCoverRectangleVisibility(false);
                                        Result = "?????????????????????????????????"+item.getGroupApplicationSender()+"????????????";
                                        break;
                                    case REJECT:
                                        groupApplicationItemController.SetCoverRectangleVisibility(true);
                                        Result = item.getProcessor()+"?????????"+item.getGroupApplicationSender()+"????????????";
                                        break;
                                }
                            }
                            groupApplicationItemController.setState(Result);

                            String GroupNumber = item.getGroupNumber();
                            groupApplicationItemController.setHeadPortrait(FileUtils.File_Image(MainCoreController.GroupHeadPortrait.get(GroupNumber)));
                            this.setGraphic(hBox);
                        }
                        else
                        {
                            this.setGraphic(null);
                        }
                    }
                };
                return listCell;
            }
        });
        GroupApplication_ObservableList.clear();
        GroupApplicationService.PullGroupApplicationList(MainCoreController.getAccountNumber());

    }
}
