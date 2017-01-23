package cn.hxgroup.www.hhu.business.tcp;

import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by CXJ on 2016/5/17.
 */
public class TcpClient
{
    private static final String TAG = "TcpClient";
    private Socket mSocket;
    private DataInputStream mDataInputStream;
    private DataOutputStream mDataOutputStream;

    private String mServerAddress;
    private int mPort;
    private boolean mReadFlag;

    public TcpClient(String serverAddress, int port)
    {
        mServerAddress = serverAddress;
        mPort = port;
    }

    public synchronized void connect()
    {
        try
        {
            Socket socket = new Socket(mServerAddress, mPort);
            mDataInputStream = new DataInputStream(socket.getInputStream());
            mDataOutputStream = new DataOutputStream(socket.getOutputStream());
            Log.e(TAG, "------------------连接成功");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public synchronized void close()
    {
        closeInputStream();
        closeOutputStream();
        closeSocket();
    }

    private void closeInputStream()
    {
        try
        {
            if (mDataInputStream != null)
            {
                mDataInputStream.close();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        mDataInputStream = null;
    }

    private void closeOutputStream()
    {
        try
        {
            if (mDataOutputStream != null)
            {
                mDataOutputStream.close();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        mDataOutputStream = null;
    }

    private void closeSocket()
    {
        try
        {
            mSocket.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        mSocket = null;
    }

    class ReadThread extends Thread
    {
        private static final int READ_MODE_HEADER = 0;//读取头字段
        private static final int READ_MODE_BODY = 1;//读取内容
        private static final int READ_MODE_IDLE = 2;//解析出错时，中间容错数、

        private int mReadMode = 0;

        @Override
        public void run()
        {
            int len = 0;
            byte[] buf = new byte[2];
            try
            {
                while (mReadFlag && (len = mDataInputStream.read(buf)) > 0)
                {
                }
            } catch (IOException e)
            {
                e.printStackTrace();
                close();
            }
        }
    }
}
