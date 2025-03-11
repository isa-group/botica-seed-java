package com.example.examples;

import es.us.isa.botica.bot.BaseBot;
import es.us.isa.botica.bot.OrderHandler;
import java.util.UUID;

/**
 * A reactive bot that processes incoming data and publishes the results.
 *
 * <p>This bot listens for the "process_data" order. When it receives a message,
 * it performs a simulated data transformation and publishes the processed result.
 */
public class DataProcessingBot extends BaseBot {

  /**
   * Handles incoming "process_data" orders.
   *
   * <p>Receives raw data, processes it, and publishes a structured result
   * for other bots to consume.
   *
   * @param rawData the incoming raw data as a string
   */
  @OrderHandler("process_data")
  public void handleProcessData(String rawData) {
    System.out.println("Received data for processing: " + rawData);

    // Process the data (simulate data transformation)
    String processedData = processData(rawData);

    // Publish the result
    publishOrder("processed_data", "store_processed_data", processedData);

    System.out.println("Processed data published: " + processedData);
  }

  /**
   * Simulates a data transformation task.
   *
   * <p>This method takes raw data, appends a UUID (simulating a processing step),
   * and returns a structured output.
   *
   * @param inputData the raw input data
   * @return a processed result with a UUID identifier
   */
  private String processData(String inputData) {
    // Simulating a processing operation (e.g., enrichment, validation)
    return "{ \"processed\": \"" + inputData + "\", \"id\": \"" + UUID.randomUUID() + "\" }";
  }
}
