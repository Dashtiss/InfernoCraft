package io.github.dashtiss.infernocraft;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import io.github.dashtiss.infernocraft.CustomBlocks.*;

public final class RegisterBlocks {
    // A simple placeable block (no custom model/texture required — will use the default missing texture but will be visible)
    public static final Block EXAMPLE_BLOCK = register("example_block",
            new Block(Block.Settings.create().strength(4.0f))
    );

    // Register the testing block here so all block registrations are in this file
    public static final Block TESTING_BLOCK = register("testing_block",
            new testingblock(Block.Settings.create().strength(4.0f))
    );

    // Register the exploding block here so all block registrations are in this file
    public static final Block EXPLODING_BLOCK = register("exploding_block",
            new ExplodingBlock(Block.Settings.create().strength(4.0f))
    );


    private static <T extends Block> T register(String path, T block) {
        Identifier id = new Identifier("infernocraft", path);
        Registry.register(Registries.BLOCK, id, block);
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
        return block;
    }

    // Called from the mod initializer to ensure the class is loaded and static fields are initialized
    public static void initialize() {
    }
}