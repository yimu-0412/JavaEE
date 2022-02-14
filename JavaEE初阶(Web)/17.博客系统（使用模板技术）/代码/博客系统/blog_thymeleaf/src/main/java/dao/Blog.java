package dao;

import java.sql.Timestamp;

/**
 * @author wangyimu
 * @Program 2022
 * @create 2022-02-13-11:51
 */
public class Blog {
    private int blogId;
    private String title;
    private String content;
    // 此处除了使用 TimeStamp 这个类型之外，还可以使用 java.sql.Data
    // 但是这个 Data 类只能表示日期，但是不能表示时分秒
    // mysql 里表示时间日期，除了使用 dateTime/timestamp 之外，还可以使用字符串
    private Timestamp postTime;
    private int userId;

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getPostTime() {
        return postTime;
    }

    public void setPostTime(Timestamp postTime) {
        this.postTime = postTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "blogId=" + blogId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", postTime=" + postTime +
                ", userId=" + userId +
                '}';
    }
}
