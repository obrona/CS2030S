public class ServiceEndEvent extends Event {
	private Customer customer;
	private Counter counter;
	private Bank bank;

	public ServiceEndEvent(double endTime, Customer customer, Counter counter, Bank bank) {
		super(endTime);
		this.customer = customer;
		this.counter = counter;
		this.bank = bank;
	}

	@Override
		public String toString() {
			String str = "";
			str = String.format("%.3f: %s done (%s)",
					this.getTime(), this.customer.serviceTypeToString(), this.counter.toString());
			return str;
		}

	public Event[] simulate() {
		counter.setAvailable();
		if (!counter.isQueueEmpty() && !bank.isQueueEmpty()) {
			Customer newCustomer = counter.removeCustomer();
			Customer nextCustomerFromBankQueue = bank.removeCustomer();
			return new Event[] {
				new DepartureEvent(this.getTime(), customer),
						new ServiceBeginEvent(this.getTime(), newCustomer, counter, bank),
						new JoinCounterQueueEvent(this.getTime(), nextCustomerFromBankQueue, counter)
			};
		} else if (!counter.isQueueEmpty() && bank.isQueueEmpty()) {
			Customer newCustomer = counter.removeCustomer();
			return new Event[] {
				new DepartureEvent(this.getTime(), customer),
						new ServiceBeginEvent(this.getTime(), newCustomer, counter, bank)
			};
		} else if (bank.isQueueEmpty()) {
			return new Event[] {
				new DepartureEvent(this.getTime(), customer)
			};
		} else {
			Customer newCustomer = bank.removeCustomer();
			return new Event[] {
				new DepartureEvent(this.getTime(), customer),
						new ServiceBeginEvent(this.getTime(), newCustomer, counter, bank)
			};
		}
	}
}
