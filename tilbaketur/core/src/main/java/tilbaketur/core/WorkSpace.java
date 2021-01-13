package tilbaketur.core;

public class WorkSpace {
  private UserList userList;
  private CarList carList;
  private AbstractUser loggedInAs;

  /**
   * Creating empty workspace.
   */
  public WorkSpace() {
    userList = new UserList();
    carList = new CarList();
    loggedInAs = null;
  }

  public WorkSpace(UserList ul, CarList cl) {
    userList = ul;
    carList = cl;
  }

  /**
   * Creating workspace with existing data.
   *
   * @param ul existing userlist
   * @param cl existing carList
   * @param user existing loggedin user
   */
  public WorkSpace(UserList ul, CarList cl, AbstractUser user) {
    userList = ul;
    carList = cl;
    loggedInAs = user;
  }

  public void setUserList(UserList userList) {
    this.userList = userList;
  }

  public void setCarList(CarList carList) {
    this.carList = carList;
  }

  public void setLoggedInAs(AbstractUser user) {
    this.loggedInAs = user;
  }

  public AbstractUser getLoggedInAs() {
    return this.loggedInAs;
  }

  public UserList getUserList() {
    return userList;
  }

  public CarList getCarList() {
    return carList;
  }

  /**
   * Adding an user to the userlist.
   *
   * @param user the user to be added.
   */
  public void addUser(AbstractUser user) {
    if (userList != null) {
      userList.addItem(user);
    }
  }

  public void removeUser(AbstractUser user) {
    userList.getItemsList().remove(user);
  }

  /**
   * Adding a car to the carlist.
   *
   * @param car the car to be added
   */
  public void addCar(Car car) {
    if (carList != null) {
      carList.addItem(car);
    }
  }


}
