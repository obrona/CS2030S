public class ArrivalEvent extends Event {
    //Constructor

    //private int customerId;

    private double serviceTime;

    private Customer customer;



    public ArrivalEvent(double arrivalTime, Customer customer, double serviceTime) {
        super(arrivalTime);
        //this.customerId = id;
        this.serviceTime = serviceTime;
        this.customer = customer;
    }

    public String toString() {
        String str = "";
        str = String.format(this.getTime() + "00: %s arrives", this.customer.toString());
        return str;
    }







   public Event[] simulate() {
        int counter = Bank.getAvailable();
        if(counter==-1) {

            return new Event[] {
                    new DepartureEvent(this.getTime(),customer)
            };
        }

        else {

           return new Event[] {
                   new ServiceBeginEvent(this.getTime(),customer,serviceTime,counter)
           } ;




        }

        //return new Event[] {};
  }
}
