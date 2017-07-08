package test;

import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.RandomAccess;

/**
 * Created by 文江 on 2017/6/30.
 */
public class Test {
    /**
     * 随机输出一个集合数组
     * @param list
     */
    private static int tag=5;
    private  static  Random r=new Random();
    public void randomoutArray(List list){
        Random rnd = r;
        if (rnd == null) r = rnd = new Random();
        int size = list.size();
        if (size < tag || list instanceof RandomAccess) {
            for (int i=size; i>1; i--)
                swap(list, i-1, rnd.nextInt(i));
        } else {
            Object arr[] = list.toArray();
            for (int i=size; i>1; i--)
                swap(arr, i-1, rnd.nextInt(i));
            ListIterator it = list.listIterator();
            for (int i=0; i<arr.length; i++) {
                it.next();
                it.set(arr[i]);
            }
        }
    }
    /**
     * 将第i个位置和第j个位置交换
     * @param list
     * @param i
     * @param j
     */
    public static void swap(List<?> list, int i, int j) {
        final List l = list;
        l.set(i, l.set(j, l.get(i)));
    }
    /**
     * 将第i个位置和第j个位置交换
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(Object[] arr, int i, int j) {
        Object tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
