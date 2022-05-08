public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

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

    class Node<T> {
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


        public void add(T element) {
            if (pointer == class_array.length - 1) resize(class_array.length * 2);
            class_array[pointer++] = element;
        }

        public void remove(int index) {
            for (int i = index; i < pointer; i++) {
                class_array[i] = class_array[i + 1];
            }
            class_array[pointer] = null;
            pointer--;
        }

        public void clear(Node<Data> array) {
            for (int i = 0; i < array.size(); i++) {
                array.remove(i);
            }
        }


    }
}