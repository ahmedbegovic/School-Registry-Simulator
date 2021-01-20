//Name: Ahmed Begovic // Student ID : 500965275

import java.util.ArrayList;

// Make class Student implement the Comparable interface
// Two student objects should be compared by their name
public class Student implements Comparable <Student>
{
  private String name;
  private String id;
  public  ArrayList<CreditCourse> courses;
  
  
  public Student(String name, String id)
  {
	 this.name = name;
	 this.id   = id;
	 courses   = new ArrayList<CreditCourse>();
  }
  
  public String getId()
  {
	  return id;
  }
  
  public String getName()
  {
	  return name;
  }
  
  // add a credit course to list of courses for this student
  public void addCourse(String courseName, String courseCode, String descr, String format,String sem, double grade)
  {
	  // create a CreditCourse object
	  CreditCourse credit = new CreditCourse(courseName, courseCode, descr, format, sem, grade);
	  // set course active
	  credit.setActive();
	  // add to courses array list
	  courses.add(credit);
  }
  
  //Student comment: I will add a method that will return a boolean if student has completed a course
  
  public boolean completed(String courseID)
  {
	  for (CreditCourse tempCourse: this.courses)
	  {
		  if ((tempCourse.getCode().equalsIgnoreCase(courseID)) && (tempCourse.getActive() == false))
		  {
			  return true;
		  }
	  }
	  
	  return false;
  }
  
  //Student comment: I will add a method that will return a CreditCourse from student's arraylist of courses
  
  public CreditCourse getCourse(String courseCode)
  {
	  for (CreditCourse tempCourse : this.courses)
	  {
		  if(tempCourse.getCode().equalsIgnoreCase(courseCode))
		  {
			  return tempCourse;
		  }
	  }
	  
	  return null;
  }
  
  // Prints a student transcript
  // Prints all completed (i.e. non active) courses for this student (course code, course name, 
  // semester, letter grade
  // see class CreditCourse for useful methods
  public void printTranscript()
  {
	  for (CreditCourse tempCourse : this.courses)
	  {
		  if (tempCourse.getActive() == false)
		  {
			  System.out.println(tempCourse.displayGrade());
		  }
	  }
  }
  
  // Prints all active courses this student is enrolled in
  // see variable active in class CreditCourse
  public void printActiveCourses()
  {
	 for (CreditCourse tempCourse : this.courses)
	 {
		 if (tempCourse.getActive() == true)
		 {
			 System.out.println(tempCourse.getDescription());
		 }
	 }
  }
  
  //Student comment: I will add a function that will print credit courses of the student
  
  public void printCreditCourses()
  {
	  for(CreditCourse tempCourse : this.courses)
	  {
		  System.out.println(tempCourse.getDescription());
	  }
  }
  
  // Drop a course (given by courseCode)
  // Find the credit course in courses arraylist above and remove it
  // only remove it if it is an active course
  public void removeActiveCourse(String courseCode)
  {
	 CreditCourse deletedCourse = null;
	 for (CreditCourse tempCourse : this.courses)
	 {
		 if (tempCourse.getCode().equalsIgnoreCase(courseCode))
		 {
			 if (tempCourse.getActive() == true)
			 {
				 deletedCourse = tempCourse;
			 }
		 }
	 }
	 
	 this.courses.remove(deletedCourse);
  }
  
  public String toString()
  {
	  return "Student ID: " + id + " Name: " + name;
  }
  
  // override equals method inherited from superclass Object
  // if student names are equal *and* student ids are equal (of "this" student
  // and "other" student) then return true
  // otherwise return false
  // Hint: you will need to cast other parameter to a local Student reference variable
  public boolean equals(Object other)
  {
	  Student temp = (Student) other;
	  
	  if ((this.getId().equals(temp.getId())) && (this.getName().equals(temp.getName()))) 
	  {
		  return true;
	  }
	  
	  return false;
  }
  
  //Student comment: Method for comparing for the Comparable interface
  public int compareTo(Student other)
  {
	  int compare = 0;
	  compare = this.getName().compareToIgnoreCase(other.getName());
	  return compare;
  }
  
}
