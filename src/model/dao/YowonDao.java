package model.dao;

import java.io.IOError;
import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Map;

import model.Yowon;
public class YowonDao {
	private HashMap<String, Yowon> dbMap;
	private String[] yowonId = {"캡틴아메리카", "캡틴마블","블랙위도우","아이언맨","토르"};
	private String[] yowonPassword = {"1234","1234","1234","1234","1234"};
	private Yowon[] yowons = new Yowon[yowonId.length];
	
	public YowonDao() {
		dbMap = new HashMap<String, Yowon>();
		for(int i = 0; i < yowonId.length; i++) {
		yowons[i] = new Yowon(yowonId[i], yowonPassword[i]);
		dbMap.put(yowonId[i], yowons[i]);
		}
		
	}
	
	

	public HashMap<String, Yowon> getDbMap() {
		return dbMap;
	}


	public void setDbMap(HashMap<String, Yowon> dbMap) {
		this.dbMap = dbMap;
	}
	
	
	public Yowon[] getYowons() {
		return yowons;
	}



	public void setYowons(Yowon[] yowons) {
		this.yowons = yowons;
	}

	
	
	public Map<String, Yowon> yowonInsert(Yowon yowon) {

		dbMap.put(yowon.getYowon_id(), yowon);
		return dbMap;

	}

	public Yowon yowonSelect(String yowon_id) {

		Yowon yowon2 = dbMap.get(yowon_id);
		return yowon2;

	}

	public Yowon yowonUpdate(Yowon yowon) {

		dbMap.put(yowon.getYowon_id(), yowon);
		return dbMap.get(yowon.getYowon_id());

	}

	public HashMap<String, Yowon> yowonDelete(Yowon yowon) {

		dbMap.remove(yowon.getYowon_id());
		return dbMap;

	}






}
