package com.example.antlr4.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.example.antlr4.parser.enums.SQLType;

public class MySQLiteParserTest {

    private MySQLiteParser parser;
    
    @Before
    public void init() {
        parser = new SecuredMySQLiteParser();
    }
    
    @Test
    public void testInsertStatement() {
        MyStatement stmt = parser.parse("INSERT INTO test(id, name) VALUES(1, 'TEST1')");
        assertEquals("test", stmt.getTableName());
        assertEquals(SQLType.INSERT_STMT, stmt.getSqlType());
    }

    @Test
    public void testInsertOrReplaceStatement() {
        MyStatement stmt = parser.parse("INSERT OR REPLACE INTO other_test(id, name, password) VALUES (4, 'abc', 'xyz')");
        assertEquals("other_test", stmt.getTableName());
        assertEquals(SQLType.INSERT_STMT, stmt.getSqlType());
    }

    @Test(expected = IllegalStateException.class)
    public void testErrorInsertStatement() {
        parser.parse("INSERT test(id, name, password) VALUES (4, 'abc', 'xyz')");
    }

    @Test(expected = IllegalStateException.class)
    public void testErrorInsertOrReplaceStatement() {
        parser.parse("INSERT OR REPLACE INTO other test(id, name, password) VALUES (4, 'abc', 'xyz')");
    }

    @Test
    public void testSimpleDeleteStatement() {
        MyStatement stmt = parser.parse("DELETE FROM news Where id > 2");
        assertEquals("news", stmt.getTableName());
        assertEquals(SQLType.DELETE_STMT, stmt.getSqlType());
    }

    @Test(expected = IllegalStateException.class)
    public void testWrongDeleteStatement() {
        parser.parse("DELETE news Where id > 10");
    }

    @Test
    public void testSimpleUpdateStatement() {
        MyStatement stmt = parser.parse("UPDATE users Set Password=123");
        assertEquals("users", stmt.getTableName());
        assertEquals(SQLType.UPDATE_STMT, stmt.getSqlType());
    }

    @Test(expected = IllegalStateException.class)
    public void testIncorrectUpdateStatement() {
        parser.parse("UPDATE FROM users Set password=123");
    }

    @Test
    public void testInsertStatementWithSchema() {
        MyStatement stmt = parser.parse("INSERT INTO aaa.test(id, name) VALUES(1, 'TEST1')");
        assertEquals("aaa", stmt.getSchema());
        assertEquals("test", stmt.getTableName());
        assertEquals(SQLType.INSERT_STMT, stmt.getSqlType());
    }

    @Test
    public void testUpdateStatementWithSchema() {
        MyStatement stmt = parser.parse("UPDATE bbb.users Set Password=123");
        assertEquals("bbb", stmt.getSchema());
        assertEquals("users", stmt.getTableName());
        assertEquals(SQLType.UPDATE_STMT, stmt.getSqlType());
    }
    
    @Test
    public void testSimpleDeleteStatementWithSchema() {
        MyStatement stmt = parser.parse("DELETE FROM main.news Where id > 2");
        assertEquals("main", stmt.getSchema());
        assertEquals("news", stmt.getTableName());
        assertEquals(SQLType.DELETE_STMT, stmt.getSqlType());
    }
    
    @Test(expected = IllegalStateException.class)
    public void testInvalidSchemaName() {
        parser.parse("DELETE FROM main schema.news Where id > 2");
    }
    
}
