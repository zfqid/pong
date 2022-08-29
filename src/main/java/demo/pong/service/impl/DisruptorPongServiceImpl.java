package demo.pong.service.impl;

import com.lmax.disruptor.RingBuffer;
import demo.pong.disruptor.MessageModel;
import demo.pong.service.DisruptorPongService;
import demo.pong.vo.FileVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisruptorPongServiceImpl implements DisruptorPongService {
    private  static Logger logger = LoggerFactory.getLogger(DisruptorPongServiceImpl.class);
    @Autowired
    private RingBuffer<MessageModel> messageModelRingBuffer;


    @Override
    public void processPongMsg(FileVo fileVo) {
        long sequence = messageModelRingBuffer.next();
        try {
            MessageModel event = messageModelRingBuffer.get(sequence);
            event.setFileVo(fileVo);
        } catch (Exception e) {
        } finally {
            messageModelRingBuffer.publish(sequence);
        }
    }
}