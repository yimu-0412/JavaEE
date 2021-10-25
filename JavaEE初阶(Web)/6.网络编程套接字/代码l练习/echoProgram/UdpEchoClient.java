package echoProgram;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * @author wangyimu
 * @Program 简单的回显程序
 * @create 2021-10-25-21:17
 */
public class UdpEchoClient {
    private DatagramSocket socket  = null;
    private String serverIp;
    private int serverPort;

    public UdpEchoClient(String serverIp, int serverPort) throws SocketException {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.socket = new DatagramSocket();
    }

    public void start() throws IOException {
        while (true) {
            Scanner scan = new Scanner(System.in);
            // 1.从标准输入一个数据
            System.out.println("->");
            String request = scan.nextLine();
            if(request .equals("exit")){
                System.out.println("exit!");
                return;
            }
            // 2.把字符串构成一个 UDP 请求,并发送数据
            //   这个 DatagramPacket 中,既要包含具体的数据,又要包含这个数据发给谁
            DatagramPacket requestPacket = new DatagramPacket(request.getBytes(),request.getBytes().length,
                    InetAddress.getByName(serverIp),serverPort);
            socket.send(requestPacket);
            // 3.尝试从服务器获取响应
            DatagramPacket responsePacket = new DatagramPacket(new byte[4096],4096);
            socket.receive(responsePacket);
            String response = new String(responsePacket.getData(),0,responsePacket.getLength());
            // 4.显示这个结果
            String log = String.format("req: %s; resp: %s",request,response);
            System.out.println(log);
        }
    }

    public static void main(String[] args) throws IOException {
        UdpEchoClient client = new UdpEchoClient("127.0.0.1",8090);
        client.start();
    }
}
