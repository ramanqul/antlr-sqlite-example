package com.example.antlr4.parser.enums;

import com.example.antlr4.SQLiteParser;

public enum SQLType {
    
    ALTER_TABLE_STMT(SQLiteParser.RULE_alter_table_stmt),
    ANALYZE_STMT(SQLiteParser.RULE_analyze_stmt),
    ATTACH_STMT(SQLiteParser.RULE_attach_stmt),
    BEGIN_STMT(SQLiteParser.RULE_begin_stmt),
    COMMIT_STMT(SQLiteParser.RULE_commit_stmt),
    COMPOUND_SELECT_STMT(SQLiteParser.RULE_compound_select_stmt),
    CREATE_INDEX_STMT(SQLiteParser.RULE_create_index_stmt),
    CREATE_TABLE_STMT(SQLiteParser.RULE_create_table_stmt),
    CREATE_TRIGGER_STMT(SQLiteParser.RULE_create_trigger_stmt),
    CREATE_VIEW_STMT(SQLiteParser.RULE_create_view_stmt),
    CREATE_VIRTUAL_TABLE_STMT(SQLiteParser.RULE_create_virtual_table_stmt),
    DELETE_STMT(SQLiteParser.RULE_delete_stmt),
    DELETE_STMT_LIMITED(SQLiteParser.RULE_delete_stmt_limited),
    DETACH_STMT(SQLiteParser.RULE_detach_stmt),
    DROP_INDEX_STMT(SQLiteParser.RULE_drop_index_stmt),
    DROP_TABLE_STMT(SQLiteParser.RULE_drop_table_stmt),
    DROP_TRIGGER_STMT(SQLiteParser.RULE_drop_trigger_stmt),
    DROP_VIEW_STMT(SQLiteParser.RULE_drop_view_stmt),
    FACTORED_SELECT_STMT(SQLiteParser.RULE_factored_select_stmt),
    INSERT_STMT(SQLiteParser.RULE_insert_stmt),
    PRAGMA_STMT(SQLiteParser.RULE_pragma_stmt),
    REINDEX_STMT(SQLiteParser.RULE_reindex_stmt),
    RELEASE_STMT(SQLiteParser.RULE_release_stmt),
    ROLLBACK_STMT(SQLiteParser.RULE_rollback_stmt),
    SAVEPOINT_STMT(SQLiteParser.RULE_savepoint_stmt),
    SIMPLE_SELECT_STMT(SQLiteParser.RULE_simple_select_stmt),
    SELECT_STMT(SQLiteParser.RULE_select_stmt),
    UPDATE_STMT(SQLiteParser.RULE_update_stmt),
    UPDATE_STMT_LIMITED(SQLiteParser.RULE_update_stmt_limited),
    VACUUM_STMT(SQLiteParser.RULE_vacuum_stmt);
    
    public final int ruleId;
    
    SQLType(int ruleId) {
        this.ruleId = ruleId;
    }

    @Override
    public String toString() {
        return name().replace("_", " ").replace("STMT", "").toLowerCase();
    }
}