public class JoinCounterQueueEvent extends Event {
	private Customer customer;
	private Counter counter;

	public JoinCounterQueueEvent(double time, Customer customer, Counter counter) {
		super(time);
		this.customer = customer;
		this.counter = counter;
	}

	@Override
		public String toString() {
			String str = "";
			str = String.format("%.3f: %s joined counter queue" + " " +
					"(" + counter.QueueToString() + ")" , this.getTime(), this.customer.toString());
			return str;
		}

	public Event[] simulate() {
		counter.addCustomer(customer);
		return new Event[] {};
	}
}

