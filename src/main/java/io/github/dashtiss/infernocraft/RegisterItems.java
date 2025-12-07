package io.github.dashtiss.infernocraft;

import io.github.dashtiss.infernocraft.ThrowingItems.Items.DynamiteItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class RegisterItems {

    public static Item register(Item item, String id) {
        // Create the identifier for the item.
        Identifier itemID = new Identifier(Infernocraft.MOD_ID, id);

        // Register the item.
        Item registeredItem = Registry.register(Registries.ITEM, itemID, item);

        // Return the registered item!
        return registeredItem;
    }

    public static final Item DYNAMITE_ITEM = register(
            // Ignore the food component for now, we'll cover it later in the food section.
            new DynamiteItem(new FabricItemSettings().maxCount(16)),
            "dynamite_item"
    );

    public static void initialize() {
    }

}
