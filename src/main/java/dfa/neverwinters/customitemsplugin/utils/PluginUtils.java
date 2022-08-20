package dfa.neverwinters.customitemsplugin.utils;

import org.bukkit.ChatColor;

public class PluginUtils {
    
    /**
     * <p>chat</p>
     * <p>Method to format string from color codes.</p>
     * @param s String to format
     * @return Formated string
     */
    public static String chat(String s)
    {

        return ChatColor.translateAlternateColorCodes('&', s);

    }

}
 