public class JoinBankQueueEvent extends Event {
	private Customer customer;
	private Bank bank;
	public JoinBankQueueEvent(double time, Customer customer, Bank bank) {
		super(time);
		this.customer = customer;
		this.bank = bank;
	}

	@Override
		public String toString() {
			String str = "";
			str = String.format("%.3f: %s joined bank queue" + " " + bank.QueueToString() ,
					this.getTime(), this.customer.toString());
			return str;
		}

	public Event[] simulate() {
		bank.addCustomer(customer);
		return new Event[] {};
	}
}

