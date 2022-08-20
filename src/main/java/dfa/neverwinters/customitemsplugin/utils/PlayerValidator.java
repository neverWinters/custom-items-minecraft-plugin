package dfa.neverwinters.customitemsplugin.utils;

import org.bukkit.entity.Player;

public class PlayerValidator {
    
    /**
     * <p>ValidatePlayerPreviousConnection</p>
     * <p>Mthod to validate if current player has connected previously to server.</p>
     * @param player Player instance
     * @return true or false
     */
    public boolean ValidatePlayerPreviousConnection(Player player)
    {

        return player.hasPlayedBefore();

    }

}
