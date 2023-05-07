import java.util.Arrays;

/**
 * The Array<T> for CS2030S
 *
 * @author XXX
 * @version CS2030S AY22/23 Semester 2
 */
class Array <T extends Comparable <T>> { // TODO: Change to bounded type parameter
	private T[] array;

	Array(int size) {
		@SuppressWarnings("unchecked")
			T[] tmp = (T[]) new Comparable[size];
		this.array = tmp;
		// TODO
	}

	public void set(int index, T item) {
		this.array[index] = item;
		// TODO
	}

	public T get(int index) {
		return this.array[index];
		// TODO
	}

	public T min() {
		// TODO
		T output = null;
		for (int i = 0; i < array.length; i++) {
			if (output == null) {
				output = array[i];
			} else {
				output = (output.compareTo(array[i]) <= 0) ? output : array[i];
			}

		}
		return output;
	}

	@Override
		public String toString() {
			StringBuilder s = new StringBuilder("[ ");
			for (int i = 0; i < array.length; i++) {
				s.append(i + ":" + array[i]);
				if (i != array.length - 1) {
					s.append(", ");
				}
			}
			return s.append(" ]").toString();
		}
}
