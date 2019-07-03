package com.example.antlr4.parser;

public class Main {

    private static void printInfo(String stmt, MyStatement stmtInfo) {
        System.out.printf("\nInput statement: \n%s\nTable name: %s\nStatement type: %s\n", stmt, stmtInfo.getTableName(), stmtInfo.getSqlType());
    }

    public static void main(String[] args) throws Exception {
        MySQLiteParser parser = new SecuredMySQLiteParser();

        String testInsertStmt = "INSERT INTO test(id, name) VALUES(1, 'TEST1')";
        String testUpdateStmt = "UPDATE users Set Password=123";
        String testDeleteStmt = "DELETE FROM news Where id > 2";
        String testSelectStmt = "SELECT id FROM homes";
        String testAlterStmt = "ALTER table accounts add column id(int) not null";
        
        printInfo(testInsertStmt, parser.parse(testInsertStmt));
        printInfo(testUpdateStmt, parser.parse(testUpdateStmt));
        printInfo(testDeleteStmt, parser.parse(testDeleteStmt));
        printInfo(testSelectStmt, parser.parse(testSelectStmt));
        printInfo(testAlterStmt, parser.parse(testAlterStmt));
    }

}
