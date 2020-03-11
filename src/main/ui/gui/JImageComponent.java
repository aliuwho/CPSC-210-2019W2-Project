package ui.gui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * code from:
 * https://www.codeproject.com/Articles/795464/Creating-a-Swing-JImageComponent
 */
public class JImageComponent extends javax.swing.JComponent {

    /**
     * Constructs a new JImageComponent object.
     */
    public JImageComponent() {
    }

    /**
     * Holds the BufferedImage for the image.
     */
    private BufferedImage bufferedImage = null;

    /**
     * Holds the Graphics for the image.
     */
    private Graphics imageGraphics = null;

    /**
     * Constructs a new JImageComponent object.
     *
     * @param bufferedImage Image to load as default image.
     */
    public JImageComponent(BufferedImage bufferedImage) {
        this.setBufferedImage(bufferedImage);
    }

    /**
     * Sets the buffered image, and updates the components bounds.
     *
     * @param bufferedImage The buffered image to set to.
     */
    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;

        // Clear the graphics object if null image specified.
        // Clear the component bounds if null image specified.
        if (this.bufferedImage == null) {
            this.imageGraphics = null;
            this.setBounds(0, 0, 0, 0);
        } else {

            // Set the graphics object.
            // Set the component's bounds.
            this.imageGraphics = this.bufferedImage.createGraphics();
            this.setBounds(0, 0, this.bufferedImage.getWidth(), this.bufferedImage.getHeight());
        }
    }

    /**
     * Loads image from an URL.
     *
     * @param imageLocation URL to image.
     * @throws IOException Throws an IOException if file cannot be loaded.
     */
    public void loadImage(URL imageLocation) throws IOException {
        this.bufferedImage = ImageIO.read(imageLocation);
        this.setBufferedImage(this.bufferedImage);
    }

    /**
     * Loads image from a file.
     *
     * @param imageLocation File to image.
     * @throws IOException Throws an IOException if file cannot be loaded.
     */
    public void loadImage(File imageLocation) throws IOException {
        this.bufferedImage = ImageIO.read(imageLocation);
        this.setBufferedImage(this.bufferedImage);
    }

    /*
     * @see javax.swing.JComponent#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics g) {

        // Exit if no image is loaded.
        if (this.bufferedImage == null) {
            return;
        }

        // Paint the visible region.
        Rectangle rectangle = this.getVisibleRect();
        paintImmediately(g, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    /*
     * @see javax.swing.JComponent#paintImmediately(int, int, int, int)
     */
    @Override
    public void paintImmediately(int x, int y, int width, int height) {

        // Exit if no image is loaded.
        if (this.bufferedImage == null) {
            return;
        }

        // Paint the region specified.
        this.paintImmediately(super.getGraphics(), x, y, width, height);
    }

    /*
     * @see javax.swing.JComponent#paintImmediately(java.awt.Rectangle)
     */
    @Override
    public void paintImmediately(Rectangle rectangle) {

        // Exit if no image is loaded.
        if (this.bufferedImage == null) {
            return;
        }

        // Paint the region specified.
        this.paintImmediately(super.getGraphics(), rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    /**
     * Paints the image onto the component.
     *
     * @param g      The Graphics object of the component onto which the
     *               image region will be painted.
     * @param x      The x value of the region to be painted.
     * @param y      The y value of the region to be painted.
     * @param width  The width of the region to be painted.
     * @param height The height of the region to be painted.
     */
    private void paintImmediately(Graphics g, int x, int y, int width, int height) {

        // Exit if no image is loaded.
        if (this.bufferedImage == null) {
            return;
        }

        int imageWidth = this.bufferedImage.getWidth();
        int imageHeight = this.bufferedImage.getHeight();

        // Exit if the dimension is beyond that of the image.
        if (x >= imageWidth || y >= imageHeight) {
            return;
        }

        // Calculate the rectangle of the image that should be rendered.
        int x1 = x < 0 ? 0 : x;
        int y1 = y < 0 ? 0 : y;
        int x2 = x + width - 1;
        int y2 = y + height - 1;

        if (x2 >= imageWidth) {
            x2 = imageWidth - 1;
        }

        if (y2 >= imageHeight) {
            y2 = imageHeight - 1;
        }

        // Draw the image.
        g.drawImage(this.bufferedImage, x1, y1, x2, y2, x1, y1, x2, y2, null);
    }

    /**
     * Returns the height of the image.
     */

    @Override
    public int getHeight() {
        if (this.bufferedImage == null) {
            return 0;
        }

        return this.bufferedImage.getHeight();
    }

    /**
     * Returns the size of the image.
     */
    @Override
    public Dimension getPreferredSize() {
        if (this.bufferedImage == null) {
            return new Dimension(0, 0);
        }

        return new Dimension(this.bufferedImage.getWidth(), this.bufferedImage.getHeight());
    }

    /**
     * Returns the size of the image.
     */
    @Override
    public Dimension getSize() {
        if (this.bufferedImage == null) {
            return new Dimension(0, 0);
        }

        return new Dimension(this.bufferedImage.getWidth(), this.bufferedImage.getHeight());
    }

    /**
     * Returns the width of the image.
     */
    @Override
    public int getWidth() {
        if (this.bufferedImage == null) {
            return 0;
        }

        return this.bufferedImage.getWidth();
    }

}
