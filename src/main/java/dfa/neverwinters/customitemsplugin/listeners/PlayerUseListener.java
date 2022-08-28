package dfa.neverwinters.customitemsplugin.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import dfa.neverwinters.customitemsplugin.Main;

public class PlayerUseListener implements Listener
{

    private Main plugin;

    public PlayerUseListener(Main plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent e)
    {

        Player player = e.getPlayer();



    }

}
