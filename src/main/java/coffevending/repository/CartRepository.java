package coffevending.repository;

import coffevending.model.CheckLines;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

public class CartRepository extends AbstractRepository {

    public boolean saveAll(Collection<CheckLines> listCheckLines, long check_id) {
        int result = 0;
        for (CheckLines checkLine : listCheckLines ){
            String sql = "INSERT INTO checklines (check_id, goods_ean, count, total) " +
                    "VALUES (?, ?, ?, ?)";
            try (PreparedStatement prSt = dataBaseConnection.getDbConnection().prepareStatement(sql)) {
                prSt.setLong(1, check_id);
                prSt.setLong(2, checkLine.getEan() );
                prSt.setInt(3, checkLine.getCount());
                prSt.setBigDecimal(4, checkLine.getTotal());
                int st = prSt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result > 0;
    }
}
