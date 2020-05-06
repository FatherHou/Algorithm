/**
 * 
 */
package ex2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author hou
 *
 */
public class RandomCity {
	public static void main(String[] args){
		Random r = new Random();
		List<Integer> ran = new ArrayList<Integer>();
		int count = 0;
		while(count < 10){
			int number = r.nextInt(10) + 1;
			boolean flag = true;
			for(int i=0;i<ran.size();i++){
				if(number == ran.get(i)){
					flag = false;
				}
			}
			if(flag){
				ran.add(number);
				count++;
			}
		}
		for(int i=0;i<ran.size();i++){
			System.out.println(ran.get(i));
		}
		for(int i=0;i<ran.size();i++){
			//实现随机的数据
			int scount = 0;
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
		}
	}
}
