package com.example.Crawler;


import com.fasterxml.jackson.databind.JsonNode;
import static com.mongodb.client.model.Updates.rename;
import com.mongodb.client.model.Updates;
import java.util.Random;
import com.mongodb.client.result.UpdateResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.FloatNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.json.JsonWriterSettings;
import org.bson.types.ObjectId;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lte;
import static com.mongodb.client.model.Updates.set;

import static com.mongodb.client.model.Filters.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class MongoClientConnectionExample {
    public static void main(String[] args) {
        String connectionString = "mongodb+srv://aims_server:aims_server@cluster0.d5o6bdx.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase("aims_database"); 
                database.runCommand(new Document("ping", 1));
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");

                // Get the collection
                MongoCollection<Document> collection = database.getCollection("product"); 

//                // Insert JSON data into MongoDB
//                String jsonFilePath = "output/merged-product-info.json"; 
//                insertJsonDataIntoMongoDB(jsonFilePath, collection);

//                // Retrieve and print data from MongoDB
//                retrieveAndPrintData(collection);
                
//             // Find and print object by title
//                String title = "Woodstock - 3 Days of Peace & Music (The Director's Cut) [DVD]";
//                Document foundDocument = findObjectByTitle(collection, title);
//                if (foundDocument != null) {
//                    System.out.println("Found document: " + foundDocument.toJson(JsonWriterSettings.builder().indent(true).build()));
//                } else {
//                    System.out.println("Document with title '" + title + "' not found.");
//                }
//                
//             // Find and print object by id
//                String id = "664f54acf06b7a9e184a59b8";
//                Document foundDocumentById = findObjectById(collection, id);
//                if (foundDocumentById != null) {
//                    System.out.println("Found document by id: " + foundDocumentById.toJson(JsonWriterSettings.builder().indent(true).build()));
//                } else {
//                    System.out.println("Document with id '" + id + "' not found.");
//                }
//                
//             // Find and print objects by price range
//                double minPrice = 10.0; 
//                double maxPrice = 12.0; 
//                List<Document> foundDocumentsByPriceRange = findObjectsByPriceRange(collection, minPrice, maxPrice);
//                if (!foundDocumentsByPriceRange.isEmpty()) {
//                    System.out.println("Found documents by price range:");
//                    for (Document doc : foundDocumentsByPriceRange) {
//                        System.out.println(doc.toJson(JsonWriterSettings.builder().indent(true).build()));
//                    }
//                } else {
//                    System.out.println("No documents found within the price range $" + minPrice + " - $" + maxPrice);
//                }
//                
//                String category = "DVD";
//                List<Document> foundDocumentsByCategory = findObjectsByCategory(collection, category);
//                if (!foundDocumentsByCategory.isEmpty()) {
//                    System.out.println("Found documents by category '" + category + "':");
//                    for (Document doc : foundDocumentsByCategory) {
//                        System.out.println(doc.toJson(JsonWriterSettings.builder().indent(true).build()));
//                    }
//                } else {
//                    System.out.println("No documents found for category '" + category + "'");
//                }
//                
//                String field = "price"; // (price, name, or quantity)
//                boolean ascendingOrder = true;
//                List<Document> sortedDocuments = findAllAndSortByField(collection, field, ascendingOrder);
//                if (!sortedDocuments.isEmpty()) {
//                    System.out.println("Sorted documents by " + field + ":");
//                    for (Document doc : sortedDocuments) {
//                        System.out.println(doc.toJson(JsonWriterSettings.builder().indent(true).build()));
//                    }
//                } else {
//                    System.out.println("No documents found.");
//                }
                
             // Remove products without a price
               // removeProductsWithoutPrice(collection);

             // Get the collection
                //MongoCollection<Document> collection = database.getCollection("yourCollectionName"); // Replace with your collection name

//                // Add a random number to the quantity of all products
//                addRandomNumberToAllQuantities(collection);
//             // Rename the field "value" to "price"
//                renameFieldValueToPrice(collection);
             // Add a new field "listPrice" with the value of price + 10
                addListPriceField(collection);
            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static void addListPriceField(MongoCollection<Document> collection) {
        for (Document doc : collection.find()) {
            if (doc.containsKey("importPrice")) {
                double price = doc.getDouble("importPrice");
                double listPrice = price + 10;

                UpdateResult result = collection.updateOne(
                    eq("_id", doc.getObjectId("_id")),
                    set("sellPrice", listPrice)
                );

                if (result.getModifiedCount() > 0) {
                    System.out.println("Updated document ID " + doc.getObjectId("_id") + ": sellPrice set to " + listPrice);
                } else {
                    System.out.println("Failed to update document ID " + doc.getObjectId("_id"));
                }
            }
        }
    }
    
    private static void renameFieldValueToPrice(MongoCollection<Document> collection) {
        UpdateResult result = collection.updateMany(new Document(), rename("price", "importPrice"));
        System.out.println("Number of documents modified: " + result.getModifiedCount());
    }

    
    private static void addRandomNumberToAllQuantities(MongoCollection<Document> collection) {
        Random random = new Random();

        for (Document doc : collection.find()) {
            int randomNumber = random.nextInt(100); // Generates a random number between 0 and 99

            if (doc.containsKey("quantity")) {
                int currentQuantity = doc.getInteger("quantity", 0);
                int newQuantity = currentQuantity + randomNumber;
                
                if (randomNumber > 70 && randomNumber <= 100) newQuantity = 0;

                UpdateResult result = collection.updateOne(
                    eq("_id", doc.getObjectId("_id")),
                    set("quantity", newQuantity)
                );

                if (result.getModifiedCount() > 0) {
                    System.out.println("Updated document ID " + doc.getObjectId("_id") + ": quantity set to " + newQuantity);
                } else {
                    System.out.println("Failed to update document ID " + doc.getObjectId("_id"));
                }
            } else {
                // Quantity doesn't exist, set it to a random number
                UpdateResult result = collection.updateOne(
                    eq("_id", doc.getObjectId("_id")),
                    set("quantity", randomNumber)
                );

                if (result.getModifiedCount() > 0) {
                    System.out.println("Updated document ID " + doc.getObjectId("_id") + ": quantity initialized to " + randomNumber);
                } else {
                    System.out.println("Failed to update document ID " + doc.getObjectId("_id"));
                }
            }
        }
    }

    
    private static void removeProductsWithoutPrice(MongoCollection<Document> collection) {
        // Remove documents where the price field is missing or null
        collection.deleteMany(or(exists("price", false), eq("price", null)));
    }

    private static void insertJsonDataIntoMongoDB(String jsonFilePath, MongoCollection<Document> collection) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ArrayNode rootNode = (ArrayNode) mapper.readTree(new File(jsonFilePath));

            for (JsonNode node : rootNode) {
                if (node instanceof ObjectNode) {
                    ObjectNode objectNode = (ObjectNode) node;

                    // Convert price to float
                    if (objectNode.has("price")) {
                        String priceStr = objectNode.get("price").asText();
                        try {
                            // Remove any non-numeric characters except for the decimal point
                            float price = Float.parseFloat(priceStr.replaceAll("[^\\d.]", ""));
                            objectNode.set("price", FloatNode.valueOf(price));
                        } catch (NumberFormatException e) {
                            System.err.println("Failed to parse price: " + priceStr);
                        }
                    }

                    // Convert quantity to int32
                    if (objectNode.has("quantity")) {
                        String quantityStr = objectNode.get("quantity").asText();
                        try {
                            int quantity = Integer.parseInt(quantityStr);
                            objectNode.set("quantity", IntNode.valueOf(quantity));
                        } catch (NumberFormatException e) {
                            System.err.println("Failed to parse quantity: " + quantityStr);
                        }
                    }

                    Document doc = Document.parse(objectNode.toString());
                    collection.insertOne(doc);
                    System.out.println("Inserted document: " + doc.toJson());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void retrieveAndPrintData(MongoCollection<Document> collection) {
        for (Document doc : collection.find()) {
            String json = doc.toJson(JsonWriterSettings.builder().indent(true).build());
            System.out.println(json);
        }
    }
    
    private static Document findObjectByTitle(MongoCollection<Document> collection, String title) {
        // Find the document with the given title
        Bson filter = eq("title", title);
        return collection.find(filter).first();
    }
    
    private static Document findObjectById(MongoCollection<Document> collection, String id) {
        // Find the document with the given _id
        ObjectId objectId = new ObjectId(id);
        return collection.find(eq("_id", objectId)).first();
    }
    
    private static List<Document> findObjectsByPriceRange(MongoCollection<Document> collection, double minPrice, double maxPrice) {
        // Find documents within the specified price range
        return collection.find(and(gte("price", minPrice), lte("price", maxPrice))).into(new ArrayList<>());
    }
    
    private static List<Document> findObjectsByCategory(MongoCollection<Document> collection, String category) {
        // Find documents with the given category
        return collection.find(eq("cat", category)).into(new ArrayList<>());
    }

    private static List<Document> findAllAndSortByField(MongoCollection<Document> collection, String field, boolean ascending) {
        // Sort documents by the specified field in ascending or descending order
        return collection.find().sort(ascending ? ascending(field) : descending(field)).into(new ArrayList<>());
    }

}
