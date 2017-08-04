package instructionsActivity;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import GridGUI.GameView;

import java.awt.event.*;
import java.io.IOException;
import java.net.URL;

/**
 * The main menu view. Extends JLayeredPane. Containing container should implement
 * componentResized() from interface ComponentListener. componentResized() in
 * containing container should call handleResize() from this class.
 * 
 * @author alanyork
 *
 */
public class InstructionsView extends JLayeredPane implements GameView {
	// CONSTANTS
	public static final String BACKGROUND_IMAGE_PATH = "/FFIV_PSP_Final_Dungeon_Background_2.png";
	public static final String MENU_MUSIC = "/DJtized_SelfvsSelf.wav";
	public static final String RETURN_BUTTON_TEXT = "/logo.png";
	public static final String INSTRUCTIONS_LABEL_TEXT = "Play";

	// FIELDS
	//ImagePreparation seems to be broken. Own helper methods used instead.
	//private ImagePreparation imageLoader = ImagePreparation.getInstance();
	private BackgroundPane backgroundPane;
	private ForegroundPane foregroundPane;
	private HallOfBonesMain.HallOfBonesMain mainFrame;
	
	// CONSTRUCTORS
	private InstructionsView() {
		//Play music
		soundEffectControl.SoundClipPlayer.playSound(MENU_MUSIC);
		// Add Background Layer
		backgroundPane = new BackgroundPane(new ImageIcon(this.getClass().getResource(BACKGROUND_IMAGE_PATH)).getImage());
		this.setSize(new Dimension(BackgroundPane.DEFAULT_WIDTH, BackgroundPane.DEFAULT_HEIGHT));
		this.setPreferredSize(new Dimension(BackgroundPane.DEFAULT_WIDTH, BackgroundPane.DEFAULT_HEIGHT));
		this.add(backgroundPane, Integer.valueOf(0));

		// Add Foreground Layer
		foregroundPane = new ForegroundPane();
		foregroundPane.setOpaque(false);
		this.add(foregroundPane, Integer.valueOf(1));

		// "Pack" the Layers
		handleResize();
	}
	
	/**
	 * Creates an instance of this class with the parameter as the ActionListener
	 * for all user-interactive components within.
	 * @param actionListener
	 * A class implementing ActionListener
	 */
	public InstructionsView(ActionListener actionListener) {
		this();
		this.setActionListener(actionListener);
	}
	
	/**
	 * Creates an instance of this class, storing the controller and the 
	 * @param controller
	 * @param mainFrame
	 */
	public InstructionsView(InstructionsController controller, HallOfBonesMain.HallOfBonesMain mainFrame) {
		this((ActionListener) controller);
		controller.setView(this);
		this.mainFrame = mainFrame;
	} 
	
	/**
	 * Creates an instance of this class with the specified ActionListener
	 * and frame.
	 * @param mainFrame
	 * @param actionListener
	 */
	public InstructionsView(HallOfBonesMain.HallOfBonesMain mainFrame, ActionListener actionListener){
		this(actionListener);
		this.mainFrame = mainFrame;
	}

	// HELPER METHODS
	// Image Loading
	private URL parsePath(String path) {
		return this.getClass().getResource(path);
	}

	private ImageIcon createImageIcon(String path, String description) {
		URL imgURL = parsePath(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	// VIEW EVENT HANDLERS AND CALLBACKS
	public void handleResize() {
		// Resize the layers
		backgroundPane.setSize(this.getWidth(), this.getHeight());
		backgroundPane.setBounds(0, 0, this.getWidth(), this.getHeight());
		foregroundPane.setSize(this.getWidth(), this.getHeight());
		foregroundPane.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.validate();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		handleResize();
	}
	
	// PUBLIC METHODS
	/**
	 * Sets the parameter as the parameter as the ActionListener
	 * for all user-interactive components within.
	 */
	public void setActionListener(ActionListener actionListener) {
		foregroundPane.setActionListener(actionListener);
	}
	
	/**
	 * Returns the frame (probably the window of the game) set by the constructor.
	 * For use with kevinj22's main frame
	 * @return
	 * The main window (frame) of the game
	 */
	public HallOfBonesMain.HallOfBonesMain getMainFrame() {
		return mainFrame;
	}
	
	@Override
	public void startView() {
		handleResize();
		soundEffectControl.SoundClipPlayer.shutUp();
		soundEffectControl.SoundClipPlayer.playSound("/DJtized_SelfvsSelf.wav");
	}
	
	// INNER CLASSES
	/**
	 * The top-most layer of the JLayeredPane, containing the UI components
	 * @author alanyork
	 *
	 */
	private class ForegroundPane extends JPanel {

		// FIELDS
		private JLabel instructionsLabel = new JLabel(INSTRUCTIONS_LABEL_TEXT);
		private JButton returnButton = new JButton(RETURN_BUTTON_TEXT);
		
		// CONSTRUCTORS
		public ForegroundPane() {
			// Initialise Components
			//  Set the ActionCommand string
			returnButton.setActionCommand(RETURN_BUTTON_TEXT);
			
			
			// Set LayoutManager
			this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

			// Add Components and Glue Spacers
			this.add(Box.createVerticalGlue());
			this.add(Box.createVerticalGlue());
			this.add(instructionsLabel);
			this.add(returnButton);
			this.add(Box.createVerticalGlue());
			this.add(Box.createVerticalGlue());
			this.add(Box.createVerticalGlue());

			// Align Components
			for (Component x : this.getComponents()) {
				((JComponent) x).setAlignmentX(CENTER_ALIGNMENT);
			}
		}
		
		// PUBLIC METHODS
		/**
		 * Sets the parameter as the ActionListener
		 * for all user-interactive components within.
		 */
		public void setActionListener(ActionListener actionListener) {
			returnButton.addActionListener(actionListener);
		}
	}
	
	/**
	 * A panel that contains nothing except an image.
	 * Adapted from BackGroundPane by kevinj22.
	 * @author alanyork
	 */
	public static class BackgroundPane extends JPanel {
		public static final int DEFAULT_WIDTH = 1400;
	    public static final int DEFAULT_HEIGHT = 920;
		private Image background;
		
		public BackgroundPane(Image image) {
			this.background = image;
			this.setSize(new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT));
	    }
		
		/*
		@Override
		public Dimension getPreferredSize() {
			//return background == null ? new Dimension(0, 0) : new Dimension(background.getWidth(this), background.getHeight(this));
			return background == null ? new Dimension(0, 0) : new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);   
	    }
	    */
	    
		
		@Override
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        if (background != null) {                
	        	g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
	        }
	   }
		
		
	}
	
}