//Name: Ahmed Begovic // Student ID : 500965275

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

public class StudentRegistrySimulator 
{
  public static void main(String[] args)
  {
	  Registry registry = null;
	  Scheduler schedule = null;
	  
	  try
	  {
		  registry = new Registry();
		  schedule = new Scheduler(registry.getAllCourses());
	  }
	  
	  catch (FileNotFoundException exception)
	  {
		  System.out.println("students.txt File Not Found.");
		  return;
	  }
	  
	  catch (IOException exception)
	  {
		  System.out.println("Bad File Format students.txt");
		  return;
	  }
	  
	  //Student comment: Create three String variables: one for student name, one for course code and one for student ID so I can use it for methods below
	  
	  String studentName = "";
	  String studentID = "";
	  String courseCode = "";
	  
	  //Double for grade in commands
	  double grade = 0;
	  
	  Scanner scanner = new Scanner(System.in);
	  System.out.print(">");
	  
	  while (scanner.hasNextLine())
	  {
		  String inputLine = scanner.nextLine();
		  if (inputLine == null || inputLine.equals("")) continue;
		  
		  Scanner commandLine = new Scanner(inputLine);
		  String command = commandLine.next();
		  
		  if (command == null || command.equals("")) continue;
		  
		  else if (command.equalsIgnoreCase("L") || command.equalsIgnoreCase("LIST"))
		  {
			  registry.printAllStudents();
		  }
		  else if (command.equalsIgnoreCase("Q") || command.equalsIgnoreCase("QUIT"))
			  return;
		  
		  else if (command.equalsIgnoreCase("REG"))
		  {
			  // register a new student in registry
			  // get name and student id string
			  // e.g. reg JohnBoy 74345
			  studentName = commandLine.next();
			  
			  
			  //Student comment : this block of code is set up so it won't throw an error if you input just name and not id
			  if(commandLine.hasNextLine())
			  {
				  studentID = commandLine.next();  
			  }
			  else
			  {
				  continue;
			  }
			  
			  // Checks:
			  //  ensure name is all alphabetic characters
			  //  ensure id string is all numeric characters
			  
			  // Student comment: I will also add another check to see if ID is 5 digits long by adding another method to the registry simulator
			  if ((studentName.equals("")) || (studentID.equals("")))
			  {
				  continue;
			  }
			  
			  if ((isStringOnlyAlphabet(studentName) == true) && (isNumeric(studentID) == true) && (isProperLength(studentID) == true))
			  {
				  registry.addNewStudent(studentName, studentID);
			  }
			  
			  //Student comment: I'm adding three if statements to print out what is wrong with the input so user can fix their input and try again. I will be adding similar if statements to other functions.
			  
			  else if(isStringOnlyAlphabet(studentName) != true) {
				  System.out.println("Student name contains invalid characters. Please try again.");
			  }
			  else if(isNumeric(studentID) != true) {
				  System.out.println("ID contains non-numeric characters. Please try again.");
			  }
			  else if(isProperLength(studentID) != true) {
				  System.out.println("ID does not contain correct length. Please input a 5-digit ID.");
			  }
			  
			  //Student comment: I will reset variables at the end of each function to avoid confusion
			  studentName = "";
			  studentID = "";
			  
		  }
		  else if (command.equalsIgnoreCase("DEL"))
		  {
			  // delete a student from registry
			  // get student id
			  studentID = commandLine.next();
			  // ensure numeric
			  if (isNumeric(studentID) && isProperLength(studentID))
			  {
			  // remove student from registry
				  registry.removeStudent(studentID);
			  }
			  
			  else if(isNumeric(studentID) != true) {
				  System.out.println("ID contains non-numeric characters. Please try again.");
			  }
			  else if(isProperLength(studentID) != true) {
				  System.out.println("ID does not contain correct length. Please input a 5-digit ID.");
			  }
			  
			  studentID = "";
		  }
		  
		  else if (command.equalsIgnoreCase("ADDC"))
		  {
			 // add a student to an active course
			 // get student id and course code strings
			  studentID = commandLine.next();
			  courseCode = commandLine.next();
			 // add student to course (see class Registry)
			  registry.addCourse(studentID, courseCode);
			  
			  studentID = "";
			  courseCode = "";
			  
		  }
		  else if (command.equalsIgnoreCase("DROPC"))
		  {
			  // get student id and course code strings
			  studentID = commandLine.next();
			  courseCode = commandLine.next();
			  // drop student from course (see class Registry)
			  registry.dropCourse(studentID, courseCode);
			  
			  studentID = "";
			  courseCode = "";
		  }
		  else if (command.equalsIgnoreCase("PAC"))
		  {
			  // print all active courses
			  registry.printActiveCourses();
			
		  }		  
		  else if (command.equalsIgnoreCase("PCL"))
		  {
			  // get course code string
			  courseCode = commandLine.next();
			  // print class list (i.e. students) for this course
			  registry.printClassList(courseCode);
			  
			  courseCode = "";
		  }
		  else if (command.equalsIgnoreCase("PGR"))
		  {
			  // get course code string
			  courseCode = commandLine.next();
			  // print name, id and grade of all students in active course
			  registry.printGrades(courseCode);
			  
			  courseCode = "";
		  }
		  else if (command.equalsIgnoreCase("PSC"))
		  {
			  // get student id string
			  studentID = commandLine.next();
			  // print all credit courses of student
			  registry.printStudentCourses(studentID);
			  
			  studentID = "";
			  
		  }
		  else if (command.equalsIgnoreCase("PST"))
		  {
			  // get student id string
			  studentID = commandLine.next();
			  // print student transcript
			  registry.printStudentTranscript(studentID);
			  
			  studentID = "";
		  }
		  else if (command.equalsIgnoreCase("SFG"))
		  {
			  // set final grade of student
			  // get course code, student id, numeric grade
			  courseCode = commandLine.next();
			  studentID = commandLine.next();
			  grade = commandLine.nextDouble();
			  // use registry to set final grade of this student (see class Registry)
			  registry.setFinalGrade(courseCode, studentID, grade);
			  
			  courseCode = "";
			  studentID = "";
			  grade = 0;
		  }
		  else if (command.equalsIgnoreCase("SCN"))
		  {
			  // get course code
			  courseCode = commandLine.next();
			  // sort list of students in course by name (i.e. alphabetically)
			  registry.sortCourseByName(courseCode);
			  // see class Registry
			  
			  courseCode = "";
			  
		  }
		  else if (command.equalsIgnoreCase("SCI"))
		  {
			// get course code
			  courseCode = commandLine.next();
			// sort list of students in course by student id
			  registry.sortCourseById(courseCode);
			// see class Registry
			  courseCode = "";
		  }
		  
		  //Student: new commands for the scheduler and the exceptions
		  
		  else if (command.equalsIgnoreCase("SCH"))
		  {
			  //get course code
			  courseCode = commandLine.next();
			  String day = commandLine.next();
			  int start = commandLine.nextInt();
			  int duration = commandLine.nextInt();
			  
			  try
			  {
				  schedule.setDayAndTime(courseCode,day, start, duration);
			  }
			  
			  catch (UnknownCourse uc)
			  {
				  System.out.println(uc);
			  }
			  
			  catch (InvalidDay invalidDay)
			  {
				  System.out.println(invalidDay);
			  }
			  
			  catch (InvalidTime invalidTime)
			  {
				  System.out.println(invalidTime);
			  }
			  
			  catch (InvalidDuration invalidDuration)
			  {
				  System.out.println(invalidDuration);
			  }
			  
			  catch (LectureTimeCollision collision)
			  {
				  System.out.println(collision);
			  }
		  }
		  
		  else if (command.equalsIgnoreCase("CSCH"))
		  {
			  courseCode = commandLine.next().toUpperCase();
			  schedule.clearSchedule(courseCode);
		  }
		  
		  else if (command.equalsIgnoreCase("PSCH"))
		  {
			  schedule.printSchedule();
		  }
		  
		  System.out.print("\n>");
	  }
  }
  
