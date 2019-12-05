package me.sailor.demolist.ui.media.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/***
 * 服务端代码，IDEA 执行
 */
public class TcpServer {
    /**
     * 端口
     */
    private static final int PORT = 8888;

    private ServerSocket server;

    private List<Socket> socketList = new ArrayList<>();

    private ExecutorService executorService = null;

    private Socket socket = null;

    /**
     * @param args
     */

    public static void main(String[] args) {
        new TcpServer();
    }

    /**
     * 构造方法 启动服务 死循环等待socket链接
     */
    public TcpServer() {
        try {
            server = new ServerSocket(PORT);
            executorService = Executors.newCachedThreadPool();
            System.out.println("服务器启动，等待客户端连接。");
            while (true) {
                //获得接入服务器的socket
                socket = server.accept();
                //加入到集合
                socketList.add(socket);
                //启动线程，监听该socket的状态
                executorService.execute(new clientService(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class clientService implements Runnable {
        private Socket socket;
        private BufferedReader in = null;
        private String message = "";

        public clientService(Socket socket) {
            this.socket = socket;
            try {
                //客服端接入就发送信息
                message = "连接成功，当前地址：" + this.socket.getInetAddress() +
                        "\n当前客服端个数：" + socketList.size()+"\n------------------------------";
                sendMessage(message);
                //获得输入流对象
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (true) {
                try {
                    if ((message=in.readLine()) != null) {
                        if (message.equals("exit")) {
                            closeSocket();
                            break;
                        } else {
                            System.out.println("客户端："+message);
                            message = "服务端：" + message;
                            sendMessage(message);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void closeSocket() throws IOException {
            socketList.remove(socket);
            in.close();
            message = "主机:" + socket.getInetAddress() + "关闭连接\n目前在线:"
                    + socketList.size();
            socket.close();
            this.sendMessage(message);
        }

        /**
         * 发送消息
         *
         * @param str
         */
        private void sendMessage(String str) {
            System.out.println(str);
            int count = socketList.size();
            for (int i = 0; i < count; i++) {
                Socket socket = socketList.get(i);
                try {
                    //创建流对象
                    PrintWriter writer = new PrintWriter(new BufferedWriter
                            (new OutputStreamWriter(socket.getOutputStream())), true);
                    //转发
                    writer.println(str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
