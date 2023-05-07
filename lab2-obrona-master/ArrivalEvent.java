public class ArrivalEvent extends Event {
	//Constructor

	//private int customerId;

	//private double serviceTime;

	private Customer customer;



	public ArrivalEvent(double arrivalTime, Customer customer) {
		super(arrivalTime);

		this.customer = customer;

		//this.serviceTime = customer.getServiceTime();
	}

	@Override
		public String toString() {

			String str = "";

			str = String.format(this.getTime() + "00: %s arrived" + " " + Bank.QueueToString() , 
					this.customer.toString());

			return str;
		}







	public Event[] simulate() {
		Counter counter = Bank.getAvailable();
		//System.out.println(counter);
		if(counter == null) {

			if (Bank.isQueueFull()) {

				return new Event[]{
					new DepartureEvent(this.getTime(), customer)
				};
			}

			else {

				return new Event[] {new JoinQueueEvent(this.getTime(), customer)};
			}






		}
		else {

			return new Event[] {
				new ServiceBeginEvent(this.getTime(),customer,counter)
			} ;



		}


	}


}
