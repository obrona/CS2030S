/**
 * Represents a generic Pair of two elements.
 *
 * @param <S> the type of the first element
 * @param <T> the type of the second element
 */
class Pair<S, T> {
  private S first;
  private T second;

  /**
   * Constructs a new Pair with the given first and second values.
   *
   * @param first  the first value of the pair
   * @param second the second value of the pair
   */
  public Pair(S first, T second) {
    this.first = first;
    this.second = second;
  }

  /**
   * Returns the first value of the pair.
   *
   * @return the first value of the pair
   */
  public S getFirst() {
    return this.first;
  }

  /**
   * Returns the second value of the pair.
   *
   * @return the second value of the pair
   */
  public T getSecond() {
    return this.second;
  }

  /**
   * Determines if the given object is equal to the current Pair object.
   * Two Pairs are considered equal if their first and  second elements 
   * are equal.
   *
   * @param o the object to compare for equality
   * @return true if the given object is an equal Pair, false otherwise
   */
  public boolean equals(Object o) {
    if (o instanceof Pair<?, ?>) {
      Pair<?, ?> p = (Pair<?, ?>) o;
      return p.first.equals(this.first) && p.second.equals(this.second);
    }
    return false;
  }

  /**
   * Returns a string representation of the Pair object.
   * The string format is: (first, second)
   *
   * @return a string representation of the Pair
   */
  public String toString() {
    return "(" + first + ", " + second + ")";
  }
}

