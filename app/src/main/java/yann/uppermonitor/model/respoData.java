package yann.uppermonitor.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * 仓库信息
 * Created by yayun.xia on 2018/1/27.
 */

public class respoData implements Parcelable {
    public String co2;
    public String o2;

    public ArrayList<singleRespoInfo> respoInfos;

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

    public respoData() {
    }

    protected respoData(Parcel in) {
        this.co2 = in.readString();
        this.o2 = in.readString();
        this.respoInfos = new ArrayList<singleRespoInfo>();
        in.readList(this.respoInfos, singleRespoInfo.class.getClassLoader());
    }

    public static final Parcelable.Creator<respoData> CREATOR = new Parcelable.Creator<respoData>() {
        @Override
        public respoData createFromParcel(Parcel source) {
            return new respoData(source);
        }

        @Override
        public respoData[] newArray(int size) {
            return new respoData[size];
        }
    };
}
