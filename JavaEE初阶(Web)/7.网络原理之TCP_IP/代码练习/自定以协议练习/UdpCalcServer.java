package calcExer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author wangyimu
 * @Program 自定义协议练习---服务器
 * 请求：字符串，第一个操作数 ； 第二个操作数  ； 运算符
 * 响应: 字符串 计算机结果
 * @create 2021-11-02-21:20
 */
public class UdpCalcServer {
    private DatagramSocket socket = null;

    public UdpCalcServer(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器启动!");
        while(true){
            // 1.读取请求并解析
            DatagramPacket requestPacket = new DatagramPacket(new byte[4096], 4096);
            socket.receive(requestPacket);
            String request = new String(requestPacket.getData(),0,requestPacket.getLength());
            // 2.根据请求计算响应
            String response = process(request);
            // 3.把响应写回到客户端
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(),response.getBytes().length,
                    requestPacket.getSocketAddress());
            socket.send(responsePacket);
            // 4.打印日志
            String log = String.format("[%s,%d] req: %s; resp: %s",requestPacket.getAddress().toString(),
                    requestPacket.getPort(),request,response);
            System.out.println(log);
        }
    }

    // process() 内部就是按照约定好的自定义协议进行具体的处理!
    private String process(String request) {
        // 1.把 request 还原成操作数和运算符
        String[] tokens = request.split(";");
        if(tokens.length != 3){
            return "[请求格式出错!]";
        }
        int num1 = Integer.parseInt(tokens[0]);
        int num2 = Integer.parseInt(tokens[1]);
        String operator = tokens[2];
        int result = 0 ;
        if(operator.equals("+")){
            result = num1 + num2;
        }else if(operator.equals("-")){
            result = num1 - num2;
        }else if(operator.equals("*")){
            result = num1 * num2;
        }else if(operator.equals("/")){
            result = num1 / num2;
        }else{
            return "[请求格式错误! 操作符不支持!]";
        }
        return result + "";
    }

    public static void main(String[] args) throws IOException {
        UdpCalcServer server = new UdpCalcServer(9090);
        server.start();
    }

}
