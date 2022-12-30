// TODO file header
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    p10 course registration
// Course:   CS 300 Fall 2022
//
// Author:   vardaan kapoor
// Email:    vkapoor5@wisc.edu
// Lecturer: (Mouna Kacem, Hobbes LeGault, or Jeff Nyhoff)
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * This class models a university-level course. It contains all relevant information for displaying
 * and ranking courses by their enrollment priority.
 */
public class Course implements Comparable<Course>/* TODO: this must implement an interface */ {
  
  // This field is used to help determine priority of courses in the compareTo method below, as
  // courses within your major department are considered higher priority.
  // For simplicity, please leave this as CS. You may change it for your own purposes later.
  private static final String MAJOR_DEPT = "CS";

  // data fields
  private final String DEPT_NAME;  // department offering this course (e.g. MATH, ART, LING, etc)
  private final int COURSE_NUM;    // number of this course (e.g. 101, 300, etc)
  private final int NUM_CREDITS;   // number of credits this course is worth (1-5)
  private int numSeatsAvailable;   // number of seats available in this course (0 or more)
  private String profName;         // the name of this course's professor, if known
  private double profRating;       // this professor's RateMyProfessor rating (0-5), if known
  
  /**
   * Creates a new Course with the given information. Professor information is not included in
   * this constructor.
   * 
   * @param deptName    department offering this course (must not be blank or null)
   * @param courseNum   number of this course (must be positive; 0 is not a valid course number)
   * @param numCredits  number of credits this course is worth (must be 1-5)
   * @param seats       number of seats currently available (must be non-negative; 0 or greater)
   * @throws IllegalArgumentException if any of the arguments do not fulfill their requirements
   */
  public Course(String deptName, int courseNum, int numCredits, int seats) {
    // TODO: update these and complete the constructor according to the description above
    if( deptName==null || deptName.isEmpty() )//checking if department is empty or not
    {
      throw new IllegalArgumentException("department name is null or empty");
    }
    if(courseNum<=0)
    {
      throw new IllegalArgumentException("course number is invalid");
    }
    if(numCredits<1 || numCredits>5)
    {
      throw new IllegalArgumentException("number of credits is invalid");
    }
    if(seats<0)
    {
      throw new IllegalArgumentException("number of seats is invalid");
    }
    //setting everything after we check for exceptions
    this.DEPT_NAME = deptName;
    this.COURSE_NUM = courseNum;
    this.NUM_CREDITS = numCredits;
    numSeatsAvailable=seats;
    //left info about the professor
  }
  
  ////////////////////////////// PROVIDED: ACCESSOR METHODS ////////////////////////////////////
  
  /**
   * Returns the number of credits this course is worth; used by the priority queue to provide
   * the top N credits worth of courses
   * @author hobbes
   * @return the number of credits this course is worth
   */
  public int getNumCredits() {

    return this.NUM_CREDITS;//giving num of credits back
  }
  
  /**
   * Determines whether two objects represent the same course by comparing department, number, 
   * credits, and professor (if known).
   * @author hobbes
   * @param o an object to compare this course to
   * @return true if o represents a course with the same department, number, credits, and professor
   *   (if known). number of seats available is NOT considered.
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Course) {
      Course other = (Course) o;//type casting object to course
      return this.DEPT_NAME.equals(other.DEPT_NAME) && this.COURSE_NUM==other.COURSE_NUM &&
          this.NUM_CREDITS==other.NUM_CREDITS && (this.profName==null) ? true : (other.profName != null &&
          this.profName.equals(other.profName) && this.profRating==other.profRating);
    }
    return false;
  }
  
  /**
   * Creates a String representation of this course, e.g. "CS 300 (closed) with Hobbes (1.4)"
   * @author hobbes
   * @return a String representing this Course
   */
  @Override
  public String toString() {
    String retval = DEPT_NAME+" "+COURSE_NUM;
    
    // seats information
    if (numSeatsAvailable == 0) retval += " (closed)";
    else retval += " ("+numSeatsAvailable+" seats)";//either available seats or closed section
    
    // prof information if available
    if (profName != null) retval += " with "+profName+" ("+profRating+")";//prof name and rating
    
    return retval;
  }
  
  //////////////////////////////// TODO: MUTATOR METHODS ////////////////////////////////////
  
  /**
   * Sets the name and RateMyProfessor rating of the professor for this class. If profName is
   * not null, rating must be between 0-5. If profName is null, profRating is ignored.
   * 
   * @param profName the name of this course's professor, if known; null otherwise
   * @param rating this professor's RateMyProfessor rating (0-5); unused if professor is unknown
   * @throws IllegalArgumentException if the professor is non-null AND the rating is invalid; if
   *     professor is null, no exception is thrown
   */
  public void setProfessor(String profName, double rating) throws IllegalArgumentException {
    // TODO: complete this
    this.profName=profName;//can set prof name directly
    if(profName==null)
    {
      profRating=rating;//if prof name is null,then we don't care about rating
    }
    else{
      if(rating>=0 && rating<=5)
      {
        profRating=rating;
      }
      else{
        throw new IllegalArgumentException("rating is invalid when professor name is not null");
      }
    }
  }
  
