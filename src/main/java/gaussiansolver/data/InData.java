package gaussiansolver.data;

import org.springframework.context.annotation.Configuration;

/**
 * Created by Josef Mayer on 20.06.2017.
 */

@Configuration
public class InData {

    public InData indat(){
        return new InData();
    }

    public double[] a;
    public double[] b;
    //public double [] result;
}
