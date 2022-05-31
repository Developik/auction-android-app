package comp3350.srsys.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.srsys.application.Main;
import comp3350.srsys.objects.Student;
import comp3350.srsys.objects.Course;
import comp3350.srsys.objects.SC;

public class DataAccessStub
{
	private String dbName;
	private String dbType = "stub";

	private ArrayList<Student> students;
	private ArrayList<Course> courses;
	private ArrayList<SC> scs;

	public DataAccessStub(String dbName)
	{
		this.dbName = dbName;
	}

	public DataAccessStub()
	{
		this(Main.dbName);
	}

	public void open(String dbName)
	{
		Student student;
		Course course;
		SC mySC;

		students = new ArrayList<Student>();
		student = new Student("100", "Gary Chalmers", "Management");
		students.add(student);
		student = new Student("200", "Selma Bouvier", "University Centre");
		students.add(student);
		student = new Student("300", "Arnie Pye", "Frank Kennedy");
		students.add(student);
		student = new Student("400", "Mary Bailey", "Off Campus");
		students.add(student);

		courses = new ArrayList<Course>();
		course = new Course("COMP3010", "Distributed Computing");
		courses.add(course);
		course = new Course("COMP3020", "Human-Computer Interaction");
		courses.add(course);
		course = new Course("COMP3350", "Software Development");
		courses.add(course);
		course = new Course("COMP3380", "Databases");
		courses.add(course);

		scs = new ArrayList<SC>();
		mySC = new SC("100", "COMP3010", "Gary Chalmers", "Distributed Computing", "C+");
		scs.add(mySC);
		mySC = new SC("200", "COMP3010", "Selma Bouvier", "Distributed Computing", "A+");
		scs.add(mySC);
		mySC = new SC("100", "COMP3350", "Gary Chalmers", "Software Development", "A");
		scs.add(mySC);
		mySC = new SC("300", "COMP3350", "Arnie Pye", "Software Development", "B");
		scs.add(mySC);
		mySC = new SC("100", "COMP3380", "Gary Chalmers", "Databases", "A");
		scs.add(mySC);
		mySC = new SC("200", "COMP3380", "Selma Bouvier", "Databases", "B");
		scs.add(mySC);

		System.out.println("Opened " +dbType +" database " +dbName);
	}

	public void close()
	{
		System.out.println("Closed " +dbType +" database " +dbName);
	}

	public String getStudentSequential(List<Student> studentResult)
	{
        studentResult.addAll(students);
		return null;
	}

	public ArrayList<Student> getStudentRandom(Student currentStudent)
	{
		ArrayList<Student> newStudents;
		int index;
		
		newStudents = new ArrayList<Student>();
		index = students.indexOf(currentStudent);
		if (index >= 0)
		{
			newStudents.add(students.get(index));
		}
		return newStudents;
	}

	public String insertStudent(Student currentStudent)
	{
		// don't bother checking for duplicates
		students.add(currentStudent);
		return null;
	}

	public String updateStudent(Student currentStudent)
	{
		int index;
		
		index = students.indexOf(currentStudent);
		if (index >= 0)
		{
			students.set(index, currentStudent);
		}
		return null;
	}

	public String deleteStudent(Student currentStudent)
	{
		int index;
		
		index = students.indexOf(currentStudent);
		if (index >= 0)
		{
			students.remove(index);
		}
		return null;
	}

	public String getCourseSequential(List<Course> courseResult)
	{
        courseResult.addAll(courses);
		return null;
	}

	public ArrayList<Course> getCourseRandom(Course currentCourse)
	{
		ArrayList<Course> newCourses;
		int index;
		
		newCourses = new ArrayList<Course>();
		index = courses.indexOf(currentCourse);
		if (index >= 0)
		{
			newCourses.add(courses.get(index));
		}
		return newCourses;
	}

	public String insertCourse(Course currentCourse)
	{
		// don't bother checking for duplicates
		courses.add(currentCourse);
		return null;
	}

	public String updateCourse(Course currentCourse)
	{
		int index;
		
		index = courses.indexOf(currentCourse);
		if (index >= 0)
		{
			courses.set(index, currentCourse);
		}
		return null;
	}

	public String deleteCourse(Course currentCourse)
	{
		int index;
		
		index = courses.indexOf(currentCourse);
		if (index >= 0)
		{
			courses.remove(index);
		}
		return null;
	}

	public ArrayList<SC> getSC(SC currentSC)
	{
		ArrayList<SC> newSCs;
		SC sc;
		int counter;
		
		// get the SC objects with the same studentID as currentSC
		newSCs = new ArrayList<SC>();
		for (counter=0; counter<scs.size(); counter++)
		{
			sc = scs.get(counter);
			if (sc.getStudentID().equals(currentSC.getStudentID()))
			{
				newSCs.add(scs.get(counter));
			}
		}
		return newSCs;
	}

	public ArrayList<SC> getCS(SC currentSC)
	{
		ArrayList<SC> newSCs;
		SC cs;
		int counter;
		
		// get the SC objects with the same courseID as currentSC
		newSCs = new ArrayList<SC>();
		for (counter=0; counter<scs.size(); counter++)
		{
			cs = scs.get(counter);
			if (cs.getCourseID().equals(currentSC.getCourseID()))
			{
				newSCs.add(scs.get(counter));
			}
		}
		return newSCs;
	}
}
