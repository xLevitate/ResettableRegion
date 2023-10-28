package me.levitate.resettableregion.block;

import org.bukkit.Material;

import java.util.*;

public class BlockManager {
    public final Set<BlockLog> blocksPlaced = new HashSet<>();
    public final Set<BlockLog> blocksBroken = new HashSet<>();

    public void resetBlocks() {
        for (BlockLog blockLog : blocksPlaced) {
            blockLog.getLocation().getBlock().setType(Material.AIR);
        }

        for (BlockLog blockLog : blocksBroken) {
            blockLog.getLocation().getBlock().setType(blockLog.getMaterial());
        }

        blocksPlaced.clear();
        blocksBroken.clear();
    }
}
