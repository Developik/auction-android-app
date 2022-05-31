package comp3350.srsys.business;

import java.util.List;

import comp3350.srsys.application.Main;
import comp3350.srsys.application.Services;
import comp3350.srsys.objects.Course;
import comp3350.srsys.objects.Student;
import comp3350.srsys.persistence.DataAccessStub;

public class AccessCourses
{
	private DataAccessStub dataAccess;
	private List<Course> courses;
	private Course course;
	private int currentCourse;

	public AccessCourses()
	{
		dataAccess = (DataAccessStub) Services.getDataAccess(Main.dbName);
		courses = null;
		course = null;
		currentCourse = 0;
	}

    public String getCourses(List<Course> courses)
    {
        courses.clear();
        return dataAccess.getCourseSequential(courses);
    }

	public Course getSequential()
	{
		String result = null;
		if (courses == null)
		{
            result = dataAccess.getCourseSequential(courses);
			currentCourse = 0;
		}
		if (currentCourse < courses.size())
		{
			course = (Course) courses.get(currentCourse);
			currentCourse++;
		}
		else
		{
			courses = null;
			course = null;
			currentCourse = 0;
		}
		return course;
	}

	public Course getRandom(String courseID)
	{
		courses = dataAccess.getCourseRandom(new Course(courseID));
		currentCourse = 0;
		if (currentCourse < courses.size())
		{
			course = (Course) courses.get(currentCourse);
			currentCourse++;
		}
		else
		{
			courses = null;
			course = null;
			currentCourse = 0;
		}
		return course;
	}

	public String insertCourse(Course currentCourse)
	{
		return dataAccess.insertCourse(currentCourse);
	}

	public String updateCourse(Course currentCourse)
	{
		return dataAccess.updateCourse(currentCourse);
	}

	public String deleteCourse(Course currentCourse)
	{
		return dataAccess.deleteCourse(currentCourse);
	}
}
