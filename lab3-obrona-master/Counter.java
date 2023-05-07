public class Counter implements Comparable < Counter > {

	private int id;

	private boolean available;

	private Queue < Customer > queue;

	public Counter(int id, int queueSize) {
		this.available = true;
		this.id = id;
		this.queue = new Queue < Customer > (queueSize);
	}

	public boolean isAvailable() {
		return this.available;
	}

	public void setUnavailable() {
		available = false;
	}

	public void setAvailable() {
		available = true;
	}

	public void addCustomer(Customer customer) {
		queue.enq(customer);
	}

	public Customer removeCustomer() {
		return queue.deq();
	}

	public boolean isQueueFull() {
		return queue.isFull();
	}

	public boolean isQueueEmpty() {
		return queue.isEmpty();
	}

	@Override
		public int compareTo(Counter o) {
			if (available) {
				return -1;
			} else if (!available && o.isAvailable()) {
				return 1;
			} else {
				if (queue.isFull()) {
					return 1;
				} else {
					return queue.length() - o.queue.length();
				}
			}
		}

	@Override
		public String toString() {
			return "by S" + id + " " + queue.toString();
		}

	public String QueueToString() {
		return "at S" + id + " " + queue.toString();
	}
}
