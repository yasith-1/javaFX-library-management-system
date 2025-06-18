package util;

import java.util.HashMap;

public class MapCollection {

    private static MapCollection instance;
    private HashMap<String, String> gerneMap;
    private HashMap<String, String> authorMap;
    private HashMap<String, String> bookStatusMap;
    private HashMap<String, String> bookMap;
    private HashMap<String, String> memberMap;
    private HashMap<String, Integer> bookQuantityMap;

    private MapCollection() {
        gerneMap = new HashMap<>();
        authorMap = new HashMap<>();
        bookStatusMap = new HashMap<>();
        bookMap = new HashMap<>();
        memberMap = new HashMap<>();
        bookQuantityMap = new HashMap<>();
    }

    public HashMap<String, String> getGerneMap() {
        return gerneMap;
    }

    public HashMap<String, String> getAuthorMap() {
        return authorMap;
    }

    public HashMap<String, String> getBookStatusMap() {
        return bookStatusMap;
    }

    public HashMap<String, String> getBookMap() {
        return bookMap;
    }

    public HashMap<String, String> getMemberMap() {
        return memberMap;
    }

    public HashMap<String, Integer> getBookQuantityMap() {
        return bookQuantityMap;
    }

    public static MapCollection getInstance() {
        return instance == null ? instance = new MapCollection() : instance;
    }
}
