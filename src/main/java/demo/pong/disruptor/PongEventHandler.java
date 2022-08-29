package demo.pong.disruptor;

import com.lmax.disruptor.EventHandler;
import demo.pong.util.FileUtil;
import demo.pong.vo.FileVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class PongEventHandler implements EventHandler<MessageModel> {
    private  static Logger logger = LoggerFactory.getLogger(PongEventHandler.class);
    @Override
    public void onEvent(MessageModel event, long sequence, boolean endOfBatch) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String content= new String("");
        FileVo fileVo = event.getFileVo();
        String fileStr = fileVo.getFilePath()+ File.separator+fileVo.getFileName();
        try {
            if (event != null) {
                content = FileUtil.getFileContent(fileStr);
            }
        } catch (Exception e) {
        }finally{
            stopWatch.stop();
            logger.info("file path：{}, file content：{}，processing time：{}millisecond",fileStr,content,stopWatch.getTotalTimeMillis());
        }
    }
}