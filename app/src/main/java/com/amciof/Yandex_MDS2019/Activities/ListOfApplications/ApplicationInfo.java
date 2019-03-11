package com.amciof.Yandex_MDS2019.Activities.ListOfApplications;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import java.util.Comparator;


public class ApplicationInfo {

    private CharSequence label;
    private CharSequence packageName;
    private Drawable icon;
    private long firstInstallTime;
    private long numberLaunch;

    private Intent launchIntent;
    private Intent settingsIntent;
    private Intent deleteIntent;

    public ApplicationInfo(
            CharSequence label,
            CharSequence packageName,
            Drawable icon,
            long firstInstallTime,
            long numberLaunch,
            Intent launchIntent,
            Intent settingsIntent,
            Intent deleteIntent
    ) {
        this.label = label;
        this.packageName = packageName;
        this.icon = icon;
        this.firstInstallTime = firstInstallTime;
        this.numberLaunch = numberLaunch;
        this.launchIntent = launchIntent;
        this.settingsIntent = settingsIntent;
        this.deleteIntent = deleteIntent;
    }

    public CharSequence getLabel() {
        return label;
    }

    public void setLabel(CharSequence label) {
        this.label = label;
    }

    public CharSequence getPackageName() {
        return packageName;
    }

    public void setPackageName(CharSequence packageName) {
        this.packageName = packageName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public long getFirstInstallTime() {
        return firstInstallTime;
    }

    public void setFirstInstallTime(long firstInstallTime) {
        this.firstInstallTime = firstInstallTime;
    }

    public long getNumberLaunch() {
        return numberLaunch;
    }

    public void setNumberLaunch(long numberLaunch) {
        this.numberLaunch = numberLaunch;
    }

    public Intent getLaunchIntent() {
        return launchIntent;
    }

    public Intent getSettingsIntent() {
        return settingsIntent;
    }

    public Intent getDeleteIntent() {
        return deleteIntent;
    }

    public static final Comparator<ApplicationInfo> ASCENDING_BY_LABEL = new Comparator<ApplicationInfo>() {
        @Override
        public int compare(ApplicationInfo o1, ApplicationInfo o2) {
            String first = o1.label.toString();
            String second = o2.label.toString();
            return first.compareToIgnoreCase(second);
        }
    };

    public static final Comparator<ApplicationInfo> ASCENDING_BY_DATE = new Comparator<ApplicationInfo>() {
        @Override
        public int compare(ApplicationInfo o1, ApplicationInfo o2) {
            return o1.firstInstallTime < o2.firstInstallTime ? 1 :
                    o1.firstInstallTime > o2.firstInstallTime ? -1 : 0;
        }
    };

    public static final Comparator<ApplicationInfo> ASCENDING_BY_LAUNCHES = new Comparator<ApplicationInfo>() {
        @Override
        public int compare(ApplicationInfo o1, ApplicationInfo o2) {
            return o1.numberLaunch < o2.numberLaunch ? 1 :
                    o1.numberLaunch > o2.numberLaunch ? -1 : 0;
        }
    };

    public static final Comparator<ApplicationInfo> DESCENDING_BY_LABEL = new Comparator<ApplicationInfo>() {
        @Override
        public int compare(ApplicationInfo o1, ApplicationInfo o2) {
            String first = o1.label.toString();
            String second = o2.label.toString();
            return second.compareToIgnoreCase(first);
        }
    };

    public static final Comparator<ApplicationInfo> DESCENDING_BY_DATE = new Comparator<ApplicationInfo>() {
        @Override
        public int compare(ApplicationInfo o1, ApplicationInfo o2) {
            return o1.firstInstallTime < o2.firstInstallTime ? -1 :
                    o1.firstInstallTime > o2.firstInstallTime ? 1 : 0;
        }
    };

    public static final Comparator<ApplicationInfo> DESCENDING_BY_LAUNCHES = new Comparator<ApplicationInfo>() {
        @Override
        public int compare(ApplicationInfo o1, ApplicationInfo o2) {
            return o1.numberLaunch < o2.numberLaunch ? -1 :
                    o1.numberLaunch > o2.numberLaunch ? 1 : 0;
        }
    };

}
