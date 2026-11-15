package com.exoticmoss.simplegalaxy.item.custom;

import com.exoticmoss.simplegalaxy.worldgen.ModDimensions;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;

import java.util.List;

//Right click: travels to the Galaxy dimension, or back to the Overworld when used inside it.
//Temporary way in, until the story gives the player a proper gateway.
public class AstralCompassItem extends Item {
    private static final int COOLDOWN_TICKS = 100;

    public AstralCompassItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResultHolder.success(stack);
        }

        ResourceKey<Level> targetKey = level.dimension() == ModDimensions.GALAXY_LEVEL ? Level.OVERWORLD : ModDimensions.GALAXY_LEVEL;
        ServerLevel target = serverLevel.getServer().getLevel(targetKey);
        if (target == null || !player.canChangeDimensions(serverLevel, target)) {
            player.displayClientMessage(Component.translatable("item.simplegalaxy.astral_compass.failed"), true);
            return InteractionResultHolder.fail(stack);
        }

        BlockPos landing = findLanding(target, player.blockPosition());
        player.changeDimension(new DimensionTransition(target, Vec3.atBottomCenterOf(landing), Vec3.ZERO,
                player.getYRot(), player.getXRot(), DimensionTransition.DO_NOTHING));
        player.resetFallDistance();
        player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);
        target.playSound(null, landing, SoundEvents.PLAYER_TELEPORT, SoundSource.PLAYERS, 1f, 0.6f);
        return InteractionResultHolder.consume(stack);
    }

    //The first free block above the ground at the same X/Z. Over the void, a small platform is built.
    private static BlockPos findLanding(ServerLevel target, BlockPos from) {
        int x = from.getX();
        int z = from.getZ();
        target.getChunk(x >> 4, z >> 4); //generates the chunk, so the heightmap is known
        int y = target.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z);
        if (y <= target.getMinBuildHeight()) {
            y = 80;
            for (BlockPos pos : BlockPos.betweenClosed(x - 1, y - 1, z - 1, x + 1, y - 1, z + 1)) {
                target.setBlockAndUpdate(pos, Blocks.OBSIDIAN.defaultBlockState());
            }
        }
        return new BlockPos(x, y, z);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.simplegalaxy.astral_compass.tooltip").withStyle(ChatFormatting.GRAY));
    }
}
