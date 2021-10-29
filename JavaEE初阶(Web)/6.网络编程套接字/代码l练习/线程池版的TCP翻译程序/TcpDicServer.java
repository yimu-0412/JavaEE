package echoTcpProgram;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author wangyimu
 * @Program 线程池版 TCP 翻译程序
 * @create 2021-10-28-23:27
 */
public class TcpDicServer extends TcpThreadPoolEchoServer {
    private HashMap<String,String> dict = new HashMap<>();

    public TcpDicServer(int port) throws IOException {
        super(port);
        dict.put("cat","猫");
        dict.put("dog","狗");
        dict.put("windows","窗户");
        dict.put("hello","你好");
    }
    // start 方法不变
    // processConnection 方法也不变
    // 只要重写 process 即可

    @Override
    public String process(String request) {
        return dict.getOrDefault(request,"要查的词不存在!");
    }

    public static void main(String[] args) throws IOException {
        TcpDicServer server = new TcpDicServer(9090);
        server.start();
    }
}
