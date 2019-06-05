import java.io.IOException;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        System.out.println("Started");

        //String[] paths = new String[] {"150x100-Humming-bird-top-blur.png", "150x100-Humming-bird-left-blur.png", "150x100-Humming-bird-right-blur.png"};
        String[] paths = new String[] {"90x60-Humming-bird-top-blur.png", "90x60-Humming-bird-left-blur.png", "90x60-Humming-bird-right-blur.png"};
        //String[] paths = new String[] {"150x100-double-mirrored-humming-bird-top-blur.png", "150x100-double-mirrored-humming-bird-left-blur.png", "150x100-double-mirrored-humming-bird-right-blur.png"};
        int windowSize = 16;

        FocusStacking fstack = new FocusStacking(windowSize);
        try {
            fstack.Stack(paths, "output_"+windowSize+"_"+LocalDateTime.now().toString()+".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished");
    }
}
