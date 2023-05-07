import java.util.Arrays;

public class Bank {
	private static Counter[] counters;

	private static BankQueue bankqueue;


	public static void createCounters(int numOfCounters) {

		counters = new Counter[numOfCounters];

		for(int i = 0; i < numOfCounters; i++) {
			counters[i] = new Counter(i);
		}

	}



	public static Counter getAvailable() {

		Counter pointer = null;
		for (int i = 0; i < counters.length; i += 1) {
			if (counters[i].isAvailable()) {

				pointer = counters[i];
				break;
			}

		}

		return pointer;
	}



	public static void createQueue(int length){


		bankqueue = new BankQueue(length);

	}


	public static boolean isQueueFull() {

		return bankqueue.isFull();

	}

	public static boolean isQueueEmpty() {

		return bankqueue.isEmpty();
	}







	public static boolean addCustomer(Customer customer) {

		return bankqueue.enq(customer);

	}


	public static Customer removeCustomer() {

		return (Customer) bankqueue.deq();
	}

	public static String QueueToString() {

		return bankqueue.toString();

	}


}
