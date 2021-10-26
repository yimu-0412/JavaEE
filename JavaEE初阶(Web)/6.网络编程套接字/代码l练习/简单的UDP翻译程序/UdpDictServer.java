package dictProgram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashMap;

/**
 * @author wangyimu
 * @Program 简单的 UDP 翻译程序
 * @create 2021-10-26-20:16
 */
public class UdpDictServer {
    private DatagramSocket socket = null;

    private HashMap<String,String> dict = new HashMap<>();

    public UdpDictServer(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
        dict.put("cat","猫");
        dict.put("dog","狗");
        dict.put("windows","窗户");
        dict.put("hello","你好");
    }

    public String process(String request){
        // 根据请求计算响应
        // 例如用户的请求是"hello",就应该返回一个"你好"
        // 这个逻辑的实现,核心就是"查表"
        // 像有道的这样专业的词典,应该把数据都放到数据库中
        // 这里的查表指的就是查数据库的表
        // 当前只是为了体验这个代码的运行,就直接查内存的 hash 表,我们完全可以把这里的数据放到数据库
        // 此处使用 getDefault 来查,如果 get 的话,key 不存在,就返回 null
        // 此处期望值不是 null,而是给客户一个提示
        return dict.getOrDefault(request,"单词在词典中不存在!");
    }

    public void start() throws IOException {
        System.out.println("服务器启动!");
        while(true){
            // 1.读取请求并解析
            DatagramPacket requestPacket = new DatagramPacket(new byte[4096],4096);
            socket.receive(requestPacket);
            String request = new String(requestPacket.getData(),0,requestPacket.getLength());

            // 2.根据请求计算响应
            String response = process(request);

            // 3.将响应写回到客户端
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(),response.getBytes().length,
                    requestPacket.getSocketAddress());
            socket.send(responsePacket);

            // 4.打印日志
            String log = String.format("[%s : %d] req: %s ; resq: %s",requestPacket.getAddress().toString(),
                    requestPacket.getPort(),request,response);
            System.out.println(log);
        }
    }

    public static void main(String[] args) throws IOException {
        UdpDictServer server = new UdpDictServer(9090);
        server.start();
    }
}
