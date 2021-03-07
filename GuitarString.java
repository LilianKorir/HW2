
import java.util.*;

/**
 * An abstract class that simulates the behavior of a guitar string.
 *
 * @author Raghuram Ramanujan
 * @author Tabitha Peck
 */
public abstract class GuitarString {

  protected int myBufferSize; // size of the buffer
  protected int myNumTics; // number of steps that have elapsed in the simulation

  public static final double ENERGY_DECAY = 0.996;

  /**
   * Advances the state of this string one step in the simulation.
   *
   * The string's energy state is updated using the Karplus-Stong formula.
   */
  public abstract void tic();

  /**
   * Simulates the act of plucking this string.
   *
   * "Plucking" the string corresponds to filling up the ring buffer with random
   * values in the range [-0.5, 0.5].
   */
  public abstract void pluck();

  /**
   * Returns the current state of this string.
   *
   * @return the energy value at the front of the ring buffer.
   */
  public abstract double sample();

  /**
   * Returns the number of steps that have elapsed in this string's history.
   *
   * @return the number of Karplus-Strong updates that have been made to this
   *         string.
   */
  public int time() {
    return myNumTics;
  }
}
