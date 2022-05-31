package comp3350.srsys.tests;

import junit.framework.Test;
import junit.framework.TestSuite;
import comp3350.srsys.tests.objects.CourseTest;
import comp3350.srsys.tests.objects.SCTest;
import comp3350.srsys.tests.objects.StudentTest;
import comp3350.srsys.tests.business.CalculateGPATest;

public class AllTests
{
	public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("All tests");
        testObjects();
        testBusiness();
        return suite;
    }

    private static void testObjects()
    {
        suite.addTestSuite(StudentTest.class);
        suite.addTestSuite(CourseTest.class);
        suite.addTestSuite(SCTest.class);
    }

    private static void testBusiness()
    {
        suite.addTestSuite(CalculateGPATest.class);
    }
}
