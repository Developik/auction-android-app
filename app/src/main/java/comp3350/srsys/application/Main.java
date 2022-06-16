package comp3350.srsys.application;

public class Main
{
	public static final String dbName="SC";

	public static void main(String[] args) throws Exception {
		startUp();

		shutDown();
		System.out.println("All done");
	}

	public static void startUp(){
		try {
			Services.createDataAccess(dbName);
		}
		catch (Exception e){}
	}

	public static void shutDown()
	{
		Services.closeDataAccess();
	}
}
