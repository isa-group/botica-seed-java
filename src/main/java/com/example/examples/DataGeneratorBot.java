package com.example.examples;

import es.us.isa.botica.bot.BaseBot;
import es.us.isa.botica.bot.ProactiveTask;
import java.time.Instant;
import java.util.UUID;

/**
 * A proactive bot that periodically generates random data and publishes it using the "process_data"
 * order.
 *
 * <p>This bot simulates a data producer, sending structured messages to be processed
 * by other bots in the Botica environment.
 */
public class DataGeneratorBot extends BaseBot {

  /**
   * Periodically generates and publishes data for processing.
   *
   * <p>Runs at regular intervals, creating a new dataset and publishing it
   * under the "process_data" order.
   */
  @ProactiveTask
  public void generateAndPublishData() {
    // Generate random data
    String generatedData = generateRandomData();

    // Publish the generated data with the "process_data" order
    publishOrder("raw_data", "process_data", generatedData);

    System.out.println("Published new generated data: " + generatedData);
  }

  /**
   * Simulates data generation.
   *
   * <p>This method generates a structured data object containing a UUID and a timestamp.
   *
   * @return a JSON-like string representing the generated data
   */
  private String generateRandomData() {
    return "{ \"data_id\": \"" + UUID.randomUUID() + "\", \"timestamp\": \"" + Instant.now()
        + "\" }";
  }
}
