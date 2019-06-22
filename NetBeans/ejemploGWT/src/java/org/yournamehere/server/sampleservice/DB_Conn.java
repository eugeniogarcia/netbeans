/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.yournamehere.server.sampleservice;

import java.sql.DriverManager;

/**
 *
 * @author Leihta
 */
public class DB_Conn {

        private java.sql.Connection con = null;
        private final String url = "jdbc:microsoft:sqlserver://";
        private final String serverName = "GARCIAZACH";
        private final String portNumber = "1433";
        private final String databaseName = "AdventureWorks";
        private final String userName = "egsmartin";
        private final String password = "vera1511";

        // Informs the driver to use server a side-cursor,
        // which permits more than one active statement
        // on a connection.
        private final String selectMethod = "cursor";

        public DB_Conn() {
        }

    protected  java.sql.Connection getConnection() {
                try {
                        //Carga el driver JDBC de MS SQL Server
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        con = DriverManager.getConnection(getConnectionUrl());

                        if (con != null)
                                System.out.println("Connection Successful!");

                } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error Trace in getConnection() : "
                                        + e.getMessage());
                }
                return con;
        }

        //Obtiene la cadena de conexión
        private String getConnectionUrl() {
                String miUrl = "jdbc:sqlserver://" + serverName + ";user=" + userName
                                + ";password=" + password + ";databaseName=" + databaseName
                                + ";";
                return miUrl;
        }

        /*
             Display the driver properties, database details
         */

        public void displayDbProperties() {
                java.sql.DatabaseMetaData dm = null;
                java.sql.ResultSet rs = null;
                try {
                        con = this.getConnection();
                        if (con != null) {
                                dm = con.getMetaData();
                                System.out.println("Driver Information");
                                System.out.println("\tDriver Name: " + dm.getDriverName());
                                System.out
                                                .println("\tDriver Version: " + dm.getDriverVersion());
                                System.out.println("\nDatabase Information ");
                                System.out.println("\tDatabase Name: "
                                                + dm.getDatabaseProductName());
                                System.out.println("\tDatabase Version: "
                                                + dm.getDatabaseProductVersion());
                                System.out.println("Avalilable Catalogs ");
                                rs = dm.getCatalogs();
                                while (rs.next()) {
                                        System.out.println("\tcatalog: " + rs.getString(1));
                                }
                                rs.close();
                                rs = null;
                                closeConnection();
                        } else
                                System.out.println("Error: No active Connection");
                } catch (Exception e) {
                        e.printStackTrace();
                }
                dm = null;
        }

        protected void closeConnection() {
                try {
                        if (con != null)
                                con.close();
                        con = null;
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

}