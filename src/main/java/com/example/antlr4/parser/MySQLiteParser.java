package com.example.antlr4.parser;

import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.example.antlr4.SQLiteBaseListener;
import com.example.antlr4.SQLiteLexer;
import com.example.antlr4.SQLiteParser;
import com.example.antlr4.SQLiteParser.Database_nameContext;
import com.example.antlr4.SQLiteParser.Sql_stmtContext;
import com.example.antlr4.SQLiteParser.Table_nameContext;
import com.example.antlr4.parser.enums.SQLType;

public class MySQLiteParser {

    private static Map<Integer, SQLType> RULE_ID_TO_SQL_TYPE;
    private static final boolean PROFILE = false;
    
    public MyStatement parse(String sql) {
        long startTime = System.currentTimeMillis();
        
        SQLiteParser parser = new SQLiteParser(new CommonTokenStream(new SQLiteLexer(CharStreams.fromString(sql))));

        long endTime = System.currentTimeMillis();
        
        if (PROFILE) {
            System.out.println("--------------------------------");
            System.out.printf("Parser creation time: %d ms\n", endTime - startTime);
        }
        
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                    int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });
        final MyStatement stmt = new MyStatement();
        
        startTime = System.currentTimeMillis();
        ParseTree tree = parser.parse();
        endTime = System.currentTimeMillis();
        
        if (PROFILE) {
            System.out.printf("Parser.parse time: %d ms\n", endTime - startTime);
        }
        
        if (RULE_ID_TO_SQL_TYPE == null) {
            RULE_ID_TO_SQL_TYPE = new HashMap<>();
            
            for (SQLType sqlType: SQLType.values()) {
                RULE_ID_TO_SQL_TYPE.put(sqlType.ruleId, sqlType);
            }
        }
        
        startTime = System.currentTimeMillis();
        ParseTreeWalker.DEFAULT.walk(new SQLiteBaseListener() {
            @Override
            public void exitSql_stmt(Sql_stmtContext ctx) {
                if (ctx.getChildCount() > 0) {
                    int ruleId = ctx.getChild(ParserRuleContext.class, 0).getRuleIndex();
                    stmt.setSqlType(RULE_ID_TO_SQL_TYPE.get(ruleId));    
                }
            }
            
            @Override
            public void exitDatabase_name(Database_nameContext ctx) {
                stmt.setSchema(ctx.getText());
            }

            @Override
            public void exitTable_name(Table_nameContext ctx) {
                stmt.setTableName(ctx.getText());
            }
        }, tree);
        endTime = System.currentTimeMillis();
        
        if (PROFILE) {
            System.out.printf("Walk tree time: %d ms\n", endTime - startTime);
        }
        
        return stmt;
    }
}
