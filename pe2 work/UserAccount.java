/**
 * The UserAccount class represents a user account with a name and email.
 */
public class UserAccount {
  private String name;
  private String email;

  /**
   * Constructs a new UserAccount with the specified name and email.
   *
   * @param name the name of the user
   * @param email the email address of the user
   */
  public UserAccount(String name, String email) {
    this.name = name;
    this.email = email;
  }

  /**
   * Returns the name of the user.
   *
   * @return the user's name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the user.
   *
   * @param name the new name for the user
   */
  public UserAccount setName(String name) {
    String newEmail = this.email;
    return new UserAccount(name, newEmail);
  }

  /**
   * Returns the email address of the user.
   *
   * @return the user's email address
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email address of the user.
   *
   * @param email the new email address for the user
   */
  public UserAccount setEmail(String email) {
    String newName = this.name;
    return new UserAccount(newName, email);
  }
}
