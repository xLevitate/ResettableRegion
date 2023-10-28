package me.levitate.resettableregion.utils;

import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import org.bukkit.Location;

public class RegionUtils {
    public static ProtectedRegion getRegionAtLocation(Location location) {
        ApplicableRegionSet set = WorldGuard.getInstance().getPlatform().getRegionContainer().get(new BukkitWorld(location.getWorld())).getApplicableRegions(BlockVector3.at(location.getX(),location.getY(),location.getZ()));

        for (ProtectedRegion region : set) {
            if (region.contains(location.getBlockX(), location.getBlockY(), location.getBlockZ())) {
                return region;
            }
        }

        return null;
    }
}
