package me.levitate.resettableregion.listener;

import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.levitate.resettableregion.ResettableRegion;
import me.levitate.resettableregion.block.BlockLog;
import me.levitate.resettableregion.block.BlockManager;
import me.levitate.resettableregion.utils.RegionUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlayerListener implements Listener {
    private final BlockManager blockManager;

    public PlayerListener(BlockManager blockManager) {
        this.blockManager = blockManager;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        ProtectedRegion region = RegionUtils.getRegionAtLocation(event.getBlockPlaced().getLocation());
        if (region == null)
            return;

        if (region.getFlag(ResettableRegion.RESETTABLE_FLAG) == StateFlag.State.ALLOW) {
            Block block = event.getBlockPlaced();
            Location location = block.getLocation();
            Material material = block.getType();

            blockManager.blocksPlaced.add(new BlockLog(material, location));
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        ProtectedRegion region = RegionUtils.getRegionAtLocation(event.getBlock().getLocation());
        if (region == null)
            return;

        if (region.getFlag(ResettableRegion.RESETTABLE_FLAG) == StateFlag.State.ALLOW) {
            Block block = event.getBlock();
            Location location = block.getLocation();
            Material material = block.getType();

            blockManager.blocksBroken.add(new BlockLog(material, location));
        }
    }
}
