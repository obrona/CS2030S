public class Counter {

    private int id;

    private boolean available;

    public Counter(int id) {

        this.available = true;

        this.id = id;

    }

    public boolean isAvailable() {

        return this.available;
    }

    public void setUnavailable() {

        available = false;
    }

    public void setAvailable()  {

        available = true;
    }

    @Override
    public String toString() {

        return "by S"  +  id;
    }

}
