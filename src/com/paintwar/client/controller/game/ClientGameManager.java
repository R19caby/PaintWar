package com.paintwar.client.controller.game;

import java.net.Inet4Address;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.paintwar.client.model.communication.gameio.GameIOReceiver;

public class ClientGameManager implements IClientGameManager {

	private String clientIp;
	private String gameName;
	private String serverName;
	private int serverRMIPort;
	private GameEntity currentLocalGame;
	private GameIOReceiver currentReceiver;
	
	public ClientGameManager() {
		
	}
	
	public void setClientIp(String newIp) {
		clientIp = newIp;
	}
	
	public void joinGame(String gameName, String serverIp, int serverRMIPort) {
		currentLocalGame = new GameEntity();
		currentReceiver = new GameIOReceiver(clientIp, gameName, serverIp, serverRMIPort, currentLocalGame);
		currentLocalGame.setIoClient(currentReceiver);
		currentLocalGame.openGame(); //the client shouldn't start the game, the server should tell him to
	}
	
	public void disconnect() {
		currentLocalGame.closeGame();
	}
	
	public static void main(String[] args) {
		ClientGameManager manag = new ClientGameManager();
		JTextField serverIP = new JTextField();
		JTextField portRMIServeur = new JTextField();
		JTextField gameName = new JTextField();
		
		serverIP.setText("localhost");
		portRMIServeur.setText("2020");
		gameName.setText("game0");
		
		Object[] message = {
		    "IP du serveur:", serverIP,
		    "Port du serveur:", portRMIServeur,
		    "Nom de la partie: ", gameName
		};

		int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			String clientIP = "localhost";
			try {
				clientIP = Inet4Address.getLocalHost().getHostAddress();
				System.out.println(clientIP);
				//start game
				manag.setClientIp(clientIP);
				manag.joinGame(gameName.getText(), serverIP.getText(), Integer.parseInt(portRMIServeur.getText()));
				
			} catch (UnknownHostException e1) {
				System.out.println("Couldn't get local IP");
				e1.printStackTrace();
			}
		}
	}
	
}
