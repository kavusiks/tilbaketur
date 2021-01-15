package tilbaketur.restapi;

import static tilbaketur.restapi.CarListResource.CARLIST_RESOURCE_PATH;
import static tilbaketur.restapi.UserListResource.USERLIST_RESOURCE_PATH;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tilbaketur.core.WorkSpace;

@Path(WorkSpaceService.WORKSPACE_SERVICE_PATH)
public class WorkSpaceService {
  public static final String WORKSPACE_SERVICE_PATH = "workspace";
  private static final Logger LOG = LoggerFactory.getLogger(WorkSpaceService.class);

  @Inject
  private WorkSpace workSpace;

  /**
   * The root resource, i.e. /todo
   *
   * @return the WorkSpace
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public WorkSpace getWorkSpace() {
    return workSpace;
  }

  @Path("/" + USERLIST_RESOURCE_PATH)
  public UserListResource getUserListResource() {
    LOG.debug("Sub-resource for UserList: ");
    return new UserListResource();
  }

  @Path("/" + CARLIST_RESOURCE_PATH)
  public CarListResource getCarListResource() {
    LOG.debug("Sub-resource for CarList: ");
    return new CarListResource();
  }

}
