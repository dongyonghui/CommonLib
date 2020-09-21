package com.dyh.android.winehall.entity.response;

import com.dyh.android.winehall.entity.BaseHttpResponseBean;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/15 0015
 * description: 课节详情
 */
public class CourseLessonDetailResponseBean extends BaseHttpResponseBean {

    private String title;
    private String audition;
    private String uuid;
    private String course_id;
    private String video_id;
    private String free;
    private VideoInfoBean video_info;
    private SuperConfigBean superConfig;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAudition() {
        return audition;
    }

    public void setAudition(String audition) {
        this.audition = audition;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public VideoInfoBean getVideo_info() {
        return video_info;
    }

    public void setVideo_info(VideoInfoBean video_info) {
        this.video_info = video_info;
    }

    public SuperConfigBean getSuperConfig() {
        return superConfig;
    }

    public void setSuperConfig(SuperConfigBean superConfig) {
        this.superConfig = superConfig;
    }

    public static class VideoInfoBean {
        /**
         * video_id : 2020090912190440001
         * file_id : 5285890807335907524
         * cover : 2020/09/09/202009091218537175000763562FNZtLXntMq.png
         * duration : 3
         * size : 0
         * url : https://1251177063.vod2.myqcloud.com/fc3d2e83vodcq1251177063/1598664f5285890807335907524/0HhOYyHq5koA.mp4
         * title : 20200909test
         */

        private String video_id;
        private String file_id;
        private String cover;
        private String duration;
        private String size;
        private String url;
        private String title;

        public String getVideo_id() {
            return video_id;
        }

        public void setVideo_id(String video_id) {
            this.video_id = video_id;
        }

        public String getFile_id() {
            return file_id;
        }

        public void setFile_id(String file_id) {
            this.file_id = file_id;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class SuperConfigBean {
        /**
         * appId : AKIDPcZNyLcP7aqreD6dV8XheMp22oLeBYAX
         * fileId : 5285890807335907524
         * pSign : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhcHBJZCI6IkFLSURQY1pOeUxjUDdhcXJlRDZkVjhYaGVNcDIyb0xlQllBWCIsImZpbGVJZCI6NTI4NTg5MDgwNzMzNTkwNzUyNCwiY3VycmVudFRpbWVTdGFtcCI6MTYwMDEzODg0OCwiZXhwaXJlVGltZVN0YW1wIjoxNjAwMjI1MjQ4LCJ1cmxBY2Nlc3NJbmZvIjp7InQiOiI1ZjYxN2ZlMCIsImV4cGVyIjoxLCJybGltaXQiOiIiLCJ1cyI6InhLSXBjcW1nIn19.Zqs9dc5Q26qXXxVutzhpQSyjy2zx1poh5XfeQd77jVc
         */

        private String appId;
        private String fileId;
        private String pSign;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getFileId() {
            return fileId;
        }

        public void setFileId(String fileId) {
            this.fileId = fileId;
        }

        public String getPSign() {
            return pSign;
        }

        public void setPSign(String pSign) {
            this.pSign = pSign;
        }
    }
}
