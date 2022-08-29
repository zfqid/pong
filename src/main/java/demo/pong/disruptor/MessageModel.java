package demo.pong.disruptor;

import demo.pong.vo.FileVo;
import lombok.Data;

@Data
public class MessageModel {
    private FileVo fileVo;
}