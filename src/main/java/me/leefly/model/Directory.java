package me.leefly.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/7/17.
 */
public class Directory implements Serializable{

    private static final long serialVersionUID = -4129643459653846210L;

    private Integer id;

    private String name;

    private String resource;

    private Integer parent;

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

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }
}