  private static boolean isStringOnlyAlphabet(String str) 
  { 
      // write method to check if string str contains only alphabetic characters
	  
	  //Student comment: Convert string into lower case so it is easier to check if it has alphabetic characters
	  
	  //Student comment: boolean for checking a string, default to true because I am looking through the string if there is a non alphabet character and assigning value to false if there is
	  boolean check = true;
	  str = str.toLowerCase();
	  
	  // Student comment: Go through all characters of the string and check if it is alphabetic only
	  
	  for (int i = 0; i < str.length(); i++)
	  {
		  if ((str.charAt(i) < 'a') || (str.charAt(i) > 'z'))
		  {
			  check = false;
		  }
	  }
	  
	  return check;
  } 
  
  public static boolean isNumeric(String str)
  {
      // write method to check if string str contains only numeric characters
	  
	  //Student comment: boolean for checking a string, default to true because I am looking through the string to see if there is a non numeric character and assigning check to false if there is.
	  boolean check = true;
	
	  // Student comment: Go through all characters of the string and check if it is digit only.
	  
	  for (int i = 0; i < str.length(); i++)
	  {
		  if ((str.charAt(i) < '0') || (str.charAt(i) > '9'))
		  {
			  check = false;
		  }
	  }
	  
	  return check;
  }
  
  public static boolean isProperLength(String str)
  {
	  //Student comment: Method for checking if ID is 5 digits long or not. By default return true because the if statement won't trigger as length is exactly 5.
	  if ((str.length() < 5) || (str.length() > 5))
	  {
		  return false;
	  }
	  
	  return true;
  }
}