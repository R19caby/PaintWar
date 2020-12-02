package com.paintwar.client.logger;

public class Logger
{
	private static boolean verbose = true;

	public static void print(String message)
	{
		if (verbose)
		{
			System.out.println(message);
		}
	}

	public static void print(Exception e)
	{
		if (verbose)
		{
			e.printStackTrace();
		}
	}
}