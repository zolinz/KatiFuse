import java.math.BigDecimal;

/**
 * Created by Zoli on 7/10/2016.
 */
public class StartApp {

        public static void main(String ... args){
        BigDecimal result =     new BigDecimal(14).multiply(new BigDecimal(1650));
        System.out.print(result.divide(new BigDecimal(100)));
    }

}
