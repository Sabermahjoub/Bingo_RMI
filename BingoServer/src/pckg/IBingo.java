package pckg;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IBingo extends Remote {
	
	int best_score () throws RemoteException;
	int my_best_score (int id) throws RemoteException;
	int roll() throws RemoteException;
	void add_round_result (int id, int score) throws RemoteException;
	List<Integer> all_my_scores(int id) throws RemoteException;
	//void initiliaze_bingo () throws RemoteException;

}
