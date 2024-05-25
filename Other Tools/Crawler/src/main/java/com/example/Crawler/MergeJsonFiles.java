package com.example.Crawler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MergeJsonFiles {

    public static void main(String[] args) {
        String directoryPath = "output/info/music"; // Directory containing the JSON files
        String outputFilePath = "output/merged-music-info.json"; // Output file

        try {
            // Get list of all JSON files in the directory
            List<File> jsonFiles = listJsonFiles(directoryPath);

            // Read and merge JSON files
            ArrayNode mergedData = mergeJsonFiles(jsonFiles);

            // Save merged data to a new JSON file
            saveMergedJson(mergedData, outputFilePath);

            System.out.println("Merged JSON data saved to " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<File> listJsonFiles(String directoryPath) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".json"));
        return files != null ? List.of(files) : new ArrayList<>();
    }

    private static ArrayNode mergeJsonFiles(List<File> jsonFiles) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode mergedData = mapper.createArrayNode();

        for (File file : jsonFiles) {
            JsonNode jsonData = mapper.readTree(file);
            mergedData.add(jsonData);
        }

        return mergedData;
    }

    private static void saveMergedJson(ArrayNode mergedData, String outputFilePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFilePath), mergedData);
    }
}
