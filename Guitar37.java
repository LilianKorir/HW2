import java.util.ArrayList;

import javax.print.DocFlavor.STRING;

/** A skeleton for the Guitar37 class. Replace this with a better Javadoc! */

public class Guitar37 implements Guitar {
    // keyboard layout
    public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    ArrayList<String> strings = new ArrayList<>();
    ArrayList<GuitarString> guitarStrings = new ArrayList<>();

    Guitar37() {

        for (int i = 0; i < KEYBOARD.length(); i++) {
            double frequency = 440 * Math.pow(2, (i - 24) / 12.0);
            guitarStrings.add(new GuitarStringArrayList(frequency));
            String str = Character.toString(KEYBOARD.charAt(i));
            strings.add(str);
        }
    }

    public boolean hasString(char string) {
        boolean x = false;
        for (int i = 0; i < KEYBOARD.length(); i++) {
            if (string == KEYBOARD.charAt(i)) {
                x = true;
            }
        }
        return x;
    }

    public void pluck(char string) {
        if (strings.contains(Character.toString(string))) {
            for (int i = 0; i < KEYBOARD.length(); i++) {
                if (string == KEYBOARD.charAt(i)) {
                    GuitarString guitarString = guitarStrings.get(i);
                    guitarString.pluck();
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void play() {
        // compute the superposition of samples
        double sample = 0;
        for (int i = 0; i < KEYBOARD.length(); i++) {
            GuitarString guitarString = guitarStrings.get(i);
            sample = sample + guitarString.sample();
        }
        // send the result to the sound card
        StdAudio.play(sample);
    }

    public void tic() {
        for (int i = 0; i < KEYBOARD.length(); i++) {
            GuitarString guitarString = guitarStrings.get(i);
            guitarString.tic();
        }
    }

}
