package collectiontest;

import java.util.*;

/**
 * Created by Zoli on 17/02/2017.
 */
public class CollectionTest {

    public static void main(String[] a) {

        Map<String, String> myMap = new HashMap<String , String>();
        myMap.put("A", "letterA");
        myMap.put("B", "letterB");
        myMap.put("C", "letterC");
        myMap.put("D", "");

        boolean result = false;
        String elements[] = { "A", "B", "C", "D"};


        result = myMap.keySet().containsAll(Arrays.asList(elements));
        if(result){
            for( String value : myMap.values()){
                if (null != value){
                    result =  value.length() > 0 ;
                    if (!result) break;
                }
            }
        }


        System.out.println(result);

    }

}
