package com.project.extractingNLP.Model.Dto;
import org.json.simple.JSONObject;

public class DirDto {
    private int did;
    private int pdid;
    private String dname;
    private String aid;

    public DirDto(int _did, int _pdid, String _dname, String _aid) {
        this.did = _did;
        this.pdid = _pdid;
        this.dname = _dname;
        this.aid = _aid;
    }



    public int getDid() {
		return did;
	}



	public void setDid(int did) {
		this.did = did;
	}



	public int getPdid() {
		return pdid;
	}



	public void setPdid(int pdid) {
		this.pdid = pdid;
	}



	public String getDname() {
		return dname;
	}



	public void setDname(String dname) {
		this.dname = dname;
	}



	public String getAid() {
		return aid;
	}



	public void setAid(String aid) {
		this.aid = aid;
	}



	public JSONObject toJSONObject() {
        JSONObject jObj = new JSONObject();
        jObj.put("did", this.did);
        jObj.put("pdid", this.pdid);
        jObj.put("dname", this.dname);
        jObj.put("aid", this.aid);
        
        return jObj;
    }
}
