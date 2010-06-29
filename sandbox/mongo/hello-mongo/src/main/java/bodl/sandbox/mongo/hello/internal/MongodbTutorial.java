package bodl.sandbox.mongo.hello.internal;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

import java.util.Set;


public class MongodbTutorial {

  public void start() {

    try {
      Mongo m = new Mongo();
      DB db = m.getDB("mydb");

      // get a list of collections
      Set<String> colls = db.getCollectionNames();

      for (String s : colls) {
        System.out.println(s);
      }

      // get a test collection. add an object
      DBCollection coll = db.getCollection("testCollection");
      BasicDBObject doc = new BasicDBObject();

      doc.put("name", "MongoDB");
      doc.put("type", "database");
      doc.put("count", 1);

      BasicDBObject info = new BasicDBObject();

      info.put("x", 203);
      info.put("y", 102);

      doc.put("info", info);

      coll.insert(doc);

      DBObject myDoc = coll.findOne();
      System.out.println(myDoc);

      for (int i = 0; i < 100; i++) {
        coll.insert(new BasicDBObject().append("i", i));
      }

      System.out.println(coll.getCount());

      // use a cursor
      DBCursor cur = coll.find();

      while (cur.hasNext()) {
        System.out.println(cur.next());
      }

      // simple query
      BasicDBObject query = new BasicDBObject();

      query.put("i", 71);

      cur = coll.find(query);

      while (cur.hasNext()) {
        System.out.println(cur.next());
      }

      // query with conditional test
      query = new BasicDBObject();

      query.put("i", new BasicDBObject("$gt", 50));  // e.g. find all where i > 50

      cur = coll.find(query);

      while (cur.hasNext()) {
        System.out.println(cur.next());
      }


      // slightly more complicated query
      query = new BasicDBObject();

      query.put("i", new BasicDBObject("$gt", 20).append("$lte", 30));  // i.e.   20 < i <= 30

      cur = coll.find(query);

      while (cur.hasNext()) {
        System.out.println(cur.next());
      }

      // index the collection (1 for ascending, -1 for descending)
      coll.createIndex(new BasicDBObject("i", 1));  // create index on "i", ascending

      // find a document, then delete it
      query = new BasicDBObject();
      query.put("i", 42);
      DBObject found = coll.findOne(query);
      coll.remove(found);

    } catch (Exception e) {
      System.out.println("mongo fail. awww.");
      System.err.println(e);
    }
  }
}
