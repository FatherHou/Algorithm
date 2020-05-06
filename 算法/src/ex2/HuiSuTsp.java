package ex2;


import java.util.Arrays;

/**
 * @author hou
 *
 */
public class HuiSuTsp {

    static int cityCount = 4;  //城市数量
    static int shortestval = 53266;    //最短长度
    static int[] shortestarr = new int[cityCount]; //最短路径
    static int stepval = 0;	//临时最短长度
    static int[] steparr = new int[cityCount];	//临时最短路径
    static int[] choosecity = new int[cityCount];	//查看城市走过的状态

    static int[][] citydistanceArr = {{-1,30,6,4},{30,-1,5,10},{6,5,-1,20},{4,10,20,-1}};;


    public static void travel(int step){
        if(step >= cityCount){
            //表示走完了所有城市
            if(stepval < shortestval){
                shortestarr = Arrays.copyOf(steparr, 4);
                shortestval = stepval;
                System.out.println("当前最短长度是" + shortestval + ",路线是 " + Arrays.toString(shortestarr));
            }
//           return;
        }
        //继续下一步
        for(int i = 0; i < cityCount ; i++){
            //下一步没有走过，并且可达，则选中
            if(choosecity[i] == 0 && citydistanceArr[steparr[step-1]][i] != -1){
                steparr[step] = i;
                stepval += citydistanceArr[steparr[step-1]][i];
                choosecity[i] = 1;
                travel(step+1);
                //这里是退回到上一步
                steparr[step] = -1;
                choosecity[i] = 0;
                stepval -= citydistanceArr[steparr[step-1]][i];
            }
        }
    }

    public static void main(String[] args) {

        for(int i = 0 ; i < cityCount ; i++){
            steparr[i] = -1;// key表示第几步,value表示选择的城市，用于表示当前路线
            choosecity[i] = 0;//key表示城市编号,value表示是否选择，判断当前路线是否已经存在该城市，用于去重
        }

        //城市编号为 0,1,2,3
        //固定第一步走城市0
        steparr[0] = 0;//  step的值表示走过的城市编号，表示第0步选择城市0
        choosecity[0] = 1;
        travel(1);

        System.out.println("运行结束================");
        System.out.println(" 最短距离 " + shortestval);
        System.out.println(" 最短路线 " + Arrays.toString(shortestarr));



    }

}