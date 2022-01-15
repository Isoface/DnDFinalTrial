package es.outlook.adriansrj.finaltrial.command;

import com.dndcraft.util.AtlasColor;

/**
 * @author AdrianSR / 14/01/2022 / 10:21 p. m.
 */
public class CommandHelloWorld extends CommandBase {
	
	@Override
	protected void invoke ( ) {
		newComponent ( )
				.color ( AtlasColor.GREEN )
				.append ( "Hello World" )
				.send ( getSender ( ) );
	}
}