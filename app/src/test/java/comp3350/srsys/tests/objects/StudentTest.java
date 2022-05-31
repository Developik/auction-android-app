package comp3350.srsys.tests.objects;

import junit.framework.TestCase;

import comp3350.srsys.objects.Student;

public class StudentTest extends TestCase
{
	public StudentTest(String arg0)
	{
		super(arg0);
	}

	public void testStudent1()
	{
		Student student;
		
		System.out.println("\nStarting testStudent");
		
		student = new Student("123", "Joe", "12 Street");
		assertNotNull(student);
		assertTrue("123".equals(student.getStudentID()));
		assertTrue("Joe".equals(student.getStudentName()));
		assertTrue("12 Street".equals(student.getStudentAddress()));
		
		System.out.println("Finished testStudent");
	}
}