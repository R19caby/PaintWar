package com.paintwar.client.model.communication.gameio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.paintwar.client.model.communication.IClientCommandReceiver;
import com.paintwar.server.logger.Logger;

public class GameIOCommandReceiver implements IClientCommandReceiver {

	private GameIOReceiver gameReceiver;
	private List<String> commands;
	
	
	public GameIOCommandReceiver(GameIOReceiver gameReceiver) {
		super();
		this.gameReceiver = gameReceiver;
		this.commands = new ArrayList<String>();
		commands.add("Bounds");
		commands.add("Draw");
		commands.add("Delete");
	}

	@Override
	public boolean isCommandAvailable(String command) {
		return commands.contains(command);
	}

	@Override
	public void executeCommand(String command, String name, HashMap<String, Object> args) {
		switch (command) {
			case ("Bounds"): {
				gameReceiver.objectUpdateBounds(name, (int)args.get ("x"), (int)args.get ("y"), (int)args.get ("w"), (int)args.get ("h"));
				break;
			}
			case ("Draw"): {
				gameReceiver.addDrawing(name, (int)args.get ("x"), (int)args.get ("y"), (int)args.get ("w"), (int)args.get ("h"));
				break;
			}
			case ("Delete"): {
				gameReceiver.deleteDessin(name);
				break;
			}
			default: {
				Logger.print("[Client/GameIO/Received message] Invalid message " + command);
				break;
			}
		}
	}

	//helps filtering messages for other services
	@Override
	public String getOriginPackage() {
		return "com.paintwar.server.service.game";
	}

}
