package io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import util.MyArrayList;

/**
 * A class for loading and saving images
 */
public class MyImageIO {

    private MyArrayList<int[][]> pixels;
    private MyArrayList<double[][]> colorChannels;
    private int width;
    private int height;
    private String resourcesRoot;

    /**
     * Pixel width of the image
     */
    public int getWidth() { return width; }
    /**
     * Pixel height of the image
     */
    public int getHeight() { return this.height; }
    /**
     * @return Pixels as 3D array (width x height x image id)
     */
    public MyArrayList<int[][]> getPixels() { return pixels; }
    /**
     * @return Selected color channels as 3D array (width x height x image id)
     */
    public MyArrayList<double[][]> getChannels() { return colorChannels; }
    /**
     * Construct the class with default resources path
     */
    public MyImageIO() {
        this(MyImageIO.getDefaultResourceRoot());
    }
    /**
     * Construct the class
     * @param resourcesRoot Path to resources folder (where the images are)
     */
    public MyImageIO(String resourcesRoot) {
        this.resourcesRoot = resourcesRoot;
        this.pixels = new MyArrayList<>();
        this.colorChannels = new MyArrayList<>();
    }
    /**
     * Load the images and store them internally.
     * The green channels can be accessed through accessor. 
     * @param fileNames Names of the images
     * @param channel Color channel RED = 16, GREEN = 8, BLUE = 0
     * @throws IllegalArgumentException If images are not of same size
     * @throws IOException If image couldn't be loaded
     */
    public void loadImages(String[] fileNames, RGB channel) throws IllegalArgumentException, IOException {

        if (fileNames == null || fileNames.length == 0)
            throw new IllegalArgumentException("No files to process");

        int i = 0;
        MyImage image = new MyImage(resourcesRoot + fileNames[i]);
        width = image.getWidth();
        height = image.getHeight();

        while (image != null) {

            // Loop through the pixels, extract green channel
            int[][] px = new int[width][height];
            double[][] gr = new double[width][height];
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int idx = x + y*width;
                    int rgb = image.getPixels()[idx];
                    px[x][y] = rgb;
                    gr[x][y] = RGB.getChannelValue(rgb, channel);
                }
            }
            pixels.add(px);
            colorChannels.add(gr);

            i++;
            if (i >= fileNames.length) break;

            // Load next image
            image = new MyImage(resourcesRoot + fileNames[i]);
            if (width != image.getWidth() || height != image.getHeight()) {
                throw new IllegalArgumentException("The images need to be of same size");
            }
        }
    }

    /**
     * Create and image object from pixel values and save to disk
     * @param pixels Pixels in integer format
     * @param width Width of the image
     * @param height Height of the image
     * @param path Path to save the image to
     * @param fileName Name of the output image
     */
    public static void saveImage(int[] pixels, int width, int height, String path, String fileName) throws IOException {

        // Create the image object
        BufferedImage image = new BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_ARGB);

        // Copy correct pixels to the image
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, pixels[y*width + x]);
            }
        }

        // Create and write image file
        File outputfile = new File(path + fileName);
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        ImageIO.write(image, extension, outputfile);
        System.out.println("Wrote file to " + path + fileName);
    }

    /**
     * Create and image object from pixel values and save to disk
     * @param pixels Pixels in integer format
     * @param width Width of the image
     * @param height Height of the image
     * @param fileName Name of the output image
     * @throws IOException If saving the image does not succeed
     */
    public static void saveImage(int[] pixels, int width, int height, String fileName) throws IOException {
        String path = MyImageIO.getDefaultResourceRoot();
        saveImage(pixels, width, height, path, fileName);
    }

    /**
     * Get the default resource root
     * @return Path to the root
     */
    private static String getDefaultResourceRoot() {
        return getDefaultResourceRoot(".");
    }

    /**
     * Get the default resource root
     * @param root Starting point
     * @return Path to the root
     */
    private static String getDefaultResourceRoot(String root) {
        try {
            return new File(root).getCanonicalPath() + "/src/main/resources/";
        } catch (IOException e) {}
        return "./src/main/resources/";
    }
}