package echoTcpProgram;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author wangyimu
 * @Program 简单的 TCP 回显程序--客户端
 * @create 2021-10-26-22:22
 */
public class TcpEchoClient {
    private String serverIP;
    private int serverPort;
    private Socket socket = null;

    public TcpEchoClient(String serverIP, int serverPort) throws IOException {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        // 让 socket 创建的同时,就和服务器进行了连接
        this.socket = new Socket(serverIP,serverPort);
    }

    public void start(){
        Scanner scan = new Scanner(System.in);

        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()) {
            while(true){
                // 1.从键盘上,读取用户输入的内容
                System.out.println("->");
                String request = scan.next();
                if(request.equals("exit")){
                    break;
                }
                // 2.把这个读取的内容构造成请求,发送给服务器
                PrintWriter writer = new PrintWriter(outputStream);
                writer.println(request);
                writer.flush();
                // 3.从服务器读取响应并解析
                Scanner responseScan = new Scanner(inputStream);
                String response = responseScan.next();
                // 4.把结果显示到界面上
                String log = String.format("req; %s; resp: %s", request, response);
                System.out.println(log);
            }
        }catch(Exception e){

        }finally {

        }
    }

    public static void main(String[] args) throws IOException {
        TcpEchoClient client = new TcpEchoClient("127.0.0.1",9090);
        client.start();
    }
}
