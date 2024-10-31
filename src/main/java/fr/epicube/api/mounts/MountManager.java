package fr.epicube.api.mounts;

import fr.epicube.api.mounts.rideable.RideableSlime;
import fr.epicube.api.utils.Flags;
import net.minecraft.server.v1_8_R3.EntityAgeable;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class MountManager {
    public static double mountSpeed = 0.20D;
    private static double maxHealth = 2.0D;

    private static void make(EntityLiving nmsEntity, Player player){
        if(!canSummonMount(player.getLocation())){
            player.sendMessage("§6[Mount] §cYou can't summon a mount here.");
            return;
        }

        // Monture staff
        LivingEntity mount = (LivingEntity) nmsEntity.getBukkitEntity();
        // Associe joueur <> Monture
        Flags.setStringFlag(mount, 1, player.getName());
        // Adulte
        if(mount instanceof EntityAgeable) ((EntityAgeable) mount).setAge(0);
        Location loc = player.getLocation();
        World nmsWorld = ((CraftWorld) loc.getWorld()).getHandle();
        nmsEntity.setPosition(loc.getX(), loc.getY() + 0.3, loc.getZ());
        nmsWorld.addEntity(nmsEntity, CreatureSpawnEvent.SpawnReason.CUSTOM);
        // Vie
        mount.setHealth(maxHealth);
        // Mount
        mount.setPassenger(player);
        player.closeInventory();
    }

    private static boolean canSummonMount(Location location) {
        org.bukkit.World world = location.getWorld();
        Block block = location.getBlock();
        for (int x = location.getBlockX() - 1; x <= location.getBlockX() + 1; x++) {
            for (int y = location.getBlockY(); y <= location.getBlockY() + 1; y++) {
                for (int z = location.getBlockZ() - 1; z <= location.getBlockZ() + 1; z++){
                    block = world.getBlockAt(x, y, z);
                    if(block.getType().isSolid()) return false;
                }
            }
        }
        return false;
    }

    public static void RideSlime(Player player){
        Location loc = player.getLocation();
        World nmsWorld = ((CraftWorld) loc.getWorld()).getHandle();
        RideableSlime nmsEntity = new RideableSlime(nmsWorld, player);
        make(nmsEntity, player);
    }

    public static boolean shouldDie(EntityLiving mount, Player rider) {
        if(mount.passenger == null || !(mount.passenger instanceof EntityHuman)){
            mount.die();
            return true;
        }
        return false;
    }
}
