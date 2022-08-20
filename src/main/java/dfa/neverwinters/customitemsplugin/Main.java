package dfa.neverwinters.customitemsplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import dfa.neverwinters.customitemsplugin.listeners.FirstJoinListener;
import dfa.neverwinters.customitemsplugin.listeners.PlayerUseListener;
import dfa.neverwinters.customitemsplugin.utils.PluginConstants;

/**
 * <p>Main</p>
 * <p>CustomItemsPlugin main class.</p>
 */
public class Main extends JavaPlugin
{
    /**
     * <p>onEnable</p>
     * <p>On enable plugin execution method.</p>
     */
    @Override
    public void onEnable()
    {

        // Configuration initialization
        onPluginStartTask();

        // Register commands
        // /drop => comando para entregar drops a un usuario en especifico

        // Register listeners
        if(this.getConfig().getBoolean("first-join-drop")) 
            Bukkit.getPluginManager().registerEvents(new FirstJoinListener(this), this);
            
        if(this.getConfig().getBoolean("executable-custom-items"))
            Bukkit.getPluginManager().registerEvents(new PlayerUseListener(this), this);

        // falta listener para items que interactuan con otras entidades

    }

    /**
     * <p>onPluginStartTask</p>
     * <p>Plugin configuration initialization and notification method.</p>
     */
    protected void onPluginStartTask()
    {

        System.out.println(
            PluginConstants.PLUGIN_CHAT_PREFIX + 
            PluginConstants.TEXT_BLANK_SPACE +
            PluginConstants.PLUGIN_ENABLED_MESSAGE
        );
        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults();

    }

}
