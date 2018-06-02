package cr.ac.tec.ec.utility;

import java.util.List;

/**
 * Created by CASA on 6/2/2018.
 */

public class MString {
    public static String toString(List<String> s){
        String result = "";
        for (int i=0;i<s.size();i++){
            if (i==s.size()-1){
                result = result + s.get(i);
            }else {
                result = result + s.get(i) + ", ";
            }
        }
        return result;
    }
}

