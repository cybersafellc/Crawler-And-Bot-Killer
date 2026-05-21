package org.antibot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.antibot.Model.Connections;
import org.antibot.Model.ObjectTables.ObjAsClassfication;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    static void main() {
        try{
            Connections db = new Connections();
            ObjAsClassfication data = db.asClassifiaction.getByAsNumber("AS23693");
            ObjectMapper maper = new ObjectMapper();
            try{
                String json = maper.writeValueAsString(data);
                System.out.println(json);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
