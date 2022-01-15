package es.outlook.adriansrj.finaltrial.command;

import com.dndcraft.AtlasPaper;
import com.dndcraft.command.CommandTemplate;
import com.dndcraft.util.BukkitComponentBuilder;
import es.outlook.adriansrj.finaltrial.FinalTrial;

/**
 * @author AdrianSR / 14/01/2022 / 10:22 p. m.
 */
public abstract class CommandBase extends CommandTemplate {
	
	protected final FinalTrial plugin;
	
	protected CommandBase ( ) {
		this.plugin = FinalTrial.getInstance ( );
	}
	
	// --- utils
	
	protected BukkitComponentBuilder newComponent ( ) {
		return AtlasPaper.get ( ).componentBuilder ( );
	}
}
