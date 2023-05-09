/**
 * The Product class represents a product with a name and price.
 */
public class Product {

  private static final double BundleDiscount = 0.90;
  private int productId;
  private String name;
  private double price;

  /**
   * Constructs a new Product with the specified name and price.
   *
   * @param productId the product ID of this produce
   * @param name the name of this product
   * @param price the price of this product
   */
  public Product(int productId, String name, double price) {
    this.productId = productId;
    this.name = name;
    this.price = price;
  }

  /**
   * Returns the name of this product.
   *
   * @return the product name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of this product.
   *
   * @param name the new product name
   */
  public Product setName(String name) {
    return new Product(this.productId, name, this.price);
  }

  /**
   * Returns the price of this product.
   *
   * @return the product price
   */
  public double getPrice() {
    return price;
  }

  /**
   * Sets the price of this product.
   *
   * @param price the new product price
   */
  public Product setPrice(double price) {
    //this.price = price;
    String nameCopy = this.name;
    return new Product(this.productId, nameCopy, price);
  }

  /**
   * Returns the product ID of this product.
   *
   * @return the product ID
   */
  public int getProductId() {
    return productId;
  }

  /**
   * Sets the product ID of this product.
   *
   * @param productId the new product ID
   */
  public Product setProductId(int productId) {
    //this.productId = productId;
    String nameCopy = this.name;
    return new Product(productId, nameCopy, this.price);
  }

  /**
   * Bundles two products together.
   *
   * @param otherProduct the other product to bundle
   */
  public Product bundle(Product otherProduct) {
    return new Product(this.productId, 
        this.name + " " + otherProduct.name + " (bundle)",
        (this.price + otherProduct.price) * Product.BundleDiscount);
  }

  /**
   * Swaps price with a given Product.
   *
   * @param otherProduct the other product to swap prices with
   */
  public Pair<Product, Product> swapPrice(Product otherProduct) {
    //double otherPrice = otherProduct.price;
    //otherProduct.price = this.price;
    //this.price = otherPrice;
    String s1 = this.name;
    String s2 = otherProduct.name;
    Product first = new Product(this.productId, s1, otherProduct.price);
    Product second = new Product(otherProduct.productId, s2, this.price);
    return new Pair<>(first, second);

  }

  /**
   * Returns a string representation of this product.
   *
   * @return a string representing the product's name and price
   */
  @Override
  public String toString() {
    return "Product: " + name + " | Price: " + price;
  }

  /**
   * Checks if this object equals to another
   *
   * @return true if the given object o equals to this.  Otherwise return false
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Product) {
      Product p = (Product) o;
      return p.productId == this.productId &&
             p.price == this.price && 
             p.name.equals(this.name);
    }
    return false;
  }
}
