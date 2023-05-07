public class ServiceEndEvent extends Event {

     //private int customerId;

     private Customer customer;
     private int counterId;
     public ServiceEndEvent(double endTime, Customer customer, int counterId) {
          super(endTime);

          this.customer = customer;

          this.counterId = counterId;
     }


     public String toString() {
          String str = "";
          str = String.format(this.getTime() + "00: %s service done (by Counter %d)",
                  this.customer.toString(), this.counterId);
          return str;
     }





     public Event[] simulate() {
          Bank.setAvailable(counterId);
          return new Event[] {
                  new DepartureEvent(this.getTime(), customer)
          };
     }


}
