package comp3350.srsys.objects;

public class Course
{
	private String courseID;
	private String courseName;

	public Course(String newID)
	{
		courseID = newID;
		courseName = null;
	}

	public Course(String newID, String newCourseName)
	{
		courseID = newID;
		courseName = newCourseName;
	}

	public String getCourseID()
	{
		return (courseID);
	}

	public String getCourseName()
	{
		return (courseName);
	}

	public String toString()
	{
		return "Course: " +courseID +" " +courseName;
	}
	
	public boolean equals(Object object)
	{
		boolean result;
		Course c;
		
		result = false;
		
		if (object instanceof Course)
		{
			c = (Course) object;
			if (((c.courseID == null) && (courseID == null)) || (c.courseID.equals(courseID)))
			{
				result = true;
			}
		}
		return result;
	}
}