/**
 * 
 */
package ex2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author hou
 *
 */
public class Compare {
//	private static int[][] test = {{-1,30,6,4},{30,-1,5,10},{6,5,-1,20},{4,10,20,-1}};
	 static int bestl = 63355;//最佳路径
	 static int bestt[] = new int[100];//最佳路径子结点
	 static int templ = 0;//临时最佳路径(用于存储)
	 static int tempt[] = new int[100];//临时最佳路径子结点(用于存储)
	 static int sure[] = new int[100];//通过这个数组记录某个城市是否走过，走过记为1，没走过记为0
	 static int ub = 0;//上界
	 static double db = 0;//下界
	 static double db1 = 0;
	 static double db2 = 0;
	 static double tempDb = 0;

	public static void travel(int city,int[][] args){
		 
		 if(city>=args.length){//如果到了最后一个城市
			 if(templ<bestl){
				 bestl = templ + args[tempt[city-1]][0];//如果临时最佳路径更小，将临时最佳路径赋值给最佳路径
				 for(int i=0;i<args.length;i++){
					 bestt[i]=tempt[i];//更新最佳路径子结点
				 }
//				 System.out.println("当前最短路径为:"+Arrays.toString(bestt)+",长度为:"+bestl);
			 }
		 }
		 for(int i=0;i<args.length;i++){
			 if(sure[i]==0&&args[tempt[city-1]][i]!=-1){//设置某城市到某城市自己的距离为-1用于检测，判断城市是否走过
				 templ = templ + args[tempt[city-1]][i];
				 tempt[city] = i;//更新最佳路径子结点
				 sure[i] = 1;//更新走过的路
				 travel(city+1,args);
				 
				 //回退到上一子结点
				 sure[i]=0;
				 tempt[city] = -1;
				 templ = templ - args[tempt[city-1]][i];
			 }
		 }
	 }
	
