/*
 * This file is part of packetevents - https://github.com/retrooper/packetevents
 * Copyright (C) 2022 retrooper and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.retrooper.packetevents.wrapper.play.client;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.PacketWrapper;

public class WrapperPlayClientDebugPing extends PacketWrapper<WrapperPlayClientDebugPing> {

    private long timestamp;

    private WrapperPlayClientDebugPing(WrapperPlayClientDebugPing wrapper) {
        super(wrapper);
    }

    public WrapperPlayClientDebugPing(PacketReceiveEvent event) {
        super(event);
    }

    public WrapperPlayClientDebugPing(long timestamp) {
        super(PacketType.Play.Client.DEBUG_PING);
        this.timestamp = timestamp;
    }

    @Override
    public void read() {
        this.timestamp = this.readLong();
    }

    @Override
    public void write() {
        this.writeLong(this.timestamp);
    }

    @Override
    public void copy(WrapperPlayClientDebugPing wrapper) {
        this.timestamp = wrapper.timestamp;
    }

    @Override
    public WrapperPlayClientDebugPing copy() {
        return new WrapperPlayClientDebugPing(this);
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
