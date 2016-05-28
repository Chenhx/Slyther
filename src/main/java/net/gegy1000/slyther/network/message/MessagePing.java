package net.gegy1000.slyther.network.message;

import net.gegy1000.slyther.client.SlytherClient;
import net.gegy1000.slyther.network.MessageByteBuffer;
import net.gegy1000.slyther.server.ConnectedClient;
import net.gegy1000.slyther.server.SlytherServer;

public class MessagePing extends SlytherServerMessageBase {
    @Override
    public void write(MessageByteBuffer buffer, SlytherServer server, ConnectedClient client) {
        buffer.writeUInt8('p');
    }

    @Override
    public void read(MessageByteBuffer buffer, SlytherClient client) {
        client.waitingForPingReturn = false;
        if (client.lagging) {
            client.etm *= client.lagMultiplier;
            client.lagging = false;
        }
    }

    @Override
    public int[] getMessageIds() {
        return new int[] { 'p' };
    }
}