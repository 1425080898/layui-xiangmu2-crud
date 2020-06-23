package com.aaa.entity;

import lombok.Data;

import java.util.List;

/*
* layui属性菜单需要的实体对象
*
* */
@Data
public class LayUiTree {

    /**
     * title : 一级2
     * id : 2
     * field :
     * spread : true
     * children : [{"title":"二级2-1","id":5,"field":"","spread":true,"children":[{"title":"三级2-1-1","id":11,"field":""},{"title":"三级2-1-2","id":12,"field":""}]},{"title":"二级2-2","id":6,"field":"","children":[{"title":"三级2-2-1","id":13,"field":""}]}]
     */

    private String title;
    private int id;
    private String field;
    private boolean spread;
    private String url;
    private boolean checked;
    private List<LayUiTree> children;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public List<LayUiTree> getChildren() {
        return children;
    }

    public void setChildren(List<LayUiTree> children) {
        this.children = children;
    }
}
