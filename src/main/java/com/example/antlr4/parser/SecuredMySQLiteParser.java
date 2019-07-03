package com.example.antlr4.parser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.example.antlr4.parser.enums.SQLType;
import com.example.antlr4.parser.exception.StatementProhibitedException;

public class SecuredMySQLiteParser extends MySQLiteParser {

    private static final Set<SQLType> FORBIDDEN_STATEMENTS = new HashSet<SQLType>(
                Arrays.asList(
                        SQLType.ALTER_TABLE_STMT,
                        SQLType.DROP_TABLE_STMT, 
                        SQLType.DROP_INDEX_STMT, 
                        SQLType.DROP_TRIGGER_STMT, 
                        SQLType.DROP_VIEW_STMT)
            );
    
    @Override
    public MyStatement parse(String sql) throws StatementProhibitedException {
        MyStatement stmt = super.parse(sql);
    
        if (stmt != null && FORBIDDEN_STATEMENTS.contains(stmt.getSqlType())) {
            throw new StatementProhibitedException(stmt.getSqlType() + " statement is not allowed!");
        }
    
        return stmt;
    }
    
    
}
