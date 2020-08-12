package com.project.extractingNLP.Model.Dto;

import org.json.simple.JSONObject;

public class AccountDto {
    private String id;
    private String pwd;
    private String name;

    public AccountDto(String _id, String _name, String _pwd) {
        this.id = _id;
        this.name = _name;
        this.pwd = _pwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    public JSONObject toJSONObject() {
        JSONObject jObj = new JSONObject();
        jObj.put("id", this.id);
        jObj.put("pwd", this.pwd);
        jObj.put("name", this.name);
        
        return jObj;
    }
}
