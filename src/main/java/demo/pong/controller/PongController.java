package demo.pong.controller;


import demo.pong.service.DisruptorPongService;
import demo.pong.vo.FileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
public class PongController {
    @Autowired
    private DisruptorPongService disruptorPongService;
    @RequestMapping(value = "/pong",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public  Mono<String> pong(@RequestBody FileVo fileVo){
        disruptorPongService.processPongMsg(fileVo);
        return Mono.just("success");
    }
}
