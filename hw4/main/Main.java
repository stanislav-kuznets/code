package ru.otus.skuznets;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
-Xms256m
-Xmx256m
-XX:MaxMetaspaceSize=256m
-XX:+UseParNewGC
*/

public class Main {
    public static void main(String[] args) throws InterruptedException {
        GCMonitor monitor = new GCMonitor();
        monitor.geGC();

        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        ses.scheduleWithFixedDelay( () -> getNotifications(monitor), 1, 1, TimeUnit.MINUTES);

        new FillArray();
    }

    private static void getNotifications(GCMonitor monitor) {

        List<GCInfo> list = monitor.getMyNotificationListener().getNotifications();

        list.forEach(System.out::println);

        System.out.println("Major total cleanUp " + Notification.getMajorTotalCleanUp() + " Major total duration " + Notification.getMajorTotalDuration());
        System.out.println("Minor total cleanUp " + Notification.getMinorTotalCleanUp() + " Minor total duration " + Notification.getMinorTotalDuration());

    }
}
