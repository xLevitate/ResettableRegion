package me.levitate.resettableregion;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import me.levitate.resettableregion.block.BlockManager;
import me.levitate.resettableregion.listener.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ResettableRegion extends JavaPlugin {
    public static StateFlag RESETTABLE_FLAG;

    @Override
    public void onEnable() {
        BlockManager blockManager = new BlockManager();

        getServer().getPluginManager().registerEvents(new PlayerListener(blockManager), this);

        Bukkit.getScheduler().runTaskTimer(this, blockManager::resetBlocks, 0L, 200L);
    }

    @Override
    public void onLoad() {
        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();

        try {
            StateFlag flag = new StateFlag("resettable", false);
            registry.register(flag);
            RESETTABLE_FLAG = flag;
        }
        catch (FlagConflictException e) {
            Flag<?> existing = registry.get("resettable");
            if (existing instanceof StateFlag) {
                RESETTABLE_FLAG = (StateFlag) existing;
            }
        }
    }
}
