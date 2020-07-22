package com.korallkarlsson.matchlockweapons.util.handlers;

import com.korallkarlsson.matchlockweapons.util.Reference;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class MatchlockPacketHandler implements IMessage {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
	
	private int data;
	
	public MatchlockPacketHandler(int data)
	{
		this.data = data;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		data = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(data);
	}
	
}
