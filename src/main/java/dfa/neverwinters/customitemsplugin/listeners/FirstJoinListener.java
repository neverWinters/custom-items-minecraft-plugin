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
import dfa.neverwinters.customitemsplugin.utils.PluginUtils;

/**
 * <p>FirstJoinListener</p>
 * <p>Player first join listener process class.</p>
 */
public class FirstJoinListener implements Listener 
{
    
    private final Main plugin;
    private static final String[] FIRST_DROP_ITEM_SUBTYPE_BOOK = new String[]{
            PluginConstants.FIRST_DROP_ITEM_SUBTYPE_WRITTEN_BOOK,
            PluginConstants.FIRST_DROP_ITEM_SUBTYPE_WRITABLE_BOOK
    };
    private static final String[] FIRST_DROP_ITEM_SUBTYPE_ARMOR = new String[]{
            PluginConstants.FIRST_DROP_ITEM_SUBTYPE_CHAINMAIL_HELMET,
            PluginConstants.FIRST_DROP_ITEM_SUBTYPE_DIAMOND_HELMET,
            PluginConstants.FIRST_DROP_ITEM_SUBTYPE_GOLDEN_HELMET,
            PluginConstants.FIRST_DROP_ITEM_SUBTYPE_IRON_HELMET,
            PluginConstants.FIRST_DROP_ITEM_SUBTYPE_LEATHER_HELMET,
            PluginConstants.FIRST_DROP_ITEM_SUBTYPE_NETHERITE_HELMET,
            PluginConstants.FIRST_DROP_ITEM_SUBTYPE_TURTLE_HELMET
    };

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
     * <p>Method to handle PlayerJoinEvent event</p>
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

                        if(this.itemSubtypeValidator(FIRST_DROP_ITEM_SUBTYPE_BOOK, (String) firstDropItem.get(PluginConstants.CONFIG_FIELD_SUBTYPE)))
                        {

                            for(int i = 0; i < (Integer) firstDropItem.get(PluginConstants.CONFIG_FIELD_QUANTITY); i++)
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
                                            "[ " + firstDropItem.get(PluginConstants.CONFIG_FIELD_SUBTYPE) + " ]"
                            );

                        }

                        break;

                    case PluginConstants.FIRST_DROP_ITEM_TYPE_ARMOR:

                        if(this.itemSubtypeValidator(FIRST_DROP_ITEM_SUBTYPE_ARMOR, (String) firstDropItem.get(PluginConstants.CONFIG_FIELD_SUBTYPE)))
                        {

                            for(int i = 0; i < (Integer) firstDropItem.get(PluginConstants.CONFIG_FIELD_QUANTITY); i++)
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
                                            "[ " + firstDropItem.get(PluginConstants.CONFIG_FIELD_SUBTYPE) + " ]"
                            );

                        }

                        break;

                    case PluginConstants.FIRST_DROP_ITEM_TYPE_WEAPON:

                        if(this.itemSubtypeValidator(PluginConstants.FIRST_DROP_ITEM_SUBTYPE_WEAPON, (String) firstDropItem.get(PluginConstants.CONFIG_FIELD_SUBTYPE)))
                        {

                            for(int i = 0; i < (Integer) firstDropItem.get(PluginConstants.CONFIG_FIELD_QUANTITY); i++)
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
                                            "[ " + firstDropItem.get(PluginConstants.CONFIG_FIELD_SUBTYPE) + " ]"
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
