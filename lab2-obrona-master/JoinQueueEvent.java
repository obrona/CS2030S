public class JoinQueueEvent extends Event {

    private Customer customer;

    public JoinQueueEvent(double time, Customer customer) {

        super(time);

        this.customer = customer;

    }


    @Override
    public String toString() {

        String str = "";
        str = String.format(this.getTime()+ "00: %s joined queue" + " " + Bank.QueueToString() , this.customer.toString());

        return str;
    }











    public Event[] simulate() {

        Bank.addCustomer(customer);



        return new Event[] {};
    }
}
