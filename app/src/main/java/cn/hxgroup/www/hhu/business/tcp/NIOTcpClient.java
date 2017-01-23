package cn.hxgroup.www.hhu.business.tcp;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Created by CXJ on 2016/5/31.
 */
public class NIOTcpClient
{

    private static final String TAG = "NIPTcpClient";

    private SocketChannel mSocketChannel;
    private Selector mSelector;
    private boolean mIsConnected;
    private OnReciveListener mListener;

    public interface OnReciveListener
    {
        void onReceive(byte[] data);
    }

    public NIOTcpClient(OnReciveListener listener)
    {
        mListener = listener;
    }

    public boolean isConnected()
    {
        return mIsConnected;
    }

    public synchronized boolean connect()
    {
        if(mIsConnected)
        {
            return true;
        }
        try
        {
            Log.e(TAG, "-------------------------------- 开始连接服务器");
            mSelector = Selector.open();
            InetSocketAddress isa = new InetSocketAddress("11.11.11.254", 1000);
            //调用open静态方法创建连接到指定主机的SocketChannel
            mSocketChannel = SocketChannel.open(isa);
            //设置该sc以非阻塞方式工作
            mSocketChannel.configureBlocking(false);
            //将Socketchannel对象注册到指定Selector
            mSocketChannel.register(mSelector, SelectionKey.OP_READ);
            Log.e(TAG, "-------------------------------- 连接服务器成功");
            mIsConnected = true;
            //启动读取服务器端数据的线程
            new ClientThread().start();
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public synchronized boolean write(byte[] data)
    {
        if(!mIsConnected)
        {
            return false;
        }
        if(data == null || data.length < 20)
        {
            return false;
        }
        String s = "";
        for (byte b : data)
        {
            s += " " + Integer.toHexString(b & 0xFF);
        }
//        Log.e("Command", "------s:" + s);
        ByteBuffer buffer = ByteBuffer.wrap(data);
        try
        {
            int len = mSocketChannel.write(buffer);
            return len > 0;
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    //定义读取服务器数据的线程
    private class ClientThread extends Thread
    {
        private long mLastRead;//最近一次读到数据时间
        private ByteArrayOutputStream mArrayOutputStream = new ByteArrayOutputStream();//缓存数据

        public void run()
        {
            try
            {
                while (mSocketChannel.isConnected() && mIsConnected)
                {
                    if (mSelector.select(5) <= 0)
                    {
//                        Log.e(TAG, "select.select() <=0");
                        long thisTime = System.currentTimeMillis();
                        if (thisTime - mLastRead > 15)
                        {
                            byte[] data = mArrayOutputStream.toByteArray();
                            //15毫秒没有读取到数据认为读取到一个帧数据
                            if (data.length > 20)
                            {
                                //处理一帧数据
                                mArrayOutputStream.reset();
                                if (mListener != null)
                                {
                                    mListener.onReceive(data);
                                }
                            }
                        }
                        continue;
                    }
                    //遍历每个有可用IO操作Channel对应的SelectionKey
                    for (SelectionKey sk : mSelector.selectedKeys())
                    {
                        //删除正在处理的SelectionKey
                        mSelector.selectedKeys().remove(sk);
                        //如果该SelectionKey对应的Channel中有可读的数据
                        if (sk.isReadable())
                        {
                            //使用NIO读取channel中的数据
                            SocketChannel sc = (SocketChannel) sk.channel();
                            ByteBuffer buff = ByteBuffer.allocate(1024 * 2);
                            int len;
//                            while ((len = sc.read(buff)) > 0)
//                            {
                            len = sc.read(buff);
                            if (len <= 0)
                            {
                                throw new Exception("len <= 0");
                            }
                            mLastRead = System.currentTimeMillis();
                            mArrayOutputStream.write(buff.array(), 0, len);
                            //如果缓存中字节数组大于1024*2个字节，则处理一次
                            if (mArrayOutputStream.toByteArray().length >= 1024 * 2)
                            {
                                byte[] data = mArrayOutputStream.toByteArray();
                                mArrayOutputStream.reset();
                                if (mListener != null)
                                {
                                    mListener.onReceive(data);
                                }
                                continue;
                            }
                            Log.e(TAG, "读取数据长度：" + len);
                            byte[] arr = new byte[len];
                            System.arraycopy(buff.array(), 0, arr, 0, len);
                            buff.clear();
                            String s = "";
                            for (byte b : arr)
                            {
                                s += " " + (Integer.toHexString(b & 0xff));
                            }
//                            }
                            Log.e(TAG, "读取完成s:" + s);
                            //为下一次读取做准备
                            sk.interestOps(SelectionKey.OP_READ);
                        }
                    }
                }
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
            try
            {
                if (mSocketChannel != null)
                {
                    mSocketChannel.close();
                }
                if (mSelector != null)
                {
                    mSelector.close();
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            //表示异常引起的关闭连接
            if (mIsConnected)
            {
                mIsConnected = false;
                close();
            }
            Log.e(TAG, "读取线程结束---------------------读取线程结束");
        }
    }

    private void onReceive(final byte[] data)
    {
        new Thread()
        {
            @Override
            public void run()
            {
                BaseFrame frame = new BaseFrame(data);
                if (frame.isAvalible())
                {
                    //解析出一个合法帧
                }
            }
        }.start();

    }

    public void close()
    {
        mIsConnected = false;
        try
        {
            if (mSocketChannel != null)
            {
                mSocketChannel.close();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            if (mSelector != null)
            {
                mSelector.close();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}