	 public static void travel2(int city,int[][] test){
			double dbTemp = 999.0;//邻接矩阵这行的第一个最小值
	   		double dbTemp2 = 999.0;//邻接矩阵这行的第二个最小值
	   		for(int j=0;j<test[tempt[city-1]].length;j++){
	   			if(test[tempt[city-1]][j]<dbTemp&&test[tempt[city-1]][j]!=-1){
	   				dbTemp = test[tempt[city-1]][j];
	   			}
	   		}
	   		for(int j=0;j<test[tempt[city-1]].length;j++){
	   			if(test[tempt[city-1]][j]<dbTemp2&&test[tempt[city-1]][j]!=dbTemp&&test[tempt[city-1]][j]!=-1){
	   				dbTemp2 = test[tempt[city-1]][j];
	   			}
	   		}
	   	    //设置一个值便于增加临时的下界来和上界比较
	   		double rdb = 0;
	   		if(dbTemp>dbTemp2){
	   			rdb=dbTemp;
	   		}else{
	   			rdb=dbTemp2;
	   		}
	   		
			 if(city>=test.length){//如果到了最后一个城市
				 if(templ<bestl){
					 bestl = templ + test[tempt[city-1]][0];//如果临时最佳路径更小，将临时最佳路径赋值给最佳路径
					 for(int i=0;i<test.length;i++){
						 bestt[i]=tempt[i];//更新最佳路径子结点
					 }
//					 System.out.println("当前最短路径为:"+Arrays.toString(bestt)+",长度为:"+bestl);
				 }
			 }
			 for(int i=0;i<test.length;i++){
				 tempDb = db;
				 if(sure[i]==0&&test[tempt[city-1]][i]!=-1){//设置某城市到某城市自己的距离为-1用于检测，判断城市是否走过
					 if(test[tempt[city-1]][i]!=dbTemp&&test[tempt[city-1]][i]!=dbTemp2){//判断这个城市是否为最小的两个值之一
						 tempDb = tempDb + test[tempt[city-1]][i] - rdb;//如果不是两个最小的城市，根据差值变更临时下界
						 if(tempDb>=ub){//临时下界大于上界，剪枝
							 continue;
						 }else{//临时下界不大于上界，正常向后进行遍历
							 templ = templ + test[tempt[city-1]][i];
							 tempt[city] = i;
							 sure[i] = 1;
							 travel2(city+1,test);
							 
							 //回退
							 sure[i]=0;
							 tempt[city] = -1;
							 templ = templ - test[tempt[city-1]][i];
						 }
					 }else{//如果是两个最小的城市，就进行正常的遍历
						 templ = templ + test[tempt[city-1]][i];
						 tempt[city] = i;
						 sure[i] = 1;
						 travel2(city+1,test);
						 
						 //回退
						 sure[i]=0;
						 tempt[city] = -1;
						 templ = templ - test[tempt[city-1]][i];
					 }
				 }
			 }
		 }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    Random r = new Random();
		List<Integer> ran = new ArrayList<Integer>();
		int count = 0;
		while(count < 5){//生成五个不同规模的二维数组
			int number = r.nextInt(10) + 1;//当前城市的规模为在1-10内随机选取
			boolean flag = true;
			for(int i=0;i<ran.size();i++){
				if(number == ran.get(i)){
					flag = false;
				}
			}
			if(flag){
				if(number >= 4){//在规模为4一下的城市无需进行计算，舍去
					ran.add(number);
					count++;
				}
			}
		}
//		for(int i=0;i<ran.size();i++){
//			System.out.println(ran.get(i));
//		}
		for(int i=0;i<ran.size();i++){
			//实现随机的数据
			int scount = 0;
			//制作邻接矩阵
			int[][] sran = new int[ran.get(i)][ran.get(i)];
			while(scount < ran.get(i)){
				for(int j=0;j< scount+1;j++){
					if(scount==j){
						sran[scount][j] = -1;
					}else{
						int snumber = r.nextInt(10) + 1;
						sran[scount][j] = snumber;
						sran[j][scount] = snumber;
					}
				}
				scount++;
			}
			System.out.println(ran.get(i) + "个城市的数据:");
			for(int k=0;k<sran.length;k++){
				for(int l=0;l<sran[k].length;l++)
					System.out.print(sran[k][l] + " ");
				System.out.println();
			}
			System.out.println();
			int[][] temp = sran;
		   	bestl = 63355;
		   	bestt = new int[sran.length];
		   	templ = 0;
		   	tempt = new int[sran.length];
		   	sure = new int[sran.length];
		   	sure[0] = 1;//从第一个城市开始出发，记0城市(即第一个城市)为1
		   	tempt[0] = 0;//最佳路径的第一个城市为0城市(即第一个城市)
		   	
		   	System.out.println("回溯法:");
		   	long begin = System.nanoTime();
		   	travel(1,temp);//开始回溯遍历

		   	long end = System.nanoTime();
		   	System.out.println("最终最短路径为:"+Arrays.toString(bestt));
		   	System.out.println("长度为:"+bestl);
		   	System.out.println("用时: "+(end-begin)+" 纳秒");//统计算法用时
		   	System.out.println("-------------------");
		   	
		   	
		   	
		    temp = sran;
			//确定上界
		    ub=0;
		    db1=0;
		    db2=0;
		    db=0;
			int[] ubSure = new int[sran.length];//用于确定上界路径是否走过的数组
			int ubCount = 0;
			//使用贪心法求出上界
			for(int k=0;ubCount!=sran.length;){//判断是否走完所有路径
				ubCount++;
				int ubTemp = 999;
				int ii = 0;
				ubSure[k]=1;
				for(int j=0;j<sran[k].length;j++){
					if(ubSure[j]==0){
						if(sran[k][j]<ubTemp&&sran[k][j]!=-1){
							ubTemp=sran[k][j];
							ii=j;
						}
					}
				}
				if(ubCount!=sran.length){
					ub = ub + ubTemp;
					k = ii;
				}else{
					ub = ub + sran[0][k];
				}
			}
		   	System.out.println("上界:"+ub);
		   	
		   	//确定下界
		    //法一:邻接矩阵每行最小两个元素相加除以2，并向上取整
		   	for(int k=0;k<sran.length;k++){
		   		double dbTemp = 999.0;//设置临时变量存放第一个最小元素
		   		double dbTemp2 = 999.0;//设置临时变量存放第二个最小元素
		   		for(int j=0;j<sran[k].length;j++){
		   			if(sran[k][j]<dbTemp&&sran[k][j]!=-1){
		   				dbTemp = sran[k][j];
		   			}
		   		}
		   		for(int j=0;j<sran[k].length;j++){
		   			if(sran[k][j]<dbTemp2&&sran[k][j]!=dbTemp&&sran[k][j]!=-1){
		   				dbTemp2 = sran[k][j];
		   			}
		   		}
		   		db1 = db1 + Math.ceil((dbTemp+dbTemp2)/2);
		   	}
		   	System.out.println("下界1:"+db1);
		    //法二:图邻接矩阵每行最小元素相加
		   	for(int k=0;k<sran.length;k++){
		   		double dbTemp = 999.0;
		   		for(int j=0;j<sran[k].length;j++){
		   			if(sran[k][j]<dbTemp&&sran[k][j]!=-1){
		   				dbTemp = sran[k][j];
		   			}
		   		}
		   		db2 = db2 + dbTemp;
		   	}
		   	System.out.println("下界2:"+db2);
		    //将更大但不超过上界的下界赋值给下界
		   	if(db1>db2){
		   		if(db1<=ub){
		   			db=db1;
		   		}else{
		   			db=db2;
		   		}
		   	}else{
		   		db=db2;
		   	}
		   	System.out.println("下界:"+db);
		   	System.out.println("分支限界法:");
		   	bestl = 63355;
		   	bestt = new int[sran.length];
		   	templ = 0;
		   	tempt = new int[sran.length];
		   	sure = new int[sran.length];
		    //与回溯法相同开始遍历
		   	sure[0] = 1;
		   	tempt[0] = 0;
		   	begin = System.nanoTime();
		   	travel2(1,temp);
		   	end = System.nanoTime();
		   	System.out.println("最终最短路径为:"+Arrays.toString(bestt));
		   	System.out.println("长度为:"+bestl);
		   	System.out.println("用时: "+(end-begin)+" 纳秒");
		   	System.out.println("-------------------");
		   	System.out.println("!!!!!!!!!!!!!!!!!!!");

		}


	}

}
