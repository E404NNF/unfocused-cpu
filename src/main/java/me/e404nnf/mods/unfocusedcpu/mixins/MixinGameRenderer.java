package me.e404nnf.mods.unfocusedcpu.mixins;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {
	@Shadow @Final private MinecraftClient client;
	
	@Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void onRenderHead(float tickDelta, long startTime, boolean tick, CallbackInfo info) {
        if (!client.isWindowFocused()) info.cancel();
    }
}
