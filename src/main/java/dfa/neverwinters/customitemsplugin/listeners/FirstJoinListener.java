package dfa.neverwinters.customitemsplugin.listeners;

import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import dfa.neverwinters.customitemsplugin.Main;
import dfa.neverwinters.customitemsplugin.utils.CustomItemGenerator;
import dfa.neverwinters.customitemsplugin.utils.PlayerValidator;
import dfa.neverwinters.customitemsplugin.utils.PluginConstants;

public class FirstJoinListener implements Listener 
{
    
    private Main plugin;
    private PlayerValidator validator = new PlayerValidator();

    public FirstJoinListener(Main plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFirstJoin(PlayerJoinEvent e)
    {

        Player player = e.getPlayer();

        if(
            !validator.ValidatePlayerPreviousConnection(player) && 
            plugin.getConfig().getBoolean("first-join-drop")
        )
        {

            List<Map<?, ?>> firstDropItemList =  plugin.getConfig().getMapList("first-join-drop-items");
            
            for(Map<?, ?> firstDropItem : firstDropItemList)
            {

                String itemType = (String) firstDropItem.get("type");
                CustomItemGenerator generator = new CustomItemGenerator();

                switch(itemType)
                {

                    case PluginConstants.FIRST_DROP_ITEM_TYPE_BOOK:

                        player.getInventory().addItem(generator.generateCustomItemBook(firstDropItem));
                        break;

                    default:

                        System.out.println(
                            PluginConstants.PLUGIN_CHAT_PREFIX +
                            PluginConstants.TEXT_BLANK_SPACE +
                            PluginConstants.FIRST_DROP_ITEM_TYPE_NOT_VALID + 
                            PluginConstants.TEXT_BLANK_SPACE +
                            "[ " + itemType + " ]"
                        );
                        break;

                }

            }

        }

    }



}
