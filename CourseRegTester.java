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
//TODO: in testenqueuedequeue, use testing for checking the order of nodes in the heap-use toString()
//TODO:use professor names and ratings in the above method to create nodes and then check these 2 methods
/**
 * This class implements unit test methods to check the correctness of Course,  CourseIterator, 
 * CourseQueue and CourseReg classes in P10.
 * 
 * Be aware that all methods in this class will be run against not only your code, but also our own
 * working and broken implementations to verify that your tests are working appropriately!
 */
public class CourseRegTester {
  
  /**
   * START HERE, and continue with testCompareTo() after this.
   * 
   * This method must test the Course constructor, accessor, and mutator methods, as well as its
   * toString() implementation. The compareTo() method will get its own test.
   * 
   * @see Course
   * @return true if the Course implementation is correct; false otherwise
   */
  public static boolean testCourse() {
    //constructor testing
    //SCENARIO 1:department name is invalid
    {
      try {
        Course c1 = new Course("", 354, 3, 5);
        //should give exception as department name is invalid
        return false;
      } catch (IllegalArgumentException e) {

      }
      catch(Exception e)
      {
        return false;
      }
      try {
        Course c1 = new Course(null, 354, 3, 5);
        //should give exception
        return false;
      } catch (IllegalArgumentException e) {

      }
      catch(Exception e)
      {
        return false;
      }
    }

    //scenario 2:course name is invalid

    {
      try {
        Course c1 = new Course("CS", 0, 3, 5);
        //should give exception
        return false;
      } catch (IllegalArgumentException e) {

      } catch (Exception e) {
        return false;//return false if any other exception is thrown
      }}
      //scenario 3:num of credits is invalid
      {
        try {
          Course c1 = new Course("CS", 3, 0, 5);
          return false;
        } catch (IllegalArgumentException e) {

        } catch (Exception e) {
          return false;//return false if any other exception is thrown
        }
      }

    //scenario 4:num of seats is invalid
    {
      try {
        Course c1 = new Course("CS", 3, 3, -3);
        return false;
      } catch (IllegalArgumentException e) {

      } catch (Exception e) {
        return false;//return false if any other exception is thrown
      }

    }
    //scenario 5:correct constructor call
    {
      try {
        Course c1 = new Course("MATH", 3, 3, 5);
//should not give exception
      } catch (Exception e) {
        return false;//return false if any  exception is thrown
      }
    }

    //testing accessor and mutator methods
    //scenario 6:before setting the professor info
    {
      //accessor methods check

      Course c1 = new Course("MATH", 3, 3, 5);
      //checking toString method
      String exp = "MATH 3 (5 seats)";
      if (!(c1.toString().equals(exp))) {
        return false;
      }
      if (c1.getNumCredits() != 3) {
        return false;//checking getter for num of credits
      }
      if (!c1.equals(c1)) {
        return false;
      }
      //scenario 1a:num of seats<0

      try {
        c1.setSeatsAvailable(-3);//should give exception as we gave invalid num of available seats
        return false;
      } catch (IllegalArgumentException e) {

      } catch (Exception e) {
        return false;
      }

      //scenario 1b:num of seats>0

      try {
        c1.setSeatsAvailable(67);
        String[] collect = c1.toString().split(" ");
        String data = collect[2];//getting num of seats
        try {
          int seats = Integer.parseInt(data.substring(1));
          if (seats != 67) {
            return false;
          }
          if (!c1.toString().equals("MATH 3 (67 seats)")) {
            return false;//checking toString() method
          }
        } catch (Exception e) {
          return false;
        }
      } catch (Exception e) {
        return false;
      }

    }

    //scenario 7:after setting the professor info
    {
        Course c2 = new Course("MATH", 5, 5, 44);
        //scenario a:professor name is null

          try {
            c2.setProfessor(null, 55);//even if rating is invalid we won't get error
          } catch (Exception e) {
            return false;
          }
          //checking is toString() still contains the prof name
          try {
            String prof_name =c2.toString().split(" ")[5];
            return false;
          }
          catch(IndexOutOfBoundsException e)
          {}



        //scenario b:prof name is not null but rating is invalid

          try{
            c2.setProfessor("HOBBES",55);
            //should give exception
            return false;
          }
          catch(IllegalArgumentException e)
          {

          }
          catch(Exception e)
          {
            return false;
          }

        //scenario c:prof name and rating both are valid

          try{
            c2.setProfessor("HOBBES",5);//valid
            try {
              String prof_name =c2.toString().split(" ")[5];//getting prof name by index 5

              String rate=c2.toString().split(" ")[6];

              int rating=(int)Double.parseDouble(rate.substring(1,rate.length()-1));//giving error

              if(!prof_name.equals("HOBBES"))
              {
                return false;}
              if(rating!=5)
              {
                return false;//checking rating
            }}
          catch(Exception e)
            {
              return false;
            }
          }

          catch(Exception e)
          {
            return false;
          }


      }


    return true; // TODO: complete this test
  }
  
