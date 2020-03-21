import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: shenchao
 * @Date: created in 21:51 2018/3/22
 * @Description:
 */
public class FormulaTest {


    @Test
    public void testFormula(){
        List<String> names = Arrays.asList("alice","peter","tom","jerry");
        System.out.println(names);
        Collections.sort(names,(a,b)->b.compareTo(a));
        System.out.println(names);

        Comparator<Integer> com = (x,y)->Integer.compare(x,y);


    }

}
