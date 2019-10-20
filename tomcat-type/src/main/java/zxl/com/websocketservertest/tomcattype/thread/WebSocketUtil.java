package zxl.com.websocketservertest.tomcattype.thread;

import zxl.com.websocketservertest.tomcattype.server.WebSocketDMT;

import java.io.IOException;
import java.util.*;

public class WebSocketUtil {
    public static volatile Map<String, WebSocketDMT> webSocketDMTMap1 = new LinkedHashMap<String, WebSocketDMT>();

    public static volatile Map<String, WebSocketDMT> webSocketDMTMap2 = new LinkedHashMap<String, WebSocketDMT>();

    public static volatile Map<String, WebSocketDMT> webSocketDMTMap3 = new LinkedHashMap<String, WebSocketDMT>();

    public static volatile Map<String, WebSocketDMT> webSocketDMTMap4 = new LinkedHashMap<String, WebSocketDMT>();

    public static Object lock1 = new Object();
    public static Object lock2 = new Object();
    public static Object lock3 = new Object();
    public static Object lock4 = new Object();

    /**
     * 判断已存在的会话是否存在当前用户
     * @param username
     * @return
     */
    public static boolean checkExists(String username){
        if(webSocketDMTMap1.containsKey(username)){
            return true;
        }
        if(webSocketDMTMap2.containsKey(username)){
            return true;
        }
        if(webSocketDMTMap3.containsKey(username)){
            return true;
        }
        if(webSocketDMTMap4.containsKey(username)){
            return true;
        }
        return false;
    }

    /**
     * 向最小回话列表中添加列
     * @param webSocketDMT
     */
    public static void addWebSocketDMT(WebSocketDMT webSocketDMT){
        String username = webSocketDMT.getUsername();
        if(!checkExists(username)){
            int[] sizes = new int[4];
            sizes[0] = webSocketDMTMap1.size();
            sizes[1] = webSocketDMTMap2.size();
            sizes[2] = webSocketDMTMap3.size();
            sizes[3] = webSocketDMTMap4.size();
            int index = 0;
            int minSize = sizes[0];
            for(int i=1;i<sizes.length;i++){
                if(sizes[i] < minSize){
                    index = i;
                    minSize = sizes[i];
                }
            }
            switch (index){
                case 0:
                    synchronized (lock1){
                        webSocketDMTMap1.put(username,webSocketDMT);
                    }
//                    getLock1();
                    break;
                case 1:
//                    getLock2();
                    synchronized (lock2){
                        webSocketDMTMap2.put(username,webSocketDMT);
                    }
                    break;
                case 2:
//                    getLock3();
                    synchronized (lock3){
                        webSocketDMTMap3.put(username,webSocketDMT);
                    }
                    break;
                case 3:
//                    getLock4();
                    synchronized (lock4){
                        webSocketDMTMap4.put(username,webSocketDMT);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 删除断开的回话列表
     * @param webSocketDMT
     */
    public static void delWebSocketDMT(WebSocketDMT webSocketDMT){
        String username = webSocketDMT.getUsername();
        if(webSocketDMTMap1.containsKey(username)){
//            getLock1();
            synchronized (lock1){
                webSocketDMTMap1.remove(username);
            }
            return;
        }
        if(webSocketDMTMap2.containsKey(username)){
//            getLock2();
//            webSocketDMTMap2.remove(username);
            synchronized (lock2){
                webSocketDMTMap2.remove(username);
            }
            return;
        }
        if(webSocketDMTMap3.containsKey(username)){
//            getLock3();
//            webSocketDMTMap3.remove(username);
            synchronized (lock3){
                webSocketDMTMap3.remove(username);
            }
            return;
        }
        if(webSocketDMTMap4.containsKey(username)){
//            getLock4();
//            webSocketDMTMap4.remove(username);
            synchronized (lock4){
                webSocketDMTMap4.remove(username);
            }
            return;
        }
    }

    public static void main(String[] args) {
        Map<String, WebSocketDMT> webSocketDMTMap1 = new LinkedHashMap<String, WebSocketDMT>();
        for(Map.Entry<String, WebSocketDMT> entity : webSocketDMTMap1.entrySet()){
            System.out.println("ss");
        }
        Map<String,String> ss = new HashMap<String, String>();
        ss.put(null,"ll");
        ss.put("33",null);
//        Map<String,String> ss = new Hashtable<String, String>();
//        ss.put(null,"ll");
//        ss.put("33",null);
    }

}
