package es.outlook.adriansrj.finaltrial.command;

import com.dndcraft.command.annotations.Cmd;
import com.dndcraft.util.AtlasColor;
import com.dndcraft.util.ItemUtil;
import com.dndcraft.util.RandomUtil;
import es.outlook.adriansrj.finaltrial.util.Constants;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author AdrianSR / 15/01/2022 / 09:00 a. m.
 */
public class CommandItem extends CommandBase {
	
	public void invoke ( Player player ) {
		Material[] toolMaterials = {
				Material.WOODEN_SWORD ,
				Material.WOODEN_AXE ,
				Material.WOODEN_HOE ,
				Material.WOODEN_PICKAXE ,
				Material.WOODEN_SHOVEL
		};
		
		ItemStack item = ItemUtil.make (
				toolMaterials[ RandomUtil.roll ( toolMaterials.length - 1 ) ] ,
				newComponent ( )
						.color ( AtlasColor.GOLD )
						.append ( "Owned Item" )
						.build ( ) );
		
		ItemUtil.setTag ( item , Constants.OWNER_KEY , player.getName ( ) );
		
		// then dropping
		player.getWorld ( ).dropItemNaturally ( player.getLocation ( ) , item );
	}
	
	@Cmd ( value = "check" )
	public void check ( Player player ) {
		ItemStack item = player.getInventory ( ).getItemInMainHand ( );
		
		if ( item.getType ( ) != Material.AIR && ItemUtil.hasTag ( item , Constants.OWNER_KEY ) ) {
			newComponent ( ).color ( AtlasColor.GREEN )
					.append ( "Item owner: " )
					.color ( AtlasColor.GOLD )
					.append ( ItemUtil.getStringTag ( item , Constants.OWNER_KEY ) )
					.send ( player );
		} else {
			newComponent ( ).color ( AtlasColor.DARK_RED )
					.append ( "Unknown Owner" )
					.send ( player );
		}
	}
}
