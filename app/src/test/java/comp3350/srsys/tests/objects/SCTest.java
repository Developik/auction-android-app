package comp3350.srsys.tests.objects;

import junit.framework.TestCase;

import comp3350.srsys.objects.SC;

public class SCTest extends TestCase
{
	public SCTest(String arg0)
	{
		super(arg0);
	}

	public void testSC1()
	{
		SC sc;

		System.out.println("\nStarting testSC");

		sc = new SC("123", "12345", "Joe", "Software Development", "A");
		assertNotNull(sc);
		assertTrue("123".equals(sc.getStudentID()));
		assertTrue("12345".equals(sc.getCourseID()));
		assertTrue("Joe".equals(sc.getStudentName()));
		assertTrue("Software Development".equals(sc.getCourseName()));
		assertTrue("A".equals(sc.getGrade()));
		assertTrue("Software Development".equals(sc.getCourseName()));

		System.out.println("Finished testSC");
	}
}