/**
 * CS2030S AY22/23 Sem 2 PE1
 * @author: STUDENT ID
 * */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Encapsulate a list of event for today.
 */
class DayCalendar {
  private int numOfEvents; // The number of events in the list.
  private Array<Integer> typeArray; // Store the type of Event i.
  private Array<String> descriptionArray; // Store the description of Event i.
  private Array<Boolean> isCancelledArray; // Store whether Event i is cancelled or not.
  private Array<Integer> startTimeArray; // Store the start time of Event i (if any).
  private Array<Integer> endTimeArray; // Store the end time of Event i (if any).
  private Array<String> personArray; // Store the person associated to Event i (if any).
  private Array<Event> events;

  // The types of event.
  private static final int BIRTHDAY = 0;
  private static final int LESSON = 1;
  private static final int MEETING = 2;

  /**
   * A constructor that reads in the list from the standard input.
   **/
  public DayCalendar() {
    this(new Scanner(System.in));
  }

  /**
   * A constructor that reads in the list from the standard input.
   * @throws FileNotFoundException
   **/
  public DayCalendar(String filename) throws FileNotFoundException {
    this(new Scanner(new File(filename)));
  }

  /**
   * A constructor that reads in the list using the given scanner.
   * @param sc The Scanner to read from.
   **/
  private DayCalendar(Scanner sc) {
    loadEvents(sc);
  }

  /**
   * Load the events from the given scanner.
   * @param sc The scanner to load from.
   **/
  private void loadEvents(Scanner sc) {
    this.numOfEvents = Integer.parseInt(sc.nextLine().trim());
    this.typeArray = new Array<Integer>(this.numOfEvents);
    this.descriptionArray = new Array<String>(this.numOfEvents);
    this.startTimeArray = new Array<Integer>(this.numOfEvents);
    this.endTimeArray = new Array<Integer>(this.numOfEvents);
    this.personArray = new Array<String>(this.numOfEvents);
    this.isCancelledArray = new Array<Boolean>(this.numOfEvents);
    this.events = new Array<Event>(this.numOfEvents);
    int i = 0;
    while (sc.hasNext()) {
      String text = sc.nextLine().trim();
      String[] arguments = text.split(",");
      createEvent(i, arguments);
      i = i + 1;
    }
  }

  /**
   * Create Event i with the given arguments.
   * @param i The index of the task.
   * @param args The arguments read from the input.
   * @return false if the input contains a wrong type; true otherwise.
   **/
  private void createEvent(int i, String[] args) {
    int type = Integer.parseInt(args[0]);
    this.typeArray.set(i, type);

    String description = args[1];
    this.descriptionArray.set(i, description);
    this.isCancelledArray.set(i, false);

    if (type == DayCalendar.LESSON) {
      int startTime = Integer.parseInt(args[2]);
      int endTime = Integer.parseInt(args[3]);

      this.startTimeArray.set(i, startTime);
      this.endTimeArray.set(i, endTime);

      events.set(i, new Lesson(description, startTime, endTime, i));

    // events.get(i).eventDetails();
       

    } else if (type == DayCalendar.BIRTHDAY) {
      events.set(i, new Birthday(description, i));
      // no further action

    } else if (type == DayCalendar.MEETING) {
      int startTime = Integer.parseInt(args[2]);
      int endTime = Integer.parseInt(args[3]);
      String meetWith = args[4];
      this.startTimeArray.set(i, startTime);
      this.endTimeArray.set(i, endTime);

      this.personArray.set(i, meetWith);

      events.set(i, new Meeting(description, startTime, endTime, meetWith, i));
    }
  }

  /**
   * Return a string representation of the description of an event.
   * @param i The index of the event.
   * @return The string representation of Event i
   **/
  private String eventDescriptionOf(int i) {
    String description = this.descriptionArray.get(i);
    int type = typeArray.get(i);
    if (type == DayCalendar.BIRTHDAY) {
      return "Birthday (" + description + ")";
    } else {
      return description;
    }
  }

  /**
   * Print the description of all non-cancelled events.
   **/
  public void printEventDescriptions() {
    for (int i = 0; i < this.numOfEvents; i++) {
     // boolean isCancelled = this.isCancelledArray.get(i);
     // if (!isCancelled) {
     //   System.out.println(i + " " + this.eventDescriptionOf(i));
     // }
     events.get(i).eventDescription();
    }
  }

  /**
   * Return a string representation of an event 
   * @param i The index of the event.
   * @return The string representation of Event i
   **/
  private String eventDetailsOf(int i) {
    String s = "";
    int type = this.typeArray.get(i);
    String description = this.descriptionArray.get(i);

    if (type == DayCalendar.BIRTHDAY) {
      s += "Birthday (" + description + ")";

    } else if (type == DayCalendar.LESSON) {
      int startTime = startTimeArray.get(i);
      int endTime = endTimeArray.get(i);
      s += description + " | " + startTime + " - " + endTime;

    } else if (type == DayCalendar.MEETING) {
      int startTime = startTimeArray.get(i);
      int endTime = endTimeArray.get(i);
      String person = personArray.get(i);
      s += description + " | " + startTime + " - " + endTime;
      s += " | Meet with " + person;
    }
    return s;
  }

  /**
   * Print the details of all non-cancelled events.
   **/
  public void printEventDetails() {
    for (int i = 0; i < this.numOfEvents; i++) {
     // boolean isCancelled = isCancelledArray.get(i);
     // if (!isCancelled) {
     // System.out.println(i + " " + this.eventDetailsOf(i));
     events.get(i).eventDetails();
    }
  }
  

  /**
   * Calculate the total busy period (excluding cancelled events).
   * @return The busy period in hours.
   **/
  public int getBusyPeriod() {
    int sum = 0;
    for (int i = 0; i < this.numOfEvents; i++) {
     /* if ((typeArray.get(i) == DayCalendar.LESSON ||
            typeArray.get(i) == DayCalendar.MEETING)) {
        boolean isCancelled = isCancelledArray.get(i);
        if (!isCancelled) {
          int startTime = startTimeArray.get(i);
          int endTime = endTimeArray.get(i);
          sum += endTime - startTime;
        }
      } */
      sum = sum + events.get(i).getDuration();
    }
    return sum;
  }


  /**
   * Print all non-cancelled events that start on or after a given time.
   * @param time The time used to select the events to remind users about.
   **/
  public void remind(int time) {
    for (int i = 0; i < this.numOfEvents; i++) {
     /* int type = typeArray.get(i);
      boolean isCancelled = isCancelledArray.get(i);
      if (!isCancelled) {
        if (type == DayCalendar.MEETING || type == DayCalendar.LESSON) {
          int startTime = startTimeArray.get(i);
          if (time <= startTime) {
            System.out.println(i + " " + this.eventDetailsOf(i));
          }
        }
      } */
     Event x = events.get(i);
     if(x.getStartTime() >= time) {
       x.eventDetails();
     }

    }
  }

  /**
   * Cancel an event .
   * @param i The index of the event to cancel
   */
  public void cancelEvent(int i) {
  /*  int type = typeArray.get(i);
    if (type == DayCalendar.LESSON || type == DayCalendar.BIRTHDAY) {
      System.out.println("Unable to cancel event: " + this.eventDescriptionOf(i));
    } else {
      isCancelledArray.set(i, true);
    } */
   try {
     events.get(i).cancel();
   } catch (IllegalCancellationException e) {
     System.out.println(e.getMessage());
   } finally {

   }

  }
}
