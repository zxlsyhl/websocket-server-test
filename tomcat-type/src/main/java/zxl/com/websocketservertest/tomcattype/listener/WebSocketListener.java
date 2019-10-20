package zxl.com.websocketservertest.tomcattype.listener;

import zxl.com.websocketservertest.tomcattype.thread.SendMstThread;
import zxl.com.websocketservertest.tomcattype.thread.WebSocketUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class WebSocketListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        new SendMstThread(WebSocketUtil.webSocketDMTMap1,1).start();
        new SendMstThread(WebSocketUtil.webSocketDMTMap2,2).start();
        new SendMstThread(WebSocketUtil.webSocketDMTMap3,3).start();
        new SendMstThread(WebSocketUtil.webSocketDMTMap4,4).start();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("contextDestroyed");
    }
}
