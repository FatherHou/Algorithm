package ex2;

import java.util.Arrays;

public class Fenzhi_Hou {
	 static int[][] test = {{-1,30,6,4},{30,-1,5,10},{6,5,-1,20},{4,10,20,-1}};
	 static int bestl = 63355;
	 static int bestt[] = new int[5];
	 static int templ = 0;
	 static int tempt[] = new int[4];
	 static int n = 4;
	 static int sure[] = new int[4];
	 static int count = 1;
	 static int ub = 0;
	 static double db = 0;
	 static double db1 = 0;
	 static double db2 = 0;
	 static double tempDb = 0;
	 
	 public static void travel(int city){
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
   		
		 if(city>=n){//如果到了最后一个城市
			 if(templ<bestl){
				 bestl = templ + test[tempt[city-1]][0];//如果临时最佳路径更小，将临时最佳路径赋值给最佳路径
				 for(int i=0;i<4;i++){
					 bestt[i]=tempt[i];//更新最佳路径子结点
				 }
				 bestt[4] = 0;
				 System.out.println("当前最短路径为:"+Arrays.toString(bestt)+",长度为:"+bestl);
			 }
		 }
		 for(int i=0;i<n;i++){
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
						 travel(city+1);
						 
						 //回退
						 sure[i]=0;
						 tempt[city] = -1;
						 templ = templ - test[tempt[city-1]][i];
					 }
				 }else{//如果是两个最小的城市，就进行正常的遍历
					 templ = templ + test[tempt[city-1]][i];
					 tempt[city] = i;
					 sure[i] = 1;
					 travel(city+1);
					 
					 //回退
					 sure[i]=0;
					 tempt[city] = -1;
					 templ = templ - test[tempt[city-1]][i];
				 }
			 }
		 }
	 }

   public static void main(String[] args) {
	   
	//确定上界
	int[] ubSure = new int[4];//用于确定上界路径是否走过的数组
	int ubCount = 0;
	//使用贪心法求出上界
	for(int i=0;ubCount!=4;){//判断是否走完所有路径
		ubCount++;
		int ubTemp = 999;
		int ii = 0;
		ubSure[i]=1;
		for(int j=0;j<test[i].length;j++){
			if(ubSure[j]==0){
				if(test[i][j]<ubTemp&&test[i][j]!=-1){
					ubTemp=test[i][j];
					ii=j;
				}
			}
		}
		if(ubCount!=4){
			ub = ub + ubTemp;
			i = ii;
		}else{
			ub = ub + test[0][i];
		}
	}
   	System.out.println("上界:"+ub);
   	
   	//确定下界
   	//法一:邻接矩阵每行最小两个元素相加除以2，并向上取整
   	for(int i=0;i<test.length;i++){
   		double dbTemp = 999.0;//设置临时变量存放第一个最小元素
   		double dbTemp2 = 999.0;//设置临时变量存放第二个最小元素
   		for(int j=0;j<test[i].length;j++){
   			if(test[i][j]<dbTemp&&test[i][j]!=-1){
   				dbTemp = test[i][j];
   			}
   		}
   		for(int j=0;j<test[i].length;j++){
   			if(test[i][j]<dbTemp2&&test[i][j]!=dbTemp&&test[i][j]!=-1){
   				dbTemp2 = test[i][j];
   			}
   		}
   		db1 = db1 + Math.ceil((dbTemp+dbTemp2)/2);
   	}
   	System.out.println("下界1:"+db1);
    //法二:图邻接矩阵每行最小元素相加
   	for(int i=0;i<test.length;i++){
   		double dbTemp = 999.0;
   		for(int j=0;j<test[i].length;j++){
   			if(test[i][j]<dbTemp&&test[i][j]!=-1){
   				dbTemp = test[i][j];
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
   	//与回溯法相同开始遍历
   	sure[0] = 1;
   	tempt[0] = 0;
   	travel(1);

   	
   	System.out.println("-------------------");
   	System.out.println("最终最短路径为:"+Arrays.toString(bestt));
   	System.out.println("长度为:"+bestl);
   }
}
