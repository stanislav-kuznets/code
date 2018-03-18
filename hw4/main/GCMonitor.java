package ru.otus.skuznets;

import javax.management.NotificationEmitter;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.ArrayList;

public class GCMonitor {

    public static ArrayList<String> list  = new ArrayList<String>();
    private Notification myNotificationListener = new Notification();

    public void geGC() {
    List<GarbageCollectorMXBean> garbageCollectorMXBeanList = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean mxBean : garbageCollectorMXBeanList) {
        System.out.println(mxBean.getName());

        NotificationEmitter emitter = (NotificationEmitter) mxBean;
        emitter.addNotificationListener(myNotificationListener, null, null);
        }
    }

    public static ArrayList<String> getList() {
        return list;
    }

    Notification getMyNotificationListener() {
        return myNotificationListener;

    }
}
