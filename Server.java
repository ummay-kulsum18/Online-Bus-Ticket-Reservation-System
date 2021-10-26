package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class Server implements Runnable {
	 ServerController serverController;
	private ServerSocket ServSock;
	Thread thread;
	NetworkUtil nc;
	public String n,m,d,t,s,inf=null;
	public MySQLConnect oc;
	StringBuffer str;

	public ObservableList<String> names= FXCollections.observableArrayList();
	
	public Server(ServerController serverController) {
		this.serverController=serverController;
		thread=new Thread(this);
		serverController.address=new Hashtable<>();
		thread.start();
	}

	public void run()
	{
		try {
			ServSock = new ServerSocket(44445);
			while (true) {
				Socket socket = ServSock.accept();
				this.nc = new NetworkUtil(socket);
				String name = (String) nc.read();
				names.add(name);


				StringTokenizer st = new StringTokenizer(name, ",");
				if (st.hasMoreTokens()) {
					n = st.nextToken();
				}
				if (st.hasMoreTokens()) {
					m = st.nextToken();
				}
				if (st.hasMoreTokens()) {
					t = st.nextToken();
				}
				if (st.hasMoreTokens()) {
					d = st.nextToken();
				}
					try {
						oc = new MySQLConnect("localhost", "test", "root", "");
						String query = "SELECT *FROM tjtable WHERE Date=' " + d + " ' AND Time= '" + t + "' ";
						ResultSet rs = oc.searchDB(query);
						while (rs.next()) {
							inf = inf + "," + rs.getString("Seat");

						}
						str = new StringBuffer(inf);
						System.out.println(str);
						str.delete(0, 5);
						inf = str.toString();
						System.out.println("query"+inf);
					} catch (Exception e) {
						inf = "-1";
						System.out.println(inf);
					}
					//System.out.println(str);
					//inf=str.toString();
					nc.write(inf);
					inf=null;
					new ReadThread(nc, this);

				}
		}catch(Exception e) {
			System.out.println("Server starts:"+e);
		}
	}
}

