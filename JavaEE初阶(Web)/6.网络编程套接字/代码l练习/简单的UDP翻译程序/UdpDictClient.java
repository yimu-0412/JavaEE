package dictProgram;

import sun.applet.Main;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

/**
 * @author wangyimu
 * @Program 简单的 UDP 翻译程序
 * @create 2021-10-26-20:17
 */
public class UdpDictClient {
    private DatagramSocket socket = null;
    private String serverIp;
    private int serverPort;

    public UdpDictClient(String serverIp, int serverPort) throws SocketException {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.socket = new DatagramSocket();
    }

    public void start() throws IOException {
        Scanner scan = new Scanner(System.in);
        while(true){
            // 1.读取输入数据
            System.out.println("->");
            String request = scan.next();
            if(request.equals("exit")){
                System.out.println("goodbye!");
                return;
            }
            // 2.构造请求并发给服务器
            DatagramPacket requestPacket = new DatagramPacket(request.getBytes(),request.getBytes().length,
                    InetAddress.getByName(serverIp),serverPort);
            socket.send(requestPacket);

            // 3. 从服务器读取响应
            DatagramPacket reqponsePacket = new DatagramPacket(new byte[4096],4096);
            socket.receive(reqponsePacket);
            String response = new String(reqponsePacket.getData(),0,reqponsePacket.getLength());

            // 4.把数据显示给用户
            String log = String.format("req: %s; resp: %s",request,response);
            System.out.println(log);
        }
    }

    public static void main(String[] args) throws IOException {
        UdpDictClient client = new UdpDictClient("127.0.0.1",9090);
        client.start();
    }
}
