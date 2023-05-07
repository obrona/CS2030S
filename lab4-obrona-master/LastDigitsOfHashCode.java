/**
 * A transformer with a parameter k that takes in an object x 
 * and outputs the last k digits of the hash value of x.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Theodore Lim 10C
 */
public class LastDigitsOfHashCode implements Transformer<Object, Integer> {
	private int powerOfTen;

	public LastDigitsOfHashCode(int numberOfDigits) {
		this.powerOfTen = 1;
		for(int i = 0; i < numberOfDigits; i++) {
			powerOfTen = powerOfTen * 10;
		}
	}

	public Integer transform(Object b) {
		int answer = b.hashCode() % powerOfTen;
		return (answer < 0 ) ? -answer : answer;
	}
}

