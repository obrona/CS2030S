package cs2030s.fp;
/**
 * The Lazy class is a wrapper around any arbitary
 * function that takes in no parameters. The function
 * is only evaluated once and the result is stored
 */
public class Lazy<T> {
  /** producer stores the function of no parameters.*/
  private Producer<? extends T> producer;
  /** value stores the result of evaluating the function*/
  private Maybe<T> value;

  /**
   * A constructor that already takes in a value
   *
   * @param v the value to be wrapped
   */
  private Lazy(T v) {
    this.value = Maybe.<T>some(v);
  }

  /**
   * Another constructor that takes in the
   * function of no parameters
   *
   * @param prod the function to evaluate when needed
   */
  private Lazy(Producer<? extends T> prod) {
    this.producer = prod;
    this.value = Maybe.<T>of(null);
  }

  /**
   * The factory method to produce new instances
   * of Lazy(v)
   *
   * @param v the value of Lazy to be created
   * @param <T> the type of v
   * @return the Lazy wrapped around v
   */
  public static <T> Lazy<T> of(T v) {
    return new Lazy<T>(v);
  }

  /**
   * Factory method for the producer
   *
   * @param prod the producer
   * @param <T> the type of producer
   * @return a new instance of Lazt
   */
  public static <T> Lazy<T> of(Producer<? extends T> prod) {
    return new Lazy<T>(prod);
  }

  /**
   * evaluates the function if not yet evaluated
   * returns the value and stores it
   *
   * @return the result of evaluating producer
   */
  public T get() {
    value = Maybe.<T>some(value.orElseGet(producer));
    return value.orElse(null);
  }

  /**
   * returns value in String if already known
   * otherwise returns a ?
   */
  @Override
    public String toString() {
      return value.map(x -> String.valueOf(x)).orElse("?");
    }

  /** 
   * maps value to something else
   *
   * @param <U> the return type
   * @param trans the Transformer
   * @return returns a new Lazy
   */
  public <U> Lazy<U> map(Transformer<? super T, ? extends U> trans) {
    return  Lazy.<U>of(() -> trans.transform(this.get()));
  }

  /**
   * a map that returns a value wrapped around Lazy once
   * instead of twice when trasform return a Lazy
   *
   * @param <U> the return type
   * @param t the Transformer
   * @return a new Lazy
   */
  public <U> Lazy<U> flatMap(Transformer<? super T, ? extends Lazy<? extends U>> t) {
    Lazy<U> temp = Lazy.of(() -> t.transform(this.get()).get());
    return temp;
  }

  /** 
   * a filter that returns a boolean wrapped around Lazy
   *
   * @param b the BooleanCondition
   * @return the boolean conditon function wrapped around Lazy
   */
  public Lazy<Boolean> filter(BooleanCondition<? super T> b) {
    return Lazy.<Boolean>of(() -> b.test(this.get()));
  }

  /**
   * 2 Lazys are equal if the value from evaluation is equal
   *
   * @param obj the Object you want to determine if equal
   * @return true or false
   */
  @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof Lazy)) {
        return false;
      } else {
        @SuppressWarnings("unchecked")
        Lazy<?> temp = (Lazy<?>) obj;
        return this.get().equals(temp.get());
      }
    }

  /**
   * combine takes in another Lazy and return a Lazy
   * that contains the lazily evaluated function that 
   * takes in this value and other Lazy value
   * 
   * @param <U> the return type
   * @param <S> the type of the other Lazy value
   * @param l the other Lazy
   * @param c the Combiner
   * @return the Lazy containing the lazily evaluated function
   */
  public <U,S> Lazy<U> combine(Lazy<? extends S> l,
      Combiner<? super T, ? super S, ? extends U> c) {
    return Lazy.<U>of(() -> c.combine(this.get(), l.get()));
  }
}
