package com.thomas.dao;

import com.thomas.dao.db.JDBIConnect;
import com.thomas.dao.model.Category;

import java.util.List;

public class CategoryDao {
    public CategoryDao() {
    }

    public boolean createCategory(String name) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "INSERT INTO categories(name) VALUES (:name)";
            return h.createUpdate(sql).bind("name", name).execute() > 0;
        });
    }

    public Category getCategory(String name) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT * FROM categories WHERE name = :name";
            return h.createQuery(sql).bind("name", name).mapToBean(Category.class).findFirst().orElse(null);
        });
    }

    public int getLatestCategoryId() {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT id FROM categories ORDER BY id DESC LIMIT 1";
            return h.createQuery(sql).mapTo(Integer.class).first();
        });
    }

    public List<Category> find(int beltId, int variantId) {
        String sql = "SELECT * FROM categories c " +
                "JOIN beltCategory bc ON c.id = bc.categoryId " +
                "WHERE bc.beltId = :beltId AND bc.variantId = :variantId";

        return JDBIConnect.get().withHandle(handle ->
                handle.createQuery(sql)
                        .bind("beltId", beltId)
                        .bind("variantId", variantId)
                        .mapToBean(Category.class)
                        .list()
        );
    }

}
