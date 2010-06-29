package bodl.commons.db.hsqldb.internal;

import java.io.PrintWriter;
import java.util.logging.Logger;

import bodl.commons.db.hsqldb.HsqldbService;
import org.hsqldb.Server;


/**
 * TODO: make this configurable
 */
public class HsqldbServiceImpl implements HsqldbService {

  Logger log = Logger.getLogger(HsqldbServiceImpl.class.getName());

  private boolean isNetwork = true;
  private String url = null;
  private Server server;

  private String serverProps = "database.1=file:data/hsqldb/bodl;dbname.1=bodl";

  public void start() {
    if (this.isNetwork) {
      if (this.url == null) {
        this.url = "jdbc:hsqldb:hsql://localhost/test";
      }

      if (server == null) {
        server = new Server();
      }

      server.putPropertiesFromString(serverProps);

      server.setDatabaseName(0, "test");
      server.setDatabasePath(0, "mem:test;sql.enforce_strict_size=true");

      server.setDatabaseName(3, "sandbox");
      server.setDatabasePath(3, "file:data/hsqldb/sandbox");

      server.start();
    } else {
      if (url == null) {
        url = "jdbc:hsqldb:mem:test;sql.enforce_strict_size=true";
      }
    }
  }

  public void stop() {
    if (server != null) {
      server.stop();
    }
  }

}
