package com.thomas.services;

import com.thomas.dao.BeltVariantDao;

import java.util.List;

public class VariantService {
    BeltVariantDao beltVariantDao;

    public VariantService() {
        this.beltVariantDao = new BeltVariantDao();
    }

    public List<String> getAllColors() {
        return beltVariantDao.getAllColors();
    }
}
