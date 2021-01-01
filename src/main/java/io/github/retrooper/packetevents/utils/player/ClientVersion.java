/*
 * MIT License
 *
 * Copyright (c) 2020 retrooper
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.github.retrooper.packetevents.utils.player;

import org.jetbrains.annotations.NotNull;

/**
 * Client Version.
 * This is a nice tool for minecraft's protocol versions.
 * You won't have to memorize the protocol version, just memorize the client version
 * as the version you see in the minecraft launcher.
 * @see <a href="https://wiki.vg/Protocol_version_numbers">https://wiki.vg/Protocol_version_numbers</a>
 * @author retrooper
 * @since 1.6.9
 */
public enum ClientVersion {
    v_1_7_10(5),
    v_1_8(47),
    v_1_9(107),
    v_1_9_1(108),
    v_1_9_2(109),
    v_1_9_3(110),
    v_1_10(210),
    v_1_11(315),
    v_1_11_1(316),
    v_1_12(335),
    v_1_12_1(338),
    v_1_12_2(340),
    v_1_13(393),
    v_1_13_1(401),
    v_1_13_2(404),
    v_1_14(477),
    v_1_14_1(480),
    v_1_14_2(485),
    v_1_14_3(490),
    v_1_14_4(498),
    v_1_15(573),
    v_1_15_1(575),
    v_1_15_2(578),
    v_1_16(735),
    v_1_16_1(736),
    v_1_16_2(751),
    v_1_16_3(753),
    v_1_16_4(754),
    LOWER_THAN_SUPPORTED_VERSIONS(v_1_7_10.protocolVersion - 1),
    HIGHER_THAN_SUPPORTED_VERSIONS(v_1_16_4.protocolVersion + 1),
    /**
     * Pre releases just aren't supported, we would end up with so many enum constants.
     */
    ANY_PRE_RELEASE_VERSION(0),

    TEMP_UNRESOLVED(-1),

    UNRESOLVED(-1);

    private static final short lowestSupportedProtocolVersion = (short) (LOWER_THAN_SUPPORTED_VERSIONS.protocolVersion + 1);
    private static final short highestSupportedProtocolVersion = (short) (HIGHER_THAN_SUPPORTED_VERSIONS.protocolVersion - 1);
    private final short protocolVersion;

    ClientVersion(int protocolVersion) {
        this.protocolVersion = (short) protocolVersion;
    }

    /**
     * Get a ClientVersion enum by protocol version.
     * @param protocolVersion Protocol version.
     * @return ClientVersion
     */
    @NotNull
    public static ClientVersion getClientVersion(int protocolVersion) {
        if (protocolVersion == -1) {
            return ClientVersion.UNRESOLVED;
        }
        for (ClientVersion version : ClientVersion.values()) {
            if (version.protocolVersion > protocolVersion) {
                break;
            } else if (version.protocolVersion == protocolVersion) {
                return version;
            }
        }
        if (protocolVersion <= lowestSupportedProtocolVersion) {
            return LOWER_THAN_SUPPORTED_VERSIONS;
        } else if (protocolVersion >= highestSupportedProtocolVersion) {
            return HIGHER_THAN_SUPPORTED_VERSIONS;
        } else {
            return ANY_PRE_RELEASE_VERSION;
        }
    }

    /**
     * Protocol version of this client version.
     * @return Protocol version.
     */
    public short getProtocolVersion() {
        return protocolVersion;
    }

    /**
     * Is this client version newer than the compared client version?
     * This method simply checks if this client version's protocol version is greater than
     * the compared client version's protocol version.
     * @param target Compared client version.
     * @return Is this client version newer than the compared client version.
     */
    public boolean isHigherThan(ClientVersion target) {
        return protocolVersion > target.protocolVersion && (target != UNRESOLVED && this != UNRESOLVED);
    }

    /**
     * Is this client version newer than or equal to the compared client version?
     * This method simply checks if this client version's protocol version is newer than or equal to
     * the compared client version's protocol version.
     * @param target Compared client version.
     * @return Is this client version newer than or equal to the compared client version.
     */
    public boolean isHigherThanOrEquals(ClientVersion target) {
        return protocolVersion >= target.protocolVersion && (target != UNRESOLVED & this != UNRESOLVED);
    }

    /**
     * Is this client version older than the compared client version?
     * This method simply checks if this client version's protocol version is less than
     * the compared client version's protocol version.
     * @param target Compared client version.
     * @return Is this client version older than the compared client version.
     */
    public boolean isLowerThan(ClientVersion target) {
        return protocolVersion < target.protocolVersion && (target != UNRESOLVED && this != UNRESOLVED);
    }

    /**
     * Is this client version older than or equal to the compared client version?
     * This method simply checks if this client version's protocol version is older than or equal to
     * the compared client version's protocol version.
     * @param target Compared client version.
     * @return Is this client version older than or equal to the compared client version.
     */
    public boolean isLowerThanOrEquals(ClientVersion target) {
        return protocolVersion <= target.protocolVersion && (target != UNRESOLVED && this != UNRESOLVED);
    }

    /**
     * Is this client version equal to the compared client version.
     * This method simply checks if this client version's protocol version
     * is equal to the compared client version's protocol version.
     * @param target Compared
     * @return Is this client version equal to the compared client version.
     */
    public boolean equals(ClientVersion target) {
        return protocolVersion == target.protocolVersion;
    }
}