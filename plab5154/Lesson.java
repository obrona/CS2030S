public class Lesson implements Event {
  private String name;
  private int startTime;
  private int endTime;
  private boolean cancelled;
  private int index;

  public Lesson(String name, int startTime, int endTime, int index) {
    this.name = name;
    this.startTime = startTime;
    this.endTime = endTime;
    cancelled = false;
    this.index = index;
  }

  @Override
  public void eventDescription() {
    String str = index + " " +  name;
    System.out.println(str);
  }

  @Override
  public void eventDetails() {
    String str = index + " " + name;
    str = str + " | " + startTime + " - " + endTime;
    System.out.println(str);
  }

  @Override
  public void cancel() throws IllegalCancellationException {
    throw new IllegalCancellationException("Unable to cancel event: " + name);
  }

  @Override
  public int getStartTime() {
    return startTime;
  }

  @Override
  public int getDuration() {
    return endTime - startTime;
  }
}



