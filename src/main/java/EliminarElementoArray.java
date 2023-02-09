import java.util.ArrayList;

public class EliminarElementoArray {
    public static void main(String[] args) {
        int [] a = new int[]{0,1,2,3,4,5,6,6,7};
        int [] b = removeElement(a,8);
        ArrayList<Integer> a2 = new ArrayList<>();
        a2.remove(4);

        for (int c:
             b) {
            System.out.println(c);
        }
    }

    public static int[] removeElement(int[] arr, int pos) {
        int[] salida = new int[arr.length-1];
        for (int i = 0, k=0; i < arr.length; i++) {
            if(i!=pos){
                salida[k]=arr[i];
                k++;
            }
        }
        return salida;
    }

}
