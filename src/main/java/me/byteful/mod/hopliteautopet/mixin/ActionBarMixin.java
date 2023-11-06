package me.byteful.mod.hopliteautopet.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.byteful.mod.hopliteautopet.NetworkUtils.isOnHoplite;
import static me.byteful.mod.hopliteautopet.NetworkUtils.sendDropItemPacket;

@Mixin(InGameHud.class)
public class ActionBarMixin {
    @Unique
    private long lastPetted = 0;

    @Inject(at = @At("HEAD"), method = "setOverlayMessage(Lnet/minecraft/text/Text;Z)V")
    private void sendMessage(Text message, boolean tinted, CallbackInfo info) {
        if (!isOnHoplite()) return;

        final ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null || !player.getInventory().isEmpty()) return;

        if (message.getString().startsWith("Time to drop: ") && System.currentTimeMillis() - lastPetted > 30_000) {
            sendDropItemPacket();
            lastPetted = System.currentTimeMillis();
        }
    }
}