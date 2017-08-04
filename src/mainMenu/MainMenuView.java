package mainMenu;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import GridGUI.GameView;

import java.awt.event.*;
import java.io.IOException;
import java.net.URL;

/**
 * The main menu view. Extends JLayeredPane.
 * @author alanyork
 *
 */
public class MainMenuView extends JLayeredPane implements GameView {
	/**
	 * Because Eclipse wants me to give this a UID
	 */
	private static final long serialVersionUID = 69691337L;
	// CONSTANTS
	public static final String BACKGROUND_IMAGE_PATH = "/FFIV_PSP_Final_Dungeon_Background_2.png";
	public static final String LOGO_IMAGE_PATH = "/logo.png";
	public static final String PLAY_BUTTON_TEXT = "Play";
	public static final String INSTRUCTIONS_BUTTON_TEXT = "Instructions";
	public static final String CREDITS_BUTTON_TEXT = "Credits";
	public static final String EXIT_BUTTON_TEXT = "Exit";
	public static final String DEFAULT_MUSIC = "/FF15_Solumnus.wav";
	public static final String VICTORY_MUSIC = "/AliceInChains_ThemBones.wav";
	public static final String DEFEAT_MUSIC = "/Defeat_Deadmau5_Creep.wav";

	// FIELDS
	private static String menuMusic = DEFAULT_MUSIC;
	//ImagePreparation seems to be broken. Own helper methods used instead.
	//private ImagePreparation imageLoader = ImagePreparation.getInstance();
	private BackgroundPane backgroundPane;
	private ForegroundPane foregroundPane;
	private HallOfBonesMain.HallOfBonesMain mainFrame;
	
	// CONSTRUCTORS
	private MainMenuView() {
		//Play music
		soundEffectControl.SoundClipPlayer.playLoopingSound(menuMusic);
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
	public MainMenuView(ActionListener actionListener) {
		this();
		this.setActionListener(actionListener);
	}
	
	/**
	 * Creates an instance of this class with the specified ActionListener,
	 * and frame.
	 * @param mainFrame
	 * @param actionListener
	 */
	public MainMenuView(MainMenuController controller, HallOfBonesMain.HallOfBonesMain mainFrame) {
		this((ActionListener) controller);
		controller.setView(this);
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
		soundEffectControl.SoundClipPlayer.playLoopingSound(menuMusic);
	}
	
	/**
	 * Set the path to the main menu music
	 * @param musicPath
	 * The path to the main menu music
	 */
	public static void setMenuMusic(String musicPath){
		menuMusic = musicPath;
	}
	
	// INNER CLASSES
	/**
	 * The top-most layer of the JLayeredPane, containing the UI components
	 * @author alanyork
	 *
	 */
	private class ForegroundPane extends JPanel {

		// FIELDS
		private JLabel logoLabel = new JLabel(createImageIcon(MainMenuView.LOGO_IMAGE_PATH, "Kevin's RPG"));
		private JButton playButton = new JButton(PLAY_BUTTON_TEXT);
		private JButton instructionsButton = new JButton(INSTRUCTIONS_BUTTON_TEXT);
		private JButton creditsButton = new JButton(CREDITS_BUTTON_TEXT);
		private JButton exitButton = new JButton(EXIT_BUTTON_TEXT);

		// CONSTRUCTORS
		public ForegroundPane() {
			// Initialise Components
			//  Set the ActionCommand string
			playButton.setActionCommand(PLAY_BUTTON_TEXT);
			instructionsButton.setActionCommand(INSTRUCTIONS_BUTTON_TEXT);
			creditsButton.setActionCommand(CREDITS_BUTTON_TEXT);
			exitButton.setActionCommand(EXIT_BUTTON_TEXT);
			
			// Set LayoutManager
			this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

			// Add Components and Glue Spacers
			this.add(Box.createVerticalGlue());
			this.add(Box.createVerticalGlue());
			this.add(logoLabel);
			this.add(Box.createVerticalGlue());
			this.add(playButton);
			this.add(instructionsButton);
			this.add(creditsButton);
			this.add(exitButton);
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
			playButton.addActionListener(actionListener);
			instructionsButton.addActionListener(actionListener);
			creditsButton.addActionListener(actionListener);
			exitButton.addActionListener(actionListener);
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