package 算法;
import java.util.Arrays;
import java.util.Scanner;
public class Sort {
	// 冒泡排序
	public void bubbleSort(int array[]) {
		int t = 0;
		for (int i = 0; i < array.length - 1; i++)
			for (int j = 0; j < array.length - 1 - i; j++)
				if (array[j] > array[j + 1]) {
					t = array[j];
					array[j] = array[j + 1];
					array[j + 1] = t;
				}
	}

	// 选择排序
	public void selectSort(int array[]) {
		int t = 0;
		for (int i = 0; i < array.length - 1; i++){
			int index=i;
			for (int j = i + 1; j < array.length; j++)
				if (array[index] > array[j])
					index=j;
			if(index!=i){ //找到了比array[i]小的则与array[i]交换位置
				t = array[i];
				array[i] = array[index];
				array[index] = t;
			}
		}
	}
	
	//插入排序
	public void insertionSort(int array[]) {
			int i, j, t = 0;
			for (i = 1; i < array.length; i++) {
				if(array[i]<array[i-1]){
					t = array[i];
					for (j = i - 1; j >= 0 && t < array[j]; j--)
						array[j + 1] = array[j];
					//插入array[i]
					array[j + 1] = t;
				}
			}
	}

	// 分治法快速排序
	public void quickSort(int array[], int low, int high) {// 传入low=0，high=array.length-1;
		int pivot, p_pos, i, t;// pivot->位索引;p_pos->轴值。
		if (low < high) {
			p_pos = low;
			pivot = array[p_pos];
			for (i = low + 1; i <= high; i++)
				if (array[i] > pivot) {
					p_pos++;
					t = array[p_pos];
					array[p_pos] = array[i];
					array[i] = t;
				}
			t = array[low];
			array[low] = array[p_pos];
			array[p_pos] = t;
			// 分而治之
			quickSort(array, low, p_pos - 1);// 排序左半部分
			quickSort(array, p_pos + 1, high);// 排序右半部分
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in); //接受用户信息
		int[] array = {11,14,54,32,234,5445,66,54,333,222,1,3,54,88,90,65,278,34,345,76,54,43,234,76,8,99,21,2,4,5,6,7,6,45,32,54,67,87,90,213,43,6,9,1,432432,6675,2322,34,5,7,8,12321,5455,677,8,9,3,342324,5,6,43,6,876,52335,11,14,54,32,234,5445,66,54,333,222,1,3,54,88,90,65,278,34,345,76,54,43,234,76,8,99,21,2,4,5,6,7,6,45,32,54,67,87,90,213,43,6,9,1,432432,6675,2322,34,5,7,8,12321,5455,677,8,9,3,342324,5,6,43,6,876,52335,11,14,54,32,234,5445,66,54,333,222,1,3,54,88,90,65,278,34,345,76,54,43,234,76,8,99,21,2,4,5,6,7,6,45,32,54,67,87,90,213,43,6,9,1,432432,6675,2322,34,5,7,8,12321,5455,677,8,9,3,342324,5,6,43,6,876,52335};
//		for(int i=0;i<10;i++){
//			String t = in.next();
//			array[i] = Integer.valueOf(t);
//		}
		System.out.println("排序前：" + Arrays.toString(array));
		Sort st = new Sort();
		long startTime = System.currentTimeMillis();//获取当前时间
//		st.bubbleSort(array);
//		st.selectSort(array);
//		st.insertionSort(array);
		st.quickSort(array, 0, array.length - 1);
		long endTime = System.currentTimeMillis();
		System.out.println("排序后：" + Arrays.toString(array));
		System.out.println("排序时间："+(endTime-startTime)+"ms");
	}

}