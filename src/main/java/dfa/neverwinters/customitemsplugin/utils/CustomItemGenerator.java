package dfa.neverwinters.customitemsplugin.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItemGenerator {
    
    /**
     * <p>generateCustomItemTypeBook</p>
     * <p>Method to generate book type custom item.</p>
     * @param bookInfo Book item information
     * @return ItemStack instance
     */
    public ItemStack generateCustomItemTypeBook(Map<?, ?> bookInfo)
    {

        ItemStack itemStack = new ItemStack(Material.getMaterial((String) bookInfo.get("subtype"))); 
        BookMeta bookMeta = (BookMeta) itemStack.getItemMeta();

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

        itemStack.setItemMeta(bookMeta);

        return itemStack;
        
    }

    /**
     * <p>generateCustomItemTypeArmor</p>
     * <p>Method to generate armor type custom item.</p>
     * @param armorInfo Armor item information
     * @return ItemStack instance
     */
    public ItemStack generateCustomItemTypeArmor(Map<?, ?> armorInfo)
    {

        ItemStack itemStack = new ItemStack(Material.getMaterial((String) armorInfo.get("subtype")));
        ItemMeta armorMeta = (ItemMeta) itemStack.getItemMeta();

        armorMeta.setDisplayName(PluginUtils.chat((String) armorInfo.get("display-name")));
        armorMeta.setUnbreakable((boolean) armorInfo.get("unbreakable"));

        AttributeModifier genericArmor = new AttributeModifier(UUID.randomUUID(), "generic.armor", (double) armorInfo.get("generic-armor-amount"), Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        AttributeModifier genericArmorToughness = new AttributeModifier(UUID.randomUUID(), "generic.armor.toughness", (double) armorInfo.get("generic-armor-toughness-amount"), Operation.ADD_NUMBER, EquipmentSlot.HEAD);

        armorMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, genericArmor);
        armorMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, genericArmorToughness);

        if(armorInfo.get("enchantments") instanceof List<?>)
        {

            List<?> enchantmentsList = (List<?>) armorInfo.get("enchantments");

            for(Object enchantment : enchantmentsList)
            { 
                
                NamespacedKey key = NamespacedKey.minecraft((String) ((Map<?, ?>) enchantment).get("name"));
                Enchantment enchant = Enchantment.getByKey(key);

                armorMeta.addEnchant(
                    enchant,
                    (int) ((Map<?, ?>) enchantment).get("level"), 
                    (boolean) ((Map<?, ?>) enchantment).get("ignore-level-restriction"));

            }

        }

        itemStack.setItemMeta(armorMeta);

        return itemStack;

    }

}
