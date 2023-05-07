public class DepartureEvent extends Event {
	//private int customerId;
	private Customer customer;

	public DepartureEvent(double departTime, Customer customer) {
		super(departTime);
		this.customer = customer;

	}

	@Override
		public String toString() {
			String str = "";
			str = String.format("%.3f: %s departed", this.getTime(), this.customer.toString());
			return str;
		}

	public Event[] simulate() {
		return new Event[]{};
	}
}

