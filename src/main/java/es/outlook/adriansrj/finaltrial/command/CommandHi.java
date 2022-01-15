package es.outlook.adriansrj.finaltrial.command;

import com.dndcraft.command.annotations.Cmd;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author AdrianSR / 15/01/2022 / 08:43 a. m.
 */
public class CommandHi extends CommandBase {
	
	@Cmd ( value = "everybody" )
	public void everybody ( Player player ) {
		hi ( player , null );
	}
	
	@Cmd ( value = "ops" )
	public void ops ( Player player ) {
		hi ( player , Player :: isOp );
	}
	
	// ---- utils
	
	protected void hi ( Player from , Predicate < Player > filter ) {
		for ( Player player : Bukkit.getOnlinePlayers ( ) ) {
			if ( !Objects.equals ( from , player )
					&& ( filter == null || filter.test ( player ) ) ) {
				from.chat ( ChatColor.GREEN + "Hi " + ChatColor.GOLD + player.getName ( ) );
			}
		}
	}
}