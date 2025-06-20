package alg.chap6.base;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class Picture implements ActionListener {
	private BufferedImage image;               // the rasterized image
    private JFrame frame;                      // on-screen view
    private String filename;                   // name of file
    private boolean isOriginUpperLeft = true;  // location of origin
    private int width, height;           		// width and height
    
    public Picture(int w, int h) {
    	if (w < 0)	throw new IllegalArgumentException("width must be nonnegative");
    	if (h < 0)	throw new IllegalArgumentException("height must be nonnegative");
    	this.width = w;
    	this.height = h;
    	image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    	filename = w + "-by-" + h;
    }
    
    public Picture(String fname) {
    	this.filename = fname;
    	try {
    		File file = new File(fname);
    		if (file.isFile())
    			image = ImageIO.read(file);
    		else {
    			URL url = getClass().getResource(fname);
    			if (url == null)
    				url = new URL(fname);
    			image = ImageIO.read(url);
    		}
    		width = image.getWidth(null);
    		height = image.getHeight(null);
    	} catch (IOException e) {
    		e.printStackTrace();
            throw new RuntimeException("Could not open file: " + fname);
    	}
    }
    
    public Picture(File file) {
    	try {
    		image = ImageIO.read(file);
    	} catch (IOException e) {
    		e.printStackTrace();
            throw new RuntimeException("Could not open file: " + file);
    	}
    	if (image == null)
    		throw new RuntimeException("Invalid image file: " + file);
    	
    	width = image.getWidth(null);
		height = image.getHeight(null);
		filename = file.getName();
    }
    
    public JLabel getJLabel() {
    	if (image == null)	return null;
    	ImageIcon icon = new ImageIcon(image);
    	return new JLabel(icon);
    }
    
    public void setOriginUpperLeft() {
    	this.isOriginUpperLeft = true;
    }
    
    public void setOriginLowerLeft() {
    	this.isOriginUpperLeft = false;
    }
    
    // Displays the picture in a window on the screen.
    @SuppressWarnings("deprecation")
	public void show() {
    	if (frame == null) {
    		frame = new JFrame();
    		
    		JMenuBar menuBar = new JMenuBar();
    		JMenu menu = new JMenu("File");
    		menuBar.add(menu);
    		JMenuItem menuItem1 = new JMenuItem(" Save...   ");
    		menuItem1.addActionListener(this);
    		menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                                     Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    		menu.add(menuItem1);
    		frame.setJMenuBar(menuBar);
    		
    		frame.setContentPane(getJLabel());
    		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    		frame.setTitle(filename);
    		frame.setResizable(false);
    		frame.pack();
    		frame.setVisible(true);
    	}
    	
    	frame.repaint();
    }
    
    public int height() {
    	return this.height;
    }
    
    public int width() {
    	return this.width;
    }
    
    public Color get(int col, int row) {
    	if (col < 0 || col >= width())  throw new IndexOutOfBoundsException("col must be between 0 and " + (width()-1));
        if (row < 0 || row >= height()) throw new IndexOutOfBoundsException("row must be between 0 and " + (height()-1));
        if (isOriginUpperLeft) return new Color(image.getRGB(col, row));
        else                   return new Color(image.getRGB(col, height - row - 1));
    }
    
    public void set(int col, int row, Color color) {
        if (col < 0 || col >= width())  throw new IndexOutOfBoundsException("col must be between 0 and " + (width()-1));
        if (row < 0 || row >= height()) throw new IndexOutOfBoundsException("row must be between 0 and " + (height()-1));
        if (color == null) throw new NullPointerException("can't set Color to null");
        if (isOriginUpperLeft) image.setRGB(col, row, color.getRGB());
        else                   image.setRGB(col, height - row - 1, color.getRGB());
    }
    
    // Returns true if this picture is equal to the argument picture.
    public boolean equals(Object other) {
    	if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Picture that = (Picture) other;
        if (this.width()  != that.width())  return false;
        if (this.height() != that.height()) return false;
        for (int col = 0; col < width(); col++)
            for (int row = 0; row < height(); row++)
                if (!this.get(col, row).equals(that.get(col, row))) return false;
        return true;
    }
    
    public int hashCode() {
        throw new UnsupportedOperationException("hashCode() is not supported because pictures are mutable");
    }
    
    public void save(String name) {
        save(new File(name));
    }
    
    public void save(File file) {
        filename = file.getName();
        if (frame != null) frame.setTitle(filename);
        String suffix = filename.substring(filename.lastIndexOf('.') + 1);
        suffix = suffix.toLowerCase();
        if (suffix.equals("jpg") || suffix.equals("png")) {
            try {
                ImageIO.write(image, suffix, file);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Error: filename must end in .jpg or .png");
        }
    }

 // Opens a save dialog box when the user selects "Save As" from the menu.
	@Override
	public void actionPerformed(ActionEvent e) {
		FileDialog chooser = new FileDialog(frame, 
							"Use a .png or .jpg extension", FileDialog.SAVE);
		chooser.setVisible(true);
		if (chooser.getFile() != null) {
			save(chooser.getDirectory() + File.separator + chooser.getFile());
		}
	}
}
