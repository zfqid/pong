package demo.pong.disruptor;

import com.lmax.disruptor.EventFactory;

public class PongEventFactory implements EventFactory<MessageModel> {
    @Override
    public MessageModel newInstance() {
        return new MessageModel();
    }
}