package yann.uppermonitor.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * 仓库信息
 * Created by yayun.xia on 2018/1/27.
 */

public class RespoData implements Parcelable {
    public String co2;
    public String o2;

    public ArrayList<SingleRespoInfo> respoInfos;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.co2);
        dest.writeString(this.o2);
        dest.writeList(this.respoInfos);
    }

    public RespoData() {
    }

    protected RespoData(Parcel in) {
        this.co2 = in.readString();
        this.o2 = in.readString();
        this.respoInfos = new ArrayList<SingleRespoInfo>();
        in.readList(this.respoInfos, SingleRespoInfo.class.getClassLoader());
    }

    public static final Parcelable.Creator<RespoData> CREATOR = new Parcelable.Creator<RespoData>() {
        @Override
        public RespoData createFromParcel(Parcel source) {
            return new RespoData(source);
        }

        @Override
        public RespoData[] newArray(int size) {
            return new RespoData[size];
        }
    };
}
