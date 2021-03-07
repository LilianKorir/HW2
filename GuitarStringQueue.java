import java.util.*;
import java.util.Queue;

import javax.swing.plaf.synth.SynthToolTipUI;

public class GuitarStringQueue extends GuitarString {
  /**
   * Advances the state of this string one step in the simulation.
   *
   * The string's energy state is updated using the Karplus-Stong formula.
   */
  int numTimes = 0;
  double positionOne; // placeholder for first number in queue
  double positionTwo; // placeholder for second number in queue
  Queue<Double> ringBuffer = new LinkedList<>();

  public GuitarStringQueue(double frequency) {
    double capacity = (StdAudio.SAMPLE_RATE / frequency);
    myBufferSize = (int) Math.round(capacity);
    for (int i = 0; i < myBufferSize; i++) {
      ringBuffer.add(0.0);
    }
    if (frequency <= 0 || capacity < 2) {
      throw new IllegalArgumentException();
    } // try running it and see what it says

  }

  GuitarStringQueue(double[] init) {
    for (int i = 0; i < init.length; i++) {
      ringBuffer.add(init[i]);
    }
    if (init.length < 2) {
      throw new IllegalArgumentException();
    }
  }

  public void tic() {
    // you remove and peek you have to peek at both then remove
    positionOne = ringBuffer.peek();
    ringBuffer.remove();
    positionTwo = ringBuffer.peek();

    double karplusStringFormula = ENERGY_DECAY * 0.5 * (positionOne + positionTwo);
    ringBuffer.add(karplusStringFormula);
    numTimes++;
  }

  /**
   * Simulates the act of plucking this string.
   *
   * "Plucking" the string corresponds to filling up the ring buffer with random
   * values in the range [-0.5, 0.5].
   */
  public void pluck() {
    ringBuffer.clear();
    double max = 0.5;
    double min = -0.5;
    double range = max - min;
    for (int i = 0; i < myBufferSize; i++) {
      double rand = Math.random() * range + min;
      ringBuffer.add(rand);
    }
    System.out.println("ringBuffer size" + ringBuffer.size());
  }

  /**
   * Returns the current state of this string.
   *
   * @return the energy value at the front of the ring buffer.
   */
  public double sample() {
    return ringBuffer.peek();
  }

  /**
   * Returns the number of steps that have elapsed in this string's history.
   *
   * @return the number of Karplus-Strong updates that have been made to this
   *         string.
   */
  public int time() {
    return numTimes;
  }
}
