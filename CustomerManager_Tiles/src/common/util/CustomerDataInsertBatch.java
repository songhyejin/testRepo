package common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CustomerDataInsertBatch {
	public static void main(String[] args) throws Exception{
		
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "scott","tiger");
		PreparedStatement pstmt = conn.prepareStatement("delete from customer");
		pstmt.executeUpdate();
		pstmt.close();
		pstmt = conn.prepareStatement("insert into customer values(?,?,?,?,?)");
		for(int i = 1; i < 1000; i++){
			String id = null;
			if(i<10){
				id = "00"+i;
			}else if(i<100){
				id = "0"+i;
			}else{
				id = i+"";
			}
			int op = (int)(Math.random()*1000);
			pstmt.setString(1, "id-"+id);
			pstmt.setString(2, "1111");
			pstmt.setString(3, "고객"+id);
			pstmt.setString(4, id+"@abc.com");
			pstmt.setInt(5, i*op);
			pstmt.addBatch();
//			pstmt.clearParameters();//생략해도 될 듯.
		}
		int cnts [] = pstmt.executeBatch();
		pstmt.close();
		System.out.println(cnts.length);
	}
}
