package echoTcpProgram;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wangyimu
 * @Program 使用线程池实现简单的 TCP 回显程序
 * @create 2021-10-28-22:48
 */
public class TcpThreadPoolEchoServer {
    private ServerSocket listenSocken = null;

    public TcpThreadPoolEchoServer(int port) throws IOException {
        listenSocken = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器启动!");
        ExecutorService executorService = Executors.newCachedThreadPool();
        while(true){
            Socket clientSocket = listenSocken.accept();
            // 使用线程池来处理当前的 processConnection
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        processConnection(clientSocket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    private void processConnection(Socket clientSocket) throws IOException {
        // 处理一个连接,在这个连接中可能会涉及到客户端和服务器之间的交互
        String log = String.format("[%s,%d] 客户端上线!",clientSocket.getInetAddress(),
                clientSocket.getPort());
        System.out.println(log);

        try(InputStream is = clientSocket.getInputStream();
            OutputStream os = clientSocket.getOutputStream()){
            while (true) {
                Scanner scan = new Scanner(is);
                // 1. 读取请求并解析
                while(!scan.hasNext()){
                    log = String.format("[%s,%d] 客户端下线!",clientSocket.getInetAddress(),
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
                log = String.format("[%s;%d]req: %s;resp: %s",clientSocket.getInetAddress().toString(),
                        clientSocket.getPort(),request,response);
                System.out.println(log);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            clientSocket.close();
        }
    }

    public String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        TcpThreadPoolEchoServer server1 = new TcpThreadPoolEchoServer(9090);
        server1.start();
    }
}
