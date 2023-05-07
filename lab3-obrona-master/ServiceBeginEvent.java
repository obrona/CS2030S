public class ServiceBeginEvent extends Event {
	private Counter counter;
	private Customer customer;
	private double serviceTime;
	private Bank bank;

	public ServiceBeginEvent(double startTime, Customer customer, Counter counter, Bank bank) {
		super(startTime);
		this.counter = counter;
		this.serviceTime = customer.getServiceTime();
		this.customer = customer;
		this.bank = bank;
	}

	@Override
		public String toString() {
			String str = "";
			str = String.format("%.3f: %s begin (%s)",
					this.getTime(), this.customer.serviceTypeToString(), this.counter.toString());
			return str;
		}

	public Event[] simulate() {
		counter.setUnavailable();
		double endTime = this.getTime() + serviceTime;
		return new Event[] {
			new ServiceEndEvent(endTime, customer, counter, bank)
		};
	}
}
