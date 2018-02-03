package yann.uppermonitor.common;

import android.content.res.Configuration;
import android.os.RemoteException;

/**
 * Created by yayun.xia on 2018/2/3.
 */

public abstract interface IActivityManager {
    abstract Configuration getConfiguration() throws RemoteException;

    abstract void updateConfiguration(Configuration paramConfiguration)
            throws RemoteException;
}
