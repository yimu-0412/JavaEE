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
 * @Program 简单的 TCP 回显程序--服务器
 * @create 2021-10-26-22:22
 */
public class TcpEchoServer {
    private ServerSocket listenSocket = null;

    public TcpEchoServer(int port) throws IOException {
        this.listenSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器启动!");
        while (true) {
            // UDP 的服务器进入主循环,就直接尝试 receive 读取请求
            // 但是 TCP 是连接的,先需要做的是,就是建立好连接
            // 当服务器运行的时候,当前是否有客户端建立连接,不确定
            // 如果客户端没有建立连接,accept 就会阻塞等待
            // 如果客户端建立连接了,此时的 accept 就会返回一个 Socket 对象
            // 进一步的服务器和客户端之间的交,就交给 clientSocket 来完成
            Socket clientSocket = listenSocket.accept();
            processConnection(clientSocket);
        }
    }

    private void processConnection(Socket clientSocket) throws IOException {
        // 处理一个连接,在这个连接中可能会涉及到客户端和服务器之间的交互
        String log = String.format("[%s:%d] 客户端上线!",clientSocket.getInetAddress().toString(),
                clientSocket.getPort());
        System.out.println(log);

        try (InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream()) {
            while(true){
                // 1. 读取请求并解析
                // 可以直接通过 inputStream 的 read 把数据读到一个 byte[],然后再转成一个String
                // 但是比较麻烦,还可以借助 Scanner 完成这个工作
                Scanner scan = new Scanner(inputStream);
                if(!scan.hasNext()){
                    log = String.format("[%s:%d] 客户端下线!",clientSocket.getInetAddress().toString(),
                            clientSocket.getPort());
                    System.out.println();
                    break;
                }

                String request = scan.next();
                // 2.根据请求来计算响应
                String response = process(request);
                // 3.把响应写给客户端
                PrintWriter writer = new PrintWriter(outputStream);
                writer.println(response);
                writer.flush();
                log = String.format("[%s;%d]req: %s;resp: %s",clientSocket.getInetAddress().toString(),
                clientSocket.getPort(),request,response);
                System.out.println(log);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            // 当前的 clientSocket 生命周期,不是跟随整个程序,而是和连接相关
            // 因此就需要每个连接结束,都要进行关闭
            // 否则随着连接的增多,这个 socket 文件就可能出现资源泄漏的情况
            clientSocket.close();
        }
    }

    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        TcpEchoServer server = new TcpEchoServer(9090);
        server.start();
    }
}
