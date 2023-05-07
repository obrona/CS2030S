public class ServiceEndEvent extends Event {



     private Customer customer;
     
     private Counter counter;
     
     public ServiceEndEvent(double endTime, Customer customer, Counter counter) {
          super(endTime);

          this.customer = customer;

          this.counter = counter;
     }

     @Override
     public String toString() {
          String str = "";
          str = String.format(this.getTime() + "00: %s done (%s)",
                  this.customer.serviceTypeToString(), this.counter.toString());
          return str;
     }





     public Event[] simulate() {
          counter.setAvailable();


          if(Bank.isQueueEmpty()) {

               return new Event[] {
                  new DepartureEvent(this.getTime(), customer)
          };
     }

          else {

               Customer newCustomer = Bank.removeCustomer();

               return new Event[] {

                      new DepartureEvent(this.getTime(),customer),

                       new ServiceBeginEvent(this.getTime(), newCustomer,counter)

               };
          }


          }



}
