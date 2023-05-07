import java.util.Random;

 public class RandomPoint extends Point {
    static int seed =1 ;
    static Random rng = new Random(seed);

      public   static void setSeed(int x) {
          seed = x;
          rng = new Random(seed);
      }
      RandomPoint(double minX, double maxX, double minY, double maxY) {
            super(maxX,maxY);
            setX((maxX-minX)*rng.nextDouble()+minX);
            setY((maxY-minY)*rng.nextDouble()+minY);



      }
     public static void main(String[] args) {
            Point p = new RandomPoint(0,1,0,1);
            setSeed(100);
            System.out.println(p.getX());
            System.out.println(p.getY());

     }



}
