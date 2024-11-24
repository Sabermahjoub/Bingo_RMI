package pckg;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class BingoServer {

	public BingoServer() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		try {
			System.setSecurityManager(new SecurityManager());
			LocateRegistry.createRegistry(1099);
			BingoImpl bingo = new BingoImpl();
			Naming.rebind("rmi://localhost:1099/bingo", bingo);
			
			System.out.println("Bingo Server is running !");

		}
		
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
