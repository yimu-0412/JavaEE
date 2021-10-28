package echoTcpProgram;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author wangyimu
 * @Program 使用线程实现简单的 TCP 回显程序
 * @create 2021-10-28-22:04
 */
public class TcpThreadEchoServer {
    private ServerSocket listenSocken = null;

    public TcpThreadEchoServer(int port) throws IOException {
        this.listenSocken = new ServerSocket(port);
    }

    public void Start() throws IOException {
        System.out.println("服务器启动!");
        while(true){
            // 在这个代码中,通过创建线程,能够保证 accept() 调用完毕之后,就能够立即再次调用 accept()
            Socket clientSocket = listenSocken.accept();
            // 创建一个线程来给这个客户端提供服务
            Thread t = new Thread(){
                @Override
                public void run() {
                    try {
                        processConnection(clientSocket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
        }
    }
    private void processConnection(Socket clientSocket) throws IOException {
        String log = String.format("[%s;%d] 客户端上线!",clientSocket.getInetAddress().toString(),
                clientSocket.getPort());
        System.out.println(log);
        try(InputStream is = clientSocket.getInputStream();
            OutputStream os = clientSocket.getOutputStream()){
            while(true){
                // 1.读取请求并解析
                Scanner scan = new Scanner(is);
                while(!scan.hasNext()){
                    log = String.format("[%s;%d] 客户端下线!", clientSocket.getInetAddress().toString(),
                            clientSocket.getPort());
                    System.out.println(log);
                    break;
                }
                String request = scan.next();

                // 2.根据请求计算响应
                String response = process(request);
                // 3.把响应写给客户端
                PrintWriter writer = new PrintWriter(os);
                writer.println(response);
                writer.flush();
                log = String.format("[%s;%d] req: %s,resp: %s",clientSocket.getInetAddress(),
                        clientSocket.getPort(),request,response);
                System.out.println(log);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            clientSocket.close();
        }

    }

    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        TcpThreadEchoServer server = new TcpThreadEchoServer(9090);
        server.Start();
    }
}
