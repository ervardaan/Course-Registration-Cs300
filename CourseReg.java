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
/**
 * A application handler for course registration using a priority queue.
 */
public class CourseReg {

  // data fields
  private CourseQueue courses;  // the priority queue of all courses
  private int creditLoad;       // the maximum number of credits you want to take
  
  /**
   * Creates a new course registration object
   * @param capacity the maximum number of courses to store in the priority queue
   * @param creditLoad the maximum number of credits to take next semester
   * @throws IllegalArgumentException if either capacity or creditLoad are not a positive integer
   */
  public CourseReg(int capacity, int creditLoad) throws IllegalArgumentException {
    if(capacity<1 || creditLoad<1)//when we have invalid courseload
    {
      throw new IllegalArgumentException("invalid arguments");
    }
    courses=new CourseQueue(capacity);//making a queue of courses
    this.creditLoad=creditLoad;
    // TODO complete this constructor, initializing all data fields
  }
  
  /**
   * Returns a string representation of the highest-priority courses with a total number of credits
   * less than or equal to the creditLoad of this CourseReg object. Use the Iterable property of the
   * CourseQueue to help you out!
   * 
   * Note that this is NOT a "knapsack" problem - you're trying to maximize priority, not number of
   * credits. Just add courses to your result String until adding the next would take you over this
   * CourseReg object's creditLoad limit.
   * 
   * @return a string representation with one course on each line, where the total number of credits
   *   represented is less than or equal to the current creditLoad value
   */
  public String getRecommendedCourses() {

    String s="";int curCredits=0;int numCredits=0;//setting current credits and total credits to 0 initially
    for(Course c:courses){
      numCredits=c.getNumCredits();
    if(curCredits+numCredits<=creditLoad)
    {
      //only when we have space and incoming course doesn't exceed that space,we add
      s+= c.toString()+"\n";
      curCredits+=numCredits;//increment total num of credits
    }
    else{
    break;}
    }


    return s;
  }
  
  /**
   * Tries to add the given course to the priority queue; return false if the queue is full
   * 
   * @param toAdd the course to add
   * @return true if the course was successfully added to the queue
   */

  public boolean add(Course toAdd) {
    try{
      courses.enqueue(toAdd);//if no exception is thrown,then return true

      return true;
    }
    catch(Exception e)
    {
      return false;
    }

  }
  
  /**
   * Updates the creditLoad data field to the provided value
   * @param creditLoad the maximum number of credits to take next semester
   * @throws IllegalArgumentException if creditLoad is not a positive integer
   */
  public void setCreditLoad(int creditLoad) throws IllegalArgumentException {
    if(creditLoad<1)
    {
      throw new IllegalArgumentException("invalid no of credits");
    }
    this.creditLoad=creditLoad;//if not invalid,then set the course load
    // TODO complete this method
  }
}
