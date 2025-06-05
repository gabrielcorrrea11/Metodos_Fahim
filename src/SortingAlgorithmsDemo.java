import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class SortingAlgorithmsDemo {

    public static void main(String[] args) {
        int[] array = generateRandomArray(15, 1, 100);

        System.out.println("Array original: " + Arrays.toString(array));

        // Demonstração de vários algoritmos de ordenação
        System.out.println("\n=== Algoritmos de Ordenação ===");

        // Bubble Sort
        int[] bubbleArray = array.clone();
        bubbleSort(bubbleArray);
        System.out.println("Bubble Sort:    " + Arrays.toString(bubbleArray));

        // Selection Sort
        int[] selectionArray = array.clone();
        selectionSort(selectionArray);
        System.out.println("Selection Sort: " + Arrays.toString(selectionArray));

        // Insertion Sort
        int[] insertionArray = array.clone();
        insertionSort(insertionArray);
        System.out.println("Insertion Sort: " + Arrays.toString(insertionArray));

        // Quick Sort
        int[] quickArray = array.clone();
        quickSort(quickArray, 0, quickArray.length - 1);
        System.out.println("Quick Sort:     " + Arrays.toString(quickArray));

        // Merge Sort
        int[] mergeArray = array.clone();
        mergeSort(mergeArray, 0, mergeArray.length - 1);
        System.out.println("Merge Sort:     " + Arrays.toString(mergeArray));

        // Shell Sort
        int[] shellArray = array.clone();
        shellSort(shellArray);
        System.out.println("Shell Sort:     " + Arrays.toString(shellArray));

        // Collections.sort (usando List)
        List<Integer> list = new ArrayList<>();
        for (int num : array) list.add(num);
        Collections.sort(list);
        System.out.println("Collections.sort:" + list);

        // Bogo Sort (cuidado com arrays grandes!)
        int[] bogoArray = array.clone();
        if (array.length < 10) { // Só para arrays pequenos
            bogoSort(bogoArray);
            System.out.println("Bogo Sort:      " + Arrays.toString(bogoArray));
        } else {
            System.out.println("Bogo Sort:      [não demonstrado para arrays grandes]");
        }

        // Heap Sort
        int[] heapArray = array.clone();
        heapSort(heapArray);
        System.out.println("Heap Sort:      " + Arrays.toString(heapArray));

        // Counting Sort
        int[] countingArray = array.clone();
        countingSort(countingArray);
        System.out.println("Counting Sort:  " + Arrays.toString(countingArray));

        // Tim Sort (usando Arrays.sort que implementa TimSort em Java)
        int[] timArray = array.clone();
        Arrays.sort(timArray);
        System.out.println("Tim Sort:       " + Arrays.toString(timArray));

        // Bucket Sort
        int[] bucketArray = array.clone();
        bucketSort(bucketArray);
        System.out.println("Bucket Sort:    " + Arrays.toString(bucketArray));

        // Radix Sort
        int[] radixArray = array.clone();
        radixSort(radixArray);
        System.out.println("Radix Sort:     " + Arrays.toString(radixArray));

        // Cocktail Sort
        int[] cocktailArray = array.clone();
        cocktailSort(cocktailArray);
        System.out.println("Cocktail Sort:  " + Arrays.toString(cocktailArray));

        // GNOME SORT (com ênfase especial)
        System.out.println("\n=== GNOME SORT (Algoritmo de apresentação) ===");
        int[] gnomeArray = array.clone();
        System.out.println("Antes do Gnome Sort: " + Arrays.toString(gnomeArray));
        gnomeSort(gnomeArray);
        System.out.println("Depois do Gnome Sort:" + Arrays.toString(gnomeArray));
        explainGnomeSort();
    }

    // Método para gerar array aleatório
    private static int[] generateRandomArray(int size, int min, int max) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }
        return array;
    }

    // ====== IMPLEMENTAÇÕES DOS ALGORITMOS ======

    // Bubble Sort
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    // Selection Sort
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            int minIdx = i;
            for (int j = i+1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    // Insertion Sort
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    // Quick Sort
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // Merge Sort
    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(arr, l, L, 0, n1);
        System.arraycopy(arr, m + 1, R, 0, n2);

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Shell Sort
    public static void shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = n/2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }

    // Bogo Sort (apenas para demonstração - extremamente ineficiente)
    public static void bogoSort(int[] arr) {
        while (!isSorted(arr)) {
            shuffle(arr);
        }
    }

    private static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i-1]) {
                return false;
            }
        }
        return true;
    }

    private static void shuffle(int[] arr) {
        Random rnd = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
    }

    // Heap Sort
    public static void heapSort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest]) {
            largest = l;
        }

        if (r < n && arr[r] > arr[largest]) {
            largest = r;
        }

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }

    // Counting Sort
    public static void countingSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int range = max - min + 1;

        int[] count = new int[range];
        int[] output = new int[arr.length];

        for (int j : arr) {
            count[j - min]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    // Bucket Sort
    public static void bucketSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int bucketCount = 10;

        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        for (int num : arr) {
            int bucketIndex = (num - min) * (bucketCount - 1) / (max - min);
            buckets.get(bucketIndex).add(num);
        }

        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }

        int index = 0;
        for (List<Integer> bucket : buckets) {
            for (int num : bucket) {
                arr[index++] = num;
            }
        }
    }

    // Radix Sort
    public static void radixSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }

    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int j : arr) {
            count[(j / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    // Cocktail Sort
    public static void cocktailSort(int[] arr) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) break;

            swapped = false;
            for (int i = arr.length - 2; i >= 0; i--) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
    }

    // GNOME SORT (algoritmo de apresentação)
    public static void gnomeSort(int[] arr) {
        int index = 0;
        int n = arr.length;

        while (index < n) {
            if (index == 0) {
                index++;
            }
            if (arr[index] >= arr[index - 1]) {
                index++;
            } else {
                int temp = arr[index];
                arr[index] = arr[index - 1];
                arr[index - 1] = temp;
                index--;
            }
        }
    }

    // Explicação do Gnome Sort
    public static void explainGnomeSort() {
        System.out.println("\nExplicação do Gnome Sort:");
        System.out.println("1. O Gnome Sort é um algoritmo de ordenação semelhante ao Insertion Sort,");
        System.out.println("   mas com uma lógica mais simples.");
        System.out.println("2. Ele funciona da seguinte forma:");
        System.out.println("   - Começa no início do array");
        System.out.println("   - Compara o elemento atual com o anterior");
        System.out.println("   - Se estiverem na ordem correta, avança para o próximo elemento");
        System.out.println("   - Se não estiverem, troca os elementos e retrocede uma posição");
        System.out.println("   - Repete até chegar ao final do array");
        System.out.println("3. Complexidade:");
        System.out.println("   - Melhor caso: O(n) - quando o array já está ordenado");
        System.out.println("   - Pior caso: O(n²) - quando o array está em ordem inversa");
        System.out.println("4. Vantagens:");
        System.out.println("   - Implementação simples e fácil de entender");
        System.out.println("   - Não usa recursão");
        System.out.println("   - Eficiente para arrays pequenos ou quase ordenados");
        System.out.println("5. Curiosidade: Também é conhecido como 'Stupid Sort' ou 'Bozo Sort'");
    }
}