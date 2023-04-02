package org.capella.connection;
/*
 * Copyright (c) 2020 Couchbase, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.couchbase.client.core.env.SecurityConfig;
import com.couchbase.client.java.*;
import com.couchbase.client.java.env.ClusterEnvironment;
import com.couchbase.client.java.kv.*;
import com.couchbase.client.java.json.*;
import com.couchbase.client.java.query.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class CloudConnect {
    // Update these variables to point to your Couchbase Capella instance and credentials.
    static String connectionString = "couchbases://cb.j3sjw33zxxigyzf.cloud.couchbase.com";
    static String username = "admin";
    static String password = "Password@P1";
    static String bucketName = "beer-sample";

    public static void main(String... args) {

        Cluster cluster = Cluster.connect(connectionString, username, password);

        // get a bucket reference
        Bucket bucket = cluster.bucket(bucketName);
        bucket.waitUntilReady(Duration.parse("PT10S")) ;

        Scope scope = bucket.scope("_default");
        Collection collection = scope.collection("_default");

        // Create a JSON object representing a beer
//        JsonObject beer = JsonObject.create()
//                .put("name", "IPA")
//                .put("abv", 6.5)
//                .put("brewery_id", "sierra_nevada");
//
//        // Insert the beer document into the collection
//        String id = "ipa";
//        collection.upsert(id, beer);

        // Retrieve the beer document from the collection
        GetResult getResult = collection.get("21st_amendment_brewery_cafe");
        System.out.println("Retrieved: " + getResult.contentAsObject().toString());

        // Update the abv of the beer document
     /*   beer.put("abv", 7.0);
        collection.replace(id, beer);*/

        // Delete the beer document from the collection
//        collection.remove(id);

        // Close the collection, bucket, and disconnect from the cluster
        cluster.disconnect();
    }
}
