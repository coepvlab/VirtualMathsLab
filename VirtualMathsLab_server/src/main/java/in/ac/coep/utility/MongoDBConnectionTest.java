//package in.ac.coep.utility;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.MongoException;
//
//public class MongoDBConnectionTest {
//
//    public static void main(String[] args) {
//        String uri = "mongodb://192.168.1.5:27017";  // Replace with your MongoDB URI
//        String databaseName = "maths";  // Replace with your database name
//
//        // Create a new MongoDB client and connect to the server
//        try (MongoClient mongoClient = MongoClients.create(uri)) {
//
//            // Get the database
//            MongoDatabase database = mongoClient.getDatabase(databaseName);
//
//            // Check connection by running a simple command
//            database.runCommand(new org.bson.Document("ping", 1));
//            System.out.println("Successfully connected to the database.");
//
//        } catch (MongoException e) {
//            System.err.println("An error occurred while attempting to connect to the database: " + e.getMessage());
//        }
//    }
//}
