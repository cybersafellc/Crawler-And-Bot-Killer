package org.antibot.Model;

import org.antibot.Response.ObjAsClassfication;

import java.sql.*;
import java.util.ArrayList;

public class AsClassifiaction {

    private String username;
    private String password;
    private String host;
    private String query_getall = "SELECT * FROM as_classification";
    private String query_by_id = "SELECT * FROM as_classification WHERE id = ?";
    private String query_by_as_number = "SELECT * FROM as_classification WHERE as_number = ?";

    public AsClassifiaction(String username, String password, String host){
        this.username = username;
        this.password = password;
        this.host = host;
    }

    public ArrayList<ObjAsClassfication> getAll(){
        try(
                Connection conn = DriverManager.getConnection(host, username, password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query_getall);
                ){
            ArrayList<ObjAsClassfication> data = new ArrayList<>();
            while (rs.next()){

                ObjAsClassfication obj = new ObjAsClassfication(rs.getString("id"), rs.getString("as_number"), rs.getString("as_name"), rs.getString("country_code"), rs.getBoolean("rom"), rs.getDate("created_at"), rs.getDate("updated_at"));
                data.add(obj);
            }
            return data;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObjAsClassfication getById(String id){
        try(
                Connection conn = DriverManager.getConnection(host, username, password);
                PreparedStatement pr = conn.prepareStatement(query_by_id)
                ){
            pr.setString(1, id);
            try(
                    ResultSet rs = pr.executeQuery();
                    ){
                while (rs.next()){
                    return new ObjAsClassfication(rs.getString("id"), rs.getString("as_number"), rs.getString("as_name"), rs.getString("country_code"), rs.getBoolean("rom"), rs.getDate("created_at"), rs.getDate("updated_at"));
                }
            }
            return new ObjAsClassfication();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObjAsClassfication getByAsNumber(String asNumber){
        try(
                Connection conn = DriverManager.getConnection(host, username, password);
                PreparedStatement pr = conn.prepareStatement(query_by_as_number)
        ){
            pr.setString(1, asNumber);
            try(
                    ResultSet rs = pr.executeQuery();
            ){
                while (rs.next()){
                    return new ObjAsClassfication(rs.getString("id"), rs.getString("as_number"), rs.getString("as_name"), rs.getString("country_code"), rs.getBoolean("rom"), rs.getDate("created_at"), rs.getDate("updated_at"));
                }
            }
            return new ObjAsClassfication();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
