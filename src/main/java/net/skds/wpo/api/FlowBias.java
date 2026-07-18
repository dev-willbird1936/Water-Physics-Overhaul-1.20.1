package net.skds.wpo.api;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;
import net.skds.wpo.client.ClientEvents;

// Optional ambient directional bias (e.g. a river current) that the core fluid
// algorithms consult when choosing which neighbor to favor, and that getVel blends
// into the rendered/entity-push flow vector. At most one source is registered at a
// time; register() overwrites any previous registration.
public final class FlowBias {

    private static volatile Source source = null;

    private FlowBias() {
    }

    public static void register(Source newSource) {
        source = newSource;
    }

    public static Bias at(BlockGetter level, BlockPos pos, FluidState state) {
        Source s = source;
        if (s == null) {
            return Bias.NONE;
        }
        // Chunk mesh building calls getFlow() with a RenderChunkRegion (a BlockAndTintGetter
        // snapshot, not a Level), so sources keyed on dimension identity (e.g. a per-dimension
        // current cache) silently see nothing during rendering even though tick/push paths -
        // which do get a real Level - resolve fine. Fall back to the client's live level there.
        if (!(level instanceof Level) && FMLEnvironment.dist == Dist.CLIENT) {
            Level clientLevel = ClientEvents.currentLevel();
            return clientLevel == null ? Bias.NONE : s.biasAt(clientLevel, pos, state);
        }
        return s.biasAt(level, pos, state);
    }

    public interface Source {
        Bias biasAt(BlockGetter level, BlockPos pos, FluidState state);
    }

    // direction is the only field the core spread/equalize algorithms read; strength and
    // the unit vector (vecX/vecZ) feed getVel's velocity blend, which drives both the
    // rendered flow angle and entity push. The vector may be diagonal / disagree with the
    // cardinal direction (e.g. a neighborhood-smoothed current at a river bend), and may be
    // present with direction == null (a cell influenced by adjacent currents but with no
    // committed current of its own - render/push only, no algorithm steering).
    public record Bias(Direction direction, double strength, double vecX, double vecZ) {
        public Bias(Direction direction, double strength) {
            this(direction, strength,
                    direction == null ? 0.0D : direction.getStepX(),
                    direction == null ? 0.0D : direction.getStepZ());
        }

        public static final Bias NONE = new Bias(null, 0.0D);
    }
}
