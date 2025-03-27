package com.ruoyi.web.controller.api.domain;

public class Dictionary {
    private Integer id;
    private String name;
    private String key;
    private int status;

    @Override
    public String toString() {
        return "Dictionary{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", status=" + status +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Dictionary() {
    }

    public Dictionary(Integer id, String name, String key, int status) {
        this.id = id;
        this.name = name;
        this.key = key;
        this.status = status;
    }
}
