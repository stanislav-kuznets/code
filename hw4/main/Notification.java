package ru.otus.skuznets;

import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;

import com.sun.management.GarbageCollectionNotificationInfo;

import java.util.ArrayList;
import java.util.List;

public class Notification implements NotificationListener {

    private static int minorTotalCleanUp;
    private static int majorTotalCleanUp;
    private static int minorTotalDuration;
    private static int majorTotalDuration;

    private List<GCInfo> notifications = new ArrayList<GCInfo>();

    public void handleNotification(javax.management.Notification notification, Object handback) {
        GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
        if (info.getGcAction().equals("end of minor GC")) {
            long minorDuration = info.getGcInfo().getDuration();
            minorTotalCleanUp ++;
            minorTotalDuration += minorDuration;
        } else {
            long majorDuration = info.getGcInfo().getDuration();
            majorTotalCleanUp++;
            majorTotalDuration += majorDuration;
        }
        notifications.add(new GCInfo(info.getGcName(), info.getGcInfo().getStartTime(), info.getGcInfo().getEndTime(), info.getGcInfo().getDuration()));
    }
    public List<GCInfo> getNotifications() {
        return notifications;
    }
    public static int getMinorTotalCleanUp() {
        return minorTotalCleanUp;
    }
    public static int getMinorTotalDuration() {
        return minorTotalDuration;
    }
    public static int getMajorTotalCleanUp() {
        return majorTotalCleanUp;
    }
    public static int getMajorTotalDuration() {
        return majorTotalDuration;
    }
}


