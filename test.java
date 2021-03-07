public class test {

import java.util.*;
import java.util.Queue;
import java.util.ArrayList;

public class GuitarStringQueue extends GuitarString {
  Queue<Double> ring_buffer;
  int count = 0;

  GuitarStringQueue(double frequency) {
    int n;
    if (frequency <= 0) {
      throw new IllegalArgumentException();
    }

    double capacity = (StdAudio.SAMPLE_RATE / frequency);
    if (capacity < 2) {
      throw new IllegalArgumentException();
    }
    n = (int) capacity;
    Queue<Double> ring_buffer = new PriorityQueue<Double>(n);
    for (int i = 1; i <= n; i++) {
      ring_buffer.add(0.0);
    }
  }

  GuitarStringQueue(double[] init) {
    Queue<Double> ring_buffer = new PriorityQueue<Double>();
    for (int i = 0; i < init.length; i++) {
      ring_buffer.add(init[i]);
    }
    if (init.length < 2) {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Simulates the act of plucking this string.
   *
   * "Plucking" the string corresponds to filling up the ring buffer with random
   * values in the range [-0.5, 0.5].
   */
  public void pluck() {
    int size = ring_buffer.size();
    ring_buffer.clear();
    for (int i = 0; i <= size; i++) {
      ring_buffer.add(Math.random() - .5);
    }
  }

  /**
   * Advances the state of this string one step in the simulation.
   *
   * The string's energy state is updated using the Karplus-Stong formula.
   */
  public void tic() {
    double positionOne = ring_buffer.poll(); // placeholder for first number inqueue
    double positionTwo = ring_buffer.peek(); // placeholder for second number inqueue
    double KarplusStringFormula = ENERGY_DECAY * ((positionOne + positionTwo) / 2);
    ring_buffer.add(KarplusStringFormula);
    count++;
  }

  /**
   * Returns the current state of this string.
   *
   * @return the energy value at the front of the ring buffer.
   */
  public double sample() {
    return ring_buffer.peek();
  }

  /**
   * Returns the number of steps that have elapsed in this string's history.
   *
   * @return the number of Karplus-Strong updates that have been made to this
   *         string.
   */
  public int time() {
    return count;
  }
}

}
