import javax.swing.*;
import java.awt.image.*;
import java.awt.geom.*;
import javax.imageio.*;
import java.io.*;

/**
 * This class represents a graphical picture. Once an instance of this is created, it can
 * be used to create Swing components showing that picture, including JButton and JLabel.
 */
public class Picture extends ImageIcon
{
    private String filename;

    /**
     * Constructor. Creates a new instance of the Picture class based on an image in the given filename.
     *
     * @param filename The filename of an image to load.
     */
    public Picture(String filename)
    {
        this.filename = filename;

        BufferedImage image;
        try {
            image = ImageIO.read(new File(filename));
        } catch (Exception e)
        {
            System.out.println("Could not find image file: " + filename);
            return;
        }
        this.setImage(image);
    }

    /**
     * Determines the filename associated with this Picture.
     */
    public String getFilename()
    {
        return filename;
    }

}
