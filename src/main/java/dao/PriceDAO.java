package dao;

import domain.Price;

import java.util.List;

public interface PriceDAO {
    String PRICES_GROUPED_BY_PRODUCT_CODE_AND_NUMBER_AND_DEPART_AND_VALUE = "select\n" +
            "  product_code,\n" +
            "  number,\n" +
            "  depart,\n" +
            "  value,\n" +
            "  min(begin) begin,\n" +
            "  max(end)   end\n" +
            "from price\n" +
            "group by product_code, number, depart, value\n" +
            "order by product_code, number, depart, begin, end";
    List<Price> getPricesGroupedByProductCodeAndNumberAndDepartAndValue();
}
