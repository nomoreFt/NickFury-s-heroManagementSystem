package frame;

import java.util.HashMap;
import java.util.Iterator;

import model.Yowon;
import model.dao.YowonDao;

public class DaoSendTest {
	public static void main(String[] args) {
		DaotEST daotest = new DaotEST();
		YowonDao dao = daotest.getDao();
		;

		HashMap<String, Yowon> db_map = dao.getDbMap();

		Iterator it = db_map.keySet().iterator();

		while (it.hasNext()) {
			String yowon_name = (String) it.next();
			System.out
					.println(db_map.get(yowon_name).getYowon_id() + " , " + db_map.get(yowon_name).getYowon_password());
		}
	}

}