  /**
   * Sets the number of seats available in this course as additional seats are added or are taken
   * by other students thoughtlessly registering for courses you want to get into
   * 
   * @param numSeatsAvailable the number of seats currently available in the course, 0 or more
   * @throws IllegalArgumentException if the number of seats is negative
   */
  public void setSeatsAvailable(int numSeatsAvailable) {
    if(numSeatsAvailable<0)//checking for invalid num of seats
    {
      throw new IllegalArgumentException("the number of available seats left is invalid");
    }
    this.numSeatsAvailable=numSeatsAvailable;
    // TODO: complete this
  }
  
  //////////////////////////////// TODO: INHERITED METHOD ////////////////////////////////////
  
  /**
   * Compares this course to another course to calculate its PRIORITY. Note that two completely
   * different courses may have EQUAL priority under this comparison; please use the provided 
   * equals() method to check whether two course objects are the SAME.
   * 
   * In descending order of importance:
   * 
   * 1. Courses in the MAJOR_DEPT are higher priority (greater) than courses not in MAJOR_DEPT. If 
   *    neither department is in MAJOR_DEPT, the courses are considered equivalent here.
   * 2. Courses with seats available are higher priority than courses with no seats available. A 
   *    class with 200 seats is equivalent to a class with 5 seats available.
   * 3. Courses with KNOWN professors are higher priority than courses without known professors.
   *    No comparison is made between professor names.
   * 4. Courses with higher-ranked profs are higher priority than courses with lower-ranked profs.
   * 
   * For the purposes of this program, course NUMBER is for display purposes only and is not
   * considered for priority.
   * 
   * @param otherCourse the course to compare this one to
   * @return negative if this is less than otherCourse, positive if this is greater than otherCourse,
   *   0 if this is equal to otherCourse
   */

  @Override

  public int compareTo(Course otherCourse) {
    String c1=this.toString();
   // System.out.println(c1);
    String c2=otherCourse.toString();
   // System.out.println(c2);
    String[] collect=c1.split(" ");//this space(delimiter) is called regex
    String[] collect2=c2.split(" ");
    String dept_1=collect[0];

    String dept_2=collect2[0];


    String status_1=collect[2];
    if(!status_1.equals("(closed)"))
    {
      status_1=status_1.substring(1);
    }

    String status_2=collect2[2];
    if(!status_2.equals("(closed)"))
    {
      status_2=status_2.substring(1);
    }

    String prof_1;String prof_2;String prof_r1=null;String prof_r2=null;//initially,we set prof name and rating as null
    if(status_1.equals("(closed)"))
    {
      //if closed,then we find prof name at index 4 otherwise at index 5
      if(collect.length>4){
      prof_1=collect[4];
      prof_r1=collect[5];}
      else{
        prof_1=null;
      }
    }
    else{
      if(collect.length>5){
      prof_1=collect[5];
      prof_r1=collect[6];}
      else{
        prof_1=null;
      }
    }
    if(status_2.equals("(closed)"))
    {
      if(collect2.length>4)
      {
      prof_2=collect2[4];
      prof_r2=collect2[5];}
      else{
        prof_2=null;
      }
    }
    else{
      if(collect2.length>5){
      prof_2=collect2[5];
      prof_r2=collect2[6];}
      else{
        prof_2=null;
      }
    }


    int pr_r1=0;int pr_r2=0;//assigning any value to individual ratings
    if(prof_r1!=null){
    pr_r1=(int)Double.parseDouble(prof_r1.substring(1,prof_r1.length()-1));}
    if(prof_r2!=null){
    pr_r2=(int)Double.parseDouble(prof_r2.substring(1,prof_r2.length()-1));}

//level 1
    if((dept_1.equals("CS") && dept_2.equals("CS")) || (!dept_1.equals("CS") && !dept_2.equals("CS")))
    {
      //level 2
      if((!status_1.equals("(closed)") && !status_2.equals("(closed)")) || (status_1.equals("(closed)") && status_2.equals("(closed)")))
      {
//level 3
        if((prof_1==null && prof_2==null)|| (prof_1!=null && prof_2!=null))
        {
          //level 4
          if(pr_r1==pr_r2)
          {
            return 0;//finally return 0 if everything is equal
          }
          else if(pr_r1>pr_r2)
          {
            return 1;
          }
          else{ return -1;}
        }
        else if(prof_1!=null && prof_2==null)
        {
          return 1;
        }
        else{
          return -1;
        }
      }
      else if(!status_1.equals("(closed)") && status_2.equals("(closed)"))
      {
        return 1;
      }
      else{
        return -1;
      }
    }
    else if(dept_1.equals("CS"))
    {
      return 1;
    }
    else{
      return -1;
    }

  }


}

