package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.util.Hashtable;


public class ServerController {
    public Hashtable<String,NetworkUtil> address;
    //public int f=0;
    public String name;
    private ServerMain s;
    @FXML
    public ListView<String> ClientList;

    @FXML
    private TextArea Message;

    @FXML
    private Button SendButton;
    @FXML
    private Button All;

    @FXML
    void SendAction(ActionEvent event){
         name=ClientList.getSelectionModel().getSelectedItem();
        NetworkUtil nc=address.get(name);
        String mess=Message.getText();
        nc.write(mess);
        ClientList.getItems().remove(name);
        Message.clear();


    }
   void setMain(ServerMain s)
    {
        this.s=s;
    }
    @FXML
    void SendThem(ActionEvent event)
    {
        NetworkUtil[]array=new NetworkUtil[address.size()];
       for(int i=0;i<address.size();i++) {
         //  address.keySet();

        }
    }
}