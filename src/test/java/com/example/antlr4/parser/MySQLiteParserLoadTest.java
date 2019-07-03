package com.example.antlr4.parser;

import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

public class MySQLiteParserLoadTest {

    private static MySQLiteParser parser;
    private static Set<String> randomSqlSet;
    private static final int LOAD_LIMIT = 1000;
    
    @BeforeClass
    public static void init() {
        parser = new MySQLiteParser();
        randomSqlSet = prepareData();
    }
    
    private static String insertTemplate(String tableName, String value) {
        return String.format("INSERT into %s(somecol) VALUES('%s')", tableName, value);
    }

    private static String updateTemplate(String tableName, String value) {
        return String.format("UPDATE %s(somecol) SET col1='%s'", tableName, value);
    }
    
    private static String deleteTemplate(String tableName, String value) {
        return String.format("DELETE FROM %s WHERE col1='%s'", tableName, value);
    }
    
    private static String selectTemplate(String tableName, String value) {
        return String.format("SELECT * FROM %s(somecol) WHERE col1='%s'", tableName, value);
    }
    
    private static Set<String> prepareData() {
        Set<String> randomSqlSet = new HashSet<>();
        
        for (int i=0;i<LOAD_LIMIT;i++) {
            int random4 = ((int)Math.random() * 10) % 4;
            
            if (random4 == 0) {
                randomSqlSet.add(insertTemplate("table"+i, "val" + i));
            } else if (random4 == 1) {
                randomSqlSet.add(updateTemplate("table"+i, "val" + i));
            } else if (random4 == 2) {
                randomSqlSet.add(deleteTemplate("table"+i, "val" + i));
            } else if (random4 == 3) {
                randomSqlSet.add(selectTemplate("table"+i, "val" + i));
            }
        }
        
        return randomSqlSet;
    }
    
    @Test
    public void testPerformance() {
        

        long startTimeMillis = System.currentTimeMillis();
        
        for(String sql: randomSqlSet) {
            parser.parse(sql);
        }
        
        long endTimeMillis = System.currentTimeMillis();
        
        System.out.printf("To process %d sql queries it took %d millis", LOAD_LIMIT, endTimeMillis - startTimeMillis);
    }
    
    
}
