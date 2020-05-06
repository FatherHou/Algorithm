import java.util.Scanner;

/**
 * 
 * @author hou
 *
 */
public class Chenlieguan {
	static int[][] x=new int[1000][1000];      //这些数组大小可以根据自己需要改
	static int[][] y=new int[1000][1000]; 
	static int[][] bestx=new int[10000][10000];  //x用来设置当前警卫，y用来表示监控情况，bestx返回最终结果
	static int n, m, best , k=0;

	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("输入m ");
		m = Integer.parseInt(in.next());
		System.out.print("输入n ");
		in = new Scanner(System.in);
		n = Integer.parseInt(in.next());
		caculate();
		System.out.println(best);
		for(int i = 1;i <= n; i++) {
			for(int j = 1;j <= m;j++) {
			   System.out.print(bestx[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	
	/**
	 * 在整个外面加上一圈，便于处理边界情况
	 */
	public static void caculate() {
		best = m*n/3+2;
		for(int i = 0;i <= m+1;i++) {
			y[0][i] = 1;
			y[n+1][i] = 1;
		}
		for(int i = 0;i <= m+1;i++) {
			y[i][0] = 1;
			y[i][m+1] = 1;
		}
		search(1,0);
	}
	
	/**
	 * 在(i, j)处设置一个警卫，并改变其周围受监控情况
	 * @param i
	 * @param j
	 */
	public static void change(int i,int j) {
		x[i][j] = 1;
		k++;
		y[i][j+1]++;
		y[i+1][j]++;
		y[i][j]++;
		y[i][j-1]++;
		y[i-1][j]++;
	}
	
	/**
	 * 撤销在(i, j)处设置的警卫，并改变其周围受监控情况
	 * @param i
	 * @param j
	 */
	public static void restore(int i,int j) {
		x[i][j] = 0;
		k--;
		y[i][j+1]--;
		y[i+1][j]--;
		y[i][j]--;
		y[i][j-1]--;
		y[i-1][j]--;
	}
	
	/**
	 * 回溯搜索
	 * 从上到下，从左至右搜索没被监控的位置
	 * @param i
	 * @param j
	 */
	public static void search(int i,int j) {
		do {
			j++;
			if(j > m) {
				i++;
				j= 1;
			}
		}while(!((y[i][j] == 0) || (i > n)));
		
		//刷新警卫值
		if(i > n) {
			if(k < best) {
				best = k;
				for(int p = 1;p <= n;p++) 
					for(int q = 1;q <= m;q++) 
						bestx[p][q] = x[p][q];
					return;
				}
			}
		
		if(i < n) {           //结点p
			change(i+1,j);
			search(i,j);      //递归搜索下一个点
			restore(i+1, j);  //恢复
		}
		
		if((y[i][j+1] == 0)) {   //结点q
			change(i,j);
			search(i,j);      
			restore(i, j);
		}
		
		if(((y[i][j+1] == 0) && (y[i][j+2] == 0))) {  //结点q
			change(i,j+1);
			search(i,j);      
			restore(i, j+1);
		}
	}
			
}