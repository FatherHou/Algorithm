package Experiment2;

import java.math.BigDecimal;


public class GamePassProbability {
	
	public double calculatePassProbability(int[] p, int num)
	{
        double pass = 0.0;
        double temNum = Integer.valueOf(num).doubleValue();
        temNum = temNum * 0.7;
        temNum = Math.ceil(temNum);
        int tem = Double.valueOf(temNum).intValue() ;
        double[][] caculate = new double[num+1][num+1];
        double[] real = new double[num];
        caculate[0][0] = 1;
//      System.out.println(num);
        for(int i=0;i<num;i++){
        	real[i]= Integer.valueOf(p[i]).doubleValue();
        	real[i]=real[i]/100;
//        	System.out.println("["+i+"]"+real[i]);
        } 
        for (int i=1;i<=num;i++){ 
        	for(int j=1;j<=num;j++){
            	BigDecimal h1 = new BigDecimal("1");
            	BigDecimal h2 = new BigDecimal(String.valueOf(real[i-1]));
            	BigDecimal h3 = new BigDecimal(String.valueOf(caculate[i-1][0]));
            	caculate[i][0]=h3.multiply((h1.subtract(h2))).doubleValue();
//            	System.out.println("["+i+"][0]:"+caculate[i][0]);
        		BigDecimal h4 = new BigDecimal(String.valueOf(caculate[i-1][j-1]));
        		BigDecimal h5 = new BigDecimal(String.valueOf(caculate[i-1][j]));
        		caculate[i][j] = h4.multiply(h2).doubleValue() + h5.multiply((h1.subtract(h2))).doubleValue();
        	}
        }
//        for(int i=0;i<num+1;i++){
//        	for(int j=0;j<num+1;j++){
//        		System.out.println("["+i+"]["+j+"]:"+caculate[i][j]);
//        	}
//        }
        for (int i = tem; i <= num; i++)
            pass += caculate[num][i];
        BigDecimal b = new BigDecimal(pass);
        pass = b.setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();        
        System.out.println(b.setScale(5, BigDecimal.ROUND_HALF_UP));
        return pass;
	}

}
