package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Encapsulates the workings of a InfiniteList
 */
public class InfiniteList<T> {
  /** the cached Sentinel.*/
  private static final InfiniteList<?> SENTINEL = new Sentinel();
  /** the head wrapped in Lazy and Maybe.*/
  private final Lazy<Maybe<T>> head;
  /** The tail wrapped in Lazy.*/
  private final Lazy<InfiniteList<T>> tail;

  /**
   * Constructor of an InfiniteList.
   */
  private InfiniteList() { 
    this.head = Lazy.of(() -> null);
    this.tail = Lazy.<InfiniteList<T>>of(() -> null);
  }

  /**
   * Constructor for InfiniteList.
   *
   * @param head the value you want the head to be.
   * @param tail the Producer that produce an InfiniteList.
   */
  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    // TODO
    this.head = Lazy.of(Maybe.some(head));
    this.tail = Lazy.of(tail);
  }

  /**
   * Constructor that takes in 2 lazys.
   *
   * @param head the Lazy head.
   * @param tail the Lazy that produces another InfiniteList.
   */
  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) {
    // TODO
    this.head = head;
    this.tail = tail;
  }

  /**
   * Creates an InfiniteList whose elements are the output
   * of Producer.
   * 
   * @param <T> the type of the result.
   * @param producer the function that creates the head.
   * @return InfiniteList of the result.
   */
  public static <T> InfiniteList<T> generate(Producer<T> producer) {
    // TODO
    return new InfiniteList<T>(Lazy.of(() -> Maybe.some(producer.produce())), 
        Lazy.of(() -> generate(producer)));
  }

  /**
   * Creates InfiniteList of the seed and the result of .
   * the seed inputted into the function next ith times.
   * 
   * @param <T> the type of the seed.
   * @param seed the starting value.
   * @param next the unary function.
   * @return the InfiniteList of x, f(x), f(f(x)).
   */
  public static <T> InfiniteList<T> iterate(T seed, Transformer<? super T, ? extends T> next) {
    // TODO
    return new InfiniteList<T>(Lazy.of(Maybe.some(seed)), 
        Lazy.of(() -> iterate(next.transform(seed), next)));
  }

  /**
   * returns the actual head. If head is None.
   * checks tail until gets the actual head.
   * 
   * @return the head.
   */
  public T head() throws NoSuchElementException {
    // TODO
    return this.head.get().orElseGet(() -> this.tail.get().head());
  }

  /**
   * return the tail with the 1st actual head. If head
   * is None, continue until find actual head.
   *
   * @return the 1st actual tail.
   */
  public InfiniteList<T> tail() throws NoSuchElementException {
    // TODO
    return this.head.get().map(x -> this.tail.get()).orElseGet(() -> this.tail.get().tail());
  }

  /**
   * maps the elements of the InfiniteList to something
   * defined by mapper.
   *
   * @param <R> the return type of the mapper.
   * @param mapper the mapping function.
   * @return the mapped infiniteList.
   */
  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
    // TODO
    return new InfiniteList<>(this.head.map(x -> x.map(mapper)),
        this.tail.map(x -> x.map(mapper)));
  }

  /**
   * filters the InfiniteList so that elements that failed
   * are turned to None and ignored.
   *
   * @param predicate the filter function.
   * @return the filtered InfiniteList.
   */
  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    // TODO
    return new InfiniteList<>(this.head.map(x -> x.filter(predicate)),
        this.tail.map(x -> x.filter(predicate)));
  }

  /**
   * the static nested class that markes the end of an
   * finite InfiniteList.
   */
  private static class Sentinel<T> extends InfiniteList<Object> {

    /**
     * A constructor for Sentinel.
     */
    public Sentinel() {
      super();
    }

    /**
     * returns the String representation of Sentinel.
     * 
     * @return the String - . 
     */
    @Override
      public String toString() {
        return "-";
      }

    /**
     * map for Sentinel.
     *
     * @param <R> the return type of mapper.
     * @param mapper the mapping function.
     * @return just the Sentinel.
     */
    @Override
      public <R> InfiniteList<R> map(Transformer<? super Object, ? extends R> mapper) {
        return InfiniteList.<R>sentinel();
      }

    /**
     * filter for Sentinel.
     *
     * @param pred the predicate.
     * @return the cached Sentinel.
     */
    @Override
      public InfiniteList<Object> filter(BooleanCondition<? super Object> pred) {
        return InfiniteList.<Object>sentinel();
      }

    /**
     * the getter for the head.
     *
     * @return throws an error.
     */
    @Override
      public Object head() throws NoSuchElementException {
        throw new NoSuchElementException("empty");
      }

    /**
     * gets the tail.
     * 
     * @return throws an error.
     */
    @Override
      public InfiniteList<Object> tail() throws NoSuchElementException{
        throw new NoSuchElementException("empty");
      }

    /**
     * takeWhile for Sentinel.
     *
     * @return the cached Sentinel.
     */
    @Override 
      public InfiniteList<Object> takeWhile(BooleanCondition<? super Object> pred) {
        return InfiniteList.<Object>sentinel();
      }

    /**
     * reduce for Sentinel.
     * @param <U> the return type of the folder.combine.
     * @return just the identity.
     */
    @Override
      public <U> U reduce(U identity, Combiner<U, ? super Object, U> folder) {
        return identity;
      }
  }

  /**
   * typecast the cached Sentinel.
   *
   * @param <T> the type you want.
   * @return the typecasted Sentinel.
   */
  public static <T> InfiniteList<T> sentinel() {
    // TODO
    @SuppressWarnings("unchecked")
    InfiniteList<T> temp = (InfiniteList<T>) SENTINEL;
    return temp;
  }

  /**
   * checks if a InfiniteList is a Sentinel.
   *
   * @return the answer, true or false.
   */
  public boolean isSentinel() {
    return this == SENTINEL;
  }


  /**
   * Delays the checking of if head is none in the tail.
   * If the head is none, head() will auto go to tail and
   * run the check.
   *
   * @param n the no. of elements.
   * @return the truncated InfiniteList.
   */
  public InfiniteList<T> limit(long n) {
    // TODO
    if (n <= 0 || this.isSentinel()) {
      return InfiniteList.<T>sentinel();
    } else {
      return new InfiniteList<T>(this.head, 
          Lazy.of(() -> this.head.get() 
              .map(x -> this.tail.get().limit(n - 1))
                  .orElseGet(() -> this.tail.get().limit(n))));
    }
  }

  /**
   * Delays the checking of the head in the tail
   * thats why the head of the new InfiniteList is of None,
   * so head() will go to the tail and run the checks.
   * If head is None, will jump to last orElseGet, no filtering 
   * needed, but if head is Some, we check. If pass
   * return the InfiniteList of head this.head, and tail.get().
   * takeWhile. If fail, the tail return Sentinel, so if head()
   * is called, error is thrown
   *
   * @param predicate the test.
   * @return the truncated InfiniteList.
   */
  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
    // TODO
    return new InfiniteList<T>(Lazy.of(() -> Maybe.<T>none()),
        Lazy.of(() -> this.head.get().
            map(x -> Maybe.some(x).filter(predicate).map(y -> 
                new InfiniteList<T>(this.head, 
                    Lazy.of(() -> this.tail.get().takeWhile(predicate))))
                        .orElseGet(() -> InfiniteList.<T>sentinel()))
                            .orElseGet(() -> this.tail.get().takeWhile(predicate))));
  }

  /**
   * folds the List from left to right.
   *
   * @param <U> the type of the output.
   * @param identity the starting value for folder.combine .
   * @param folder the function to fold.
   * @return the output of folding the list.
   */
  public <U> U reduce(U identity, Combiner<U, ? super T, U> folder) {
    // TODO
    return this.tail.get().reduce(
        this.head.get().
            map(x -> folder.combine(identity, x)).orElse(identity), folder);
  }

  /**
   * counts the no. of elements in the List.
   *
   * @return the no. of elements.
   */
  public long count() {
    // TODO
    return this.reduce(0, (x,y) -> x + 1);
  }

  /**
   * creates a List of the elements in your InfiniteList.
   *
   * @return the list with all your elements.
   */
  public List<T> toList() {
    // TODO
    List<T> output = new ArrayList<>();
    InfiniteList<T> curr = this;
    while (!curr.isSentinel()) {
      //System.out.println(1);
      curr.head.get().ifPresent(x -> output.add(x));
      curr = curr.tail.get();
    }
    return output;
  }

  /**
   * the String representation of the InfiniteList.
   *
   * @return the String representation of the InfiniteList.
   */
  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }
}
