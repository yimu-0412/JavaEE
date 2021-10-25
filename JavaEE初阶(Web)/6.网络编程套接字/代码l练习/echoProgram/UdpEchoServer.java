package echoProgram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author wangyimu
 * @Program 简单的回显程序
 * @create 2021-10-25-21:18
 */
public class UdpEchoServer {
    private DatagramSocket socket = null;

    // port表示端口号
    // 服务器在启动的时候，需要关联（绑定）一个端口号
    // 收到数据的时候，就会根据这个端口号来决定把数据交给那个进程
    // 虽然此处 port 写的是 int，但是实际上端口号是一个两个字节的无符号整数
    // 范围 0 ~ 65535
    public UdpEchoServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    // 通过这个方法来启动服务器
    public void start() throws IOException {
        System.out.println("服务器启动!");
        while (true) {
            // 1. 读取请求,当前服务器不知道客户啥时候发来请求,此时 receive() 也会阻塞等待
            //  如果此时有请求过来, receive 就会返回
            //  参数 DatagramPacket 是一个输出型参数,socket 中读到的数据会设置到这个参数的对象中
            //  DatagramPacket 在构造的时候,需要指定一个缓冲区(就是一段内存空间,需要使用 byte[])
            DatagramPacket requestPacket = new DatagramPacket(new byte[4096],4096);
            socket.receive(requestPacket);
            // 把 requestPacket 对象里面的内容读取出来,作为一个字符串
            String request = new String(requestPacket.getData(),0,requestPacket.getLength());
            // 2.根据请求来计算响应
            String response = process(request);
            // 3.把响应写到客户端,这时候也需要构造一个 DatagramPacket
            //   此时给 DatagramPacket 中设置的长度,必须是"字节的个数"
            //   如果直接取 response.length() 此处得到的是,字符串的长度,也就是"字符的个数"
            //   当前的 requestPacket 在构造的时候,还需要指定这个包发给谁
            //   其实发送给的目标,就是发请求的一方
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(), response.getBytes().length,
                    requestPacket.getSocketAddress());
            socket.send(responsePacket);
            // 4.加上日志打印
            //   %d 表示要打印一个有符号十进制的整数, %s 表示要打印一个字符串
            String log = String.format("[%s:%d] req: %s; resp: %s",requestPacket.getAddress().toString(),
                    requestPacket.getPort(),request,response);
            System.out.println(log);
        }
    }

    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        UdpEchoServer server = new UdpEchoServer(8090);
        server.start();
    }

}
