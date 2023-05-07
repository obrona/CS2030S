public class ServiceBeginEvent extends Event{

    private int counterId;
    private Customer customer;
    //private int customerId;

    private double serviceTime;

    //private double startTime;
    public ServiceBeginEvent(double startTime, Customer customer, double serviceTime, int counterId) {

        super(startTime);

        this.counterId = counterId;

        this.serviceTime = serviceTime;

        this.customer = customer;

    }

    public String toString() {

        String str = "";
        str = String.format(this.getTime() + "00: %s service begin (by Counter %d)",
                this.customer.toString(), this.counterId);
        return str;
    }








    public Event[] simulate() {

        Bank.setUnavailable(counterId);
        double endTime = this.getTime() + serviceTime;

        return new Event[] {
                new ServiceEndEvent(endTime,customer,counterId)

        };
    }
}
