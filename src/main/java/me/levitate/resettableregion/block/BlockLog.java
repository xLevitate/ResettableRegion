package me.levitate.resettableregion.block;

import org.bukkit.Location;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

public class BlockLog {
    private final Material material;
    private final Location location;

    public BlockLog(@NotNull Material material, @NotNull Location location) {
        this.material = material;
        this.location = location;
    }

    public Material getMaterial() {
        return material;
    }

    public Location getLocation() {
        return location;
    }
}
