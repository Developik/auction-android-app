package comp3350.srsys.tests.business;

import junit.framework.TestCase;

import comp3350.srsys.business.Calculate;
import comp3350.srsys.objects.SC;
import java.util.List;
import java.util.ArrayList;

public class CalculateGPATest extends TestCase
{
	List <SC>list;
	SC sc;
	String resultGPA;

	public CalculateGPATest(String arg0)
	{
		super(arg0);
	}

	public void testNullList()
	{
		System.out.println("\nStarting testCalculateGPA: null list");

		resultGPA = "";
		resultGPA = Calculate.gpa(null);

		assertNotNull(resultGPA);
		assertTrue(" ".equals(resultGPA));

		System.out.println("Finished testCalculateGPA: null list");
	}
	
	public void testEmptyList()
	{
		System.out.println("\nStarting testCalculateGPA: empty list");

		resultGPA = "";
		list = new ArrayList<SC>();
		resultGPA = Calculate.gpa(list);

		assertNotNull(resultGPA);
		assertTrue(" ".equals(resultGPA));

		System.out.println("Finished testCalculateGPA: empty list");
	}
	
	public void testNullObject()
	{	
		List <SC>list;
		
		System.out.println("\nStarting testCalculateGPA: null object");

		resultGPA = "";
		list = new ArrayList<SC>();
		list.add(null);
		resultGPA = Calculate.gpa(list);

		assertNotNull(resultGPA);
		assertTrue("?".equals(resultGPA));

		System.out.println("Finished testCalculateGPA: null object");
	}

	public void testMissingGrades()
	{
		System.out.println("\nStarting testCalculateGPA: missing grades");

		resultGPA = "";
		list = new ArrayList<SC>();
		sc = new SC("123", "Joe", null, null, " ");
		list.add(sc);
		sc = new SC("456", "Mary", null, null, " ");
		list.add(sc);
		resultGPA = Calculate.gpa(list);

		assertNotNull(resultGPA);
		assertTrue(" ".equals(resultGPA));

		System.out.println("Finished testCalculateGPA: missing grades");
	}

	public void testValidGrades()
	{
		System.out.println("\nStarting testCalculateGPA: valid grades");

		resultGPA = "";
		list = new ArrayList<SC>();
		sc = new SC("123", "Joe", null, null, "A");
		list.add(sc);
		sc = new SC("456", "Mary", null, null, "B");
		list.add(sc);
		resultGPA = Calculate.gpa(list);

		assertNotNull(resultGPA);
		assertTrue("3.5".equals(resultGPA));

		System.out.println("Finished testCalculateGPA: valid grades");
	}
	
	public void testInvalidGrades()
	{
		System.out.println("\nStarting testCalculateGPA: invalid grades");

		resultGPA = "";
		list = new ArrayList<SC>();
		sc = new SC("123", "Joe", null, null, "X");
		list.add(sc);
		sc = new SC("456", "Mary", null, null, "Y");
		list.add(sc);
		resultGPA = Calculate.gpa(list);

		assertNotNull(resultGPA);
		assertTrue("?".equals(resultGPA));

		System.out.println("Finished testCalculateGPA: invalid grades");
	}

	public void testSomeInvalidGrades()
	{
		System.out.println("\nStarting testCalculateGPA: some invalid grades");

		resultGPA = "";
		list = new ArrayList<SC>();
		sc = new SC("123", "Joe", null, null, "X");
		list.add(sc);
		sc = new SC("456", "Mary", null, null, "A");
		list.add(sc);
		sc = new SC("457", "Sally", null, null, "B");
		list.add(sc);
		resultGPA = Calculate.gpa(list);

		assertNotNull(resultGPA);
		assertTrue("?".equals(resultGPA));

		System.out.println("Finished testCalculateGPA: some invalid grades");
	}	
	
	public void testBlankGrades()
	{
		System.out.println("\nStarting testCalculateGPA: blank grades");

		resultGPA = "";
		list = new ArrayList<SC>();
		sc = new SC("123", "Joe", null, null, " ");
		list.add(sc);
		sc = new SC("124", "Bill", null, null, " ");
		list.add(sc);
		sc = new SC("456", "Mary", null, null, "X");
		list.add(sc);
		sc = new SC("457", "Sally", null, null, "X");
		list.add(sc);
		resultGPA = Calculate.gpa(list);

		assertNotNull(resultGPA);
		assertTrue("?".equals(resultGPA));

		System.out.println("Finished testCalculateGPA: blank grades");
	}

	public void testMixedGrades()
	{
		System.out.println("\nStarting testCalculateGPA: mixed grades");

		resultGPA = "";
		list = new ArrayList<SC>();
		sc = new SC("123", "Joe", null, null, " ");
		list.add(sc);
		sc = new SC("124", "Bill", null, null, " ");
		list.add(sc);
		sc = new SC("456", "Mary", null, null, "A");
		list.add(sc);
		sc = new SC("457", "Sally", null, null, "F");
		list.add(sc);
		resultGPA = Calculate.gpa(list);

		assertNotNull(resultGPA);
		assertTrue("2.0".equals(resultGPA));

		System.out.println("Finished testCalculateGPA: mixed grades");
	}
}