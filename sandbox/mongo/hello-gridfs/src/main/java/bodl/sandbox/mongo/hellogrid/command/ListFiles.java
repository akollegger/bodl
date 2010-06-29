package bodl.sandbox.mongo.hellogrid.command;

import com.mongodb.*;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.gogo.commands.Option;
import org.apache.felix.karaf.shell.console.OsgiCommandSupport;

import java.io.File;
import java.math.BigInteger;
import java.util.List;

@Command(scope = "grid", name = "list", description = "list files in default GridFS")
public class ListFiles extends OsgiCommandSupport {

  @Override
  protected Object doExecute() throws Exception {
    try {
      Mongo m = new Mongo();
      DB sandboxDB = m.getDB("sandbox");

      GridFS sandboxFS = new GridFS(sandboxDB);

      DBCursor fileCursor = sandboxFS.getFileList();

      for (DBObject obj : fileCursor) {
        System.out.println("obj: " + obj);
      }

    } catch (Exception e) {
      System.err.println("Save failed, because: " + e);
      e.printStackTrace();
    }


    return null;
  }
}