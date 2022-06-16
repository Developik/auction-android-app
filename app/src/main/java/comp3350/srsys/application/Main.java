package comp3350.srsys.application;

public class Main
{
	public static final String dbName="SC";

	public static void main(String[] args) throws Exception {
		startUp();

		shutDown();
		System.out.println("All done");
	}

	public static void startUp() throws Exception {
		Services.createDataAccess(dbName);
	}

	public static void shutDown()
	{
		Services.closeDataAccess();
	}
}
