package dao;

import domain.Price;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PriceDAOImpl extends AbstractDAO implements PriceDAO {

    @Override
    public List<Price> getPricesGroupedByProductCodeAndNumberAndDepartAndValue() {
        List<Price> result = new ArrayList<>();

        try(PreparedStatement ps =
                    dbConnection.prepareStatement(PRICES_GROUPED_BY_PRODUCT_CODE_AND_NUMBER_AND_DEPART_AND_VALUE);
            ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                Price price = new Price();
                price.setProductCode(resultSet.getString("product_code"));
                price.setNumber(resultSet.getInt("number"));
                price.setDepart(resultSet.getInt("depart"));
                price.setValue(resultSet.getLong("value"));
                price.setBegin(resultSet.getTimestamp("begin"));
                price.setEnd(resultSet.getTimestamp("end"));

                result.add(price);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
