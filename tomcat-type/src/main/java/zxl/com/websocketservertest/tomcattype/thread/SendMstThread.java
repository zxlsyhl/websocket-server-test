package zxl.com.websocketservertest.tomcattype.thread;

import zxl.com.websocketservertest.tomcattype.server.WebSocketDMT;

import java.io.IOException;
import java.util.Map;


public class SendMstThread extends Thread{
    private Map<String,WebSocketDMT> webSocketDMTMap;
    private int lockId;

    public int getLockId() {
        return lockId;
    }

    public void setLockId(int lockId) {
        this.lockId = lockId;
    }

    public Map<String, WebSocketDMT> getWebSocketDMTMap() {
        return webSocketDMTMap;
    }

    public void setWebSocketDMTMap(Map<String, WebSocketDMT> webSocketDMTMap) {
        this.webSocketDMTMap = webSocketDMTMap;
    }

    @Override
    public void run() {
        while (true){
            int locknow = 0;
            switch (lockId){
                case 1:
//                    locknow = WebSocketUtil.getLock1();
                    synchronized (WebSocketUtil.lock1){
                        doSomething();
                    }
                    break;
                case 2:
                    synchronized (WebSocketUtil.lock2){
                        doSomething();
                    }
                    break;
                case 3:
                    synchronized (WebSocketUtil.lock3){
                        doSomething();
                    }
                    break;
                case 4:
                    synchronized (WebSocketUtil.lock4){
                        doSomething();
                    }
                    break;
            }
        }
    }

    public void doSomething(){
        for(Map.Entry<String, WebSocketDMT> entity : webSocketDMTMap.entrySet()){
            try {
                if("0000".equals(entity.getKey())){
                    StringBuilder sb = new StringBuilder("连接情况:");
                    sb.append(System.currentTimeMillis());
                    sb.append("；管道1：").append(WebSocketUtil.webSocketDMTMap1.size());
                    sb.append("；管道2：").append(WebSocketUtil.webSocketDMTMap2.size());
                    sb.append("；管道3：").append(WebSocketUtil.webSocketDMTMap3.size());
                    sb.append("；管道4：").append(WebSocketUtil.webSocketDMTMap4.size());
                    entity.getValue().sendMessage(sb.toString());
                }
                //entity.getValue().sendMessage("222");
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    public SendMstThread() {
    }

    public SendMstThread(Map<String, WebSocketDMT> webSocketDMTMap) {
        this.webSocketDMTMap = webSocketDMTMap;
    }

    public SendMstThread(Map<String, WebSocketDMT> webSocketDMTMap, int lockId) {
        this.webSocketDMTMap = webSocketDMTMap;
        this.lockId = lockId;
    }

    public static void main(String[] args) {
        int i =0;
        switch (i){
            case 0:
                System.out.println(i);
                break;
            case 1:
                System.out.println(i);
                break;
        }
        System.out.println("结束");
    }
}
