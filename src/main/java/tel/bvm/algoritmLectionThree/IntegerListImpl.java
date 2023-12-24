package tel.bvm.algoritmLectionThree;

import org.springframework.stereotype.Service;
import tel.bvm.algoritmLectionThree.exception.InvalidIndexException;
import tel.bvm.algoritmLectionThree.exception.NullPointerItemException;
import tel.bvm.algoritmLectionThree.exception.StorageIsFullException;

import java.util.Arrays;

@Service
public class IntegerListImpl implements IntegerList {

    private final Integer[] storage;
    private int size;

    public IntegerListImpl() {
        storage = new Integer[10];
    }

    public IntegerListImpl(int initSize) {
        storage = new Integer[initSize];
    }

    private void validateItem(Integer item) {
        if (item == null) {
            throw new NullPointerItemException();
        }
    }

    private void validateSize() {
        if (size == storage.length) {
            throw new StorageIsFullException();
        }
    }

    private void validateIndex(int index) {
        if (index >= size) {
            throw new InvalidIndexException();
        }
    }

    private void sort(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private boolean binarySearch(Integer[] arr, Integer item) {
        int min = 0;
        int max = arr.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item == arr[mid]) {
                return true;
            }

            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public Integer add(Integer item) {
//        validateItem(item);
//        validateSize();
        storage[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {

//        for (int i = 0; i < storage.length; i++) {
//            if (storage[i].isEmpty()) {
//                System.out.println("Found empty Integer at index " + i);
//                break;
//            }

//            if (Arrays.stream(storage).anyMatch(s -> isEmpty())) {
//                Integer[] storage = new Integer[]{
//                        "0 - zero",
//                        "1 - one",
//                        "2 - two",
//                        "3 - three",
//                        "4 - four",
//                        "5 - five",
//                        "6 - six",
//                        "7 - seven",
//                        "8 - eight",
//                        "9 - nine"};
//            }

        validateItem(item);
        validateSize();
//        validateIndex(index);

        if (index == size) {
            storage[size++] = item;
            return item;
        }
//        System.arraycopy(storage, index, storage, index + 1, size - index);
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        storage[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);

        int index = indexOf(item);
//        if (index == -1) {
//            throw new ElementNotFoundException();
//        }
//
////        if (index == size) {
////            storage[size--] = null;
////            return item;
////        }
//
//        if (index != size) {
//        System.arraycopy(storage, index + 1, storage, index, size - (index + 1));
//        }
//
//        size--;
        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);
        Integer item = storage[index];

        if (index != size) {
            System.arraycopy(storage, index + 1, storage, index, size - (index + 1));
        }

        return item;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] storageCopy = toArray();
        sort(storageCopy);
        return binarySearch(storageCopy, item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        return storage[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(storage, size);
    }
}
