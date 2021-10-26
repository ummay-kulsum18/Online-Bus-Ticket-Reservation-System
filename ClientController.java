package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;

import java.sql.DatabaseMetaData;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class ClientController {
    String seat=null;
    String name, stime, date;
    String mobile;
    ArrayList<Integer> arr = new ArrayList<>();
    Rectangle[] seats = new Rectangle[40];
    DateController dateController;
    //private DateController dateController;
    public ObservableList<String> times = FXCollections.observableArrayList("8:00AM", "01:00PM", "05:00PM", "10:00PM");
    NetworkUtil nc;
    @FXML
    private AnchorPane anchor;

    @FXML
    private Label tj;

    @FXML
    private Label Dhaka;

    public TextArea Msgbox;
    @FXML
    private ImageView back;
    @FXML
    private ImageView back2;
    @FXML
    private Label sokal;
    @FXML
    private Label dupur;
    @FXML
    private Label bikal;
    @FXML
    private Label raat;
    @FXML
    private Label Service;
    @FXML
    private Button Continue;
    @FXML
    private Button Confirm;

    @FXML
    private Label levelDate;
    @FXML
    private Label mn;
    @FXML
    private Label cn;
    @FXML
    private TextField ClientName;
    @FXML
    private TextField Mobile;
    @FXML
    private Label levelTime;
    @FXML
    private Rectangle bus;
    @FXML
    private Label a;

    @FXML
    private Label b;

    @FXML
    private Label c;

    @FXML
    private Label d;

    @FXML
    private Label f;

    @FXML
    private Label g;

    @FXML
    private Label h;

    @FXML
    private Label i;

    @FXML
    private Label j;

    @FXML
    private Label l1;

    @FXML
    private Label l2;

    @FXML
    private Label l3;

    @FXML
    private Label l4;
    @FXML
    private Label e;
    @FXML
    private Rectangle K1;

    @FXML
    private Rectangle K2;

    @FXML
    private Label Available;
    @FXML
    private DatePicker datePicker;

    @FXML
    private Label Booked;
    @FXML
    private Button Next;
    @FXML
    private ChoiceBox<String> time;
    @FXML
    private Label error1;
    @FXML
    private Label ColorErr;

    @FXML
    private Button reload;
    @FXML
    private Label bookingerror;
    @FXML
    private Label successmeg;

    @FXML
    private Label thanked;

    @FXML
    void reloadaction(ActionEvent event) {

    }
    @FXML
    void ContinueAction(ActionEvent event) {
        back.setVisible(false);
        tj.setVisible(false);
        Dhaka.setVisible(false);
        Continue.setVisible(false);
        back2.setVisible(true);
        Service.setVisible(true);
        sokal.setVisible(true);
        dupur.setVisible(true);
        bikal.setVisible(true);
        raat.setVisible(true);
        datePicker.setVisible(true);
        init();
        levelDate.setVisible(true);
        cn.setVisible(true);
        ClientName.setVisible(true);
        mn.setVisible(true);
        Mobile.setVisible(true);
        levelTime.setVisible(true);
        time.setVisible(true);
        time.setItems(times);
        Next.setVisible(true);
        error1.setVisible(true);
        // Confirm.setVisible(true);

    }

    @FXML
    void ConnectAction(ActionEvent event) {
       /*try {
            String serverAddress="127.0.0.1";
            int serverPort=44444;
            nc = new NetworkUtil(serverAddress,serverPort);
            String name=Clientname.getText();
            nc.write(name);
            ConnectButton.setVisible(false);
            Clientname.setVisible(false);
            Msgbox.setVisible(true);
            msg.setVisible(true);
            new ReadThread(nc,this);
        } catch(Exception e) {
            System.out.println (e);
        }
            ConnectButton.setVisible(false);
            Clientname.setVisible(false);
            Msgbox.setVisible(true);*/

    }

    //item.isBefore(date.minusDays(7)) ||
    void init() {
        LocalDate date = LocalDate.now();

        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                if (item.isBefore(date.minusDays(0)) || item.isAfter(date.plusDays(7))) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        };

        datePicker.setDayCellFactory(dayCellFactory);
    }

    @FXML
    void Confirmpressed(ActionEvent event) {
        System.out.println(seat);
        nc.write(seat);
        seat=null;
        String e=(String)nc.read();
        if(e.equals("succeed")){
            System.out.println("succeed");
            if(errmsg==1){
                bookingerror.setVisible(false);
            }
            successmeg.setVisible(true);
            thanked.setVisible(true);

        }
        else if(e.equals("error")){
            System.out.println("error");
            bookingerror.setVisible(true);
            errmsg=1;
            colour=0;
        }
    }

    int flag = 0;
    int colour = 0;
    int errmsg=0;
    @FXML
    private Label error;

    void trying() {
        try {
            name = ClientName.getText();
            mobile = Mobile.getText();
            stime = time.getValue();
            date = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
            error.setVisible(true);
            flag = 1;
        }
        ;
    }

    @FXML
    void NextAction(ActionEvent event) {
        try {
            name = ClientName.getText();
            mobile = Mobile.getText();
            stime = time.getValue();
            date = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
            error.setVisible(true);
            flag = 1;
        }
        if (flag == 1) {
            flag = 0;
            trying();
        }

        if (flag == 0) {
            try {
                String serverAddress = "127.0.0.1";
                int serverPort = 44445;
                nc = new NetworkUtil(serverAddress, serverPort);
                name = ClientName.getText();
                mobile = Mobile.getText();
                stime = time.getValue();
                date = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                nc.write(name + "," + mobile + "," + stime + "," + date);
            } catch (Exception e) {
                System.out.println("Here1" + e);
            }
            creation();
                String choco = (String) nc.read();
                System.out.println(choco);
                if (choco.equals("-1")) {
                    //creation();
                    //continue;
                } else {
                    StringTokenizer tng = new StringTokenizer(choco, ",");
                    System.out.println(tng.countTokens());
                    while (tng.hasMoreTokens()) {
                        int i = Integer.parseInt(tng.nextToken());
                        arr.add(i);
                        System.out.println(arr);
                        seats[i].setFill(Color.CHOCOLATE);
                        System.out.println(arr);
                        //create1();

                    }


                    //System.out.println(choco);
           /* try {
                String choco = (String) nc.read();
                StringTokenizer tng = new StringTokenizer(choco, ",");
                while (tng.hasMoreTokens()) {
                    int i = Integer.parseInt(tng.nextToken());
                    arr.add(i);
                    //System.out.println(arr);
                }
                System.out.println(arr);
                create1();

            } catch (Exception e) {
                creation();
            }
            ;*/

           /* while(tng.hasMoreTokens()&& tng.nextToken()!=null)
            {
                int i = Integer.parseInt(tng.nextToken());
               arr.add(i);
            }*/
                }
            }
            colour=0;
        }



        void create1 ()
        {
            bus.setVisible(true);
            Confirm.setVisible(true);
            Circle driver = new Circle(8, Color.DARKOLIVEGREEN);
            driver.setCenterX(650);
            driver.setCenterY(55);
            //driver.setVisible(true);
            anchor.getChildren().add(driver);
            Rectangle[] seats = new Rectangle[40];
            int u = 0;
            for (int i = 0; i < 10; i++) {
                //seats[i] = new Rectangle(15, 15, Color.DODGERBLUE);
                if (arr.contains(i)) {
                    seats[i] = new Rectangle(15, 15, Color.CHOCOLATE);
                } else {
                    seats[i] = new Rectangle(15, 15, Color.DODGERBLUE);
                }
                seats[i].setLayoutX(470);
                seats[i].setLayoutY(75 + (32 * u));
                u++;
                int finalk = i;
                seats[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        if (colour == 0) {
                            if (seats[finalk].getFill() == Color.CHOCOLATE) {
                                ColorErr.setVisible(true);
                            } else {
                                seats[finalk].setFill(Color.CHOCOLATE);
                                seat = finalk + "";
                                colour = 1;
                            }
                        }
                    }
                });
                seats[i].setVisible(true);
                anchor.getChildren().add(seats[i]);
            }
            int r = 0;
            for (int i = 10; i < 20; i++) {
                //seats[i] = new Rectangle(15, 15, Color.DODGERBLUE);
                if (arr.contains(i)) {
                    seats[i] = new Rectangle(15, 15, Color.CHOCOLATE);
                } else {
                    seats[i] = new Rectangle(15, 15, Color.DODGERBLUE);
                }
                seats[i].setLayoutX(500);
                seats[i].setLayoutY(75 + (32 * r));
                r++;
                seats[i].setVisible(true);
                int finalk = i;
                seats[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        if (colour == 0) {
                            if (seats[finalk].getFill() == Color.CHOCOLATE) {
                                ColorErr.setVisible(true);
                            } else {
                                seats[finalk].setFill(Color.CHOCOLATE);
                                seat = finalk + "";
                                colour = 1;
                            }
                        }
                    }
                });
                anchor.getChildren().add(seats[i]);
            }
            int k = 0;
            for (int i = 20; i < 30; i++) {
                //seats[i] = new Rectangle(15, 15, Color.DODGERBLUE);
                if (arr.contains(i)) {
                    seats[i] = new Rectangle(15, 15, Color.CHOCOLATE);
                } else {
                    seats[i] = new Rectangle(15, 15, Color.DODGERBLUE);
                }
                seats[i].setLayoutX(610);
                seats[i].setLayoutY(75 + (32 * k));
                k++;
                seats[i].setVisible(true);
                int finalk = i;
                seats[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        if (colour == 0) {
                            if (seats[finalk].getFill() == Color.CHOCOLATE) {
                                ColorErr.setVisible(true);
                            } else {
                                seats[finalk].setFill(Color.CHOCOLATE);
                                seat = finalk + "";
                                colour = 1;
                            }
                        }
                    }
                });
                anchor.getChildren().add(seats[i]);
            }
            int j = 0;
            for (int i = 30; i < 40; i++) {

                //seats[i] = new Rectangle(15, 15, Color.DODGERBLUE);
                if (arr.contains(i)) {
                    seats[i] = new Rectangle(15, 15, Color.CHOCOLATE);
                } else {
                    seats[i] = new Rectangle(15, 15, Color.DODGERBLUE);
                }
                seats[i].setLayoutX(640);
                seats[i].setLayoutY(75 + (32 * j));
                j++;
                //seats[i].setVisible(true);
                int finalk = i;

                seats[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        if (colour == 0) {
                            if (seats[finalk].getFill() == Color.CHOCOLATE) {
                                ColorErr.setVisible(true);
                            } else {
                                seats[finalk].setFill(Color.CHOCOLATE);
                                seat = finalk + "";
                                colour = 1;
                            }
                        }
                    }
                });

                //seats[i].setOnAction(myEventHandler);
                anchor.getChildren().add(seats[i]);
            }


        }


        void creation () {
            bus.setVisible(true);
            Confirm.setVisible(true);
            Circle driver = new Circle(8, Color.DARKOLIVEGREEN);
            driver.setCenterX(650);
            driver.setCenterY(55);
            //driver.setVisible(true);
            anchor.getChildren().add(driver);

            int u = 0;
            for (int i = 0; i < 10; i++) {
                seats[i] = new Rectangle(15, 15, Color.DODGERBLUE);
                seats[i].setLayoutX(470);
                seats[i].setLayoutY(75 + (32 * u));
                u++;
                int finalk = i;
                seats[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        /*if (colour ==0) {
                            seats[finalk].setFill(Color.CHOCOLATE);
                            seat = finalk + "";
                            colour = 1;
                        }*/
                       // if (colour<4) {
                        if(colour==0){
                            if (seats[finalk].getFill() == Color.CHOCOLATE) {
                                ColorErr.setVisible(true);
                            } else {
                                seats[finalk].setFill(Color.CHOCOLATE);
                                seat=finalk+"";
                                //seat =seat+","+ finalk + "";
                                //colour++;
                                colour=1;
                            }
                        }
                    }
                });
                seats[i].setVisible(true);
                anchor.getChildren().add(seats[i]);
            }
            int r = 0;
            for (int i = 10; i < 20; i++) {
                seats[i] = new Rectangle(15, 15, Color.DODGERBLUE);
                seats[i].setLayoutX(500);
                seats[i].setLayoutY(75 + (32 * r));
                r++;
                seats[i].setVisible(true);
                int finalk = i;
                seats[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        /*if (colour == 0) {
                            seats[finalk].setFill(Color.CHOCOLATE);
                            seat = finalk + "";
                            colour = 1;
                        }*/
                       // if (colour <4) {
                        if(colour==0){
                            if (seats[finalk].getFill() == Color.CHOCOLATE) {
                                ColorErr.setVisible(true);
                            } else {
                                seats[finalk].setFill(Color.CHOCOLATE);
                                seat=finalk+"";
                                colour=1;
                               // seat =seat+","+ finalk + "";
                                //colour ++;
                            }
                        }
                    }
                });
                anchor.getChildren().add(seats[i]);
            }
            int k = 0;
            for (int i = 20; i < 30; i++) {
                seats[i] = new Rectangle(15, 15, Color.DODGERBLUE);
                seats[i].setLayoutX(610);
                seats[i].setLayoutY(75 + (32 * k));
                k++;
                seats[i].setVisible(true);
                int finalk = i;
                seats[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                       /* if (colour == 0) {
                            seats[finalk].setFill(Color.CHOCOLATE);
                            seat = finalk + "";
                            colour = 1;
                        }*/
                       // if (colour<4) {
                        if(colour==0){
                            if (seats[finalk].getFill() == Color.CHOCOLATE) {
                                ColorErr.setVisible(true);
                            } else {
                                seats[finalk].setFill(Color.CHOCOLATE);
                                //seat =seat+","+ finalk + "";
                                //colour ++;
                                seat=finalk+"";
                                colour=1;
                            }
                        }
                    }
                });
                anchor.getChildren().add(seats[i]);
            }
            int j = 0;
            for (int i = 30; i < 40; i++) {

                seats[i] = new Rectangle(15, 15, Color.DODGERBLUE);
                seats[i].setLayoutX(640);
                seats[i].setLayoutY(75 + (32 * j));
                j++;
                //seats[i].setVisible(true);
                int finalI = i;

                seats[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                       /* if (colour == 0) {
                            seats[finalI].setFill(Color.CHOCOLATE);
                            //int f=finalI+1;
                            seat = finalI + "";
                            colour = 1;
                        }*/
                        //if (colour<4) {
                        if(colour==0){
                            if (seats[finalI].getFill() == Color.CHOCOLATE) {
                                ColorErr.setVisible(true);
                            } else {
                                seats[finalI].setFill(Color.CHOCOLATE);
                                //seat =seat+","+ finalI + "";
                                //colour++;
                                seat=finalI+"";
                                colour=1;
                            }
                        }
                    }
                });

                //seats[i].setOnAction(myEventHandler);
                anchor.getChildren().add(seats[i]);
            }

        }
        }


class myEventHandler implements EventHandler
{
    @Override
    public void handle(Event event)
    {

    }
}



