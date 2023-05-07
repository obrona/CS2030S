public class Customer {
    private final int customerId;

    public Customer(int customerId) {

        this.customerId = customerId;
    }



    public String toString() {

        return String.format("Customer %d",this.customerId);
    }


}
