import java.util.Arrays;

public class Bank {
	private Array < Counter > counters;
	private BankQueue bankqueue;
	public void createCounters(int numOfCounters, int counterQueueSize) {
		counters = new Array < Counter > (numOfCounters);
		for (int i = 0; i < numOfCounters; i++) {
			counters.set(i, new Counter(i, counterQueueSize));
		}
	}

	public Counter getAvailable() {
		Counter counter = counters.min();
		//System.out.println(counter.toString());
		//System.out.println(counter.isQueueFull());
		if (!counter.isAvailable() && counter.isQueueFull()) {
			counter = null;
		}
		//System.out.println(counter.toString());
		return counter;
	}



	public void createQueue(int length) {
		bankqueue = new BankQueue(length);
	}

	public boolean isQueueFull() {
		return bankqueue.isFull();
	}

	public boolean isQueueEmpty() {
		return bankqueue.isEmpty();
	}

	public boolean addCustomer(Customer customer) {
		return bankqueue.enq(customer);
	}

	public Customer removeCustomer() {
		return (Customer) bankqueue.deq();
	}

	public String QueueToString() {
		return bankqueue.toString();
	}
}
