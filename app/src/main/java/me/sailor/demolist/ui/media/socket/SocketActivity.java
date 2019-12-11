package me.sailor.demolist.ui.media.socket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import me.sailor.demolist.R;

/**
 * 需要启动java socketserver
 */
public class SocketActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEditText;
    private TextView mTextView;
    private Button send, connect, close;
    private Socket mSocket;
    OutputStream outputStream;
    ExecutorService mPoolExecutor;
    private static Handler handler;
    StringBuffer buffer;
    private BufferedReader in = null;
    private PrintWriter out = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        mEditText = findViewById(R.id.edit);
        mTextView = findViewById(R.id.text);
        send = findViewById(R.id.send);
        send.setOnClickListener(this);
        connect = findViewById(R.id.connect);
        connect.setOnClickListener(this);
        close = findViewById(R.id.close);
        close.setOnClickListener(this);
        mPoolExecutor = Executors.newCachedThreadPool();
        buffer = new StringBuffer();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mTextView.append((CharSequence) msg.obj);
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send:
                mPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        String str = mEditText.getText().toString();
                        if (mSocket!=null&&mSocket.isConnected()) {
                            out.println(str);//点击按钮发送消息
                            mEditText.setText("");
                        }
                    }
                });
                break;
            case R.id.connect:
                mPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (mSocket == null) {
                                mSocket = new Socket("192.168.50.152", 8089);
                                //接收消息的流对象
                                in = new BufferedReader(new InputStreamReader(mSocket
                                        .getInputStream()));
                                //发送消息的流对象
                                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                                        mSocket.getOutputStream())), true);
                                if (mSocket.isConnected()) {
                                    while (true) {
                                        String getLine;
                                        //读取接收的信息
                                        if ((getLine = in.readLine()) != null) {
                                            getLine += "\n";
                                            Message message = new Message();
                                            message.obj = getLine;
                                            //通知UI更新
                                            handler.sendMessage(message);
                                        }
                                    }
                                }

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
            case R.id.close:
                close();
                break;
            default:

        }
    }

    @Override
    protected void onDestroy() {
        if (mSocket!=null){
            close();
        }
        super.onDestroy();
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, SocketActivity.class);
        context.startActivity(starter);
    }
    private void close() {
        mPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    out.println("exit");
                    in.close();
                    out.close();
                    mSocket.close();
                    mSocket = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
