package dfa.neverwinters.customitemsplugin.listeners;

import java.util.Arrays;
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

    /**
     * <p>FirstJoinListener</p>
     * <p>Custom class constructor.</p>
     * @param plugin Current plugin instance
     */
    public FirstJoinListener(Main plugin)
    {
        this.plugin = plugin;
    }

    /**
     * <p>onFirstJoin</p>
     * <p>Method to handle PlayerJointEvent event</p>
     * @param e Event to handle
     */
    @EventHandler
    public void onFirstJoin(PlayerJoinEvent e)
    {

        Player player = e.getPlayer();

        if(!validator.ValidatePlayerPreviousConnection(player))
        {

            List<Map<?, ?>> firstDropItemList =  plugin.getConfig().getMapList("first-join-drop-items");
            
            for(Map<?, ?> firstDropItem : firstDropItemList)
            {

                String itemType = (String) firstDropItem.get("type");
                CustomItemGenerator generator = new CustomItemGenerator();

                switch(itemType)
                {

                    case PluginConstants.FIRST_DROP_ITEM_TYPE_BOOK:

                        if(this.itemSubtypeValidator(PluginConstants.FIRST_DROP_ITEM_SUBTYPE_BOOK, (String) firstDropItem.get("subtype")))
                        {

                            for(int i = 0; i < (int) firstDropItem.get("quantity"); i++)
                            {

                                player.getInventory().addItem(generator.generateCustomItemTypeBook(firstDropItem));

                            }

                        }
                        else
                        {
                            System.out.println(
                                PluginConstants.PLUGIN_CHAT_PREFIX +
                                PluginConstants.TEXT_BLANK_SPACE +
                                PluginConstants.FIRST_DROP_ITEM_SUBTYPE_NOT_VALID +
                                PluginConstants.TEXT_BLANK_SPACE +
                                "[ " + (String) firstDropItem.get("subtype") + " ]"
                            );
                        }

                        break;

                    case PluginConstants.FIRST_DROP_ITEM_TYPE_ARMOR:

                        if(this.itemSubtypeValidator(PluginConstants.FIRST_DROP_ITEM_SUBTYPE_ARMOR, (String) firstDropItem.get("subtype")))
                        {

                            for(int i = 0; i < (int) firstDropItem.get("quantity"); i++)
                            {

                                player.getInventory().addItem(generator.generateCustomItemTypeArmor(firstDropItem));

                            }

                        }
                        else 
                        {

                            System.out.println(
                                PluginConstants.PLUGIN_CHAT_PREFIX +
                                PluginConstants.TEXT_BLANK_SPACE +
                                PluginConstants.FIRST_DROP_ITEM_SUBTYPE_NOT_VALID +
                                PluginConstants.TEXT_BLANK_SPACE +
                                "[ " + (String) firstDropItem.get("subtype") + " ]"
                            );

                        }

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

    /**
     * <p>itemSubtypeValidator</p>
     * <p>Method to validate if string exist in subtype string array.</p>
     * @param subtypeArray Subtype string array
     * @param stringToValidate String to validate
     * @return true or false
     */
    protected boolean itemSubtypeValidator(String[] subtypeArray, String stringToValidate)
    {

        List<String> validOptions = Arrays.asList(subtypeArray);

        return validOptions.contains(stringToValidate);

    }

}
