package demo.pong.service;

import demo.pong.vo.FileVo;

public interface DisruptorPongService {

    /**
     * message
     * @param fileVo
     */
    void processPongMsg(FileVo fileVo);
}
