package comp3350.srsys.objects;

public class SC
{
	private String studentID;
	private String courseID;
	private String studentName;
	private String courseName;
	private String grade;

	public SC(String newStudentID, String newCourseID)
	{
		studentID = newStudentID;
		courseID = newCourseID;
		studentName = null;
		courseName = null;
		grade = null;
	}

	public SC(String newStudentID, String newCourseID, String newStudentName, String newCourseName, String newGrade)
	{
		studentID = newStudentID;
		courseID = newCourseID;
		studentName = newStudentName;
		courseName = newCourseName;
		grade = newGrade;
	}

	public String getStudentID()
	{
		return (studentID);
	}

	public String getStudentName()
	{
		return (studentName);
	}

	public String getCourseID()
	{
		return (courseID);
	}

	public String getCourseName()
	{
		return (courseName);
	}

	public String getGrade()
	{
		return grade;
	}

	public String toString()
	{
		return "SC: " +studentID +" " +courseID +" " +grade;
	}
	
	public boolean equals(Object object)
	{
		boolean result;
		SC sc;
		
		result = false;
		
		if (object instanceof SC)
		{
			sc = (SC) object;
			if ((((sc.studentID == null) && (studentID == null)) || ((sc.studentID != null) && (sc.studentID.equals(studentID))))
			 && (((sc.courseID == null)  && (courseID == null))  || ((sc.courseID != null)  && (sc.courseID.equals(courseID)))))
			{
				result = true;
			}
		}
		return result;
	}
}
