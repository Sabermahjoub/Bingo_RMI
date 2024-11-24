package pckg;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BingoImpl extends UnicastRemoteObject implements IBingo {
    private final List<PlayerScore> playersArray;
    private final Random random;

    public BingoImpl() throws RemoteException {
        this.playersArray = Collections.synchronizedList(new ArrayList<>());
        this.random = new Random();
    }


    @Override
    public synchronized int best_score() throws RemoteException {
        int max = 0;
        synchronized (playersArray) { 
            for (PlayerScore PS : playersArray) {
                if (PS.getScore() > max) max = PS.getScore();
            }
        }
        return max;
    }

    @Override
    public synchronized int roll() throws RemoteException {
        return random.nextInt(10); // Generate a number from 0 to 9

		/*
		 * int number = -1; synchronized (allowedNumbers) { // Protect shared list while
		 * (!allowedNumbers.contains(number)) { number = random.nextInt(10); // Generate
		 * a number from 0 to 9 } allowedNumbers.remove(Integer.valueOf(number)); }
		 * return number;
		 */
    }

    @Override
    public synchronized int my_best_score(int id) throws RemoteException {
        int max = 0;
        synchronized (playersArray) {
            for (PlayerScore PS : playersArray) {
                if (PS.getId() == id && PS.getScore() > max) max = PS.getScore();
            }
        }
        return max;
    }

    @Override
    public synchronized void add_round_result(int id, int score) throws RemoteException {
        synchronized (playersArray) {
            PlayerScore newPlayer = new PlayerScore(id, score);
            playersArray.add(newPlayer);
        }
    }

    public synchronized boolean playerExists(int id) {
        synchronized (playersArray) { // Explicitly synchronize during iteration
            for (PlayerScore PS : playersArray) {
                if (PS.getId() == id) return true;
            }
        }
        return false;
    }


	@Override
	public List<Integer> all_my_scores(int id) throws RemoteException {
		List<Integer> list = new ArrayList<>(); 
		for (PlayerScore PS : this.playersArray) {
			if (PS.getId() == id) list.add(PS.getScore());
		}
		return list;
	}
}
