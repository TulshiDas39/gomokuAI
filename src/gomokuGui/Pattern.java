package gomokuGui;

public class Pattern {

	private int[][]pattern;
	private int[]solutions;
	/*private int[][][][] board;*/
	public Pattern(int []pattern,int[] solutions)
	{
		this.pattern=new int[2][1];
		this.pattern[0]=pattern;
		this.pattern[1]=convertPattern(pattern);
		this.solutions=solutions;
	}
	public int[] getPattern(int playerIndex) {
        return this.pattern[playerIndex - 1];
    }
	public int[] getsolutions() {
        return this.solutions;
    }

	private int[] convertPattern(int[] pattern) {
		// TODO Auto-generated method stub
		int []convertPattern=new int [pattern.length];
		for(int i=0;i<pattern.length;i++)
		{
			if(pattern[i]==1)
				convertPattern[i]=2;
			else convertPattern[i]=0;
		}
		
		return convertPattern;
	}
	/*public void check()
	{
		PatternCreation pr=new PatternCreation();
		for(int i=0;i<gm.board.length;i++)
		{
			for(int j=0;j<gm.board.length;j++)
			{
				pr.getThrees(gm.board[i][j],2);
			}
		}
	}
	*/
	
}
