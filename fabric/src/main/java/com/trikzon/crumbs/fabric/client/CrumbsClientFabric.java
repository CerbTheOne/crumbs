package com.trikzon.crumbs.fabric.client;


import com.trikzon.crumbs.client.CrumbsClient;
import com.trikzon.crumbs.client.platform.AbstractPlatformClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Function;

public class CrumbsClientFabric implements ClientModInitializer, AbstractPlatformClient {
    @Override
    public void onInitializeClient() {
        CrumbsClient.init(this);
    }

    @Override
    public <T extends BlockEntity> void registerBlockEntityRenderer(BlockEntityType<T> beType, Function<BlockEntityRenderDispatcher, BlockEntityRenderer<T>> renderer) {
        BlockEntityRendererRegistry.INSTANCE.register(beType, renderer);
    }

    @Override
    public void registerSpritesToAtlas(ResourceLocation atlas, ResourceLocation... spriteLocations) {
        ClientSpriteRegistryCallback.event(atlas).register((textureAtlas, registry) -> {
            for (ResourceLocation sprite : spriteLocations) {
                registry.register(sprite);
            }
        });
    }
}