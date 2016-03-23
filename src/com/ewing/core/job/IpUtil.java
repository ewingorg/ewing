package com.ewing.core.job;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ip工具类
 * 
 * @author tanson lam
 * @createDate 2015年2月27日
 * 
 */
public class IpUtil {

    /**
     * 检验是否ip地址
     * 
     * @param addr
     * @return
     */
    public static boolean validateIpAddress(String addr) {
        if (addr.length() < 7 || addr.length() > 15 || "".equals(addr)) {
            return false;
        }

        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pat = Pattern.compile(rexp);
        Matcher mat = pat.matcher(addr);
        boolean ipAddress = mat.find();
        return ipAddress;
    }

    /**
     * 获取本地ip地址
     * 
     * @return
     * @throws SocketException
     */
    public static String getLocalIpAddress() throws SocketException {
        Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
        Enumeration<InetAddress> addresses;
        while (en.hasMoreElements()) {
            NetworkInterface networkinterface = en.nextElement();
            addresses = networkinterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                String ip = addresses.nextElement().getHostAddress();
                if (!validateIpAddress(ip) || ip.equals("127.0.0.1"))
                    continue;
                return ip;
            }
        }
        return null;
    }

    /**
     * 获取本地ip地址
     * 
     * @return
     * @throws SocketException
     */
    public static String getLocalIpAddress(String networkPrefix) throws SocketException {
        Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
        Enumeration<InetAddress> addresses;
        while (en.hasMoreElements()) {
            NetworkInterface networkinterface = en.nextElement();
            addresses = networkinterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                String ip = addresses.nextElement().getHostAddress();
                if (!validateIpAddress(ip) || ip.equals("127.0.0.1"))
                    continue;
                if (ip.startsWith(networkPrefix))
                    return ip;
            }
        }
        return null;
    }

    public static void main(String[] args) throws UnknownHostException, SocketException {
        System.out.println(getLocalIpAddress());
    }

}
