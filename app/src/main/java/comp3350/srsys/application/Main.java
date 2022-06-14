package comp3350.srsys.application;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import comp3350.srsys.business.ItemLogic;

public class Main
{
	public static final String dbName="SC";

	public static void main(String[] args)
	{
		startUp();

		//CLI.run();
		
		shutDown();
		System.out.println("All done");
	}

	public static void startUp()
	{
		Services.createDataAccess(dbName);
	}

	public static void shutDown()
	{
		Services.closeDataAccess();
	}
}
