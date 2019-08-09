package model;

public class Yowon {
	    private String Yowon_id;
	    private String Yowon_password;
	    
	    
		public Yowon(String yowon_id, String yowon_password) {
			Yowon_id = yowon_id;
			Yowon_password = yowon_password;
		}
		public String getYowon_id() {
			return Yowon_id;
		}
		public void setYowon_id(String yowon_id) {
			Yowon_id = yowon_id;
		}
		public String getYowon_password() {
			return Yowon_password;
		}
		public void setYowon_password(String yowon_password) {
			Yowon_password = yowon_password;
		}
		@Override
		public String toString() {
			return "Yowon [Yowon_id=" + Yowon_id + ", Yowon_password=" + Yowon_password + "]";
		}
	
		
}
