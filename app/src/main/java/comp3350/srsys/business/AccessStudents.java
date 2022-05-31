package comp3350.srsys.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.srsys.application.Main;
import comp3350.srsys.application.Services;
import comp3350.srsys.objects.Student;
import comp3350.srsys.persistence.DataAccessStub;

public class AccessStudents
{
	private DataAccessStub dataAccess;
	private List<Student> students;
	private Student student;
	private int currentStudent;

	public AccessStudents()
	{
		dataAccess = (DataAccessStub) Services.getDataAccess(Main.dbName);
		students = null;
		student = null;
		currentStudent = 0;
	}

    public String getStudents(List<Student> students)
    {
        students.clear();
        return dataAccess.getStudentSequential(students);
    }

    public Student getSequential()
    {
        String result = null;
        if (students == null)
        {
            students = new ArrayList<Student>();
            result = dataAccess.getStudentSequential(students);
            currentStudent = 0;
        }
        if (currentStudent < students.size())
        {
            student = (Student) students.get(currentStudent);
            currentStudent++;
        }
        else
        {
            students = null;
            student = null;
            currentStudent = 0;
        }
        return student;
    }

	public Student getRandom(String studentID)
	{
		student = null;
		if (studentID.trim().equals(""))
		{
			//System.out.println("*** Invalid student number");
		}
		else
		{
			students = dataAccess.getStudentRandom(new Student(studentID));
			if (students.size()==1)
			{
				student = (Student) students.get(0);
			}
		}
		return student;
	}

	public String insertStudent(Student currentStudent)
	{
		return dataAccess.insertStudent(currentStudent);
	}

	public String updateStudent(Student currentStudent)
	{
		return dataAccess.updateStudent(currentStudent);
	}

	public String deleteStudent(Student currentStudent)
	{
		return dataAccess.deleteStudent(currentStudent);
	}
}
