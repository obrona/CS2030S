package cs2030s.fp;
import java.util.NoSuchElementException;
/**
 * CS2030S Lab 5
 * AY22/23 Semester 2
 *
 * @author Theodore Lim 10C
 */
 public abstract class Maybe<T> {
   private static final Maybe<?> noneObj = new None();
	 
	 private static class None extends Maybe<Object> {
     
		 @Override
		 public String toString() {
			 return "[]";
		 }

		 @Override
		 public boolean equals(Object obj) {
			 if (obj instanceof None) {
				 return true;
			 } else {
				 return false;
			 }
		 }

		 @Override
		 protected Object get() throws NoSuchElementException {
			 throw new NoSuchElementException();
		 }
	   
		 @Override
		 public Maybe<Object> filter(BooleanCondition<? super Object> pred) {
			 return Maybe.<Object>none();
		 }

		 @Override
		 public <U> Maybe<U> map(Transformer<? super Object, ? extends U> changer) {
			 return Maybe.<U>of(null);
		 }

		 @Override
		 public <U> Maybe<U> flatMap(Transformer<? super Object, 
		     ? extends Maybe<? extends U>> change) {
		   return Maybe.<U>of(null);
		 }

		 @Override
		 public Object orElse(Object t) {
			 return t;
		 }

		 @Override
		 public Object orElseGet(Producer<? extends Object> prod) {
			 return prod.produce();
	   }

		 @Override
		 public void ifPresent(Consumer<? super Object> cons) {
			 //Nothing
		 }
   }

	 private static final class Some<T> extends Maybe<T> {
		 private final T content;

		 public Some(T content) {
			 this.content = content;
		 }

		 @Override
		 public String toString() {
			 if (content == null) {
				 return "[null]";
			 } else {
				 return "[" + content.toString() + "]";
			 }
		 }

		 @Override
		 public boolean equals(Object obj) {
			 if (this == obj) {

				 return true;
			 } else if (!(obj instanceof Some<?>)) {
				 return false;
			 } else {
				 Some<?> temp = (Some<?>) obj;
				 if (this.content == null) {
					 return temp.content == null;
				 } else {
					 return this.content.equals(temp.content);
				 }
			 }
		 }

		 @Override
		 protected T get() throws NoSuchElementException {
			 return this.content;
		 }
	   
		 @Override
		 public Maybe<T> filter(BooleanCondition<? super T> pred) {
			 if (content == null) {
				 return this;
			 } else if (pred.test(content)) {
				 return this;
			 } else {
				 return Maybe.<T>none();
		   }
		 }

	   @Override
		 public <U> Maybe<U> map(Transformer<? super T, ? extends U> changer) {
			 return Maybe.<U>some(changer.transform(content));
		 }
     
		 @Override
		 public <U> Maybe<U> flatMap(Transformer<? super T, 
		     ? extends Maybe<? extends U>> change) {
			 @SuppressWarnings("unchecked")
			 Maybe<U> temp = (Maybe<U>) change.transform(content);
       return temp;
		 }

		 @Override
		 public T orElse(T t) {
			 return this.content;
		 }

		 @Override
		 public T orElseGet(Producer<? extends T> prod) {
			 return this.content;
		 }

		 @Override
		 public void ifPresent(Consumer<? super T> cons) {
			 cons.consume(this.content);
		 }
   }

	 public static <T> Maybe<T> none() {
		 @SuppressWarnings("unchecked")
		 Maybe<T> temp = (Maybe<T>) noneObj;
		 return temp;
	 }

	 public static <T> Maybe<T> some(T t) {
		 return new Some<T>(t);
   }

	 public static <T> Maybe<T> of(T content) {
		 if (content == null) {
			 return Maybe.<T>none();
		 } else {
			 return Maybe.<T>some(content);
		 }
	 }

	 protected abstract T get() throws NoSuchElementException;

	 public abstract Maybe<T> filter(BooleanCondition<? super T> pred);

	 public abstract <U> Maybe<U> map(Transformer<? super T, ? extends U> changer);

	 public abstract<U> Maybe<U> flatMap(Transformer<? super T, 
	     ? extends Maybe<? extends U>> change);
	 
   public abstract T orElse(T t);

	 public abstract T orElseGet(Producer<? extends T> prod);

	 public abstract void ifPresent(Consumer<? super T> cons);
}















