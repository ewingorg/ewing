package com.ewing.core.job;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 网络通讯服务类
 * 
 * @author tanson lam
 * @createDate 2015年3月2日
 * 
 */
public class NetServer implements Runnable {
    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    Thread listener = null;

    public static void main(String[] args) throws IOException {
        new NetServer(11100);
    }

    public NetServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);

        listener = new Thread(this);

        listener.start();
    }

    public void run() {
        boolean bError = false;
        while (!bError) {
            try {
                clientSocket = serverSocket.accept();
                synchronized (clientSocket) {
                    InputStream ins = clientSocket.getInputStream();
                    try {
                        StringBuffer out = new StringBuffer();
                        byte[] b = new byte[4096];
                        for (int n; (n = ins.read(b)) != -1;) {
                            out.append(new String(b, 0, n));
                        }
                        System.out.println(out.toString());
                    } catch (Exception e) {
                        System.err.println("Exception in close, " + e.getMessage());
                    } finally {
                        if (ins != null)
                            ins.close();
                    }

                }
            } catch (IOException e) {
                bError = true;
            }
        }

        try {
            serverSocket.close();
        } catch (Exception e) {
            JobLogger.error("Exception in close, " + e.getMessage());
        }
    }

    public void disconnect() {
        if (clientSocket == null) {
            return;
        }
        synchronized (clientSocket) {
            try {
                clientSocket.notify();
            } catch (Exception e) {
                JobLogger.error("Exception in notify, " + e.getMessage());
            }
        }
    }

    public void stop() {
        listener.interrupt();
        try {
            serverSocket.close();
        } catch (Exception e) {
            JobLogger.error("Exception in close, " + e.getMessage());
        }
    }

    public InputStream getInputStream() throws IOException {
        if (clientSocket != null) {
            return (clientSocket.getInputStream());
        } else {
            return (null);
        }
    }

    public OutputStream getOutputStream() throws IOException {
        if (clientSocket != null) {
            return (clientSocket.getOutputStream());
        } else {
            return (null);
        }
    }
}
