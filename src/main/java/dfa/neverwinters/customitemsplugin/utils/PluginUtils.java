package dfa.neverwinters.customitemsplugin.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class PluginUtils
{

    /**
     * <p>PluginUtils</p>
     * <p>Private class constructor.</p>
     */
    private PluginUtils() { }

    /**
     * <p>chat</p>
     * <p>Method to format string from color codes.</p>
     * @param s String to format
     * @return Formatted string
     */
    public static String chat(String s) { return ChatColor.translateAlternateColorCodes('&', s); }

    /**
     * <p>logger</p>
     * <p>Method to log messages through Bukkit logger.</p>
     * @param s String to log
     */
    public static void logger(String s) { Bukkit.getLogger().info(s); }

}
 