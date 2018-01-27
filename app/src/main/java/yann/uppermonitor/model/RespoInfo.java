package yann.uppermonitor.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 主页仓库信息
 * Created by yayun.xia on 2018/1/27.
 */

public class RespoInfo implements Parcelable {
    public String name;
    public String temperature;
    public String day;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.temperature);
        dest.writeString(this.day);
    }

    public RespoInfo() {
    }

    protected RespoInfo(Parcel in) {
        this.name = in.readString();
        this.temperature = in.readString();
        this.day = in.readString();
    }

    public static final Parcelable.Creator<RespoInfo> CREATOR = new Parcelable.Creator<RespoInfo>() {
        @Override
        public RespoInfo createFromParcel(Parcel source) {
            return new RespoInfo(source);
        }

        @Override
        public RespoInfo[] newArray(int size) {
            return new RespoInfo[size];
        }
    };
}
