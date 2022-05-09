import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static class Data {
        public int[] index;
        public int data;
        public String str;

        public Data() {
            this.index = getArray();
            this.data = (int)(Math.random()*200-100);
            this.str = RandString();
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
/*--------------------------------------------------------------------------------------------------*/
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
            if(index>= class_array.length-1) resize(class_array.length+2);
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
/*-----------------------------------------------------------------------------------------------------------------------*/


    public static void main(String[] args) {
        boolean Exit =false;
        Node<Data> block = new Node<>();

        Scanner scan = new Scanner(System.in);

        for(int i=0;i<block.size();i++) {
            System.out.println(Arrays.toString(block.get(i).index));
            System.out.println(block.get(i).data);
            System.out.println(block.get(i).str);
            System.out.println("---------------------");
        }
        while(!Exit){
            System.out.println("""
                1 - Add new record on start position
                2 - Add new record on end position
                3 - Add new record on index position
                4 - Remove the record on index position
                5 - Changing the record on index position
                6 - Clear all blocks
                7 - Find the record from block types
                8 - Output all list
                9 - Output last record
               10 - Exit
                """);
            int toggle = scan.nextInt();
            switch (toggle){
                case 1 -> {
                    if(block.size()>6){
                        System.out.println("Record limit exceeded");
                        break;
                    }
                    block.add_index(new Data(),0);
                }
                case 2 ->{
                    if(block.size()>6){
                        System.out.println("Record limit exceeded");
                        break;
                    }
                    block.add_index(new Data(),block.size());
                }
                case 3 -> {
                    System.out.println("Enter index: ");
                    int index = scan.nextInt();
                    if(block.size()>6){
                        System.out.println("Record limit exceeded");
                        break;
                    }
                    block.add_index(new Data(),index);
                }
                case 4 ->{
                    System.out.println("Enter index: ");
                    int index = scan.nextInt();
                    block.remove(index);
                }
                case 5 ->{
                    System.out.println("Enter index: ");
                    int index = scan.nextInt();
                    block.remove(index);
                    block.add_index(new Data(),index);
                }
                case 6 -> {
                    for(int i =0;i<block.pointer;i++){
                        block.remove(i);
                    }
                }
                case 7 ->{
                    System.out.println("""
                            1 - find by array
                            2 - find by number
                            3 - fund by word
                            Choose the type for search:""");
                    toggle = scan.nextInt();
                    switch (toggle){

                        case 1 -> {
                            System.out.print("Enter the array elements: ");
                            String[] NewStrArray = new String[3];
                            for (int j = 0; j < 3; j++) {
                                NewStrArray[j] = scan.next();
                            }
                            for (int i = 0; i < block.size(); i++) {
                                if (Arrays.toString(NewStrArray).equals(Arrays.toString(block.get(i).index)))
                                    System.out.println("Found in " + (i + 1) + "position" + '\n' + "Record: " + Arrays.toString(block.get(i).index) + " " + block.get(i).data + " " + block.get(i).str);
                                else {
                                    System.out.println("Not found in " + (i + 1) + "position");
                                }
                            }
                        }
                        case 2 -> {
                            System.out.println("Enter the number");
                            int tmp = scan.nextInt();
                            for(int i=0;i< block.size();i++) {
                                if (block.get(i).data == tmp) {
                                    System.out.println("Found in " + (i + 1) + "position" + '\n' + "Record: " + Arrays.toString(block.get(i).index) + " " + block.get(i).data + " " + block.get(i).str);
                                }else{
                                    System.out.println("Not found in " + (i + 1) + "position");
                                }
                            }
                        }
                        case 3 -> {
                            System.out.print("Enter the word: ");
                            String res = scan.next();
                            int counter = 0;
                            for (int i = 0; i < block.size(); i++) {
                                if (Objects.equals(res, block.get(i).str)) {
                                    System.out.println("Find in " + counter + " block");
                                    System.out.println(Arrays.toString(block.get(i).index) + " " + block.get(i).data + " " + block.get(i).str);
                                    break;
                                }
                                else {
                                    System.out.println("Not found in " + (counter + 1) + " block");
                                }
                                counter++;
                            }
                        }
                    }

                }
                case 8 ->{
                    for (int i = 0; i < block.size(); i++) {
                        System.out.println(Arrays.toString(block.get(i).index) + " " + block.get(i).data+ " " + block.get(i).str);
                    }
                }
                case 9 -> {

                    if (block.pointer > 0) {
                        System.out.println(Arrays.toString(block.get(block.pointer).index) + " " + block.get(block.pointer).data + " " + block.get(block.pointer).str);
                    }
                }
                case 10 ->{Exit=true;}

            }
        }
    }
}