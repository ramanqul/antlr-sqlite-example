package com.example.antlr4.parser;

import com.example.antlr4.parser.enums.SQLType;

public class MyStatement {

    private String tableName;
    private SQLType sqlType;
    private String schema;
    
    public MyStatement() {}
    
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public SQLType getSqlType() {
        return sqlType;
    }
    public void setSqlType(SQLType sqlType) {
        this.sqlType = sqlType;
    }
    public String getSchema() {
        return schema;
    }
    public void setSchema(String schemaName) {
        this.schema = schemaName;
    }
    
}