  /**
   * This method must test the Course compareTo() implementation. Be sure to test ALL FOUR levels
   * of the comparison here!
   * 
   * Once you complete this test, finish the Course implementation if you have not done so already,
   * then move to testCourseQueue() and testEnqueueDequeue().
   * 
   * @see Course#compareTo(Course)
   * @return true if the compareTo() implementation is correct; false otherwise
   */
  public static boolean testCompareTo() {
    //scenario 1:both courses in major department
    Course c1=new Course("CS",3,3,0);
    c1.setProfessor("HOBBES", 4);
    {

      Course c2 = new Course("CS", 3, 3, 0);

      c2.setProfessor("HOBBES", 4);
      //checking equal courses
      if (c1.compareTo(c2) != 0) {
        return false;
      }
    }

    {

      Course c3 = new Course("CS", 3, 3, 0);
//rating differs-level 4 changes
      c3.setProfessor("HOBBES", 5);
      if (c1.compareTo(c3)>= 0) {
        return false;
      }
    }

    {
      Course c2 = new Course("CS", 3, 3, 0);
//level 3 changes as prof name differs
      c2.setProfessor(null, 5);
      if (c1.compareTo(c2)<= 0) {
        return false;
      }
    }

    {
      Course c2 = new Course("CS", 3, 3, 5);
//level 2 changes as num of seats differs
      c2.setProfessor(null, 5);
      if (c1.compareTo(c2)>= 0) {
        return false;
      }
    }

    {
      Course c2 = new Course("MATH", 3, 3, 5);
Course ss=new Course("CS", 3, 3, 5);
      ss.setProfessor("HOBBES", 4);
      c2.setProfessor(null, 5);
      //level 1 changes-department name differs
      if (ss.compareTo(c2)<= 0) {
        return false;
      }
    }



    {
      Course c3=new Course("MATH", 3, 3, 5);
      Course c2 = new Course("MATH", 3, 3, 5);
c3.setProfessor("HOBBES",2);
      c2.setProfessor(null, 5);
      //changes in prof name
      if (c3.compareTo(c2)<= 0) {
        return false;
      }
    }


    return true;


  }
  
  /**
   * This method must test the other methods in CourseQueue (isEmpty, size, peek). Verify normal 
   * cases and error cases, as well as a filled and re-emptied queue.
   * 
   * Once you have completed this method, implement the required methods in CourseQueue and verify
   * that they work correctly.
   * 
   * @see CourseQueue
   * @return true if CourseQueue's other methods are implemented correctly; false otherwise
   */
  public static boolean testCourseQueue() {
    //scenario 1:capacity<0
    try{
    CourseQueue cq1=new CourseQueue(-4);
    //should give exception
    return false;}
    catch(IllegalArgumentException e)
    {

    }
    catch(Exception e)
    {
      return false;
    }


    //scenario 3:we add many elements and then check peek and size and empty
    {
      CourseQueue cq3=new CourseQueue(10);
      Course collect1=new Course("CS",3,3,20);
      cq3.enqueue(collect1);
      Course collect2=new Course("MATH",5,1,0);
      cq3.enqueue(collect2);
      //2 elements added
      if(cq3.isEmpty())
      {
        return false;
      }
      if(cq3.size()!=2)
      {
        return false;
      }
      if(!cq3.peek().equals(collect1))//checking result of peek
      {
        return false;
      }
    }
    //scenario 4:we remove some elements and then check peek,size,empty
    {
CourseQueue cq3=new CourseQueue(10);
      Course collect1=new Course("CS",3,3,20);
      cq3.enqueue(collect1);
      Course collect2=new Course("MATH",5,1,0);
      cq3.enqueue(collect2);
      int prev_size=cq3.size();//previous size before dequeuing
      try {
        cq3.dequeue();//now only one element is left
        if (cq3.isEmpty()) {
          return false;
        }
        if (cq3.size() != (prev_size - 1)) {
          return false;
        }
        if (!cq3.peek().equals(collect2)) {
          return false;
        }
      }
      catch(Exception  e)
      {
        return false;
      }
    }

    return true; // TODO: complete this test
  }
  
