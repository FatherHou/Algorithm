
/**
 * 
 */

/**
 * @author hou
 *
 */
public class PerTree {
	 private int n;		//作业数
	 private int[][] m;	//作业所需的时间
	 private int t1;	//机器一的处理时间
	 private int[] t2;	//机器二的处理时间
	 private int tSum;	//处理时间和
	 private int bestTime;	//最优时间
	 private int[] x;	//当前顺序
	 private int[] bestx;	//当前最优顺序

	 
	 public PerTree(int n,int[][] m){
		 this.n=n;
		 this.m=m;
		 t1=0;
		 t2=new int[n];
		 tSum=0;
		 bestTime=10000;
		 bestx=new int[n];
		 x=new int[n];
		 for(int i=0;i<n;i++){
			x[i]=i;
		 }
	 }
	 
	 public void back(int count){
			if(count>=n){
				for(int j=0;j<n;j++)
					bestx[j]=x[j];
				bestTime=tSum;
			}
			else{
				for(int j=count;j<n;j++){
					t1=t1+m[x[j]][0];//在第一台机器的时间
					if(count!=0){
						//第二台机器的时间等于上一个作业在第二台机器的时间和这次作业在第一台机器的时间中最大的加上这次作业在第二台机器的时间
						t2[count]=((t2[count-1]>t1)?t2[count-1]:t1)+m[x[j]][1];
					}else{
						t2[count]=t1+m[x[j]][1];
					}
					tSum=tSum+t2[count];
					if(tSum<bestTime){
						swap(x,count,j);
						back(count+1);
						swap(x,count,j);
					}
					//回退
					t1=t1-m[x[j]][0];
					tSum=tSum-t2[count];
				}
			}
	 }
	 
	 public void swap(int[] l,int a,int b){
			int temp=l[a];
			l[a]=l[b];
			l[b]=temp;
	 }
	 
	 public static void main(String[] args){
			int nt=4;
			int[][] mt={{2,1},{3,1},{2,3},{4,3}};
			PerTree f = new PerTree(nt,mt);
			f.back(0);
			System.out.println("最优顺序为：");
			for(int i=0;i<nt;i++)
				System.out.print((f.bestx[i]+1)+" ");
			System.out.println();
			System.out.println("所需的最短时间为："+f.bestTime);
	 }
}
