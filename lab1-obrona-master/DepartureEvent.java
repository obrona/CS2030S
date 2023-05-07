public class DepartureEvent extends Event {

    //private int customerId;

    private Customer customer;

    public DepartureEvent(double departTime, Customer customer) {
         super(departTime);
         this.customer = customer;
     }

     public String toString() {
         String str = "";
         str = String.format(this.getTime() + "00: %s departed", this.customer.toString());
         return  str;
     }



     public Event[] simulate() {
         if(true) {
             //do nothing
         }


       return new Event[] {};

     }







}