  /**
   * This method must test the enqueue and dequeue methods in CourseQueue. Verify normal cases and
   * error cases, as well as filling and emptying the queue.
   * 
   * You may also test the percolate methods directly, though this is not required.
   * 
   * Once you have completed this method, implement the enqueue/dequeue and percolate methods in
   * CourseQueue and verify that they work correctly, then move on to testCourseIterator().
   * 
   * @see CourseQueue#enqueue(Course)
   * @see CourseQueue#dequeue()
   * @return true if the CourseQueue enqueue/dequeue implementations are correct; false otherwise
   */
  public static boolean testEnqueueDequeue() {

    CourseQueue cq=new CourseQueue(5);

    //scenario 1:array is empty
    {
      try{
      cq.enqueue(new Course("CS",3,3,20));
      if(cq.size()!=1)//size should have been increased
      {
      return false;}
      }
      catch(Exception e)
      {
        return false;
      }

    }
    //scenario 2:the argument course is null
    {

      try{
      cq.enqueue(null);
      //should give exception
      return false;}
      catch(NullPointerException e)
      {

      }
      catch(Exception e)
      {

        return false ;
      }

    }

    //scenario 3:adding to full queue
    {
      try {
        //adding 4 elements first
        if (cq.size() == 1) {
          cq.enqueue(new Course("MATH", 3, 3, 20));
        }
        if (cq.size() != 2) {
          return false;
        }
        cq.enqueue(new Course("PSYCHOLOGY", 5, 4, 100));
        cq.enqueue(new Course("PHYSICS", 1, 2, 30));
        cq.enqueue(new Course("MATH", 1, 3, 0));

        int prev_size = cq.size();//store previous size for later comparison

        if (prev_size == 5) {
          //ADDING THE EXTRA ELEMENT
          try {

            cq.enqueue(new Course("biology", 4, 5, 1));
            //should give exception as queue is now full
            return false;
          } catch (IllegalStateException e) {

          } catch (Exception e) {
            return false;
          }
          if (cq.size() != prev_size) {
            //size should not have been increased
            return false;
          }
        } else {
          return false;
        }

      }
      catch(Exception e)
      {
        return false;
      }
    }

    //scenario extra1:check percolateup with index not out of bounds
    {
      try {
        CourseQueue cq2 = new CourseQueue(9);//capacity set to 9
        cq2.enqueue(new Course("CS", 3, 3, 20));
        cq2.enqueue(new Course("MATH", 3, 3, 20));
        cq2.enqueue(new Course("PSYCHOLOGY", 5, 4, 100));
        cq2.enqueue(new Course("PHYSICS", 1, 2, 30));
        //should not be equal
        //System.out.println(new Course("MATH",3,3,20).compareTo(new Course("PHYSICS",1,2,30)));
        cq2.enqueue(new Course("MATH", 1, 3, 0));
        cq2.enqueue(new Course("CS", 5, 4, 0));
        cq2.enqueue(new Course("ASTRONOMY", 2, 1, 100));
        cq2.enqueue(new Course("physiology", 1, 1, 0));
        if (cq2.size() == 8) {
          cq2.enqueue(new Course("CS", 2, 3, 100));//setting last element
          if (cq2.size() != 9) {
            return false;
          }

        } else {
          return false;
        }
      }
      catch(Exception e)
      {
        return false;
      }
    }


      //dequeue scenarios-------->>>>>
      CourseQueue cqd = new CourseQueue(10);
      //scenario 1:dequeing from an empty queue
      try {
        cqd.dequeue();//should give exception
        return false;
      } catch (NoSuchElementException e) {
        //e.printStackTrace();
      } catch (Exception e) {
        return false;
      }

      //scenario 2:dequeing from 1 element queue
      {
        cqd.enqueue(new Course("CS", 3, 3, 20));
        try {
          cqd.dequeue();
          if (cqd.size() != 0) {
            //after dequeing the size should be 0
            return false;
          }
        } catch (Exception e) {
          return false;
        }
      }

      //scenario 3:dequeing from many elements queue
      {
        cqd.enqueue(new Course("CS", 3, 3, 20));
        cqd.enqueue(new Course("MATH", 3, 3, 20));
        cqd.enqueue(new Course("PSYCHOLOGY", 5, 4, 100));
        cqd.enqueue(new Course("PHYSICS", 1, 2, 30));

        cqd.enqueue(new Course("MATH", 1, 3, 0));
        cqd.enqueue(new Course("CS", 5, 4, 0));
        cqd.enqueue(new Course("ASTRONOMY", 2, 1, 100));
        cqd.enqueue(new Course("physiology", 1, 1, 0));
        cqd.enqueue(new Course("CS", 2, 3, 100));

        //cqd.toString();
        //repeatedly removing root nodes
        //remove 1-------->>
        try {
          cqd.dequeue();
          if (cqd.size() != 8) {
            //size decremented by 1
            return false;
          }
        } catch (Exception e) {
          return false;
        }
        //removing 2------->>
        try {
          cqd.dequeue();
          if (cqd.size() != 7) {
            return false;
          }
        } catch (Exception e) {
          return false;
        }
        //removing 3------>>
        try {
          cqd.dequeue();
          if (cqd.size() != 6) {
            return false;
          }
        } catch (Exception e) {
          return false;
        }
        //removing 4------>>
        try {
          cqd.dequeue();
          if (cqd.size() != 5) {
            return false;
          }
        } catch (Exception e) {
          return false;
        }
        //removing 5------>>
        try {
          cqd.dequeue();
          if (cqd.size() != 4) {
            return false;
          }
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
        //removing 6------>>
        try {
          cqd.dequeue();
          if (cqd.size() != 3) {
            return false;
          }
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
        //removing 7------>>
        try {
          cqd.dequeue();
          if (cqd.size() != 2) {
            return false;
          }
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
        //removing 8
        try {
          cqd.dequeue();
          if (cqd.size() != 1) {
            return false;
          }
        } catch (Exception e) {
          //valid deque should never give exception
          return false;
        }
        //removing 9
        try {
          cqd.dequeue();
          if (cqd.size() != 0) {
            return false;
          }
        } catch (Exception e) {

          return false;
        }



      }

//scenario :percolate down swaps parent with its larger child
    {
      Course c1=new Course("CS", 3, 3, 20);
      Course c2=new Course("MATH", 3, 3, 20);
      Course c3=new Course("PSYCHOLOGY", 5, 4, 100);
      Course c4=new Course("PHYSICS", 1, 2, 30);
      Course c5=new Course("MATH", 1, 3, 0);
      Course c6=new Course("CS", 5, 4, 0);
      Course c7=new Course("ASTRONOMY", 2, 1, 100);
      Course c8=new Course("physiology", 1, 1, 0);
      Course c9=new Course("CS", 2, 3, 100);
      cqd.enqueue(c1);
      cqd.enqueue(c2);
      cqd.enqueue(c3);
      cqd.enqueue(c4);

      cqd.enqueue(c5);
      cqd.enqueue(c6);
      cqd.enqueue(c7);
      cqd.enqueue(c8);
      cqd.enqueue(c9);

      String s="";
      s+=c9.toString()+"\n";
      s+=c6.toString()+"\n";s+=c7.toString()+"\n";s+=c4.toString()+"\n";s+=c2.toString()+"\n";s+=c3.toString()+"\n";
      s+=c8.toString()+"\n";s+=c5.toString()+"\n";
      //we added elements to s in order of dequeing and not in order of intial queue
      cqd.dequeue();
      //we trim before comparing expected and result string
      if(!((s.trim()).equals(cqd.toString().trim())))
      {
        return false;
      }
    }



    return true; // TODO: complete this test
  }
  
  /**
   * This method must test the CourseIterator class. The CourseIterator iterates through a deep copy
   * of a CourseQueue in decreasing order of priority, returning each Course object in turn.
   * 
   * Once you have completed this method, implement the CourseIterator class and make CourseQueue
   * into an Iterable class. Verify that this works correctly, and then move on to the final test
   * method: testCourseReg().
   * 
   * @see CourseIterator
   * @return true if the CourseIterator implementation is correct; false otherwise
   */
  public static boolean testCourseIterator() {
    CourseQueue cq2=new CourseQueue(9);
    cq2.enqueue(new Course("CS",3,3,20));
    cq2.enqueue(new Course("MATH",3,3,20));
    cq2.enqueue(new Course("PSYCHOLOGY",5,4,100));
    cq2.enqueue(new Course("PHYSICS",1,2,30));
    cq2.enqueue(new Course("MATH",1,3,0));
    cq2.enqueue(new Course("CS",5,4,0));
    cq2.enqueue(new Course("ASTRONOMY",2,1,100));
    cq2.enqueue(new Course("physiology",1,1,0));
    cq2.enqueue(new Course("CS", 2, 3, 100));



    CourseIterator ci= (CourseIterator) cq2.iterator();//casting iterator object to courseiterator object

    while(true)
    {
      try{
      ci.next();
        }
      catch(NoSuchElementException e)
      {
       //whenever we get exception,we break the infinite while loop
        break;
      }
      catch(Exception e)
      {
        return false;//we don't expect any exception other than the above one
      }

    }


    return true; // TODO: complete this test
  }
  
  /**
   * This method must test the constructor and three methods of the CourseReg class (setCreditLoad,
   * add, and getRecommendedCourses). Verify normal cases and error cases.
   * 
   * Once you have completed this method, implement CourseReg and verify that it works correctly.
   * Then you're DONE! Save and submit to gradescope, and enjoy being DONE with programming
   * assignments in CS 300 !!!
   * 
   * @see CourseReg
   * @return true if CourseReg has been implemented correctly; false otherwise
   */
  public static boolean testCourseReg() {
    //testing the constructor
    //scenario 1:capacity is invalid
    {
      try {
        CourseReg cr = new CourseReg(0, 5);
        //capacity can't be 0
        return false;
      } catch (IllegalArgumentException e) {

      } catch (Exception e) {
        return false;//no other exception expected
      }
    }

    //scenario 2:course load is invalid
    {
      try {
        CourseReg cr = new CourseReg(6, 0);
        return false;
      } catch (IllegalArgumentException e) {

      } catch (Exception e) {
        return false;//no other exception expected
      }
    }

    //scenario 3:both are valid
    {
      try {
        CourseReg cr = new CourseReg(6, 10);

      } catch (Exception e) {
        return false;
      }
    }

    //scenario 4:getting course list when course load is 1
    {
      CourseReg cr=new CourseReg(3,1);
      cr.add(new Course("MTH",6,1,0));
      cr.add(new Course("PSYCH",12,2,6));//it is the top but we can't get it as its
      //number of credits>1
      cr.add(new Course("MUSIC",10,4,100));
      if(!cr.getRecommendedCourses().equals(""))
      {
        return false;
      }

    }

    {
      CourseReg cr=new CourseReg(3,1);
      cr.add(new Course("CS",6,1,7));
      cr.add(new Course("PSYCH",12,2,6));
      cr.add(new Course("MUSIC",10,4,0));
     //checking string representation of recommended courses
      if(!cr.getRecommendedCourses().equals(new Course("CS",6,1,7).toString()+"\n"))
      {
        return false;
      }
    }

    //scenario 5:getting course list when course load is 10
    {
      //cs6,cs67,music,psych,math
      CourseReg cr=new CourseReg(10,10);
      cr.add(new Course("MATH",6,3,0));
      cr.add(new Course("CS",12,4,6));
      cr.add(new Course("MUSIC",10,2,100));
      cr.add(new Course("psych",7,3,0));
      cr.add(new Course("CS",4,1,67));

      String s="";//s will store the expected output to compare
      s+=new Course("CS",12,4,6).toString()+"\n";
      s+=new Course("CS",4,1,67).toString()+"\n";
      s+=new Course("MUSIC",10,2,100).toString()+"\n";
      s+=new Course("psych",7,3,0).toString()+"\n";

      if(!(cr.getRecommendedCourses().equals(s)))
      {
        return false;
      }
    }

    //getting course list when course load is 100
    {
      //adding some courses
      CourseReg cr=new CourseReg(10,100);
      cr.add(new Course("MATH",6,3,0));
      cr.add(new Course("CS",12,4,6));
      cr.add(new Course("MUSIC",10,2,100));
      cr.add(new Course("psych",7,3,0));
      cr.add(new Course("CS",4,1,67));
      String s="";
      s+=new Course("CS",12,4,6).toString()+"\n";
      s+=new Course("CS",4,1,67).toString()+"\n";
      s+=new Course("MUSIC",10,2,100).toString()+"\n";
      s+=new Course("psych",7,3,0).toString()+"\n";
s+=new Course("MATH",6,3,0).toString()+"\n";
      if(!(cr.getRecommendedCourses().equals(s)))
      {
        return false;
      }
    }

    //scenario 6:setting course load to some greater value and checking string again
    {
      CourseReg cr=new CourseReg(7,10);
      cr.add(new Course("MATH",6,3,0));
      cr.add(new Course("CS",12,4,6));
      cr.add(new Course("MUSIC",10,2,100));
      cr.add(new Course("psych",7,3,0));
      cr.add(new Course("CS",4,1,67));
      String s="";
      //number of courses we get should have been increased
      s+=new Course("CS",12,4,6).toString()+"\n";
      s+=new Course("CS",4,1,67).toString()+"\n";
      s+=new Course("MUSIC",10,2,100).toString()+"\n";
      s+=new Course("psych",7,3,0).toString()+"\n";
      if(!cr.getRecommendedCourses().equals(s))
      {
        return false;
      }
cr.setCreditLoad(15);//increase course load
      s+=new Course("MATH",6,3,0).toString()+"\n";
      if(!cr.getRecommendedCourses().equals(s))
      {
        return false;
      }

    }

    //scenario 7:adding an element to a queue with space
    {
      try {
        CourseReg cr = new CourseReg(5, 14);
//we have space
        if (!cr.add(new Course("CS", 3, 5, 10))) {
          return false;
        }


        cr.add(new Course("MATH", 6, 1, 0));
        cr.add(new Course("PSYCH", 12, 2, 6));
        cr.add(new Course("MUSIC", 10, 4, 100));
        if (!cr.add(new Course("CS", 7, 4, 0))) {
          //add should return true if successfully added
          return false;
        }


        //scenario adding to a full queue
        if (cr.add(new Course("ART", 3, 3, 0))) {
          //should throw exception
          return false;
        }
      }
      catch(Exception e)
      {
         return false;
      }
    }


    return true; // TODO: complete this test
  }
  
  /**
   * This method calls all test methods defined by us; you may add additional methods to this if
   * you like. All test methods must be public static boolean.
   * 
   * @return true if all tests in this class return true; false otherwise
   */
  public static boolean runAllTests() {
    boolean testVal = true;
    
    // test Course
    System.out.print("testCourse(): ");
    if (!testCourse()) {
      System.out.println("FAIL");
      testVal = false;
    } else { System.out.println("pass"); }
    
    // test compareTo
    System.out.print("testCompareTo(): ");
    if (!testCompareTo()) {
      System.out.println("FAIL");
      testVal = false;
    } else { System.out.println("pass"); }
    
    // test CourseIterator
    System.out.print("testCourseIterator(): ");
    if (!testCourseIterator()) {
      System.out.println("FAIL");
      testVal = false;
    } else { System.out.println("pass"); }
    
    // test CourseQueue enqueue/dequeue
    System.out.print("testEnqueueDequeue(): ");
    if (!testEnqueueDequeue()) {
      System.out.println("FAIL");
      testVal = false;
    } else { System.out.println("pass"); }
    
    // test CourseQueue
    System.out.print("testCourseQueue(): ");
    if (!testCourseQueue()) {
      System.out.println("FAIL");
      testVal = false;
    } else { System.out.println("pass"); }
    
    // test CourseReg
    System.out.print("testCourseReg(): ");
    if (!testCourseReg()) {
      System.out.println("FAIL");
      testVal = false;
    } else { System.out.println("pass"); }
    
    return testVal;
  }
  
  /**
   * Calls runAllTests() so you can verify your program
   * 
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
    runAllTests();
    //System.out.println( testCourse());

//    System.out.println(testCompareTo());
    //System.out.println(testCourseQueue());
 //System.out.println(testEnqueueDequeue());
  // System.out.println(testCourseIterator());
    //System.out.println(testCourseReg());
   //System.out.println(new Course("MATH",3,3,20).compareTo(new Course("PHYSICS",1,2,30)));
   //System.out.println(new Course("MATH",3,3,20).compareTo(new Course("MATH",1,3,0)));
  }
}
