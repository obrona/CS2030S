import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * Encapsulates the result of a query: for a bus stop and a search string,
 * it stores a map of bus services that servce stops with matching name.
 * e.g., stop 12345, name "MRT", with map contains:
 *    96: 11223 "Clementi MRT"
 *    95: 22334 "Buona Vista MRT"
 *
 * author: Ooi Wei Tsang
 * version: CS2030S AY22/23 Semester 2, Lab 8
 */
class BusRoutes {
  /** the first bus stop */
  final BusStop stop;
  /** the name of the second bus stop */
  final String name;
  /** the Map wrapped in CompletableFuture */
  final CompletableFuture<Map<BusService, CompletableFuture<Set<BusStop>>>> services;

  /**
   * Constructor for creating a bus route.
   * @param stop The first bus stop.
   * @param name The second bus stop.
   * @param services The set of bus services between the two stops.
   */
  BusRoutes(BusStop stop, String name, 
      CompletableFuture<Map<BusService, CompletableFuture<Set<BusStop>>>> services) {
    this.stop = stop;
    this.name = name;
    this.services = services;
  }

  /**
   * Return a string describing the bus route.
   * @return The first line contains the query information:
   *     bus stop id and search string.  The remaining line contains 
   *     the bus services and matching stops served, wrapped in 
   *     CompletableFuture.
   */
  public CompletableFuture<String> description() {
    CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
      String result = "Search for: " + this.stop + " <-> " + name + ":\n";
      result += "From " +  this.stop + "\n";
      return result;
    });
    /*for (BusService service : services.keySet()) {
        //Set<BusStop> stops = services.get(service);
      CompletableFuture<String> temp = services.get(service).thenApplyAsync(x -> 
          describeService(service, x));
      cf = cf.thenCombineAsync(temp, (x,y) -> x + y);
      //result += describeService(service, stops);
    }*/
   CompletableFuture<String> output = services.thenComposeAsync(x -> {
      CompletableFuture<String> temp = CompletableFuture.completedFuture("");
      for(BusService service : x.keySet()) {
        CompletableFuture<String> other = x.get(service).thenApplyAsync(y -> 
            describeService(service, y));
        temp = temp.thenCombineAsync(other, (string1, string2) -> string1 + string2);
      }
      return temp;
    });
    return cf.thenCombineAsync(output, (s1, s2) -> s1 + s2);
  }

  /**
   * Return a string representation a bus service and its matching stops.
   * @param service A bus service
   * @param stops A set of bus stops served by the service
   * @return The first line contains the query information:
   *     bus stop id and search string.  The remaining line contains 
   *     the bus services and matching stops served.
   */
  public String describeService(BusService service, Set<BusStop> stops) {
    if (stops.isEmpty()) {
      return "";
    } 
    return stops.stream()
        .filter(stop -> stop != this.stop) 
        .reduce("- Can take " + service + " to:\n",
            (str, stop) -> str += "  - " + stop + "\n",
            (str1, str2) -> str1 + str2);
  }
}
