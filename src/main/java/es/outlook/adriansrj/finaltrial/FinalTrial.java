package es.outlook.adriansrj.finaltrial;

import com.dndcraft.command.Commands;
import com.dndcraft.command.exception.InvalidPluginCommandException;
import es.outlook.adriansrj.finaltrial.command.CommandHelloWorld;
import es.outlook.adriansrj.finaltrial.command.CommandHi;
import es.outlook.adriansrj.finaltrial.command.CommandItem;
import es.outlook.adriansrj.finaltrial.command.CommandMenu;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class FinalTrial extends JavaPlugin {
	
	public static FinalTrial getInstance ( ) {
		return JavaPlugin.getPlugin ( FinalTrial.class );
	}
	
	@Override
	public void onEnable ( ) {
		// registering commands
		try {
			Commands.build ( Objects.requireNonNull ( getCommand ( "helloworld" ) ) ,
							 CommandHelloWorld :: new );
			Commands.build ( Objects.requireNonNull ( getCommand ( "menu" ) ) ,
							 CommandMenu :: new );
			Commands.build ( Objects.requireNonNull ( getCommand ( "hi" ) ) ,
							 CommandHi :: new );
			Commands.build ( Objects.requireNonNull ( getCommand ( "item" ) ) ,
							 CommandItem :: new );
		} catch ( InvalidPluginCommandException ex ) {
			ex.printStackTrace ( );
		}
	}
	
	@Override
	public void onDisable ( ) {
		// Plugin shutdown logic
	}
}
