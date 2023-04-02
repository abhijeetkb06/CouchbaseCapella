package org.capella.connection;

import com.couchbase.client.core.error.CouchbaseException;
import com.couchbase.client.core.error.DocumentExistsException;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.kv.MutationResult;

import java.time.Duration;

public class Local {
    // Update these variables to point to your Couchbase Capella instance and
    // credentials.
    static String connectionString = "127.0.0.1";
    static String username = "Administrator";
    static String password = "password";
    static String bucketName = "beer-sample";

    public static void main(String... args) {

        Cluster cluster = Cluster.connect("127.0.0.1", "Administrator", "password");


        // get a bucket reference
        Bucket bucket = cluster.bucket(bucketName);
        bucket.waitUntilReady(Duration.parse("PT10S"));

        // get a user defined collection reference
        //    Scope scope = bucket.scope("_default");
        //    Collection collection = scope.collection("_default");

        // Upsert Document

      /*  // Get Document
        GetResult getResult = bucket.defaultCollection().get("21st_amendment_brewery_cafe");

        String name = getResult.contentAsObject().getString("name");
        System.out.println(name); // name == "mike"

        // Call the query() method on the cluster object and store the result.
        QueryResult result = cluster.query("select \"Hello World\" as greeting");

        // Return the result rows with the rowsAsObject() method and print to the
        // terminal.
        System.out.println(result.rowsAsObject());
*/

        Collection beerColl = bucket.defaultCollection();

//        System.out.println(beerColl.get("doc::001").contentAsObject().get("name"));
        try {
            JsonObject beerDocument = JsonObject.create().put("abv", 4.2);
            beerDocument.put("brewery_id", "Abhijeet");
            beerDocument.put("brewery_id", "Abhijeet");
            beerDocument.put("category", "xyz");
            beerDocument.put("description", "Traditional Style Ale");
            beerDocument.put("ibu", 0);
            beerDocument.put("name", "A new Test Beer");
            beerDocument.put("srm", 0);
            beerDocument.put("style", "Special Bitter");
            beerDocument.put("type", "beer");
            beerDocument.put("upc", 0);
            MutationResult insertResult = beerColl.insert("AbhijeetDoc2", beerDocument);
            System.out.println(beerColl.get("AbhijeetDoc2").contentAsObject());
//            System.out.println(insertResult);
        } catch (DocumentExistsException ex) {
            ex.printStackTrace();
        } catch (CouchbaseException ex) {
           ex.printStackTrace();
        }

        int i=0;

       /* while (i < 10) {
            String id= "0";
            CounterResult myID = beerColl.binary().increment(id, IncrementOptions.incrementOptions().initial(1));
            System.out.println("************ myID.content()************** " + myID.content());
//            bucket.defaultCollection().binary().increment(id, IncrementOptions.incrementOptions().delta(1));
            System.out.println("************ myID.content()************** " + myID.content());

            MutationResult upsertResult = beerColl.upsert(
                    id + myID.content(),
                    JsonObject.create().put("id", id+i)
                    .put("name", "Tyler's AirBnB"+i)
                            .put("country", "Canada"+i)
                            .put("type", "hotel"+i)
                            .put("state", "California"+i)
                            .put("phone", "612-280-1303")
            );
            System.out.println("i= "+ i +"************ documents inserted  myID.content()************** " + myID.content());
         i++;
        }*/
    }
}
