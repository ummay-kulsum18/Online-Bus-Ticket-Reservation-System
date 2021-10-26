package sample;

import java.util.StringTokenizer;

public class clientreadThread implements Runnable {
    private Thread thread;
    private NetworkUtil nc;
    private ClientController clientController;
    private ClientMain clientMain;
    public String choco;

    public clientreadThread(NetworkUtil nc, ClientController clientController) {
        this.thread = new Thread();
        this.nc = nc;
        this.clientController = clientController;
        thread.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                 choco = (String) nc.read();
                /*if (choco.equals("-1")) {
                    clientController.creation();
                } else {
                    StringTokenizer tng = new StringTokenizer(choco, ",");
                    while (tng.hasMoreTokens()) {
                    /*if(tng.nextToken().equals(null)){
                        break;
                    }
                        int i = Integer.parseInt(tng.nextToken());
                        clientController.arr.add(i);
                        //System.out.println(arr);
                    }
                    System.out.println(clientController.arr);
                    clientController.create1();

                }*/
                //nc.closeConnection();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        nc.closeConnection();
    }
    void setClientMain(ClientMain main)
    {
        this.clientMain=main;
    }
}