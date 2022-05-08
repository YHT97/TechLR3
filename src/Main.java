import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static class Data {
        public int[] index;
        public int data;
        public String str;

        public Data(int[] index, int data, String str) {
            this.index = index;
            this.data = data;
            this.str = str;
        }
    }

    static class Node<T> {
        private final int INIT_SIZE = 6;
        private Object[] class_array = new Object[INIT_SIZE];
        private int pointer = 0;

        public void resize(int newLenght) {
            Object[] NewArray = new Object[newLenght];
            System.arraycopy(class_array, 0, NewArray, 0, pointer);
            class_array = NewArray;
        }

        public T get(int index) {
            return (T) class_array[index];
        }

        public int size() {
            return pointer;
        }


        public void add(T element , int index) {
            if (pointer == class_array.length - 1) resize(class_array.length * 2);
            class_array[pointer++] = element;
        }
        public void add_index(T element,int index){
            if(index> class_array.length-1) resize(class_array.length+2);
            Object[] tmp = new Object[class_array.length+1];
            if (index >= 0) System.arraycopy(class_array, 0, tmp, 0, index);
            tmp[index]=element;
            if (class_array.length - index >= 0) {
                System.arraycopy(class_array, index, tmp, index + 1, class_array.length - index);
            }
            resize(tmp.length);
            class_array=tmp;
            pointer++;
        }

        public void remove(int index) {
            if (pointer - index >= 0) System.arraycopy(class_array, index + 1, class_array, index, pointer - index);
            class_array[pointer] = null;
            pointer--;
        }

        public void clear(Node<Data> array) {
            for (int i = 0; i < array.size(); i++) {
                array.remove(i);
            }
        }
    }
    static int[] getArray() {
        int[] array = new int[3];
        for (int i = 0; i < 3; i++) {
            array[i] = (int) (Math.random() * 200) - 100;
        }
        return array;
    }
    public static String RandString(){
        String alp = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rand = new Random();
        StringBuilder build = new StringBuilder();
        for(int i=0;i<10;i++){
            build.append(alp.charAt(rand.nextInt(alp.length())));
        }
        return build.toString();
    }

    public static void main(String[] args) {
        Node<Data> block = new Node<>();
        for(int i=0;i<block.size();i++) {

            System.out.println(Arrays.toString(block.get(i).index));
            System.out.println(block.get(i).data);
            System.out.println(block.get(i).str);
            System.out.println("---------------------");
        }
    }
}