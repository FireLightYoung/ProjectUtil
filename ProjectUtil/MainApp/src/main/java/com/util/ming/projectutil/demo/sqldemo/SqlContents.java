package com.util.ming.projectutil.demo.sqldemo;

/**
 * Created by ming on 17/10/27.
 */

public class SqlContents {
    public static final String createTable1 = "CREATE TABLE IF NOT EXISTS COMPANY(\n" +
            "   ID INT PRIMARY KEY     NOT NULL,\n" +
            "   NAME           TEXT    NOT NULL,\n" +
            "   AGE            INT     NOT NULL,\n" +
            "   ADDRESS        CHAR(50),\n" +
            "   SALARY         REAL\n" +
            ");";
    public static final String createTable2 = "CREATE TABLE DEPARTMENT(\n" +
            "   ID INT PRIMARY KEY      NOT NULL,\n" +
            "   DEPT           CHAR(50) NOT NULL,\n" +
            "   EMP_ID         INT      NOT NULL\n" +
            ");";

    public static final String insertData1 = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY)\n" +
            "VALUES (1, 'Paul', 32, 'California', 20000.00 );\n" +
            "\n" +
            "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY)\n" +
            "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );\n" +
            "\n" +
            "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY)\n" +
            "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );\n" +
            "\n" +
            "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY)\n" +
            "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );\n" +
            "\n" +
            "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY)\n" +
            "VALUES (5, 'David', 27, 'Texas', 85000.00 );\n" +
            "\n" +
            "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY)\n" +
            "VALUES (6, 'Kim', 22, 'South-Hall', 45000.00 );";
    public static final String selectData = "SELECT * FROM COMPANY";
    public static final String updateData = "UPDATE COMPANY SET ADDRESS = \"CHINA\" WHERE NAME = \"Allen\"";
    public static final String insertData4 = "";
    public static final String insertData5 = "";


}
