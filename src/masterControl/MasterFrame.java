package masterControl;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.*;

/**
 * The window (JFrame) for the game. Contains JPanels.
 * @author alanyork
 *
 */
public class MasterFrame extends JFrame{
	// CONSTANTS
	public static final MasterFrame INSTANCE = new MasterFrame();
	public static final String GAME_NAME = "Hall of Bones";
	public static final int DEFAULT_WIDTH = 1400;
    public static final int DEFAULT_HEIGHT = 920;
	
	// CONSTRUCTORS
    /**
     * Spawns the MasterFrame
     */
	public MasterFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(GAME_NAME);
		this.setSize(new Dimension(MasterFrame.DEFAULT_WIDTH, MasterFrame.DEFAULT_HEIGHT));
		this.setPreferredSize(new Dimension(MasterFrame.DEFAULT_WIDTH, MasterFrame.DEFAULT_HEIGHT));
		this.add(content);
		this.pack();
		this.setVisible(true);
	}
	
	// FIELDS
	private JComponent currentView;
	private JPanel content = new JPanel(new CardLayout());
	
	// PUBLIC METHODS
	
	/**
	 * Pass the current view (JComponent) to the JFrame to be displayed
	 * @param view
	 * The view to be displayed
	 */
	public void setView(JComponent view){
		if (currentView != null){
			content.remove(currentView);
		}
		content.add(view, view.getClass().getName());
		((CardLayout) content.getLayout()).show(content, view.getClass().getName());
		view.requestFocus();
	}
	
	/**
	 * Returns the MasterFrame
	 * @return
	 * The MasterFrame
	 */
	public static MasterFrame getInstance(){
		return INSTANCE;
	}

}
