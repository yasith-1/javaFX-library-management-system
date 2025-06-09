package repository.custom.impl;

import dto.Book;
import entity.BookEntity;
import entity.IssuedBookEntity;
import entity.MemberEntity;
import repository.custom.IssuedBookRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class IssuedBookRepositoryImpl implements IssuedBookRepository {

    HashMap<String, String> bookmap = new HashMap<>();
    HashMap<String, String> memberMap = new HashMap<>();

    @Override

    public HashMap<String, String> getMemberSet() {
        try {
            ResultSet resultset = CrudUtil.execute("SELECT `id` ,`name` FROM `member` WHERE `type_id`=?",2);
            while (resultset.next()) {
                memberMap.put(resultset.getString("name"),resultset.getString("id"));
            }
            return memberMap;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HashMap<String, String> getBookSet() {
        try {
            ResultSet resultset = CrudUtil.execute("SELECT `title` ,`isbn` FROM `book`");
            while (resultset.next()) {
                bookmap.put(resultset.getString("title"),resultset.getString("isbn"));
            }
            return bookmap;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean add(IssuedBookEntity entity) {
        try {
            Boolean result = CrudUtil.execute("INSERT INTO `member_has_book` VALUES (?,?,?,?,?,?)",
                    entity.getMemberId(),
                    entity.getIsbn(),
                    entity.getQty(),
                    entity.getDate(),
                    entity.getTime(),
                    entity.getReturnDate());
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean update(IssuedBookEntity entity) {
        return null;
    }

    @Override
    public Boolean delete(String s) {
        return null;
    }

    @Override
    public IssuedBookEntity search(String s) {
        return null;
    }

}
