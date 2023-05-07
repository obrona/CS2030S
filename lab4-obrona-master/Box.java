/**
 * A generic box storing an item.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Theodore Lim 10C
 */
public class Box<T> {
	private final T content;
  private static final Box<?> EMPTY_BOX = new Box<Object>(null);

	private Box(T content) {
		this.content = content;
	}

  public static <T> Box<T> of(T content) {
		if (content == null) {
			return null;
    } else {
			return new Box<T>(content);
		}
  }

  public static <T> Box<T> ofNullable(T content) {
		if (content == null) {
			return Box.<T>empty();
		} else {
		  return new Box<T>(content);
		}
  }

  public boolean isPresent() {
		return (!(this.content == null));
	}

  @Override
	public String toString() {
	  if (this.content == null) {
			return "[]";
		}
    return "[" + content.toString() + "]";
	}
  
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if(obj instanceof Box<?>) {
			Box<?>b = (Box<?>) obj;

			if (content instanceof Box<?> && b.content instanceof Box<?>) {
				return this.content.equals(b.content);
			} else if (this.content == null || b.content == null) {
				return (this.content == b.content);
			} else {
				return this.content.equals(b.content);
			}
	  } else  {
			return false;
		}
	}

  public static <T> Box<T> empty() {
    @SuppressWarnings("unchecked")
		Box<T> temp = (Box<T>) EMPTY_BOX;
    return temp;
	}

  public Box<T> filter(BooleanCondition<? super T> pred) {
		if (this.content == null) {
			return this;
		} else if (pred.test(this.content)) {
			return this;
		} else {
		return Box.<T>empty();
		}
	} 
  
	public <U> Box<U> map(Transformer<? super T, U> t) {
		if (this.content == null) {
			return Box.<U>empty();
		} else {
			U result = t.transform(this.content);
			if (result == null) {
				return Box.<U>empty();
		  } else {
				return Box.<U>of(result);
		  }
    } 
  }
}


