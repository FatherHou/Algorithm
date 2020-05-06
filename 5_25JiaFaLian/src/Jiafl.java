import java.util.Scanner;

/**
 * @author hou
 *
 */
public class Jiafl {
	 private static int MAX = 10000;
     private static int n,best,lb,ub,t;
     private static int[] chain,hby,parent;
     private static boolean found;
     
     public static void main(String[] args){
    	 System.out.println("长度: ");
         Scanner in = new Scanner(System.in);
         while (true){
             best = MAX;
             n = in.nextInt();
             chain = new int[MAX];
             hby = new int[MAX];
             parent = new int[MAX+1];
             if(n <= MAX){
            	 search(); 
                 System.out.println(best-1);
                 for(int i=1; i<=best; i++)
                     System.out.print(chain[i]+" ");
                 System.out.println();
             }
             else 
            	 System.out.println("请输入更小的数字");
         }
     }
     
     private static void search(){
         lb = lower(n);
         ub = powertree(n);
         int j=0;
         while(!isOdd(n)){
        	 n=n/2; 
             n++;
         }
         t = j-1;
         if(lb < ub){
             found = false;
             while (!found){
                 System.out.println("lb="+lb);
                 hby[1] = 1;
                 backtrack(1);
                 lb++;
                 if(lb == ub) found=true;
             }
         }
     }
     
     private static int powertree(int n){
         found = false;
         ub = 1;
         for(int i=1; i<=MAX; i++) parent[i]=0;
         while (!found){
             hby[1] = 1;
             find(1);
             ub++;
         }

         return best;
     }
     
     private static void find(int step){
         int i,k;
         if(!found)
             if(hby[step] == n){
                 best = step;
                 for(i=1; i<=best; i++) chain[i]=hby[i];
                 found = true;
                 return;
             }else if(step <= ub)
                 for(i=1; i<=step; i++){
                     k = hby[step]+hby[i];
                     if(k <= n){
                         hby[step+1] = k;
                         if(parent[k] == 0) parent[k]=hby[step];
                         if(parent[k] == hby[step]) find(step+1);
                     }
                 }
     }
     
     private static void backtrack(int step){
         if(!found)
             if(hby[step] == n){
                 best = step;
                 for(int i=1; i<=best; i++)
                     chain[i] = hby[i];
                 found = true;

                 return;
             }else if(step < lb)
                 for(int i=step; i>=1; i--)
                     if(2*hby[i] > hby[step])
                         for(int j=i; j>=1; j--){
                             int k = hby[i]+hby[j];
                             hby[step+1] = k;
                             if(k>hby[step] && k<=n)
                                 if(!pruned(step+1))
                                     backtrack(step+1);
                         }
     }
     
     private static boolean pruned(int step){
         if(step < lb-t-1) 
        	 return (dea(3*hby[step])+step+2 > lb);
         else 
        	 return (dea(hby[step])+step > lb);
     }

     private static int dea(int num){
         int i=0;
         while(num<n){
        	 num=num/2;
        	 i++;
         }
         return i;
     }

     private static int lower(int m){
         int i=0,j=1,e=0,f=1;
         while(m>1){
        	 i++; 
        	 if(isOdd(m)) 
        		 j++; 
        	 m=m/2;
         }
         while(j>1){
        	 e++;
        	 if(isOdd(j))
        		 f++;
        	 j=j/2;
         }
         if(f>1)
        	 e++;
         i += e+1;

         return i;
     }

     private static boolean isOdd(int num){
    	 if(num%2 == 1)
    		 return true;
    	 else 
    		 return false;
     }
}
