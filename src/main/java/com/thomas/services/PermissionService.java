package com.thomas.services;

import com.thomas.dao.RoleDao;
import com.thomas.dao.UserDao;
import com.thomas.dao.model.UserPermissionView;

import java.util.ArrayList;
import java.util.List;

public class PermissionService {
    RoleDao roleDao;
    UserDao userDao;

    public PermissionService() {
        roleDao = new RoleDao();
        userDao = new UserDao();
    }

    public boolean checkPermission(String resource, int userId, String permission) {
        return roleDao.checkPermission(resource, userId, permission);
    }

    public boolean promote(int userId) {
        return roleDao.promote(userId);
    }

    public boolean demote(int userId) {
        return roleDao.demote(userId);
    }

    public List<UserPermissionView> getAll() {
        List<UserPermissionView> userPermissionViews = new ArrayList<>();
        List<Integer> userIdWithPermission = roleDao.getAllUserWithPermissions();
        for (Integer userId : userIdWithPermission) {
            userPermissionViews.add(new UserPermissionView(userId, null, null, null));
        }
        for (UserPermissionView v : userPermissionViews) {
            v.setPermissions(roleDao.getPermissions(v.getUserid()));
            v.setResources(roleDao.getResources(v.getUserid()));
            v.setUserEmail(userDao.findUserEmail(null, v.getUserid()).getEmail());
            v.setGroupName(roleDao.getGroupName(v.getUserid()));
        }

        return userPermissionViews;
    }
}
