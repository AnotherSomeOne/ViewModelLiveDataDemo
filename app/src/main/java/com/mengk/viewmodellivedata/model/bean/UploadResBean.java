package com.mengk.viewmodellivedata.model.bean;

/**
 * Desc
 * Date 2019/11/2
 * author mengk
 */
public class UploadResBean {

    /**
     * code : 200
     * msg : 成功
     * data : {"imgUrl":"http://sta.5yqz2.com/static/avatar/fb977f1e5f83e3e5edab09cd3671c1d7.jpg","imgPath":"/static/avatar/fb977f1e5f83e3e5edab09cd3671c1d7.jpg"}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * imgUrl : http://sta.5yqz2.com/static/avatar/fb977f1e5f83e3e5edab09cd3671c1d7.jpg
         * imgPath : /static/avatar/fb977f1e5f83e3e5edab09cd3671c1d7.jpg
         */

        private String imgUrl;
        private String imgPath;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getImgPath() {
            return imgPath;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }
    }
}
