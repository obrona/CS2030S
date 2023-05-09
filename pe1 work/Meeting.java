public class Meeting implements Event {
  private String eventName;
  private int startTime;
  private int endTime;
  private String person;
  private boolean cancelled;
  private int index;

  public Meeting(String eventName, int startTime, int endTime, String person, int index) {
    this.eventName = eventName;
    this.startTime = startTime;
    this.endTime = endTime;
    this.person = person;
    cancelled = false;
    this.index = index;
  }

  @Override
  public void eventDescription() {
    if (cancelled == false) {
      String str = index + " " + eventName;
      System.out.println(str);
    } else {
      //Do nothing
    }
  }

  @Override
  public void eventDetails() {
    if (cancelled == false) {
      String str = index + " " + eventName;
      str = str + " | " + startTime + " - " + endTime;
      str = str + " | Meet with " + person;
      System.out.println(str);
    } else {
      //Do nothing
    }
  }

   @Override
   public void cancel() throws IllegalCancellationException {
     if (cancelled == false) {
       cancelled = true;
     } else {
       //Do nothing
     }
   }

   @Override
   public int getStartTime() {
     if (cancelled == false) {
     return startTime;
     } else {
       return -999;
     }
   }

   @Override
   public int getDuration() {
    if (cancelled == false) {
     return endTime - startTime;
    } else {
     return 0;
    }
   }
}
