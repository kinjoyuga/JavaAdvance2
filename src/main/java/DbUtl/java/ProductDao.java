package DbUtl.java;

//import DbUtl.record.ProductRecord;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductDao {
    //フィールド
    private Connection connection;

    //コンストラクタ
    public ProductDao(Connection connection) {
        this.connection = connection;
    }

    //メソッド　ProductDao#findById
    public ProductRecord findById(int id) {/*指定されたIDに一致するやつを探しProductRecordとして返す*/
        var SQL = "SELECT * FROM products WHERE id = ?";/*後で?の値を変える*/
        try (var stmt = this.connection.prepareStatement(SQL)) {
//準備されたステートメントを生成、connectionにあるConnectionで準備されたステートメントを作成。
            stmt.setInt(1, id);
//ここで?の値を設定する。１はプレースホルダー
            var rs = stmt.executeQuery();
            if (rs.next()) {
                var product = new ProductRecord(rs.getInt("id"), rs.getString("name"), rs.getInt("price"));
                return product;
            }
            return null;/*Productをnullで初期化*/
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //メソッド　findByName
    public List<ProductRecord> findByName(String name) {
        var SQL = "SELECT * FROM products WHERE name LIKE ?";
        try (var stmt = this.connection.prepareStatement(SQL)) {
            stmt.setString(1, "%" + name + "%");
            var rs = stmt.executeQuery();
            List<ProductRecord> List = new ArrayList<>();
            while (rs.next()) {
                List.add(new ProductRecord(rs.getInt("id"),rs.getString("name"),rs.getInt("price")));
            }
            return List;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //メソッド  ProductDao#insert
    public int insert(ProductRecord product) {
        var SQL = "INSERT INTO products VALUES (?,?,?)";
        try (var stmt = this.connection.prepareStatement(SQL)) {
            stmt.setInt(1, product.id());
            stmt.setString(2, product.name());
            stmt.setInt(3, product.price());
            int result = stmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //メソッド　ProductDao#update
    public int update(ProductRecord product/*ここと*/){
        var SQL = "UPDATE product SET id = ?, name = ?, price = ?";
        try (var stmt = this.connection.prepareStatement(SQL)){
            stmt.setInt(1, product/*ここ同じ*/.id());
            stmt.setString(2, product.name());
            stmt.setInt(3, product.price());
            var result = stmt.executeUpdate();
            return result;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int delete(int id){
        var SQL = "DELETE FROM products WHERE id = ?";
        try(var stmt = this.connection.prepareStatement(SQL)) {
            stmt.setInt(1, id);
            var result = stmt.executeUpdate();
            return result;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}