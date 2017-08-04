package DungeonMVC;

import javax.swing.JComponent;

public class DungeonController {

	private DungeonView view;
	boolean battleOver;
	
	public DungeonController(DungeonView view)
	{
		this.view = view;
	}
	
	public void signalStartBattle()
	{
		view.startBattleView();
	}
	
	public void signalSendToMainMenu()
	{
		view.switchToMainMenu();
	}
	
	public void signalMoveComponent(JComponent component, int nextX, int nextY)
	{
		//  Move the component
		view.moveComponent(component, nextX, nextY);
	}
	
	public void signalHideChest()
	{
		view.chestOpened();
	}
	
}
