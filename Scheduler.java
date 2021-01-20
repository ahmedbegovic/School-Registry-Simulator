//Name: Ahmed Begovic, Student ID: 500965275

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;

public class Scheduler 
{
    // In main() after you create a Registry object, create a Scheduler object and pass in the students ArrayList/TreeMap
	// If you do not want to try using a Map then uncomment
	// the line below and comment out the TreeMap line
	
	//ArrayList<Student> students;
	
	TreeMap<String,ActiveCourse> schedule;
		
	public Scheduler(TreeMap<String,ActiveCourse> courses)
	{
	  schedule = courses;
	}
	
	public void setDayAndTime(String courseCode, String day, int startTime, int duration) throws RuntimeException
	{
		
		//Student: create a set for days so it's easier to check for exception
		courseCode = courseCode.toUpperCase();
		TreeSet<String> days = new TreeSet<String>();
		days.add("Mon");
		days.add("Tue");
		days.add("Wed");
		days.add("Thur");
		days.add("Fri");
		
		//Student: checking for exceptions
		if(!schedule.containsKey(courseCode))
		{
			throw new UnknownCourse(courseCode + " does not exist.");
		}
		
		if(!days.contains(day))
		{
			throw new InvalidDay(day + " is not in the schedule.");
		}
		
		if((startTime < 800) || (startTime > 1700))
		{
			throw new InvalidTime(String.valueOf(startTime));
		}
		
		if ((startTime + duration * 100) > 1700)
		{
			throw new InvalidTime(String.valueOf(startTime));
		}
		
		if ((duration < 1) || (duration > 3))
		{
			throw new InvalidDuration(String.valueOf(duration));
		}
		
		for (ActiveCourse tempCourse : schedule.values())
		{
			int courseLectureEnd = tempCourse.getLectureStart() + tempCourse.getDuration() * 100;
			
			if(day.equalsIgnoreCase(tempCourse.getLectureDay()))
			{
				if((tempCourse.getLectureStart() <= startTime) && (courseLectureEnd >= startTime))
				{
					throw new LectureTimeCollision("Lecture time collision.");
				}
				
				if ((startTime >= tempCourse.getLectureStart()) && (courseLectureEnd >= startTime))
				{
					throw new LectureTimeCollision("Lecture time collision.");
				}
			}
		}
		
		//Student: setting the date, time, length
		ActiveCourse tempCourse = schedule.get(courseCode);
		tempCourse.setDate(day, startTime, duration);
		
	}
	
	//Student: Method for clearing the schedule
	public void clearSchedule(String courseCode)
	{
		// see Assignment doc
		courseCode = courseCode.toUpperCase();
		ActiveCourse tempCourse = null;
		tempCourse = schedule.get(courseCode);
		
		if(tempCourse != null)
		{
			tempCourse.setDate("", 0, 0);
		}

		
	}
	
	
	//Student: Method for printing the schedule
	public void printSchedule()
	{
		// see assignment doc
		String[][] timetable = new String[10][6];
		String[] days = new String[] {"Days:","Mon","Tue","Wed","Thur","Fri"};
		
		//Student: Nested for loop to fill the time table with days and hours
		for (int i = 0; i < timetable.length; i++)
		{
			if (i == 0)
			{
				for (int j = 0; j < timetable[i].length; j++)
				{
				timetable[i][j] = days[j];
				}
			}
			
			if ((i > 0) && (i < 3))
			{
				timetable[i][0] = "0" + (8+i-1)*100;
			}
			if (i >= 3)
			{
				timetable[i][0] = String.valueOf((8+i)*100);
			}
			
		}
		
		//Student: Nested for loop to go through each course and print the schedule
		for (ActiveCourse tempCourse : schedule.values())
		{
			String lectureTime = String.valueOf(tempCourse.getLectureStart());
			int tempDay = 0;
			int tempHour = 0;
			
			if(Integer.valueOf(lectureTime) < 1000)
			{
				lectureTime = "0" + lectureTime;
			}
			
			//Student: Find the matching day and hour for the course
			for(int i = 0; i < timetable.length; i++)
			{
				if (i == 0)
				{
					for (int j = 0; j < timetable[i].length; j++)
					{
						if(tempCourse.getLectureDay().equalsIgnoreCase(timetable[i][j]))
						{
							tempDay = j;
						}
					}
				}
				
				if(lectureTime.equalsIgnoreCase(timetable[i][0]))
				{
					tempHour = i;
				}
			}
			
			//Student: Repeat printing the course for the duration
			for(int i = 0; i < tempCourse.getDuration(); i++)
			{
				timetable[tempHour + i][tempDay] = tempCourse.getCourseCode();
			}
			
		}
		
		//Student: fill the nulls with empty space
		for (int i = 0; i < timetable.length; i++)
		{
			for (int j = 0; j < timetable[i].length; j++)
			{
				if(timetable[i][j] == null)
				{
					timetable[i][j] = "";
				}
			}
		}
		
		//Student: print the timetable
		for (int i = 0; i < timetable.length; i++)
		{
			for (int j = 0; j < timetable[i].length; j++)
			{
				System.out.print(timetable[i][j] + "\t");
			}
			System.out.println();
		}
		
	}
	
}

//Student: exceptions for scheduler

class UnknownCourse extends RuntimeException
{
	public UnknownCourse() {}
	
	public UnknownCourse (String message) {
		super(message);
	}
}

class InvalidDay extends RuntimeException
{
	public InvalidDay() {}
	
	public InvalidDay (String message) {
		super(message);
	}
}

class InvalidTime extends RuntimeException
{
	public InvalidTime() {}
	
	public InvalidTime (String message) {
		super(message);
	}
}

class InvalidDuration extends RuntimeException
{
	public InvalidDuration() {}
	
	public InvalidDuration (String message) {
		super(message);
	}
}

class LectureTimeCollision extends RuntimeException
{
	public LectureTimeCollision() {}
	
	public LectureTimeCollision (String message) {
		super(message);
	}
}
