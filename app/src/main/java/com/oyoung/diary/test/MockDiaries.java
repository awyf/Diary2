package com.oyoung.diary.test;

import com.oyoung.diary.model.Diary;

import java.util.LinkedHashMap;
import java.util.Map;

public class MockDiaries {
    private static final String DESCRIPTION = "今天,天气晴朗,在校园里,我看到了很美的风景";
    public static Map<String, Diary> mock() {
        return mock(new LinkedHashMap<>());
    }
    public static Map<String, Diary> mock(Map<String, Diary> data) {
        Diary test1 = getDiary("2022-11-02  艺术节");
        data.put(test1.getId(), test1);
        Diary test2 = getDiary("2022-11-02  艺术节");
        data.put(test2.getId(), test2);
        Diary test3 = getDiary("2022-11-02  艺术节");
        data.put(test3.getId(), test3);
        Diary test4 = getDiary("2022-11-02  艺术节");
        data.put(test4.getId(), test4);
        Diary test5 = getDiary("2022-11-02  艺术节");
        data.put(test5.getId(), test5);
        Diary test6 = getDiary("2022-11-02  艺术节");
        data.put(test6.getId(), test6);
        Diary test7 = getDiary("2022-11-02  艺术节");
        data.put(test7.getId(), test7);
        Diary test8 = getDiary("2022-11-02  艺术节");
        data.put(test8.getId(), test8);
        Diary test9 = getDiary("2022-11-02  艺术节");
        data.put(test9.getId(), test9);
        return data;
    }

    private static Diary getDiary(String s) {
        return new Diary(s, DESCRIPTION);
    }
}
