package org.antibot.Model;

import java.sql.*;

public class Connections {
    private String username;
    private String password;
    private String host;
    // public field / table object
    public AsClassifiaction asClassifiaction;

    public Connections() throws SQLException{
        this.username = System.getenv("USER_MYSQL");
        this.password = System.getenv("PASS_MYSQL");
        this.host = System.getenv("HOST_MYSQL");
        setUpConnection();
    }

    public Connections(String user, String password, String host) throws SQLException {
        this.username = user;
        this.password = password;
        this.host = host;
        setUpConnection();
    }

    // method testing database and setup orm
    private void setUpConnection(){
        try (Connection conn = DriverManager.getConnection(host, username, password);){
            // setup field object
            this.asClassifiaction = new AsClassifiaction(username, password, host);
            System.out.println("Database connected... already to execute");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
