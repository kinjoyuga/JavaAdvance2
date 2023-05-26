package DbUtl.java;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtl {
    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");/*jdbcをロードするためクラスをロード*/
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb", "testuser", "test");
            /*testdbのデータに基づいてConnectionを取得*/
        } catch (Exception e) {
            // 本来は専用の例外クラスを作成したほうがよい
            throw new RuntimeException(e);
        }
    }
}