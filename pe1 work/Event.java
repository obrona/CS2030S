public interface Event {

  public void eventDescription();

  public void eventDetails();

  public void cancel() throws IllegalCancellationException;

  public int getStartTime();

  public int getDuration();

  

  
}
