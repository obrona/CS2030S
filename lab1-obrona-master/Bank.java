public class Bank {
     private static boolean[] available;




     public static void setCounters(boolean[] available1) {
         available = available1;

     }

     public static int getAvailable() {
         int counter = -1;
         for (int i = 0; i < available.length; i += 1) {
             if (available[i]) {
                 counter = i;
                 break;
             }

         }
          return counter;
     }

     public static void setUnavailable(int counter) {
         available[counter] = false;

     }

     public static void setAvailable(int counter) {
         available[counter] = true;
     }




}
