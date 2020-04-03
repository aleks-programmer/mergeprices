import dao.PriceDAOImpl;
import service.PriceService;
import service.PriceServiceImpl;


public class Main {
    private static PriceService priceService;


    private static void init() {
        priceService = new PriceServiceImpl(new PriceDAOImpl());
    }

    public static void main(String[] args) {
        init();
        priceService.mergePrices();
    }

}
