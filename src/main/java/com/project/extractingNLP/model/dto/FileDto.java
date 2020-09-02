package com.project.extractingNLP.model.dto;

import org.json.simple.JSONObject;

public class FileDto {
    private int fid;
    private int pdid;
    private String fname;
    private String aid;

    public FileDto(int _fid, int _pdid, String _fname, String _aid) {
        this.fid = _fid;
        this.pdid = _pdid;
        this.fname = _fname;
        this.aid = _aid;
    }



    public int getFid() {
		return fid;
	}



	public void setFid(int fid) {
		this.fid = fid;
	}



	public int getPdid() {
		return pdid;
	}



	public void setPdid(int pdid) {
		this.pdid = pdid;
	}



	public String getFname() {
		return fname;
	}



	public void setFname(String fname) {
		this.fname = fname;
	}



	public String getAid() {
		return aid;
	}



	public void setAid(String aid) {
		this.aid = aid;
	}



	public JSONObject toJSONObject() {
        JSONObject jObj = new JSONObject();
        jObj.put("fid", this.fid);
        jObj.put("pdid", this.pdid);
        jObj.put("fname", this.fname);
        jObj.put("aid", this.aid);
        
        return jObj;
    }
}
