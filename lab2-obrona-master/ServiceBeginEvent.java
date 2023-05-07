public class ServiceBeginEvent extends Event{

    private Counter counter;
    private Customer customer;
    //private int customerId;

    private double serviceTime;

    //private double startTime;
    public ServiceBeginEvent(double startTime, Customer customer,  Counter counter) {

        super(startTime);

        this.counter = counter;

        this.serviceTime = customer.getServiceTime();

        this.customer = customer;

    }

    @Override
    public String toString() {

        String str = "";
        str = String.format(this.getTime() + "00: %s begin (%s)",
                this.customer.serviceTypeToString(), this.counter.toString());
        return str;
    }








    public Event[] simulate() {

        counter.setUnavailable();
        double endTime = this.getTime() + serviceTime;

        return new Event[] {
                new ServiceEndEvent(endTime,customer,counter)

        };
    }
}
