/**

 
* CS2030S Lab 0: Point.java
 * Semester 2, 2022/23
 *
 * <p>The Point class encapsulates a point on a 2D plane.
 *
 * @author XXX
 */

class Point {
     private double x;
     private double y;
     Point(double x, double y) {
         this.x = x;
         this.y = y;

     }
     public double getX() {
         return x;

     }
     public double getY() {
         return y;
     }
    public void setX(double x) {
         this.x = x;
    }
    public void setY(double y) {
         this.y = y;
    }


     @Override
     public String toString() {

         return "(" + x + ","  + " "+ y + ")";

     }

    // TODO
}
