package es.outlook.adriansrj.finaltrial.menu;

import com.dndcraft.AtlasPaper;
import com.dndcraft.menu.Menu;
import com.dndcraft.menu.MenuAction;
import com.dndcraft.menu.MenuAgent;
import com.dndcraft.menu.MenuBuilder;
import com.dndcraft.menu.icon.Button;
import com.dndcraft.menu.icon.Pad;
import com.dndcraft.util.AtlasColor;
import com.dndcraft.util.BukkitComponentBuilder;
import com.dndcraft.util.ItemUtil;
import es.outlook.adriansrj.finaltrial.util.Constants;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * @author AdrianSR / 15/01/2022 / 06:43 a. m.
 */
public class MyMenu {
	
	// nighttime starts at 12542 and ends at 23961
	
	public void open ( Player player ) {
		build ( ).openSession ( player );
	}
	
	protected Menu build ( ) {
		MenuBuilder builder = new MenuBuilder ( "MyMenu" , Constants.THREE_ROW );
		
		// button
		builder.icon ( 13 , new Button ( ) {
			
			@Override
			public ItemStack getItemStack ( MenuAgent menuAgent ) {
				World world = menuAgent.getPlayer ( ).getWorld ( );
				
				return ItemUtil.make (
						Material.CLOCK , 1 ,
						// display name
						AtlasPaper.get ( ).componentBuilder ( )
								.color ( AtlasColor.GOLD )
								.append ( "Estimate Time Left for " )
								.append ( isDaytime ( world ) ? "nightfall" : "dawn" )
								.build ( ) ,
						// lore
						Arrays.asList (
								AtlasPaper.get ( ).componentBuilder ( ).append ( "" ).build ( ) ,
								AtlasPaper.get ( ).componentBuilder ( )
										.color ( AtlasColor.GOLD )
										.append ( "Estimates the time" )
										// it seems that 'newline()'
										// is not working.
										.newline ( )
										.append ( "for the next " )
										.append ( isDaytime ( world ) ? "nightfall" : "dawn" )
										.build ( )
						)
				);
			}
			
			@Override
			public void click ( MenuAction menuAction ) {
				// calculating ticks left
				long                   ticksLeft;
				Player                 player    = menuAction.getPlayer ( );
				World                  world     = player.getWorld ( );
				long                   time      = world.getTime ( ) % 24000L;
				boolean                nighttime = isNighttime ( world );
				BukkitComponentBuilder result    = AtlasPaper.get ( ).componentBuilder ( );
				
				if ( nighttime ) {
					ticksLeft = 23961L - time;
				} else {
					if ( time < 12542L ) {
						ticksLeft = 12542L - time;
					} else {
						ticksLeft = time - 23961L;
					}
				}
				
				// converting ticks to milliseconds
				long millisecondsLeft = ( ticksLeft / 20L ) * 1000L;
				
				// then sending result
				result.color ( AtlasColor.GOLD )
						.append ( "Time left for " + ( nighttime ? "dawn" : "nightfall" ) )
						.append ( ": " )
						.append ( DurationFormatUtils.formatDuration (
								millisecondsLeft , "mm'm' ss's'" , false ) )
						.send ( player );
				
				// refreshing
				menuAction.refreshItem ( this );
			}
		} );
		
		// pad
		builder.icon ( 4 , new Pad ( Material.GLASS_PANE ) );
		
		return builder.build ( );
	}
	
	// --- utils
	
	private boolean isNighttime ( World world ) {
		long time = world.getTime ( ) % 24000L;
		
		return time >= 12542L && time < 23961L;
	}
	
	private boolean isDaytime ( World world ) {
		long time = world.getTime ( ) % 24000L;
		
		return time < 12542L || time > 23961L;
	}
}