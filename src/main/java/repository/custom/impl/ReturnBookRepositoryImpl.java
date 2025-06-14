package repository.custom.impl;

import repository.custom.ReturnBookRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.util.HashMap;

public class ReturnBookRepositoryImpl implements ReturnBookRepository {

    HashMap<String, String> bookmap = new HashMap<>();
    HashMap<String, String> memberMap = new HashMap<>();

    @Override
    public HashMap<String, String> getMemberSet() {
        try {
            ResultSet resultset = CrudUtil.execute("SELECT `id` ,`name` FROM `member` WHERE `type_id`=?", 2);
            while (resultset.next()) {
                memberMap.put(resultset.getString("name"), resultset.getString("id"));
            }
            return memberMap;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HashMap<String, String> getBookSet() {
        try {
            ResultSet resultset = CrudUtil.execute("SELECT `title` ,`isbn` FROM `book` WHERE `status_id`=?","S001");
            while (resultset.next()) {
                bookmap.put(resultset.getString("title"), resultset.getString("isbn"));
            }
            return bookmap;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean add(Object entity) {
        return null;
    }

    @Override
    public Boolean update(Object entity) {
        return null;
    }

    @Override
    public Boolean delete(Object o) {
        return null;
    }

    @Override
    public Object search(Object o) {
        return null;
    }
}
