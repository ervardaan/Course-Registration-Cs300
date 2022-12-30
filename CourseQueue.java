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
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Array-based heap implementation of a priority queue containing Courses. Guarantees the
 * max-heap invariant, so that the Course at the root should have the highest score, and all
 * children always have a score lower than or equal to their parent's.
 * 
 * The root of a non-empty queue is always at index 0 of this array-heap.
 */
public class CourseQueue implements Iterable<Course>,PriorityQueueADT<Course>
        /* TODO: add PriorityQueueADT<Course> once Course is Comparable*/ {
  
  // data fields
  private Course[] queue; // array max-heap of courses representing this priority queue
  private int size;       // number of courses currently in this priority queue
  
  /**
   * Creates a new, empty CourseQueue with the given capacity
   * 
   * @param capacity the capacity of this CourseQueue
   * @throws IllegalArgumentException if the capacity is not a positive integer
   */
  public CourseQueue(int capacity) {
    if(capacity<0)
    {
      throw new IllegalArgumentException("capacity given is invalid");}

    size=0;//size starts at 0
    queue=new Course[capacity];//making new queue array
    // TODO complete this constructor, initializing ALL data fields
  }
  
  /**
   * Returns a deep copy of this CourseQueue containing all of its elements in the same order.
   * This method does not return the deepest copy, meaning that you do not need to duplicate 
   * courses. Only the instance of the heap (including the array and its size) will be duplicated.
   * 
   * @return a deep copy of this CourseQueue, which has the same capacity and size as this queue.
   */
  public CourseQueue deepCopy() {

    CourseQueue deepcopy1=new CourseQueue(this.size());
    for(int i=0;i<size();i++)
    {
      Course collect=queue[i];//getting course at particular index
      if(collect==null)
      {
        break;//checking if course is null.then break
      }
      deepcopy1.enqueue(collect);
    }
    return deepcopy1;



  }
  
  /**
   * Returns an Iterator for this CourseQueue which proceeds from the highest-priority to the
   * lowest-priority Course in the queue. Note that this should be an iterator over a DEEP COPY
   * of this queue.
   * 
   * @see CourseIterator
   * @return an Iterator for this CourseQueue
   */
  @Override
  public Iterator<Course> iterator() {
    return new CourseIterator(deepCopy());//making a new courseiterator object for coursequeue to use

  }
  
  ///////////////////////////// TODO: PRIORITY QUEUE METHODS //////////////////////////////////
  // Add the @Override annotation to these methods once this class implements PriorityQueueADT!
  
  /**
   * Checks whether this CourseQueue is empty
   * 
   * @return {@code true} if this CourseQueue is empty
   */
  @Override
  public boolean isEmpty() {
    return size()==0 ;//checking is queue is empty or not
    // TODO complete this method
  }
  
  /**
   * Returns the size of this CourseQueue
   * 
   * @return the size of this CourseQueue
   */
  @Override
  public int size() {
    return size; //accessor to get size
    // TODO complete this method
  }
  
  /**
   * Adds the given Course to this CourseQueue and use the percolateUp() method to
   * maintain max-heap invariant of CourseQueue. Courses should be compared using 
   * the Course.compareTo() method.
   * 
   * 
   * @param toAdd Course to add to this CourseQueue
   * @throws NullPointerException if the given Course is null
   * @throws IllegalStateException with a descriptive error message if this CourseQueue is full
   */
  public void enqueue(Course toAdd) throws NullPointerException, IllegalStateException {
    if(size()== queue.length)//when queue is full

    {
      throw new IllegalStateException("course queue is full");
    }
    if(toAdd==null)//when incoming course is null
    {
      throw new NullPointerException("given course is null");
    }
    //adding the course to end
    int child_index=size();
    queue[child_index]=toAdd;

    size++;
    //implementation 1:using another private helper method only
    /*
    if(size()!=1)//we add element at root and do nothing else
    {
    percolateUp(toAdd,child_index);}
    */

     //implementation 2:using protected helper method

    if(size()!=1)//we add element at root and do nothing else
    {
      percolateUp(child_index);}

  }
  /*
  private void percolateUp(Course toAdd,int child_index)
  {

    int parent_index=(child_index-1)/2;
    if(parent_index<0)
    {

    }
    else {
      Course collect_parent = queue[parent_index];
      if (queue[parent_index].compareTo(toAdd) < 0) {
        queue[parent_index] = toAdd;
        queue[child_index] = collect_parent;
        percolateUp(toAdd, parent_index);
      }
    }
  }

   */

  /**
   * Removes and returns the Course at the root of this CourseQueue, i.e. the Course
   * with the highest priority. Use the percolateDown() method to maintain max-heap invariant of
   * CourseQueue. Courses should be compared using the Course.compareTo() method.
   * 
   * @return the Course in this CourseQueue with the highest priority
   * @throws NoSuchElementException with a descriptive error message if this CourseQueue is
   *                                empty
   */
  public Course dequeue() throws NoSuchElementException {
    if(isEmpty())//when there is nothing to remove
    {
      throw new NoSuchElementException("queue is empty");
    }

    Course collect=queue[0];
    size--;//decreasing size if size is not 0
    if(size()==0)
    {

      queue[0]=null;
    }
    else{
      queue[0]=queue[size()];//getting last course to swap
      queue[size()]=null;//setting last course now to null
      percolateDown(0);
    }



    return collect; // TODO complete this method
  }
  
  /**
   * Returns the Course at the root of this CourseQueue, i.e. the Course with
   * the highest priority.
   * 
   * @return the Course in this CourseQueue with the highest priority
   * @throws NoSuchElementException if this CourseQueue is empty
   */
  public Course peek() throws NoSuchElementException {
    if(isEmpty())
    {
      throw new NoSuchElementException("we can't peek as queue is empty");
    }
    return queue[0];//getting the first element
    // TODO complete this method
  }
  
  ///////////////////////////// TODO: QUEUE HELPER METHODS //////////////////////////////////
  
  /**
   * Restores the max-heap invariant of a given subtree by percolating its root down the tree. If 
   * the element at the given index does not violate the max-heap invariant (it is higher priority 
   * than its children), then this method does not modify the heap. 
   * 
   * Otherwise, if there is a heap violation, then swap the element with the correct child and 
   * continue percolating the element down the heap.
   * 
   * This method may be implemented iteratively or recursively.
   * 
   * @param index index of the element in the heap to percolate downwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  protected void percolateDown(int index) throws IndexOutOfBoundsException {
    if(index<0 || index>=size())//if index out of bounds
    {
      throw new IndexOutOfBoundsException("invalid index");
    }
    Course current=queue[index];//get element to percolate down
    if(size()>(2*index+2))//should be only > if we are using length
    {

      //we have both right and left children
      int compare=queue[2*index+1].compareTo(queue[2*index+2]);//left vs right
      if(compare>=0)//if left>=right-choose left
      {
        if(queue[2*index+1].compareTo(current)>0){
        //swap left with current
        queue[index]=queue[2*index+1];
        queue[2*index+1]=current;

        percolateDown(2*index+1);
        }
      }
      else{
        if(queue[2*index+2].compareTo(current)>0){
        //swap right with current
        queue[index]=queue[2*index+2];
        queue[2*index+2]=current;
          percolateDown(2*index+2);
        }
      }
    }
    else if(size()>(2*index+1))
    {
      if(queue[2*index+1].compareTo(current)>0) {


        queue[index] = queue[2 * index + 1];
        queue[2 * index + 1] = current;
      }}
      else{

      }

    // TODO complete this method
  }
  /*
  protected void percolateUp(int index) throws IndexOutOfBoundsException {

    // TODO complete this method
    if(index<0 || index>=size())
    {
      throw new IndexOutOfBoundsException("invalid index");
    }
    //implementation 1:using another private helper method
    //percolateUp(queue[index],index);


    //implementation 2:using this method only

    int parent_index=(index-1)/2;
   // System.out.println(parent_index);

    if(parent_index<0)
    {

    }
    else {
      Course collect_parent = queue[parent_index];
//      System.out.println(collect_parent.toString());
//      System.out.println(queue[index].toString());
//      System.out.println(queue[parent_index].compareTo(queue[index]));
      if (queue[parent_index].compareTo(queue[index]) < 0) {
       // System.out.println("swapping");
        queue[parent_index] = queue[index];
        queue[index] = collect_parent;
        //percolateUp(queue[index], parent_index);
        percolateUp(parent_index);
      }
    }

  }

   */
  /**
   * Restores the max-heap invariant of the tree by percolating a leaf up the tree. If the element 
   * at the given index does not violate the max-heap invariant (it is lower priority than its 
   * parent), then this method does not modify the heap.
   * 
   * Otherwise, if there is a heap violation, swap the element with its parent and continue
   * percolating the element up the heap.
   * 
   * This method may be implemented iteratively or recursively.
   * 
   * @param index index of the element in the heap to percolate upwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */

  protected void percolateUp(int index) throws IndexOutOfBoundsException
  {
    if(index<0 || index>=size())
    {
      throw new IndexOutOfBoundsException("invalid index");
    }
    //implementation 1:using another private helper method
    //percolateUp(queue[index],index);


    //implementation 2:using this method only

    int parent_index=(index-1)/2;


    if(parent_index<0)//if parent's index is less than 0 then we don't do anything and return back to the caller
    {

    }
    else {
      Course collect_parent = queue[parent_index];

      if (queue[parent_index].compareTo(queue[index]) < 0) {
        // System.out.println("swapping");
        queue[parent_index] = queue[index];//swapping child with parent when parent<child
        queue[index] = collect_parent;

        percolateUp(parent_index);
      }
    }
  }
  /*
  private Course getDescendingOrderElements()
  {
    Course collect=this.queue[0];
    for(int i=1;i<this.size();i++)
    {
      this.queue[i-1]=this.queue[i];
    }
    this.size--;
    return collect;

  }

   */
  /////////////////////////// PROVIDED: TO STRING ////////////////////////////////////
  
  /**
   * Returns a String representing this CourseQueue, where each element (course) of the queue is 
   * listed on a separate line, in order from the highest priority to the lowest priority.
   * 
   * @author yiwei
   * @see Course#toString()
   * @see CourseIterator
   * @return a String representing this CourseQueue
   */
  @Override
  public String toString() {
    StringBuilder val = new StringBuilder();
    
    for (Course c : this) {//using iterator of coursequeue

      val.append(c).append("\n");//appending a string works the same way as concatenating strings
    }
    
    return val.toString().trim();//using toString() on StringBuilder object
  }

}
