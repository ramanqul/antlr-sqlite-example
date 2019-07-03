package com.example.antlr4.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.example.antlr4.parser.enums.SQLType;
import com.example.antlr4.parser.exception.StatementProhibitedException;

public class SecuredMySQLiteParserTest {

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
    
    @Test(expected = StatementProhibitedException.class)
    public void testForbiddenAlterStatement() {
        parser.parse("Alter table points rename to grades");
    }
    
    @Test(expected = StatementProhibitedException.class)
    public void testForbiddenDropStatement() {
        parser.parse("DROP table accounts");
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

}
