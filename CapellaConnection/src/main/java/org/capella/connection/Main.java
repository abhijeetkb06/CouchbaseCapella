package org.capella.connection;

import java.time.Duration;

import com.couchbase.client.core.env.SecurityConfig;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.ClusterOptions;
import com.couchbase.client.java.env.ClusterEnvironment;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.query.QueryResult;

public class Main {
    // Update these variables to point to your Couchbase Capella instance and
    // credentials.
    static String connectionString = "couchbases://cb.xvpbqpsukslebjn3.cloud.couchbase.com";
    static String username = "abhijeet";
    static String password = "Password@P1";
    static String bucketName = "beer-sample";

    public static void main(String... args) {

        ClusterEnvironment clusterEnvironment = ClusterEnvironment.builder()
                .securityConfig(SecurityConfig.enableTls(true)).build();
//			ClusterEnvironment.builder().securityConfig(SecurityConfig.trustCertificate(Path.of("/Users/abhijeetbehera/Downloads/vertical-moose-root-certificate.pem")).enableTls(true)).build();

        ClusterOptions options = ClusterOptions.clusterOptions(username, password).environment(clusterEnvironment);

        Cluster cluster = Cluster.connect(connectionString, options);

        // get a bucket reference
        Bucket bucket = cluster.bucket(bucketName);
        bucket.waitUntilReady(Duration.parse("PT10S"));

        // get a user defined collection reference
        //    Scope scope = bucket.scope("_default");
        //    Collection collection = scope.collection("_default");

        // Upsert Document

        // Get Document
        GetResult getResult = bucket.defaultCollection().get("21st_amendment_brewery_cafe");
        String name = getResult.contentAsObject().getString("name");
        System.out.println(name); // name == "mike"

        // Call the query() method on the cluster object and store the result.
        QueryResult result = cluster.query("select \"Hello World\" as greeting");

        // Return the result rows with the rowsAsObject() method and print to the
        // terminal.
        System.out.println(result.rowsAsObject());
    }
}
