package test;

/**
 * Created by 文江 on 2017/7/19.
 */
public class Test2 {



    public static void main(String[] args) {
        int i=32;
        System.out.println(ispower2(i));
        int[] a= new int[]{1, 2, 4, 5, 6};
        System.out.println(searchMissNum(a));
    }

   public static boolean ispower2(int i){
        return  (i & (i - 1))==0;
   }

    /**
     *在一个连续数组中找出丢失的数
     * @param num
     * @return
     */
   public  static int searchMissNum(int num[]){
       int length=num.length;
       int add=0;
       for(int i=1;i<=length;i++){
           add+=i;
       }
       int add2=0;
       for(int i=0;i<length;i++){
           add2+=num[i];
       }

           return add-add2;
   }

}
