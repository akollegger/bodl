package bodl.sandbox.mongo.hellogrid.command;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.gogo.commands.Option;
import org.apache.felix.karaf.shell.console.OsgiCommandSupport;
import org.bson.types.ObjectId;

import java.io.File;
import java.math.BigInteger;
import java.util.List;

@Command(scope = "grid", name = "save", description = "saves file to default GridFS")
public class SaveFileToGrid extends OsgiCommandSupport {

  @Argument(name = "filename", description = "filename to save", required = true, multiValued = false)
  String filename;

  @Override
  protected Object doExecute() throws Exception {
    try {
      Mongo m = new Mongo();
      DB sandboxDB = m.getDB("sandbox");

      GridFS sandboxFS = new GridFS(sandboxDB);
      GridFSInputFile gridFile = sandboxFS.createFile(new File(filename));
      gridFile.save();
      System.out.println("Created file: " + gridFile);

      ObjectId fileId = (ObjectId) gridFile.getId();
      GridFSDBFile foundFile = sandboxFS.findOne(fileId);
      if (foundFile == null) {
        System.err.println("Expected file not found!");
      } else {
        System.out.println("Found as:" + foundFile);
        BigInteger md5 = new BigInteger(foundFile.getMD5(), 16);
        BigInteger sha1 = new BigInteger(DigestUtils.sha(foundFile.getInputStream()));
        BigInteger sha256 = new BigInteger(DigestUtils.sha256(foundFile.getInputStream()));

        System.out.println("hex identifiers:");
        printConversion("md5", md5, 16);
        printConversion("sha1", sha1, 16);
        printConversion("sha256", sha256, 16);

        System.out.println("base36 identifiers:");
        printConversion("md5", md5, 36);
        printConversion("sha1", sha1, 36);
        printConversion("sha256", sha256, 36);

        System.out.println("base62 identifiers:");
        printConversion("md5", md5, 62);
        printConversion("sha1", sha1, 62);
        printConversion("sha256", sha256, 62);
      }
    } catch (Exception e) {
      System.err.println("Save failed, because: " + e);
      e.printStackTrace();
    }

    return null;
  }

  private void printConversion(String label, BigInteger value, int radix) {
    String valueAsString = (radix <=36) ? value.toString(radix) : (BaseConverter.toBase62(value));
    System.out.println("\t" + label + ": " + valueAsString + " (" + valueAsString.length() + ")");
  }
}