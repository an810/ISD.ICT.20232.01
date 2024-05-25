package com.example.Crawler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.FloatNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonToMongoScript {

    public static void main(String[] args) {
        String jsonFilePath = "output/merged-cd-info.json"; // Path to the JSON file
        String scriptFilePath = "output/mongo-script-cd-product.js"; // Path to the output MongoDB script file

        try {
        	// Read the JSON file
            ObjectMapper mapper = new ObjectMapper();
            ArrayNode rootNode = (ArrayNode) mapper.readTree(new File(jsonFilePath));
            
            // Convert price and quantity fields
            convertFields(rootNode);

            // Generate MongoDB insert commands
            String mongoScript = generateMongoScript(rootNode, "product");

            // Save the script to a file
            saveScriptToFile(mongoScript, scriptFilePath);

            System.out.println("MongoDB script saved to " + scriptFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void convertFields(ArrayNode rootNode) {
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
            }
        }
    }

    private static String generateMongoScript(JsonNode rootNode, String collectionName) {
        StringBuilder script = new StringBuilder();
        script.append("use yourDatabaseName;\n\n"); // Specify your database name

        for (JsonNode node : rootNode) {
            String json = node.toString();
            script.append("db.").append(collectionName).append(".insert(").append(json).append(");\n");
        }

        return script.toString();
    }

    private static void saveScriptToFile(String script, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(script);
        }
    }
}