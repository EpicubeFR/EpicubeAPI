package fr.epicube.api.mounts.rideable;

import fr.epicube.api.mounts.MountManager;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.EntitySlime;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

public class RideableSlime extends EntitySlime {

    private final Player rider;

    public RideableSlime(World world, Player rider) {
        super(world);
        this.rider = rider;
    }

    @Override
    public void e(float sideMot, float forMot) {
        if (MountManager.shouldDie(this, rider)) return;

        // Set the entity's pitch, yaw, head rotation etc.
        this.lastYaw = this.yaw = this.passenger.yaw;
        this.pitch = this.passenger.pitch * 0.5F;
        this.a(this.yaw, this.pitch);
        this.aM = this.aK = this.yaw;

        this.S = 1.0F; // The custom entity will now automatically climb up 1 high blocks
        sideMot = ((EntityLiving) this.passenger).aZ * 0.5F;
        forMot = ((EntityLiving) this.passenger).ba;

        if (forMot <= 0.0F) {
            forMot *= 0.25F; // Make backward slower
        }

        Field jump = null;
        try {
            jump = EntityLiving.class.getDeclaredField("bc");
            jump.setAccessible(true);
            if (jump != null && this.onGround) {
                if (jump.getBoolean(this.passenger)) {
                    double jumpHeight = 0.5D;
                    this.motY = jumpHeight;
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }

        this.g(sideMot, forMot); // Apply movement
    }
}
