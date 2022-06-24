package com.juntai.disabled.basecomponent.base;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class BaseResult implements Parcelable {


    public int code;
    public String msg;
    public String message;
    public List<String> filePaths;
    /**
     * errorData : {"errorMsg":"昵称必须是2-10位字符"}
     */

    private ErrorDataBean errorData;

    public static final Creator<BaseResult> CREATOR = new Creator<BaseResult>() {
        @Override
        public BaseResult createFromParcel(Parcel in) {
            return new BaseResult(in);
        }

        @Override
        public BaseResult[] newArray(int size) {
            return new BaseResult[size];
        }
    };

    public List<String> getFilePaths() {
        if (filePaths == null) {
            return new ArrayList<>();
        }
        return filePaths;
    }


    public void setFilePaths(List<String> filePaths) {
        this.filePaths = filePaths;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? "" : msg;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message == null ? "" : message;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    public ErrorDataBean getErrorData() {
        return errorData;
    }

    public void setErrorData(ErrorDataBean errorData) {
        this.errorData = errorData;
    }

    public static class ErrorDataBean implements Parcelable {
        /**
         * errorMsg : 昵称必须是2-10位字符
         */

        private String errorMsg;

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.errorMsg);
        }

        public ErrorDataBean() {
        }

        protected ErrorDataBean(Parcel in) {
            this.errorMsg = in.readString();
        }

        public static final Creator<ErrorDataBean> CREATOR = new Creator<ErrorDataBean>() {
            @Override
            public ErrorDataBean createFromParcel(Parcel source) {
                return new ErrorDataBean(source);
            }

            @Override
            public ErrorDataBean[] newArray(int size) {
                return new ErrorDataBean[size];
            }
        };
    }

    public BaseResult() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeString(this.msg);
        dest.writeString(this.message);
        dest.writeStringList(this.filePaths);
        dest.writeParcelable(this.errorData, flags);
    }

    protected BaseResult(Parcel in) {
        this.code = in.readInt();
        this.msg = in.readString();
        this.message = in.readString();
        this.filePaths = in.createStringArrayList();
        this.errorData = in.readParcelable(ErrorDataBean.class.getClassLoader());
    }

}
