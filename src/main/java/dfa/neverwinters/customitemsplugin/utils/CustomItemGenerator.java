package dfa.neverwinters.customitemsplugin.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class CustomItemGenerator {
    
    public ItemStack generateCustomItemBook(Map<?, ?> bookInfo)
    {

        ItemStack itemStack = new ItemStack(Material.getMaterial((String) bookInfo.get("subtype"))); 
        BookMeta bookMeta = (BookMeta) itemStack.getItemMeta();

        if((int) bookInfo.get("quantity") > 0)
        {

            bookMeta.setDisplayName(PluginUtils.chat((String) bookInfo.get("display-name")));
            bookMeta.setAuthor((String) bookInfo.get("author"));
            bookMeta.setTitle((String) bookInfo.get("title"));

            if(bookInfo.get("text") instanceof List<?>)
            {

                List<?> bookPages = (List<?>) bookInfo.get("text");

                for(Object bookPage : bookPages)
                { 

                    bookMeta.addPage((String) bookPage);

                }
                
            }

            if(bookInfo.get("lore") instanceof ArrayList<?>)
            {

                ArrayList<String> loreVerified = new ArrayList<String>();
                List<?> lore = (List<?>) bookInfo.get("lore");

                for(Object loreLine : lore)
                {
                    
                    loreVerified.add((String) loreLine);

                }

                if(!loreVerified.isEmpty()){ bookMeta.setLore(loreVerified); }
            }
            
        }

        itemStack.setItemMeta(bookMeta);

        return itemStack;
        
    }

}
