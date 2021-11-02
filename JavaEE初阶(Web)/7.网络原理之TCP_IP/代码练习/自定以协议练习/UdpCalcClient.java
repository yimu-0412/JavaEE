package calcExer;



import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * @author wangyimu
 * @Program 自定义协议练习---客户端
 * 请求：字符串，第一个操作数 ； 第二个操作数  ； 运算符
 * 响应: 字符串 计算机结果
 * @create 2021-11-02-22:18
 */
public class UdpCalcClient {
    private DatagramSocket socket = null;
    private String serverIP;
    private int serverPort;

    public UdpCalcClient(String serverIP, int serverPort) throws SocketException {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.socket = new DatagramSocket();
    }
    public void start() throws IOException {
        Scanner scan = new Scanner(System.in);
        while(true){
            // 1.让用户进行输入
            System.out.println("请输入操作数 num1:");
            int num1 = scan.nextInt();
            System.out.println("请输入操作数 num2:" );
            int num2 = scan.nextInt();
            System.out.println("请输入运算符(+ - * /):");
            String operator = scan.next();
            // 2.构造并发送请求
            String request = num1 + ";" + num2 + ";" + operator;
            DatagramPacket requestPacket = new DatagramPacket(request.getBytes(),request.getBytes().length,
                    InetAddress.getByName(serverIP),serverPort);
            socket.send(requestPacket);
            // 2.尝试连接服务器的响应
            DatagramPacket responsePacket = new DatagramPacket(new byte[4096],4096);
            socket.receive(responsePacket);
            String response = new String(responsePacket.getData(),0,responsePacket.getLength());
            // 4.显示这个结果
            System.out.println("计算结果为：" + response);
        }
    }

    public static void main(String[] args) throws IOException {
        UdpCalcClient client = new UdpCalcClient("127.0.0.1",9090);
        client.start();
    }
}
