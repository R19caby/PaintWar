package com.paintwar.server.logger;

public class Logger
{
	private static boolean verbose = true;

	public static void printMessage(String message)
	{
		if (verbose)
		{
			System.out.println(message);
		}
	}
}
