package com.dyh.android.winehall.entity.response;

import com.dyh.android.winehall.entity.BaseHttpResponseBean;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/9 0009 10:13
 * description:
 */
public class CourseCommentItemResponseBean extends BaseHttpResponseBean {

    /**
     * user_id : 2020073116125700001
     * content : 好评，好东西，666
     * score : 3
     * created_at : 2020-09-02 17:34:22
     * user : {"user_id":"2020073116125700001","avatar_show":"https://luoding-test.oss-cn-beijing.aliyuncs.com/manual/default_avatar.png","nickname":"Poised_flw"}
     */

    private String user_id;
    private String content;
    private String score;
    private String created_at;
    private UserBean user;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * user_id : 2020073116125700001
         * avatar_show : https://luoding-test.oss-cn-beijing.aliyuncs.com/manual/default_avatar.png
         * nickname : Poised_flw
         */

        private String user_id;
        private String avatar_show;
        private String nickname;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getAvatar_show() {
            return avatar_show;
        }

        public void setAvatar_show(String avatar_show) {
            this.avatar_show = avatar_show;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
