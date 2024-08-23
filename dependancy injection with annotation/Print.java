package ir.freeland.spring.initialspringboot;

import org.springframework.stereotype.Service;

/**
 * Simple output handler
 *
 * Used only for demonstration purposes :P
 */
@Service
public class Print {
  public void printing() {
    System.out.println("Printes successfully!");
  }
}
