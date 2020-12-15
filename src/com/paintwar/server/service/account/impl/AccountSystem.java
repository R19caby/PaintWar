package com.paintwar.server.service.account.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiConsumer;

import com.paintwar.server.exception.AlreadyExistingUsernameException;
import com.paintwar.server.exception.NoSuchUsernameException;
import com.paintwar.server.exception.PasswordDoNotMatchException;
import com.paintwar.server.logger.Logger;
import com.paintwar.server.service.account.interfaces.IAccountSystem;
import com.paintwar.server.service.account.interfaces.IGameRecap;
import com.paintwar.server.service.account.interfaces.IUser;
import com.paintwar.server.service.account.interfaces.IUserFriendlist;
import com.paintwar.server.service.account.interfaces.IUserInventory;
import com.paintwar.server.service.account.interfaces.IUserMetadata;

public class AccountSystem implements IAccountSystem
{
	/**
	 * This is the file where all the data from
	 * {@link com.paintwar.server.service.account.interfaces.IAccountSystem
	 * AccoutSystem} is saved.
	 */
	private final static String SAVE_FILE = "/com.paintwar/server/save/save";

	private HashMap<String, IUser> users;

	public AccountSystem()
	{
		users = new HashMap<String, IUser>();

		try
		{
			load();
		} catch (IOException e)
		{
			Logger.print(e);
		}
	}

	@Override
	public String login(String username, String cryptedPassword)
			throws RemoteException, NoSuchUsernameException, PasswordDoNotMatchException
	{
		IUserMetadata umd = users.get(username).getUserMetadata();
		if (umd.getCryptedPassword().equals(cryptedPassword))
		{
			return umd.getIdentificationToken();
		} else
		{
			throw new PasswordDoNotMatchException();
		}
	}

	@Override
	public void createUser(String username, String cryptedPassword, byte[] salt)
			throws RemoteException, AlreadyExistingUsernameException
	{
		if (users.get(username) != null)
		{
			throw new AlreadyExistingUsernameException();
		} else
		{
			IUserMetadata metadata = new UserMetadata(username, cryptedPassword, salt, new Level(), new UserStats(),
					new GameHistory(new ArrayList<IGameRecap>()));
			IUserInventory inventory = new UserInventory();
			IUserFriendlist friendlist = new UserFriendlist(new ArrayList<IUser>());

			IUser user = new User(metadata, inventory, friendlist);

			users.put(username, user);
		}
	}

	@Override
	public void deleteUser(String username) throws RemoteException, NoSuchUsernameException
	{
		if (users.get(username) != null)
		{
			users.remove(username);
		} else
		{
			throw new NoSuchUsernameException();
		}
	}

	/**
	 * This method saves all the data of the
	 * {@link com.paintwar.server.service.account.interfaces.IAccountSystem
	 * AccoutSystem} to {@link SAVE_FILE this file}.
	 * 
	 * @throws IOException When the file cannot be found or is corrupted.
	 */
	private void save() throws IOException
	{
		Logger.print("Saving ...");

		File saveFile = new File(SAVE_FILE);

		FileOutputStream fileOut = new FileOutputStream(saveFile);
		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

		users.forEach(new BiConsumer<String, IUser>()
		{
			@Override
			public void accept(String username, IUser user)
			{
				try
				{
					Logger.print(String.format("{0} is being saved...", user.toString()));
					objectOut.writeObject(user);
				} catch (IOException e)
				{
					Logger.print(e);
				}
			}
		});

		objectOut.close();
		fileOut.close();

		Logger.print("Successfully saved.");
	}

	/**
	 * Loads the user data from disk. The data is saved in the same location as it
	 * is loaded. It is loaded from {@link SAVE_FILE this file}.
	 */
	private void load() throws IOException
	{
		try
		{
			// First, we need the file where the data is saved. See save method for more
			// informations about the format in which the data is saved. It is important
			// that the same format is used to load the data, otherwhise, the loading method
			// is much more complicated (use of instanceof the know the type of each
			// objects).
			File saveFile = new File(SAVE_FILE);

			// Then, we need 2 streams : the first is the file input stream, it will output
			// the raw from the file.
			FileInputStream fileInput = new FileInputStream(saveFile);

			// The second is the object input stream, it will read the raw data and output
			// java Objects from the first stream.
			ObjectInputStream objectInput = new ObjectInputStream(fileInput);

			// We start loading the data from the stream.
			Logger.print(String.format("Loading objects from the file {0}.", SAVE_FILE));

			// Here we can load any data, but we only have IUser Objects in our save file.

			// -----------------------------------------------------------------------------
			Logger.print(String.format("Loading users from the file {0}.", SAVE_FILE));
			HashMap<String, IUser> loadedUsers = new HashMap<String, IUser>();

			// We'll iterate over the stream until we cannot anymore. The try-catch block
			// will output an exception if : 1) we reach the end of the stream 2) we try to
			// cast IUser to an Object that doesn't implements IUser. We need a boolean to
			// stop iterate over the stream.
			boolean empty = false;

			while (!empty)
			{
				try
				{
					// Read objects
					IUser user = (IUser) objectInput.readObject();

					Logger.print("Loading " + user.toString() + "...");

					// save objects in the hashmap
					loadedUsers.put(user.getUserMetadata().getUsername(), user);

				} catch (Exception e1)
				{
					// As previously said, this exception is thrown when the stream is empty or when
					// the object we tried to read isn't an IUser.
					Logger.print(String.format("All users from the file {0} have been successfully loaded", SAVE_FILE));

					// We save all our loaded users into the attribute users.
					users = loadedUsers;

					// We can stop iterate over the stream.
					empty = true;
				}
			}
			// -----------------------------------------------------------------------------

			// Here we can continue to load objects from the stream, if needed.

			Logger.print(String.format("All objects for the file {0} have been loaded", SAVE_FILE));

			// Don't forget to close the streams.
			Logger.print("Closing all input streams.");
			fileInput.close();
			objectInput.close();

		} catch (IOException e)
		{
			throw e;
		} catch (Exception e2)
		{
			// This exception is thrown if something went wrong, like : the save is
			// corrupted, the version has changed, a NullPointerException is thrown when
			// accessing the user's name, or many more.
			Logger.print(e2);
		}
	}
}
