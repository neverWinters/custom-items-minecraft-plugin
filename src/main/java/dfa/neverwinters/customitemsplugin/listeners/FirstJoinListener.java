package dfa.neverwinters.customitemsplugin.listeners;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import dfa.neverwinters.customitemsplugin.utils.PluginUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import dfa.neverwinters.customitemsplugin.Main;
import dfa.neverwinters.customitemsplugin.utils.CustomItemGenerator;
import dfa.neverwinters.customitemsplugin.utils.PlayerValidator;
import dfa.neverwinters.customitemsplugin.utils.PluginConstants;

/**
 * <p>FirstJoinListener</p>
 * <p>Player first join listener process class.</p>
 */
public class FirstJoinListener implements Listener 
{
    
    private final Main plugin;

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

        if(!PlayerValidator.validatePlayerPreviousConnection(player))
        {

            List<Map<?, ?>> firstDropItemList =  plugin.getConfig().getMapList("first-join-drop-items");
            
            for(Map<?, ?> firstDropItem : firstDropItemList)
            {

                String itemType = (String) firstDropItem.get("type");

                switch(itemType)
                {

                    case PluginConstants.FIRST_DROP_ITEM_TYPE_BOOK:

                        if(this.itemSubtypeValidator(PluginConstants.FIRST_DROP_ITEM_SUBTYPE_BOOK, (String) firstDropItem.get("subtype")))
                        {

                            for(int i = 0; i < (Integer) firstDropItem.get("quantity"); i++)
                            {

                                player.getInventory().addItem(CustomItemGenerator.generateCustomItemTypeBook(firstDropItem));

                            }

                        }
                        else
                        {

                            PluginUtils.logger(
                                    PluginConstants.PLUGIN_CHAT_PREFIX +
                                            PluginConstants.TEXT_BLANK_SPACE +
                                            PluginConstants.FIRST_DROP_ITEM_SUBTYPE_NOT_VALID +
                                            PluginConstants.TEXT_BLANK_SPACE +
                                            "[ " + firstDropItem.get("subtype") + " ]"
                            );

                        }

                        break;

                    case PluginConstants.FIRST_DROP_ITEM_TYPE_ARMOR:

                        if(this.itemSubtypeValidator(PluginConstants.FIRST_DROP_ITEM_SUBTYPE_ARMOR, (String) firstDropItem.get("subtype")))
                        {

                            for(int i = 0; i < (Integer) firstDropItem.get("quantity"); i++)
                            {

                                player.getInventory().addItem(CustomItemGenerator.generateCustomItemTypeArmor(firstDropItem));

                            }

                        }
                        else 
                        {

                            PluginUtils.logger(
                                    PluginConstants.PLUGIN_CHAT_PREFIX +
                                            PluginConstants.TEXT_BLANK_SPACE +
                                            PluginConstants.FIRST_DROP_ITEM_SUBTYPE_NOT_VALID +
                                            PluginConstants.TEXT_BLANK_SPACE +
                                            "[ " + firstDropItem.get("subtype") + " ]"
                            );

                        }

                        break;

                    case PluginConstants.FIRST_DROP_ITEM_TYPE_WEAPON:

                        if(this.itemSubtypeValidator(PluginConstants.FIRST_DROP_ITEM_SUBTYPE_WEAPON, (String) firstDropItem.get("subtype")))
                        {

                            for(int i = 0; i < (Integer) firstDropItem.get("quantity"); i++)
                            {

                                player.getInventory().addItem(CustomItemGenerator.generateCustomItemTypeWeapon(firstDropItem));

                            }

                        }
                        else
                        {

                            PluginUtils.logger(
                                    PluginConstants.PLUGIN_CHAT_PREFIX +
                                            PluginConstants.TEXT_BLANK_SPACE +
                                            PluginConstants.FIRST_DROP_ITEM_SUBTYPE_NOT_VALID +
                                            PluginConstants.TEXT_BLANK_SPACE +
                                            "[ " + firstDropItem.get("subtype") + " ]"
                            );

                        }

                        break;

                    default:

                        PluginUtils.logger(
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
