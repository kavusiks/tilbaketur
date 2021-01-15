package tilbaketur.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tilbaketur.core.UserList;

public class UserListResource {
  public static final String USERLIST_RESOURCE_PATH = "userlist";
  private static final Logger LOG = LoggerFactory.getLogger(UserListResource.class);

  private UserList userList;
}
