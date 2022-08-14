package dfa.neverwinters.customitemsplugin.utils;

import org.bukkit.entity.Player;

public class PlayerValidator {
    
    /**
     * 
     * @param player
     * @return
     */
    public boolean ValidatePlayerPreviousConnection(Player player)
    {

        return player.hasPlayedBefore();

    }

}
