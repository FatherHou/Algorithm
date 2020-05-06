/**
 * @author hou
 *
 */
public class Gaojingdu {
	public static void main(String[] args) {
	  int[] A= new int[101]; 
	  int[] B= new int[101]; 
	  int i,j,k,n,r;
	  A[1]=1;
	  for(i=2;i<=100;i++) {  //置初值：首位为1，其余为0
	      A[i]=0;
	  }
	  n=1;   
	  i=1; 
	  while(A[1]<=9)
	    {  
	      if(i>=n) {   //发现有更大的满足条件的高精度数据
	          n=i;    //转存到数组B中
	          for(k=1;k<=n;k++)  
	              B[k]=A[k];
	        }
	      i=i+1;
	      r=0;
	      for(j=1;j<=i;j++) { //检查第i位是否满足条件
	          r=r*10+A[j]; 
	          r=r%i;
	        }
	      if(r!=0) {          //若不满足条件
	         A[i]=A[i]+i-r ;      //第i位可能的解
	         while (A[i]>9&&i>1) { //搜索完第i位的解，回溯到前一位
	                   A[i]=0;  
	                   i=i-1;  
	                   A[i]=A[i]+i;
	               }   
	        }
	    }
	  System.out.println("最终结果:");
	  for (k=1;k<=n;k++) {
	      System.out.print(B[k]);
	  }
	}
}
