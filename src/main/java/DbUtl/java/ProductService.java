package DbUtl.java;

import java.sql.SQLException;
import java.util.List;
public class ProductService {
    final private ProductDao productDao;

    public ProductService(){
        var connection = DbUtl.getConnection();
        productDao = new ProductDao(connection);
    }

    public ProductRecord/*戻り値*/ findById(int id/*intの引数１つ*/) {
        try(var connection = DbUtl.getConnection()){
            var productDao = new ProductDao(connection);
            var product = productDao.findById(id);
            if(product == null){
                throw new ProductNotFoundException();
            }
            return product;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public List<ProductRecord> findByName(String name) {
        try(var connection = DbUtl.getConnection()){
            var productDao = new ProductDao(connection);
            var List = productDao.findByName(name);
            return List;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int insert(ProductRecord product){
        try(var connection = DbUtl.getConnection()) {
            var productDao = new ProductDao(connection);
            var result = productDao.insert(product);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int update(ProductRecord product){
        try(var connection = DbUtl.getConnection();){
            var productDao = new ProductDao(connection);
            var result = productDao.update(product);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int delete(int id){
        try(var connection = DbUtl.getConnection()){
            var productDao = new ProductDao(connection);
            var result = productDao.delete(id);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}