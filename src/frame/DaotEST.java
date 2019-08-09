package frame;

import java.io.ObjectInputStream.GetField;
import java.util.HashMap;
import java.util.Iterator;

import model.Yowon;
import model.dao.YowonDao;

public class DaotEST {
	YowonDao dao = new YowonDao();
	public YowonDao getDao() {
		return this.dao;
	}
	public DaotEST() {
		dao.yowonInsert(new Yowon("ÇæÅ©", "¿È‡œ‡œ‡œ"));
		HashMap<String, Yowon> db_map= dao.getDbMap();
		
		Iterator it = db_map.keySet().iterator();
		
		while(it.hasNext()) {
			String yowon_name = (String) it.next();
			System.out.println(db_map.get(yowon_name).getYowon_id()+" , " + db_map.get(yowon_name).getYowon_password());
		}
	}
	public static void main(String[] args) {
		new DaotEST(); 
}
}
