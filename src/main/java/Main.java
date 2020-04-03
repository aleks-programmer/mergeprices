import dao.PriceDAOImpl;
import domain.Price;
import service.PriceService;
import service.PriceServiceImpl;

import java.util.List;


public class Main {
    private static PriceService priceService;


    private static void init() {
        priceService = new PriceServiceImpl(new PriceDAOImpl());
    }

    public static void main(String[] args) {
        init();
        List<Price> prices = priceService.mergePrices();

        prices.forEach(System.out::println);
    }

}
