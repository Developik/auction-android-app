package comp3350.bms.application;

// Purpose: Starter code for the BMS application.

public class Main
{
	public static final String dbName="SC";
	public static final String stubName="Stub";
	private static String dbPathName = "database/SC";

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

	public static String getDBPathName() {
		if (dbPathName == null)
			return dbName;
		else
			return dbPathName;
	}

	public static void setDBPathName(String pathName) {
		System.out.println("Setting DB path to: " + pathName);
		dbPathName = pathName;
	}
}
