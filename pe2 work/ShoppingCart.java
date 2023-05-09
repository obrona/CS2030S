import cs2030s.fp.Maybe;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Stream;

/**
 * The ShoppingCart class represents a user's shopping cart, 
 * managing a collection of products and their quantities.
 */
public class ShoppingCart {
  private Map<Integer, Pair<Product, Integer>> cartContents;
  private UserAccount userAccount;

  /**
   * Constructs a new ShoppingCart.
   *
   * @param cartContents the contents of the shopping cart
   */
  public ShoppingCart(UserAccount u, Map<Integer, Pair<Product, Integer>> cartContents) {
    this.userAccount = userAccount;
    this.cartContents = cartContents;
  }

  /**
   * Returns the product when given an id.
   *
   * @param number the product id used to get the product
   */
  private Maybe<Product> findProduct(int number) {
    Pair<Product, Integer> pair = cartContents.get(number);
    /*if (pair == null) {
      return null;
    }*/
    return Maybe.of(pair).map(x -> x.getFirst());
  }

  /**
   * Returns a stream of the (product, quantity) pair.
   *
   * @param number the product id used to get the product
   */
  private Stream<Pair<Product, Integer>> getProductStream() {
    return cartContents.values().stream();
  }

  /**
   * Returns the product quantity in the shopping cart.
   *
   * @param product the product to get the quantity of
   */
  private int getProductQuantity(Product product) {
    return cartContents.get(product.getProductId()).getSecond();
  }

  /**
   * Removes the specified product from the shopping cart.
   *
   * @param product the product id of the product to remove from the cart
   */
  public void removeProduct(int productId) {
    cartContents.remove(productId);
  }

  /**
   * Returns the total for the product in the shopping cart.
   *
   * @return the total of the product in the cart
   */
  public double getTotal() {
    //double total = 0;
    /*for (Pair<Product, Integer> pair : cartContents.values()) {
      total += pair.getFirst().getPrice() * pair.getSecond();
    }
    return total; */
    return this.getProductStream().reduce(0.0, (x,y) -> x + y.getFirst().getPrice()
        * (double) y.getSecond(), (x,y) -> x + y);
  }

  /**
   * Updates the price of the specified product in the shopping cart.
   *
   * @param product the product to update
   * @param newPrice the new price for the product
   */
  public void updateProductPrice(int productId, int newPrice) {
    //Product product = findProduct(productId);
    /*if (product == null) {
      // Incorrect Product Id
    } else {
      int quantity = getProductQuantity(product);
      Product newProduct = product.setPrice(newPrice);
      cartContents.put(productId, new Pair<>(newProduct, quantity));
    } */
    
    Maybe<Product> product = findProduct(productId);
    int quantity = product.map(x -> getProductQuantity(x)).orElse(-1);
    //Product(-1, "hh", 99) is placeholder for orElse
    Product newProduct = product.map(x -> x.setPrice(newPrice)).orElse(new Product(-1, "hh", 99));
    product.ifPresent(x -> cartContents.put(productId, new Pair<>(newProduct, quantity)));
  }

  /**
   * Updates the quantity of the specified product in the shopping cart.
   *
   * @param product the product to update
   * @param newQuantity the new quantity for the product
   */
  public void updateProductQuantity(int productId, int newQuantity) {
    /*Product product = findProduct(productId);
    if (product == null) {
      // Incorrect Product Id
    } else {
      cartContents.put(productId, new Pair<>(product, newQuantity));
    }*/
    Maybe<Product> product = findProduct(productId);
    product.ifPresent(x -> cartContents.put(productId, 
        new Pair<>(product.orElse(new Product(-1,"hh",99)),
            newQuantity)));
  }

  /**
   * Bundles two products together and adds to the cart. 
   *
   * @param a the first product id 
   * @param b the second product id 
   */
  public void bundleProduct(int a, int b) {
    /*Product productA = findProduct(a);
    Product productB = findProduct(b);
    if (productA == null || productB == null) {
      // No Such Product
    } else {
      int quantity = getProductQuantity(productA);
      Product bundled = productA.bundle(productB);
      cartContents.remove(b);
      cartContents.put(a, new Pair<>(bundled, quantity));
    } */
    Maybe<Product> productA = findProduct(a);
    Maybe<Product> productB = findProduct(b);
    int quantity = productA.map(x -> getProductQuantity(x)).orElse(-1);
    Maybe<Product> bundled = productA.flatMap(x ->
        productB.map(y -> x.bundle(y)));
    productB.ifPresent(x -> cartContents.remove(b));
    bundled.ifPresent(x -> cartContents.put(a, new Pair<>(
        bundled.orElse(new Product(-1, "hh", 99)), quantity)));

  }

  /**
   * Returns a string representing the contents of the shopping cart, 
   * sorted in ascending order of product id.
   *
   * @return The shopping cart as a string
   */
  public String cartContents() {
    ArrayList<Pair<Product, Integer>> sortedProducts = new ArrayList<>(cartContents.values());
    // Double.compare(d1,d2) returns 0 if d1 is numerically equal to d2; 
    // a value less than 0 if d1 is numerically less than d2; 
    // and a value greater than 0 if d1 is numerically greater than d2
    sortedProducts.sort((x, y) -> Double.compare(y.getFirst().getPrice(), x.getFirst().getPrice()));
    /*String s = "";
    for (Pair<Product, Integer> pair : sortedProducts) {
      s += pair.getFirst() + " | Quantity: " + pair.getSecond() + "\n";
    }
    return s;*/
    return sortedProducts.stream().reduce("", (x,y) -> x + y.getFirst() + 
        " | Quantity: " + y.getSecond() + "\n", (x,y) -> x + y);
  }

  /**
   * Checks if this object equals to another
   *
   * @return true if the given object o equals to this.  Otherwise return false
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof ShoppingCart) {
      ShoppingCart sc = (ShoppingCart) o;
      return sc.cartContents.equals(this.cartContents);
    }
    return false;
  }

  /**
   * Return a string representation of the map of products/quantity pairs.
   *
   * @return a string representation of this shopping cart.
   */
  @Override
  public String toString() {
    return this.cartContents.toString();
  }
}
