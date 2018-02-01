package yann.uppermonitor.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yayun.xia on 2018/2/1.
 */

public class singleRespoInfo implements Parcelable {
    public String date;
    public String name;
    public String day;
    public String temperature;
    public String temperatureSet;
    public String temperatureLimit;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeString(this.name);
        dest.writeString(this.day);
        dest.writeString(this.temperature);
        dest.writeString(this.temperatureSet);
        dest.writeString(this.temperatureLimit);
    }

    public singleRespoInfo() {
    }

    protected singleRespoInfo(Parcel in) {
        this.date = in.readString();
        this.name = in.readString();
        this.day = in.readString();
        this.temperature = in.readString();
        this.temperatureSet = in.readString();
        this.temperatureLimit = in.readString();
    }

    public static final Parcelable.Creator<singleRespoInfo> CREATOR = new Parcelable.Creator<singleRespoInfo>() {
        @Override
        public singleRespoInfo createFromParcel(Parcel source) {
            return new singleRespoInfo(source);
        }

        @Override
        public singleRespoInfo[] newArray(int size) {
            return new singleRespoInfo[size];
        }
    };
}
