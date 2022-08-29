package demo.pong

import demo.pong.service.DisruptorPongService
import demo.pong.util.FileUtil
import demo.pong.vo.FileVo
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification;

//@ActiveProfiles("dev")
//@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DisruptorPongService.class)
@SpringBootTest(classes = App.class)
class AppTest extends Specification {

    @Autowired
    private DisruptorPongService disruptorPongService;
    def "disruptor test"() {
        given:
        def fileVo = new FileVo();
        def  filePath = "E:\\ping\\file";
        def  fileName = "1661577283010.txt";
        def  content = "1661577283010.txt";
            fileVo.setFilePath(filePath)
            fileVo.setFileName(fileName)
        def  file = new File(filePath);
        when:
            file.mkdirs();
            file = new File(filePath+File.separator+fileName);
            file.createNewFile()
            FileOutputStream local = new FileOutputStream(file)
            local.write(content.getBytes());
            disruptorPongService.processPongMsg(fileVo);
        then:
            true
//        res.response.status == HttpServletResponse.SC_OK


    }
    def "get file content test"() {
        given:
        def  filePath = "/pong/";
        def  fileName = "test.txt";
        def  content = "hello"
        def  file = new File(filePath);
        when:
            file.mkdirs();
            file = new File(filePath+fileName);
            file.createNewFile()
            FileOutputStream local = new FileOutputStream(file)
            local.write(content.getBytes());
            def result = FileUtil.getFileContent(filePath+fileName)
        then:
           result.equals(content)


    }

}
