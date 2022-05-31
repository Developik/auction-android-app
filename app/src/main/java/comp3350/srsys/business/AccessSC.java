package comp3350.srsys.business;

import java.util.List;

import comp3350.srsys.application.Main;
import comp3350.srsys.application.Services;
import comp3350.srsys.objects.SC;
import comp3350.srsys.persistence.DataAccessStub;

public class AccessSC
{
	private DataAccessStub dataAccess;
	private List<SC> elements;

	private SC studentCourse;
	private int currentSC;

	private int currentCS;

	private String gpa;

	public AccessSC()
	{
		dataAccess = (DataAccessStub) Services.getDataAccess(Main.dbName);
		elements = null;
		currentSC = 0;
		currentCS = 0;
	}

	public SC getSC(String studentID)
	{
		if (elements == null)
		{
			elements = dataAccess.getSC(new SC(studentID, null));
			gpa = Calculate.gpa(elements);
			currentSC = 0;
		}
		if (currentSC < elements.size())
		{
			studentCourse = (SC) elements.get(currentSC);
			currentSC++;
		}
		else
		{
			elements = null;
			studentCourse = null;
			currentSC = 0;
		}
		return studentCourse;
	}

	public SC getCS(String courseID)
	{
		if (elements == null)
		{
			elements = dataAccess.getCS(new SC(null, courseID));
			gpa = Calculate.gpa(elements);
			currentCS = 0;
		}
		if (currentCS < elements.size())
		{
			studentCourse = (SC) elements.get(currentCS);
			currentCS++;
		}
		else
		{
			elements = null;
			studentCourse = null;
			currentCS = 0;
		}
		return studentCourse;
	}

	public String getGPA()
	{
		return gpa;
	}
}
