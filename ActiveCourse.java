//Name: Ahmed Begovic // Student ID : 500965275

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Active University Course


public class ActiveCourse extends Course
{
	private ArrayList<Student> students; 
	private String             semester;
	private int lectureStart;
	private int lectureDuration;
	private String lectureDay;
   // Add a constructor method with appropriate parameters
   // should call super class constructor to initialize inherited variables
   // make sure to *copy* students array list being passed in into new arraylist of students
   // see class Registry to see how an ActiveCourse object is created and used

	public ActiveCourse(String name, String code, String descr, String fmt,String semester, ArrayList<Student> students)
   {
	   super(name, code, descr, fmt);
	   this.semester = semester;
	   this.students = new ArrayList<Student>(students);
	   this.lectureStart = 0;
	   this.lectureDuration = 0;
	   this.lectureDay = "";
   }
   
   public String getSemester()
   {
	   return semester;
   }
   
   // Prints the students in this course  (name and student id)
   
   public void printClassList()
   {
	   for (Student tempStudent : this.students)
	   {
		   System.out.println(tempStudent);
	   }
   }
   
   //Student comment: I will add a method that will return whether or not a student is enrolled in the active course
   
   public boolean isEnrolled(String studentID)
   {
	   for (Student tempStudent : this.students)
	   {
		   if(tempStudent.getId().equalsIgnoreCase(studentID))
		   {
			   return true;
		   }
	   }
	   return false;
   }
   
   //Student comment: I will add a method that will add a student to the current array list of students
   
   public void addStudent(Student student)
   {
	   this.students.add(student);
   }
   
   //Student: Method for the scheduler so I can input the course code into the time table
   public String getCourseCode()
   {
	  return super.getCode();
   }
   
   //Student comment: I will add a method that will remove a student from the current array list of students
   
   public void removeStudent(Student student)
   {
	   this.students.remove(student);
   }
   
   //Student comment: I will add a method that will return a student from the active course
   
   public Student getStudent(String studentID)
   {
	   for (Student tempStudent : students)
	   {
		   if (tempStudent.getId().equalsIgnoreCase(studentID))
		   {
			   return tempStudent;
		   }
	   }
	   return null;
   }
   
   //Student comment: I'll add methods to get the lecture day, duration and lecture start as well as setting those three variables (4 methods in total)
   
   public int getLectureStart()
   {
	   return this.lectureStart;
   }
   
   public String getLectureDay()
   {
	   return this.lectureDay;
   }
   
   public int getDuration()
   {
	   return this.lectureDuration;
   }
   
   public void setDate (String day, int start, int duration)
   {
	   this.lectureStart = start;
	   this.lectureDay = day;
	   this.lectureDuration = duration;
   }
   
   // Prints the grade of each student in this course (along with name and student id)
   // 
   public void printGrades()
   {
	   for (Student tempStudent : this.students)
	   {
		   System.out.println(tempStudent + " Grade: " + getGrade(tempStudent.getId()));
	   }
   }
   
   // Returns the numeric grade in this course for this student
   // If student not found in course, return 0 
   public double getGrade(String studentId)
   {
	  // Search the student's list of credit courses to find the course code that matches this active course
	  for (Student tempStudent : this.students)
	  {
		  if (tempStudent.getId().equalsIgnoreCase(studentId))
		  {
			  for (CreditCourse tempCourse : tempStudent.courses)
			  {
				  if(tempCourse.getCode().equalsIgnoreCase(this.getCode()))
				  {
					  return tempCourse.grade;
				  }
			  }
		  }
	  }
	  // see class Student method getGrade() 
	  // return the grade stored in the credit course object
	  return 0; 
   }
   
   // Returns a String containing the course information as well as the semester and the number of students 
   // enrolled in the course
   // must override method in the superclass Course and use super class method getDescription()
   public String getDescription()
   {
	   return super.getDescription() + " - " + this.semester + " - Class size: "+ students.size();
   }
     
   // Sort the students in the course by name using the Collections.sort() method with appropriate arguments
   // Make use of a private Comparator class below
   public void sortByName()
   {
 	  Collections.sort(students, new NameComparator());
   }
   
   // Fill in the class so that this class implement the Comparator interface
   // This class is used to compare two Student objects based on student name
   private class NameComparator implements Comparator <Student>
   {
   	 public int compare(Student x, Student y)
   	 {
   		 return x.getName().compareToIgnoreCase(y.getName());
   	 }
   	 
   }
   
   // Sort the students in the course by student id using the Collections.sort() method with appropriate arguments
   // Make use of a private Comparator class below
   public void sortById()
   {
	   Collections.sort(students, new IdComparator());
   }
   
   // Fill in the class so that this class implement the Comparator interface
   // This class is used to compare two Student objects based on student id
   private class IdComparator implements Comparator <Student>
   {
	   public int compare(Student x, Student y)
	   	 {
	   		 return x.getId().compareToIgnoreCase(y.getId());
	   	 }
   }
}
