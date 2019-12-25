package com.mengk.viewmodellivedata.model.bean;

import java.util.List;

public class TabBean {

    private List<CustomLablesEntity> customLables;

    public void setCustomLables(List<CustomLablesEntity> customLables) {
        this.customLables = customLables;
    }

    public List<CustomLablesEntity> getCustomLables() {
        return customLables;
    }

    public class CustomLablesEntity {
        /**
         * sportType : null
         * lableType : 2
         * name : 式门
         * mediaType : 0
         * id : 1
         * jumpUrl :
         * categoryId : null
         */
        private String sportType;
        private int lableType;
        private String name;
        private int mediaType;
        private String id;
        private String jumpUrl;
        private String categoryId;

        public void setSportType(String sportType) {
            this.sportType = sportType;
        }

        public void setLableType(int lableType) {
            this.lableType = lableType;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setMediaType(int mediaType) {
            this.mediaType = mediaType;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setJumpUrl(String jumpUrl) {
            this.jumpUrl = jumpUrl;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getSportType() {
            return sportType;
        }

        public int getLableType() {
            return lableType;
        }

        public String getName() {
            return name;
        }

        public int getMediaType() {
            return mediaType;
        }

        public String getId() {
            return id;
        }

        public String getJumpUrl() {
            return jumpUrl;
        }

        public String getCategoryId() {
            return categoryId;
        }
    }
}
