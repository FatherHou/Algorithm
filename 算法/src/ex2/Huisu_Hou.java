package ex2;

import java.util.Arrays;

/**
 * @author hou
 *
 */
public class Huisu_Hou {
	 static int[][] test = {{-1,30,6,4},{30,-1,5,10},{6,5,-1,20},{4,10,20,-1}};//一组临时规模的城市数据
	 static int bestl = 63355;//最佳路径
	 static int bestt[] = new int[4];//最佳路径子结点
	 static int templ = 0;//临时最佳路径(用于存储)
	 static int tempt[] = new int[4];//临时最佳路径子结点(用于存储)
	 static int n = 4;//城市数据规模
	 static int sure[] = new int[4];//通过这个数组记录某个城市是否走过，走过记为1，没走过记为0
	 public static void travel(int city){
		 if(city>=n){//如果到了最后一个城市
			 if(templ<bestl){
				 bestl = templ;//如果临时最佳路径更小，将临时最佳路径赋值给最佳路径
				 for(int i=0;i<4;i++){
					 bestt[i]=tempt[i];//更新最佳路径子结点
				 }
				 System.out.println("当前最短路径为:"+Arrays.toString(bestt)+",长度为:"+bestl);
			 }
		 }
		 for(int i=0;i<n;i++){
			 if(sure[i]==0&&test[tempt[city-1]][i]!=-1){//设置某城市到某城市自己的距离为-1用于检测，判断城市是否走过
				 templ = templ + test[tempt[city-1]][i];
				 tempt[city] = i;//更新最佳路径子结点
				 sure[i] = 1;//更新走过的路
				 travel(city+1);
				 
				 //回退到上一子结点
				 sure[i]=0;
				 tempt[city] = -1;
				 templ = templ - test[tempt[city-1]][i];
			 }
		 }
	 }

    public static void main(String[] args) {

    	sure[0] = 1;//从第一个城市开始出发，记0城市(即第一个城市)为1
    	tempt[0] = 0;//最佳路径的第一个城市为0城市(即第一个城市)
    	travel(1);//开始回溯遍历

    	
    	System.out.println("-------------------");
    	System.out.println("最终最短路径为:"+Arrays.toString(bestt));
    	System.out.println("长度为:"+bestl);
    }
}
