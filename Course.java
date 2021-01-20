//Name: Ahmed Begovic // Student ID : 500965275

public class Course
{
	private String code;
	private String name;
	private String description;
	private String format;
	   
	public Course()
	{
	  this.code        = "";
	  this.name        = "";
	  this.description = "";
	  this.format      = "";
	}
	   
	public Course(String name, String code, String descr, String fmt)
	{
	  this.code        = code;
	  this.name        = name;
	  this.description = descr;
	  this.format      = fmt;
	}
	   
	public String getCode()
	{
	   return code;
	}
	   
	public String getName()
	{
	  return name;
	}
	   
	public String getFormat()
	{
	  return format;
	}
	   
	public String getDescription()
	{
	  return code +" - " + name + "\n" + description + "\n" + format;
	}
	
	 public String getInfo()
	 {
	   return code +" - " + name;
	 }

	 // Student comment: A method that I will use for getting course description for the registry class so it won't collide with getDescription from ActiveCourse
	 public String getCourseDescription()
	 {
		 return description;
	 }
	 
	 // static method to convert numeric score to letter grade string 
	 // e.g. 91 --> "A+"
	 //Student comment : Scale was based on Ryerson's offical GPA scale: https://www.ryerson.ca/registrar/faculty/grading/gradescales_ugrad/
	 public static String convertNumericGrade(double score)
	 {
		 if (score >= 90) {
			return "A+";
		 }
		 if (score >= 85 && score < 90) {
				return "A";
		 }
		 if (score >= 80 && score < 85) {
				return "A-";
		 }
		 if (score >= 77 && score < 80) {
				return "B+";
		 }
		 if (score >= 73 && score < 77) {
				return "B";
		 }
		 if (score >= 70 && score < 73) {
				return "B-";
		 }
		 if (score >= 67 && score < 70) {
				return "C+";
		 }
		 if (score >= 63 && score < 67) {
				return "C";
		 }
		 if (score >= 60 && score < 63) {
				return "C-";
		 }
		 if (score >= 57 && score < 60) {
				return "D+";
			 }
		 if (score >= 53 && score < 57) {
				return "D";
			 }
		 if (score >= 50 && score < 53) {
				return "D-";
			 }
		 // Student comment : If a score doesn't match any of the above if statements, return F
		 return "F";
	 }
	 
}
