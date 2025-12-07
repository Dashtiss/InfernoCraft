package io.github.dashtiss.infernocraft.effects;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.lodestar.lodestone.registry.common.particle.LodestoneParticleRegistry;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;

import java.awt.*;
import java.util.Random;

public class ExplosionEffect {

    private static final Random RAND = new Random();
    private static final Logger LOGGER = LogManager.getLogger();

    public static void spawnExplosion(World world, Vec3d pos) {
        // Log effect invocation
        LOGGER.info("spawnExplosion triggered at {}", pos);
        LOGGER.debug("Spawning center flash + {} sparks + {} smoke particles", 25, 40);

        // CENTER FLASH
        WorldParticleBuilder.create(LodestoneParticleRegistry.WISP_PARTICLE)
                .setColorData(ColorParticleData.create(new Color(255, 255, 200), new Color(255, 200, 100)).build())
                .setScaleData(GenericParticleData.create(1.2f, 0f).build())
                .setTransparencyData(GenericParticleData.create(0.9f, 0f).build())
                .setLifetime(10)
                .spawn(world, pos.x, pos.y, pos.z);

        // OUTWARD BURST SPARKS / FIRE-LIKE PARTICLES
        for (int i = 0; i < 25; i++) {
            double vx = (RAND.nextDouble() - 0.5) * 0.6;
            double vy = (RAND.nextDouble() - 0.5) * 0.6 + 0.2;
            double vz = (RAND.nextDouble() - 0.5) * 0.6;

            WorldParticleBuilder.create(LodestoneParticleRegistry.WISP_PARTICLE)
                    .setColorData(ColorParticleData.create(new Color(255, 180, 50), new Color(255, 80, 20)).build())
                    .setScaleData(GenericParticleData.create(0.4f, 0f).build())
                    .setTransparencyData(GenericParticleData.create(0.8f, 0f).build())
                    .addMotion(vx, vy, vz)
                    .setLifetime(12 + RAND.nextInt(8))
                    .spawn(world, pos.x, pos.y, pos.z);
        }

        // SMOKE / DUST CLOUD
        for (int i = 0; i < 40; i++) {
            double vx = (RAND.nextDouble() - 0.5) * 0.2;
            double vy = RAND.nextDouble() * 0.1 + 0.1;
            double vz = (RAND.nextDouble() - 0.5) * 0.2;

            WorldParticleBuilder.create(LodestoneParticleRegistry.WISP_PARTICLE)
                    .setColorData(ColorParticleData.create(new Color(80, 80, 80, 200), new Color(40, 40, 40, 0)).build())
                    .setScaleData(GenericParticleData.create(0.6f, 1.2f).build())   // grow over time (smoke expands)
                    .setTransparencyData(GenericParticleData.create(0.5f, 0f).build())
                    .addMotion(vx, vy, vz)
                    .setLifetime(30 + RAND.nextInt(15))
                    .spawn(world, pos.x, pos.y, pos.z);
        }
    }
}
