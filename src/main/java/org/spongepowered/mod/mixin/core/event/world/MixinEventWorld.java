/*
 * This file is part of Sponge, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.mod.mixin.core.event.world;

import org.spongepowered.api.event.source.world.WorldEvent;
import org.spongepowered.api.util.annotation.NonnullByDefault;
import org.spongepowered.api.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.mod.interfaces.IMixinEvent;
import org.spongepowered.mod.mixin.core.fml.common.eventhandler.MixinEvent;

@NonnullByDefault
@Mixin(value = net.minecraftforge.event.world.WorldEvent.class, remap = false)
public abstract class MixinEventWorld extends MixinEvent implements WorldEvent {

    @Shadow public net.minecraft.world.World world;

    @Override
    public World getWorld() {
        return (World) this.world;
    }

    @SuppressWarnings("unused")
    private static net.minecraftforge.event.world.WorldEvent fromSpongeEvent(WorldEvent spongeEvent) {
        net.minecraftforge.event.world.WorldEvent event =
                new net.minecraftforge.event.world.WorldEvent((net.minecraft.world.World) spongeEvent.getWorld());
        ((IMixinEvent) event).setSpongeEvent(spongeEvent);
        return event;
    }
}
