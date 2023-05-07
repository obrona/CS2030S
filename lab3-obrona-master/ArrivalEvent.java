public class ArrivalEvent extends Event {
	//Constructor
	//private int customerId;
	//private double serviceTime;
	private Customer customer;
	private Bank bank;


	public ArrivalEvent(double arrivalTime, Customer customer, Bank bank) {
		super(arrivalTime);
		this.customer = customer;
		this.bank = bank;
	}

	@Override
		public String toString() {
			String str = "";
			str = String.format("%.3f: %s arrived" + " " + bank.QueueToString(),
					this.getTime(), this.customer.toString());

			return str;
		}
	public Event[] simulate() {
		Counter counter = bank.getAvailable();
		//System.out.println(counter);
		if (counter == null) {
			if (bank.isQueueFull()) {
				return new Event[] {
					new DepartureEvent(this.getTime(), customer)
				};
			} else {
				return new Event[] {
					new JoinBankQueueEvent(this.getTime(), customer, bank)
				};
			}
		} else {
			if (counter.isAvailable()) {
				return new Event[] {
					new ServiceBeginEvent(this.getTime(), customer, counter, bank)
				};
			} else {
				return new Event[] {
					new JoinCounterQueueEvent(this.getTime(), customer, counter)
				};
			}
		}
	}
}
