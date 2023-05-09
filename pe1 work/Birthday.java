public class Birthday implements Event {
  private String name;
  private boolean cancelled;
  private int index;

  public Birthday(String name, int index ) {
    this.name = name;
    cancelled = false;
    this.index = index;
  }
  
  @Override
  public void eventDescription() {
    String str = index + " Birthday (" + this.name + ")";
    System.out.println(str);
  }
  
  @Override
  public void eventDetails() {
    String str = index + " Birthday (" + this.name + ")";
    System.out.println(str);
  }

  @Override
  public void cancel() throws IllegalCancellationException {
    throw new IllegalCancellationException("Unable to cancel event: Birthday (" + name + ")");
  }

  @Override
  public int getStartTime() {
    return -999; 
  }

  @Override
  public int getDuration() {
    return 0;
  }
}

