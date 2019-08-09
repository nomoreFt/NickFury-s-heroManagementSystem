package model.service;

import java.lang.reflect.Member;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import model.Yowon;
import model.dao.YowonDao;
	public class YowonService {
		
		YowonDao dao = new YowonDao();
		
		public void yowonRegister(Yowon yowon) {
			printMembers(dao.yowonInsert(yowon));
		}

		public Yowon yowonSearch(String yowon) {
			Yowon yowon1 = dao.yowonSelect(yowon);
			printMember(yowon1);
			
			return yowon1;
		}

		public Yowon yowonModify(Yowon yowon) {
			
			Yowon yowonAft = dao.yowonUpdate(yowon);
			printMember(yowonAft);
			
			return yowonAft;
		}
		
		public void yowonRemove(Yowon yowon) {
			printMembers(dao.yowonDelete(yowon));
		}
		
		private void printMembers(Map<String, Yowon> map) {
			
			Set<String> keys = map.keySet();
			Iterator<String> iterator = keys.iterator();
			
			while (iterator.hasNext()) {
				String key = iterator.next();
				Yowon yowon = map.get(key);
				printMember(yowon);
			}
			
		}
		
		private void printMember(Yowon yowon1) {
			
			System.out.print("ID:" + yowon1.getYowon_id() + "\t");
			System.out.print("|PW:" + yowon1.getYowon_password() + "\t");
			
		}

	}
