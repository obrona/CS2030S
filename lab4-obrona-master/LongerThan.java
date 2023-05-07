/**
 * A boolean condition with parameter x that can be applied to
 * a string.  Returns true if the string is longer than x; false
 * otherwise.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Theodore Lim 10C 
 */

public class LongerThan implements BooleanCondition<String> {
	private int length;

	public LongerThan(int length) {
		this.length = length;
	}

	public boolean test(String str) {
		return (str.length() > length);
	}
}
