package com.rotpaddon.exampleaddon.network

import com.github.standobyte.jojo.network.packets.IModPacketHandler
import com.rotpaddon.exampleaddon.AddonMain
import com.rotpaddon.exampleaddon.network.server.TutorialDataPacket
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.util.FakePlayer
import net.minecraftforge.fml.network.NetworkDirection
import net.minecraftforge.fml.network.NetworkRegistry
import net.minecraftforge.fml.network.PacketDistributor
import net.minecraftforge.fml.network.simple.SimpleChannel
import java.util.*

object AddonPackets {
    private val PROTOCOL_VERSION = "1"
    private var channel: SimpleChannel? = null
    private var packetIndex = 0

    fun init() {
        channel = NetworkRegistry.ChannelBuilder
            .named(ResourceLocation(AddonMain.MOD_ID, "main_channel"))
            .clientAcceptedVersions { it == PROTOCOL_VERSION }
            .serverAcceptedVersions { it == PROTOCOL_VERSION }
            .networkProtocolVersion { PROTOCOL_VERSION }
            .simpleChannel()

        registerMessage(channel!!, TutorialDataPacket.Handler(), Optional.of(NetworkDirection.PLAY_TO_CLIENT))
    }

    private fun <MSG> registerMessage(
        channel: SimpleChannel,
        handler: IModPacketHandler<MSG>,
        networkDirection: Optional<NetworkDirection>
    ) {
        if (packetIndex > 127) {
            throw IllegalStateException("Too many packets (> 127) registered for a single channel!")
        }
        channel.registerMessage(
            packetIndex++,
            handler.packetClass,
            handler::encode,
            handler::decode,
            handler::enqueueHandleSetHandled,
            networkDirection
        )
    }

    fun sendToClient(msg: Any, player: ServerPlayerEntity) {
        if (player !is FakePlayer) {
            channel!!.send(PacketDistributor.PLAYER.with { player }, msg)
        }
    }

    fun sendToClient(msg: Any, player: PlayerEntity) {
        if (player is ServerPlayerEntity) {
            sendToClient(msg, player)
        } else {
            AddonMain.LOGGER.warn("You can't send a message not by a player: " + player.name.string)
        }
    }

    fun sendToServer(msg: Any) {
        channel!!.sendToServer(msg)
    }

    fun sendToClientsTracking(msg: Any, entity: Entity) {
        channel!!.send(PacketDistributor.TRACKING_ENTITY.with { entity }, msg)
    }

    fun sendToClientsTrackingAndSelf(msg: Any, entity: Entity) {
        channel!!.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with { entity }, msg)
    }

    fun sendToAllPlayers(msg: Any) {
        channel!!.send(PacketDistributor.ALL.noArg(), msg)
    }
}