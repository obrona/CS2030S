/**
 * Takes an item and return the item in a box.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Theodore Lim 10C
 */
public class BoxIt<T> implements Transformer<T, Box<T>> {

	public Box<T> transform(T content) {
		return Box.<T>ofNullable(content);
	}
}
