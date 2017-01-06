package org.ychen.cms.dto;

/**
 * Created by cy on 16/12/20.
 */
public class TreeDto {
    private int id;
    private String name;
    private int isParent;

    public TreeDto() {
    }

    public TreeDto(int id, String name, int isParent) {
        this.id = id;
        this.name = name;
        this.isParent = isParent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsParent() {
        return isParent;
    }

    public void setIsParent(int isParent) {
        this.isParent = isParent;
    }
}
