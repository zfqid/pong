package demo.pong.config;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import demo.pong.disruptor.MessageModel;
import demo.pong.disruptor.PongEventFactory;
import demo.pong.disruptor.PongEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class DisruptorConfig {

    @Bean("messageModel")
    public RingBuffer<MessageModel> messageModelRingBuffer() {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        PongEventFactory factory = new PongEventFactory();

        int bufferSize = 1024 * 256;

        Disruptor<MessageModel> disruptor = new Disruptor<>(factory, bufferSize, executor,
                ProducerType.SINGLE, new BlockingWaitStrategy());

        disruptor.handleEventsWith(new PongEventHandler());

        disruptor.start();

        RingBuffer<MessageModel> ringBuffer = disruptor.getRingBuffer();

        return ringBuffer;
    }
}