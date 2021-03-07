import java.util.*;
import java.util.ArrayList;

public class GuitarStringArrayList extends GuitarString {
  /**
   * Advances the state of this string one step in the simulation.
   *
   * The string's energy state is updated using the Karplus-Stong formula.
   */
  int numTimes = 0;
  double positionOne = 0; // placeholder for first number in queue
  double positionTwo = 0; // placeholder for second number in queue
  ArrayList<Double> ringBuffer = new ArrayList<>();

  public GuitarStringArrayList(double frequency) {
    double capacity = (StdAudio.SAMPLE_RATE / frequency);
    myBufferSize = (int) capacity;
    for (int i = 0; i < myBufferSize; i++) {
      ringBuffer.add(0.0);
    }
    if (frequency <= 0 || capacity < 2) {
      throw new IllegalArgumentException();
    }

  }

  GuitarStringArrayList(double[] init) {
    for (int i = 0; i < init.length; i++) {
      ringBuffer.add(init[i]);
    }
    if (init.length < 2) {
      throw new IllegalArgumentException();
    }
  }

  public void tic() {
    positionOne = ringBuffer.get(0);
    positionTwo = ringBuffer.get(1);
    ringBuffer.remove(0);
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
    for (int i = 0; i <= myBufferSize; i++) {
      double rand = Math.random() * range + min;
      ringBuffer.add(rand);
    }
  }

  /**
   * Returns the current state of this string.
   *
   * @return the energy value at the front of the ring buffer.
   */
  public double sample() {
    return ringBuffer.get(0);
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
