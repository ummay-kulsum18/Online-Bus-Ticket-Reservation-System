package sample;
import javafx.fxml.FXMLLoader;

public class ReadThread implements Runnable {
	private Thread thr;
	private NetworkUtil nc;
	Server server;
	private ClientController clientController;
	 public String st1;


	public ReadThread(NetworkUtil nc,Server server) {
		this.nc=nc;
		this.server=server;
		this.thr = new Thread(this);
		thr.start();
	}
	
	public void run() {
		try {
			while(true) {
				st1=(String)nc.read();

				if(st1.equals(null)) break;
				else {
					/*StringBuffer s = new StringBuffer(st1);
					System.out.println(s);
					s.delete(0, 5);
					st1 = s.toString();*/
					try {
						String sql = "INSERT INTO tjtable " + " VALUES (' " + server.n + " ', ' " + server.m + "', ' " + server.d + "', '" + server.t + "','" + st1 + "')";
						server.oc.updateDB(sql);
						nc.write("succeed");
						//server.oc.connection.setAutoCommit(false);
						//server.oc.connection.commit();
						System.out.println(st1);
						//st1=null;
					}catch(Exception e){
						nc.write("error");
					}
				}
				/*server.n=null;
				server.m=null;
				server.d=null;
				server.t=null;
				server.s=null;
				st1=null;*/


			}
		} catch(Exception e) {
			System.out.println (e);                        
		}
		nc.closeConnection();
	}
}



