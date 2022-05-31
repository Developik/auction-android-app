package comp3350.srsys.objects;

public class Student
{
	private String studentID;
	private String studentName;
	private String studentAddress;

	public Student(String newID)
	{
		studentID = newID;
		studentName = null;
		studentAddress = null;
	}

	public Student(String newID, String newStudentName, String newStudentAddress)
	{
		studentID = newID;
		studentName = newStudentName;
		studentAddress = newStudentAddress;
	}

	public String getStudentID()
	{
		return (studentID);
	}

	public String getStudentName()
	{
		return (studentName);
	}

	public String getStudentAddress()
	{
		return (studentAddress);
	}

	public String toString()
	{
		return "Student: " +studentID +" " +studentName +" " +studentAddress;
	}
	
	public boolean equals(Object object)
	{
		boolean result;
		Student s;
		
		result = false;
		
		if (object instanceof Student)
		{
			s = (Student) object;
			if (((s.studentID == null) && (studentID == null)) || (s.studentID.equals(studentID)))
			{
				result = true;
			}
		}
		return result;
	}
}
