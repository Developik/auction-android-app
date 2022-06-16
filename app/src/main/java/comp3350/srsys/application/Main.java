package comp3350.srsys.application;

public class Main
{
	public static final String dbName="SC";

	public static void main(String[] args)
	{
		startUp();

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
