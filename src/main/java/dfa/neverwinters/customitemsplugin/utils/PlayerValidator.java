package dfa.neverwinters.customitemsplugin.utils;

import org.bukkit.entity.Player;

public class PlayerValidator {
    
    /**
     * <p>validatePlayerPreviousConnection</p>
     * <p>Method to validate if current player has connected previously to server.</p>
     * @param player Player instance
     * @return true or false
     */
    public static boolean validatePlayerPreviousConnection(Player player)
    {

        return player.hasPlayedBefore();

    }

}
