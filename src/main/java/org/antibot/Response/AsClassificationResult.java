package org.antibot.Response;

import java.sql.Date;

public class AsClassificationResult {
    private String id;
    private String as_number;
    private String as_name;
    private String country_code;
    private boolean rom;
    private Date created_at;
    private Date updated_at;

    public AsClassificationResult(){}
    public AsClassificationResult(String id, String as_number, String as_name, String country_code, boolean rom, Date created_at, Date updated_at){
        this.id = id;
        this.as_name = as_name;
        this.as_number = as_number;
        this.country_code = country_code;
        this.rom = rom;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return id;
    }

    public void setAs_number(String as_number){
        this.as_number = as_number;
    }
    public String getAs_number(){
        return as_number;
    }

    public void setAs_name(String as_name){
        this.as_name = as_name;
    }
    public String getAs_name(){
        return as_name;
    }

    public void setCountry_code(String country_code){
        this.country_code = country_code;
    }
    public String getCountry_code(){
        return country_code;
    }

    public void setRom(boolean rom){
        this.rom = rom;
    }
    public boolean getRom(){
        return rom;
    }

    public void setCreated_at(Date created_at){
        this.created_at = created_at;
    }
    public Date getCreated_at(){
        return created_at;
    }

    public void setUpdated_at(Date updated_at){
        this.updated_at = updated_at;
    }
    public Date getUpdated_at(){
        return updated_at;
    }
}
