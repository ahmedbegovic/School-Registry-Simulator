//Name: Ahmed Begovic // Student ID : 500965275

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Registry
{
   private TreeMap<String, Student>      students = new TreeMap<String, Student>();
   private TreeMap<String, ActiveCourse> courses  = new TreeMap<String, ActiveCourse>();
   private String registryID;
   private String registryName;
   
   public Registry() throws FileNotFoundException, IOException
   {
	// Add some students
	   // in A2 we will read from a file
	   
	   //Student: Loading the file and throwing the exceptions
	   File file = new File("students.txt");

	   Scanner scanner = new Scanner(file);
	   
	   while(scanner.hasNextLine())
	   {
		   String line = scanner.nextLine();
		   fetch(line);
		   
		   if ((this.registryID.equals("")) || (this.registryName.equals("")))
		   {
			   throw new IOException();
		   }
		   
		   students.put(registryID, new Student(this.registryName,this.registryID));
		   
		   this.registryID = "";
		   this.registryName = "";
	   }

	   // sort the students alphabetically - see class Student
	   
	   ArrayList<Student> list = new ArrayList<Student>();
	   
	   // Add some active courses with students
	   	// CPS209
	     String courseName = "Computer Science II";
	     String courseCode = "CPS209";
	     String descr = "Learn how to write complex programs!";
	     String format = "3Lec 2Lab";
	     courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
	     // CPS511
	     courseName = "Computer Graphics";
	     courseCode = "CPS511";
	     descr = "Learn how to write cool graphics programs";
	     format = "3Lec";
	     courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"F2020",list));
	     // CPS643
	     courseName = "Virtual Reality";
	     courseCode = "CPS643";
	     descr = "Learn how to write extremely cool virtual reality programs";
	     format = "3Lec 2Lab";
	     courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
	     // CPS706
	     courseName = "Computer Networks";
	     courseCode = "CPS706";
	     descr = "Learn about Computer Networking";
	     format = "3Lec 1Lab";
	     courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
	     // CPS616
	     courseName = "Algorithms";
	     courseCode = "CPS616";
	     descr = "Learn about Algorithms";
	     format = "3Lec 1Lab";
	     courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
   }
   
   //Student comment: I will add two methods, getStudent and getCourse, that will return a student or a course from the registry depending on the student or course code, it is going to be easier to write addCourse and dropCourse methods
   
   public Student getStudent(String studentID)
   {
	   studentID = studentID.toUpperCase();
	   Student tempStudent = null;
	   tempStudent = students.get(studentID);
	   return tempStudent;
   }
   
   public ActiveCourse getCourse(String courseCode)
   {
	   courseCode = courseCode.toUpperCase();
	   ActiveCourse tempCourse = null;
	   tempCourse = courses.get(courseCode);
	   return tempCourse;
   }
   
   // Student comment: I will add a method that will check if the course exists so I can prevent errors from happening
   
   public boolean courseExists(String courseCode)
   {
	   courseCode = courseCode.toUpperCase();
	   ActiveCourse tempCourse = null;
	   tempCourse = courses.get(courseCode);
	   
	   if (tempCourse == null) {
		   return false;
	   }
	   
	   return true;
   }
   
   //Student comment: I will add a method that will check if a student exists so I can prevent errors from happening
   
   public boolean studentExists(String studentID)
   {
	   studentID = studentID.toUpperCase();
	   Student tempStudent = null;
	   tempStudent = students.get(studentID);
	   
	   if (tempStudent == null) {
		   return false;
	   }
	   
	   return true;
   }
   
   //Student comment: A method that will help read the file and get the student ID and name so I can use it for creating exceptions and students
   
   public void fetch(String line)
   {
	   //Find the first digit of the string
	   int i = 0;
	   while ((i < line.length()) && (!Character.isDigit(line.charAt(i)))) { i++; }

	   if(i < line.length())
	   {
		   
	   this.registryName = line.substring(0,i-1);
	   this.registryID = line.substring(i);
	   
	   }
	   
	   else
	   {
		   this.registryName = "";
		   this.registryID = "";
	   }
   }
   
   //Student comment: I will add a method that will return the TreeMap of courses for the scheduler
   
   public TreeMap<String, ActiveCourse> getAllCourses()
   {
	   return this.courses;
   }
   
   // Add new student to the registry (students arraylist above) 
   public boolean addNewStudent(String name, String id)
   {
	   // Create a new student object
	   Student newStudent = new Student(name,id);
	   //Student comment: add a boolean variable for checking if student is in registry, default variable is false
	   boolean check = false;
	   // check to ensure student is not already in registry

	   if (students.containsKey(id) == true)
	   {
			   check = true;
			   System.out.println("Student already exists. Please enter a new student.");
	   }
	   // Student comment: If check is false, add the new student
	   if (check == false)
	   {
		   students.put(id, newStudent);
		   return true;
	   }
	   // make use of equals method in class Student
	   return false;
   }
   
   // Remove student from registry 
   public boolean removeStudent(String studentId)
   {
	   if (studentExists(studentId) == false)
	   {
		   System.out.println("This student does not exist. Please type in a valid student.");
		   return false;
	   }
	   
	   // Find student in students arraylist
		   if(students.containsKey(studentId))
		   {
			   // If found, remove this student and return true
			   students.remove(studentId);
			   return true;
		   }
		   
	   return false;
   }
   
   // Print all registered students
   public void printAllStudents()
   {
	   for (String key : students.keySet())
	   {
		   System.out.println("ID: " + key + " Name: " + students.get(key));   
	   }
	   
   }
   
   // Given a studentId and a course code, add student to the active course
   public void addCourse(String studentId, String courseCode)
   {
	   //Student comment: I will check first if course exists so I can prevent errors when executing the rest of the code in this function. I will do the same for dropCourse function.
	   if (courseExists(courseCode) == false)
	   {
		   System.out.println("This course does not exist. Please type in a valid course.");
		   return;
	   }
	   //Student comment: I will do the same with student
	   if (studentExists(studentId) == false)
	   {
		   System.out.println("This student does not exist. Please type in a valid student.");
		   return;
	   }
	   
	// Find student object in registry (i.e. students arraylist)
	   Student tempStudent = getStudent(studentId);
	// Check if student has already taken this course in the past Hint: look at their credit course list
	   if (tempStudent.completed(courseCode) == false)
	   {
		// If not, then find the active course in courses array list using course code
		  ActiveCourse tempCourse = getCourse(courseCode);
			// If active course found then check to see if student already enrolled in this course
		  if (tempCourse.isEnrolled(studentId) == false)
		  {
			// If not already enrolled
			  //add student to the active course
			  tempCourse.addStudent(tempStudent);
				//add course to student list of credit courses with initial grade of 0
			  tempStudent.addCourse(tempCourse.getName(), tempCourse.getCode(), tempCourse.getCourseDescription(), tempCourse.getFormat(), tempCourse.getSemester(), 0);
		  }
			
	   }
		  
	}
		   
   // Given a studentId and a course code, drop student from the active course
   public void dropCourse(String studentId, String courseCode)
   {
	   
	   if (studentExists(studentId) == false)
	   {
		   System.out.println("This student does not exist. Please type in a valid student.");
		   return;
	   }
	   
	   if (courseExists(courseCode) == false)
	   {
		   System.out.println("This course does not exist. Please type in a valid course.");
		   return;
	   }
	   
	   // Find the active course
	   ActiveCourse tempCourse = courses.get(courseCode);
	   // Find the student in the list of students for this course
	   Student tempStudent = tempCourse.getStudent(studentId);
	   // If student found:
	   //   remove the student from the active course
	   tempCourse.removeStudent(tempStudent);
	   //   remove the credit course from the student's list of credit courses
	   tempStudent.removeActiveCourse(courseCode);
   }
   
   // Print all active courses
   public void printActiveCourses()
   {
	   for (String key: courses.keySet())
	   {
		   ActiveCourse ac = courses.get(key);
		   System.out.println(ac.getDescription());
	   }
   }
   
   // Print the list of students in an active course
   public void printClassList(String courseCode)
   {
	  /*
	   *  Student comment: Go through all list of active courses in the registry using a loop and once a match is found out, call printClassList() method found
	   *  in the ActiveCourse class. If a course is not found, output error saying, course not found.
	   */
	   
	   if (courseExists(courseCode) == false)
	   {
		   System.out.println("This course does not exist. Please type in a valid course.");
		   return;
	   }
	   courseCode = courseCode.toUpperCase(); 
	   ActiveCourse tempCourse = courses.get(courseCode);
	   tempCourse.printClassList();
	
   }
   
   // Given a course code, find course and sort class list by student name
   public void sortCourseByName(String courseCode)
   {
	   if (courseExists(courseCode) == false)
	   {
		   System.out.println("This course does not exist. Please type in a valid course.");
		   return;
	   }
	   courseCode = courseCode.toUpperCase(); 
	   ActiveCourse tempCourse = courses.get(courseCode);
	   tempCourse.sortByName();
   }
   
   // Given a course code, find course and sort class list by student name
   public void sortCourseById(String courseCode)
   {
	   if (courseExists(courseCode) == false)
	   {
		   System.out.println("This course does not exist. Please type in a valid course.");
		   return;
	   }
	   courseCode = courseCode.toUpperCase(); 
	   ActiveCourse tempCourse = courses.get(courseCode);
	   tempCourse.sortById();
   }
   
   // Given a course code, find course and print student names and grades
   public void printGrades(String courseCode)
   {
	   if (courseExists(courseCode) == false)
	   {
		   System.out.println("This course does not exist. Please type in a valid course.");
		   return;
	   }
	   courseCode = courseCode.toUpperCase(); 
	   ActiveCourse tempCourse = courses.get(courseCode);
	   tempCourse.printGrades();

   }
   
   // Given a studentId, print all active courses of student
   public void printStudentCourses(String studentId)
   {
	   if (studentExists(studentId) == false)
	   {
		   System.out.println("This student does not exist. Please type in a valid student.");
		   return;
	   }
	   
	   // Student comment: Registry.java says I should print all active courses of the student while StudentRegistrySimulator.java says print all credit courses. I will add both methods but I will put all credit courses method in comments
	   Student tempStudent = students.get(studentId);
	   tempStudent.printActiveCourses();
	   //tempStudent.printCreditCourses();
   }
   
   // Given a studentId, print all completed courses and grades of student
   public void printStudentTranscript(String studentId)
   {
	   if (studentExists(studentId) == false)
	   {
		   System.out.println("This student does not exist. Please type in a valid student.");
		   return;
	   }
	   
	   Student tempStudent = students.get(studentId);
	   tempStudent.printTranscript();
   }
   
   // Given a course code, student id and numeric grade
   // set the final grade of the student
   public void setFinalGrade(String courseCode, String studentId, double grade)
   {
	   if (courseExists(courseCode) == false)
	   {
		   System.out.println("This course does not exist. Please type in a valid course.");
		   return;
	   }
	    
	   if (studentExists(studentId) == false)
	   {
		   System.out.println("This student does not exist. Please type in a valid student.");
		   return;
	   }
	   
	   // find the active course
	   courseCode = courseCode.toUpperCase();
	   ActiveCourse tempCourse = courses.get(courseCode);
	   // If found, find the student in class list
	   Student tempStudent = tempCourse.getStudent(studentId);
	   // then search student credit course list in student object and find course
	   CreditCourse tempCredit = tempStudent.getCourse(courseCode);
	   // set the grade in credit course and set credit course inactive
	   tempCredit.grade = grade;
	   tempCredit.setInactive();
	   
   }
  
}
