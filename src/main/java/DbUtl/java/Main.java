package DbUtl.java;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
//４-１ ProductDao#findById idで検索している
        var productService = new ProductService();
        var product = productService.findById(101);
        System.out.println(product);

//４-２ ProductDao#findByName　名前で検索している
        var productList = productService.findByName("地");
        productList.stream().forEach(System.out::println);

//４-３　ProductDao#insert　レコードを追加している(二回目以降エラー)
//        var newProduct = new ProductRecord(104,"物体",20000);
//        productService.insert(newProduct);
//        product = productService.findById(104);
//        System.out.println(product);
    }
}

    /*
    public static void main(String[] args) {

        // JDBCドライバの読み込み
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
            return;
        }

        // 接続
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb", "testuser", "test")) {
            var productService = new ProductService(con);
            try {
                var productRecord = productService.findById(1);
                System.out.println(productRecord);
            } catch (ProductNotFoundException e){
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
*/