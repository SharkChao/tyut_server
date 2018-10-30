package com.winning.bean;

public class Message {
    private int id;
    private String msg_title;
    private String msg_type;
    private String msg_date;
    private String msg_imgs;
    private String msg_content;
    private int publish_person;
    private String publish_person_name;
    private String head_url;

    public String getMsg_title() {
        return msg_title;
    }

    public void setMsg_title(String msg_title) {
        this.msg_title = msg_title;
    }

    public String getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(String msg_type) {
        this.msg_type = msg_type;
    }

    public String getMsg_date() {
        return msg_date;
    }

    public void setMsg_date(String msg_date) {
        this.msg_date = msg_date;
    }

    public String getMsg_imgs() {
        return msg_imgs;
    }

    public void setMsg_imgs(String msg_imgs) {
        this.msg_imgs = msg_imgs;
    }

    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

    public int getPublish_person() {
        return publish_person;
    }

    public void setPublish_person(int publish_person) {
        this.publish_person = publish_person;
    }

    public String getPublish_person_name() {
        return publish_person_name;
    }

    public void setPublish_person_name(String publish_person_name) {
        this.publish_person_name = publish_person_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHead_url() {
        return head_url;
    }

    public void setHead_url(String head_url) {
        this.head_url = head_url;
    }
}
