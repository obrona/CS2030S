public class Customer {
	private final int customerId;
	private final int serviceType;
	private final double serviceTime;

	public Customer(int customerId, int serviceType, double serviceTime) {
		this.customerId = customerId;
		this.serviceType = serviceType;
		this.serviceTime = serviceTime;
	}

	public double getServiceTime() {
		return this.serviceTime;
	}
	//Because Array class needs Comparable. This does nothing

	@Override
		public String toString() {
			return "C" + customerId;
		}

	public String serviceTypeToString() {
		if (serviceType == 1) {
			return String.format("C%d Withdrawal", this.customerId);
		} else if (serviceType == 0) {
			return String.format("C%d Deposit", this.customerId);
		} else {
			return String.format("C%d OpenAccount", this.customerId);
		}
	}
}
