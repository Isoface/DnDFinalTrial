package es.outlook.adriansrj.finaltrial.command;

import es.outlook.adriansrj.finaltrial.menu.MyMenu;
import org.bukkit.entity.Player;

/**
 * @author AdrianSR / 15/01/2022 / 06:41 a. m.
 */
public class CommandMenu extends CommandBase {
	
	private final MyMenu handle;
	
	public CommandMenu ( ) {
		this.handle = new MyMenu ( );
	}
	
	public void invoke ( Player player ) {
		handle.open ( player );
	}
}