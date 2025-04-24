package com.thomas.dao.model;

import java.util.List;

public class UserPermissionView {
    private int userid;
    private List<String> resources;
    private List<String> permissions;
    private String userEmail;
    private String groupName;

    public UserPermissionView(int userid, List<String> resources, List<String> permissions, String groupName) {
        this.userid = userid;
        this.resources = resources;
        this.permissions = permissions;
        this.groupName = groupName;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public List<String> getResources() {
        return resources;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
