
package com.example.netherwartguard;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.client.player.ClientPreAttackCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

/**
 * Client-side Nether Wart protection.
 * Prevents harvesting immature Nether Wart.
 */
public final class NetherWartGuardClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientPreAttackCallback.EVENT.register(
                NetherWartGuardClient::shouldCancelAttack
        );
    }

    private static boolean shouldCancelAttack(
            Minecraft client,
            LocalPlayer player,
            int clickCount
    ) {
        if (client == null || player == null
                || client.level == null || client.hitResult == null) {
            return false;
        }

        if (client.hitResult.getType() != HitResult.Type.BLOCK
                || !(client.hitResult instanceof BlockHitResult blockHit)) {
            return false;
        }

        BlockPos pos = blockHit.getBlockPos();
        BlockState state = client.level.getBlockState(pos);

        if (!state.is(Blocks.NETHER_WART)) {
            return false;
        }

        // Ages 0-2 are protected. Age 3 can be harvested.
        return state.getValue(NetherWartBlock.AGE) < 3;
    }
}
