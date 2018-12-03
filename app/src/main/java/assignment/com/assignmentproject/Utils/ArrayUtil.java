package assignment.com.assignmentproject.Utils;

import java.util.List;

public class ArrayUtil {

    public static boolean isEmpty(final List array) {
        if(null == array)
        return true;
        else if(array.isEmpty()){
            return true;
        }
        return false;
    }

    public static boolean isEmpty(final Object[] array) {
        return array == null || array.length<1;
    }
}
