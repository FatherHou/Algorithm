import java.util.Scanner;

/**
 * @author hou
 *
 */
public class ljmg {
	    private static int[] dx = {0,-1,-1,0,1,1,1,0,-1};
	    private static int[] dy = {0,0,1,1,1,0,-1,-1,-1};
	    private static int n,m,k;
	    private static int dirs,best,count;
	    private static int x,y,x1,y1;
	    private static int[][] board,bestb;
	    private static int MAX = 100000;

	    public static void main(String[] args){
	        Scanner in = new Scanner(System.in);
	        System.out.println("请按题目要求输入参数:");
	        while (true){
	            best = MAX;
	            dirs = 0;
	            n = in.nextInt();
	            m = in.nextInt();
	            k = in.nextInt();

	            board = new int[n+1][m+1];
	            bestb = new int[n+1][m+1];

	            for(int i=1; i<=n; i++)
	                for(int j=1; j<=m; j++)
	                    board[i][j] = 0;

	            for(int i=1; i<=k; i++){
	                int row = in.nextInt();
	                int col = in.nextInt();
	                board[row][col] = -1;
	            }

	            x = in.nextInt();
	            y = in.nextInt();
	            x1 = in.nextInt();
	            y1 = in.nextInt();

	            board[x][y] = 1;

	            //最开始只能向下走(1,0),本程序中即为(dx[5],dy[5]): x(行)增加，y(列)不变
	            //所以最开始的di设为5，不然方向会多一次比较，会多1
	            search(1,x,y,5);

	            System.out.println(best);
	            System.out.println(count);
	            for(int i=1; i<=n; i++){
	                for(int j=1; j<=m; j++)
	                    System.out.print(bestb[i][j]+" ");
	                System.out.println();
	            }

	        }
	    }

	    private static void search(int dep, int x, int y, int di){
	        if(dep==m*n-k && x==x1 && y==y1 && dirs<=best){
	            if(dirs < best){
	                best = dirs;
	                count = 1;
	                save();
	            }else
	                count++;
	            return;
	        }
	        if(dep==m*n-k || x==x1 && y==y1 || dirs>best) return;
	        else
	            for(int i=1; i<=8; i++){
	                if(stepok(x+dx[i], y+dy[i])){
	                    board[x+dx[i]][y+dy[i]] = dep+1;
	                    if(di != i) dirs++;
	                    search(dep+1,x+dx[i],y+dy[i],i);
	                    if(di != i) dirs--;
	                    board[x+dx[i]][y+dy[i]] = 0;
	                }
	            }
	    }

	    private static boolean stepok(int x, int y){
	        return (x>0 && x<=n && y>0 && y<=m && board[x][y]==0);
	    }

	    private static void save(){
	        for(int i=1; i<=n; i++)
	            for(int j=1; j<=m; j++)
	                bestb[i][j] = board[i][j];
	    }
}