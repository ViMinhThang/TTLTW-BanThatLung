package com.thomas.dao;

import com.thomas.dao.db.JDBIConnect;

import java.util.List;

public class RoleDao {
    public boolean checkPermission(String resource, int userId, String permission) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT p.permissionName FROM Permissions p " + "JOIN groupPermissions gp ON p.id = gp.permissionId " + "JOIN userGroups ug ON gp.groupId = ug.groupId " + "JOIN users u ON ug.userId = u.id " + "JOIN resources r ON gp.resourceId = r.id " + "WHERE u.id = :id AND r.resourceName = :resource AND p.permissionName = :permission";
            String result = h.createQuery(sql).bind("id", userId).bind("resource", resource).bind("permission", permission).mapTo(String.class).findFirst().orElse(null);
            return result != null;
        });
    }

    public boolean promote(int userId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "UPDATE  userGroups SET groupId =groupId-1 WHERE userId=:userId";
            return h.createUpdate(sql).bind("userId", userId).execute() > 0;
        });
    }

    public boolean demote(int userId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "UPDATE  userGroups SET groupId =groupId+1 WHERE userId=:userId";
            return h.createUpdate(sql).bind("userId", userId).execute() > 0;
        });
    }

    public List<String> getPermissions(int userId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = " SELECT DISTINCT p.permissionName FROM Permissions p JOIN GroupPermissions gp ON p.id = gp.permissionId " + "JOIN UserGroups ug ON gp.groupId = ug.groupId " + "WHERE ug.userId = :userId";

            return h.createQuery(sql).bind("userId", userId).mapTo(String.class).list();
        });
    }

    public List<String> getResources(int userId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = " SELECT DISTINCT r.resourceName FROM Resources r JOIN GroupPermissions gp ON r.id = gp.resourceId " + "JOIN UserGroups ug ON gp.groupId = ug.groupId " + "WHERE ug.userId = :userId";

            return h.createQuery(sql).bind("userId", userId).mapTo(String.class).list();
        });
    }

    public String getGroupName(int userId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = " SELECT DISTINCT g.groupName FROM `groups` g JOIN GroupPermissions gp ON g.id = gp.groupId " + "JOIN UserGroups ug ON gp.groupId = ug.groupId " + "WHERE ug.userId = :userId";

            return h.createQuery(sql).bind("userId", userId).mapTo(String.class).findFirst().orElse(null);
        });
    }

    public List<Integer> getAllUserWithPermissions() {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT DISTINCT ug.userId FROM UserGroups ug JOIN GroupPermissions gp ON ug.groupId = gp.groupId";

            return h.createQuery(sql)
                    .mapTo(Integer.class)
                    .list();
        });
    }

}
