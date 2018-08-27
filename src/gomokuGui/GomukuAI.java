package gomokuGui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Stack;

public class GomukuAI {
	int board[][];
	static int p,q;
	int aiPlayer;
	int humanPlayer;
	int count=0;
	int firstx=0;
	int firsty=0;
	/*Move lastmove;*/
	int currentTurn;
	int max,min,pos,x,y;
	private Stack<Move>mymove=new Stack<>();
    static final int[][][][] adjacent=new int[10][10][4][10];
	public GomukuAI()
	{
		
	}
	public GomukuAI(int board[][]) {
		this.board=board;
		p=0;q=0;
		/*state = new State(10);
		evaluator  = new Evaluator();*/
		
	}
	
	/*public void updateState(Move move){
		this.state.makeMove(move);
	}*/
	public  void makeMove(Move m,int turn)
	{
		/*System.out.println(m.row+"  "+m.col);*/
		board[m.row][m.col]=turn;
		
		/*System.out.println(turn);*/
		mymove.push(m);
		currentTurn=turn;
		/*System.out.println("check");*/
		/*lastmove=move;*/
	}
	public Move setMove(int turn, int[] humanturnx, int[] humanturny) {
		aiPlayer=turn;
		if(aiPlayer==2) humanPlayer=1;
		else humanPlayer=2;
		
		/*this.state.generateDirections();*/
		updateBoardStatus();
		
		Move move=findBestMove(board,humanturnx,humanturny);
		/*System.out.println(move.row+" "+move.col);*/
		makeMove(move,turn);
		/*board[move.row][move.col]=turn;*/
		return move;
		/*updateState(move);*/
		
		
	}
	
	private ArrayList<Move> getThreatForAi()
	{
		/*System.out.println("ttt");*/
		PatternCreation pr=new PatternCreation();
		HashSet<Move>threesForAi=new HashSet<>();
		HashSet<Move>foursForAI=new HashSet<>();
		HashSet<Move>solutionForAi=new HashSet<>();
		HashSet<Move>Humanthrees=new HashSet<>();
		HashSet<Move>Humanfours=new HashSet<>();
		HashSet<Move>Humansolution=new HashSet<>();
		
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board.length;j++)
			{
				if(board[i][j]==humanPlayer)
				{
					/*System.out.println(adjacent[i][j][1][4]+"   tt");*/
					Humanfours.addAll(pr.getFoursForPlayer(i, j, humanPlayer));
					Humanthrees.addAll(pr.getThreesForPlayer(i, j, humanPlayer));
					Humansolution.addAll(pr.getsolutionForPlayer(i, j, humanPlayer));
				}
				else if(board[i][j]==aiPlayer)
				{
					foursForAI.addAll(pr.getFoursForPlayer(i, j, aiPlayer));
					threesForAi.addAll(pr.getThreesForPlayer(i, j, aiPlayer));
					solutionForAi.addAll(pr.getsolutionForPlayer(i, j, aiPlayer));
				}
			}
		}
		if(!foursForAI.isEmpty())
			return new ArrayList<>(foursForAI);
		if(!Humanfours.isEmpty())
		{
			
			return new ArrayList<>(Humanfours);
		}	
		if(!threesForAi.isEmpty())
		{
			Humansolution.addAll(threesForAi);
			return new ArrayList<>(threesForAi);
		}
		if(!Humanthrees.isEmpty())
		{
			Humanthrees.addAll(solutionForAi);
			/*for(Move m:Humanthrees)
				System.out.println("Set move: "+m.row+m.col);*/
			return new ArrayList<>(Humanthrees);
		}
		
		return new ArrayList<>();
		
	}
	
	private void updateBoardStatus() {
		// TODO Auto-generated method stub
		for(int row=0;row<board.length;row++)
		{
			for(int col=0;col<board.length;col++)
			{
				
				adjacent[row][col][0][4]=board[row][col];
				adjacent[row][col][1][4]=board[row][col];
				adjacent[row][col][2][4]=board[row][col];
				adjacent[row][col][3][4]=board[row][col];
				
				for(int i=0;i<5;i++)
				{
					// diagonal left
					if(row-i>=0&&col-i>=0)
						adjacent[row][col][0][4-i]=board[row-i][col-i];
					else
						adjacent[row][col][0][4-i]=0;
					// diagonal bottom left
					if(row+i<10 && col+i<10)
						adjacent[row][col][0][4+i]=board[row+i][col+i];
					else
						adjacent[row][col][0][4+i]=0;
					// diagonal top right
					if(row-i>=0 && col+i<10)
						adjacent[row][col][1][4-i]=board[row-i][col+i];
					else
						adjacent[row][col][1][4-i]=0;
			
					// diagonal bottom right
					
					if(row+i<10 && col-i>=0)
						adjacent[row][col][1][4+i]=board[row+i][col-i];
					else
						adjacent[row][col][1][4+i]=0;
					// horizontal left
					if(col-i>=0)
						adjacent[row][col][2][4-i]=board[row][col-i];
					else
						adjacent[row][col][2][4-i]=0;
					// horizontal right
					if(col+i<10)
						adjacent[row][col][2][4+i]=board[row][col+i];
					else
						adjacent[row][col][3][4+i]=0;
					// vertical top
					if(row-i>=0)
						adjacent[row][col][3][4-i]=board[row-i][col];
					else
						adjacent[row][col][3][4-i]=0;
					//vertical bottom
					if(row+i<10)
						adjacent[row][col][3][4+i]=board[row+i][col];
					else
						adjacent[row][col][3][4+i]=0;
				}
			}
		}
	}

	private Move findBestMove(int board[][], int[] humanturnx, int[] humanturny){
		int bestVal=Integer.MIN_VALUE;
		
		Move move=new Move();
		
		move.row=-1;
		move.col=-1;
		
		if(count<2)
		{
		  
			 if (count==0 && board[4][4]==0)
			 {
				 move.row=4;
				 move.col=4;
				 firstx=4;
				 firsty=4;
			 }
			 else if(count==0&& board[4][4]==1)
			 {
				 min=3;
				 max=5;
				 Random rn=new Random();
				 while(true){
					 x=rn.nextInt(max-min+1)+min;
					 y=rn.nextInt(max-min+1)+min;
					    if(board[x][y]==0)
					    	break;
				 
				 }
				 move.row=x;
				 move.col=y;
				 firstx=x;
				 firsty=y;
				 /*move.row=3;
				 move.col=3;
				 firstx=3;
				 firsty=3;*/
			 }
			 else if(count==1)
			 {
				 min=(firstx-1);
				 max=(firsty+1);
				 /*System.out.println("min :"+min+"  max: "+max);*/
				 Random rn=new Random();
				 while(true){
				   
				    x=rn.nextInt(max-min+1)+min;
				    y=rn.nextInt(max-min+1)+min;
				    
				    if(board[x][y]==0 &&(x>=min&&x<=firstx+1) &&(y>=firsty-1 &&y<=firsty+1))
				    	break;
				 
				 }
				 move.row=x;
				 move.col=y;
				 
			 }
		}
		
		else{
			 
			 ArrayList<Move>threatresponse=getThreatForAi();
			 /*System.out.println("size of set: "+threatresponse.size());*/
			 if(!threatresponse.isEmpty())
			 {
				 for(Move moves:threatresponse)
				 {
					 System.out.println("threat moves: "+moves.row+" "+moves.col);
					 if(board[moves.row][moves.col]==0){
					/* System.out.println(moves.row+" threat move  "+moves.col);*/
					   board[moves.row][moves.col]=aiPlayer;
					   int val=minimax(board, 0, false, bestVal, Integer.MAX_VALUE);
					   board[moves.row][moves.col]=0;
					 if(val>bestVal)
					 {
						 bestVal=val;
						 move=moves;
					 }
					 
					 } 
				 }
				 return move;
			 }
			for(int i=0;i<board.length;i++)
			{
				for(int j=0;j<board.length;j++)
				{
					if(board[i][j]==0)
					{
						if(hasAdjacent(i,j,2,aiPlayer))
						{
							board[i][j]=aiPlayer;
							int val=minimax(board, 0, false, bestVal,Integer.MAX_VALUE);
							board[i][j]=0;
							
							if(val>bestVal)
							{
								bestVal=val;
								move.row=i;
								move.col=j;
							}
						}
					}
				}
			}
			/*if(bestVal==Integer.MIN_VALUE)
			{
				for(int i=0;i<board.length;i++)
				{
					for(int j=0;j<board.length;j++)
					{
						board[i][j]=aiPlayer;
						int val=minimax(board, 0, false, bestVal,Integer.MAX_VALUE);
						board[i][j]=0;
						
						
						if(val>bestVal)
						{
							bestVal=val;
							move.row=i;
							move.col=j;
						}
					}
				}
				
			}*/
		}
			 
		count++;
		
		
		
		return move;
	
		
}
	
	
	private boolean hasAdjacent(int row, int col, int distance, int index) {
		// TODO Auto-generated method stub
		updateBoardStatus();
		for(int i=0;i<4;i++)
		{
			for(int j=1;j<=distance;j++)
			{
				if(adjacent[row][col][i][4+j]==index
						||adjacent[row][col][i][4-j]==index)
					return true;
			}
		}
		return false;
	}
	public  int evaluatestate(int depth)
	{
		updateBoardStatus();
		int winstate=checkTerminalState();
		if(winstate==aiPlayer)
			return 1000+depth;
		else if(winstate==humanPlayer)
			return 100-depth;
		
		int score=0;
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board.length;j++)
			{
				if(board[i][j]==humanPlayer)
					score-=evaluateFeld(i,j,humanPlayer);
				else if(board[i][j]==aiPlayer)
					score+=evaluateFeld(i, j, aiPlayer);
			}
		}
		return score;
	}
	
	private int evaluateFeld(int row, int col,int index) {
		// TODO Auto-generated method stub
		int []values={10,9,7,5,3};
		int score=0;
		for(int i=0;i<4;i++)
		{
		   
			for(int j=0;(j+4)<9;j++)
			{
				int stone=0;
				int empty=0;
				for(int k=0;k<=4;k++)
				{
				if(adjacent[row][col][i][j+k]==index)
					stone++;
				else if(adjacent[row][col][i][j+k]==0)
					empty++;
				else
					break;
				}
				 if(empty== 0 || empty== 5) continue;
				 
				 if(empty+stone==5)
					 score+=values[empty];
			}
			
				 
		}
		return score;
	}
	public int checkTerminalState()
	{
		updateBoardStatus();
		Move move = mymove.peek();
        int row = move.row;
        int col = move.col;
        int lastTurn=currentTurn;
        for(int i=0;i<4;i++)
        {
        	for(int j=0;j<6;j++)
        	{
        		if(adjacent[row][col][i][j]==lastTurn)
        		{
        			int stone=0;
        			for(int k=1;k<5;k++)
        			{
        				if(adjacent[row][col][i][j+k]==lastTurn)
        					stone++;
        				else
        					break;
        			}
        			if(stone==4) return lastTurn;
        			
        			
        		}
        	}
        }
        return mymove.size() == board.length * board.length ? 3 : 0;
        
	}
	

	private int minimax(int board[][],int depth,boolean isMax,int alpha,int beta) {
		/*System.out.println("depth:"+depth+". isMax:"+isMax);*/
		/*updateBoardStatus();*/
		if(depth>2){
			
			int score=evaluatestate(depth);
		   return score;
			
			
		}
		
	    // If there are no more moves and no winner then
	    // it is a draw
	    if (isMovesLeft(board)==false)
	        return 0;
	 
	    // If this maximizer's move
	    if (isMax)
	    {
	        int best = Integer.MIN_VALUE;
	 
	        for (int i = 0; i<10; i++)
	        {
	            for (int j = 0; j<10; j++)
	            {
	                if (board[i][j]==0 &&(hasAdjacent(i, j, 2, aiPlayer)==true||hasAdjacent(i, j, 2, humanPlayer)==true))
	                {
	                    board[i][j] = aiPlayer;
	 
	                    
		                    best = Math.max( best,
		                        minimax(board, depth+1, false,alpha,beta) );
		                   /* System.out.println("best:"+ best+".i,j"+i+","+j);*/
		                    if(best>alpha)alpha = best;
		                    
	              
	                    board[i][j] = 0;
	                    if(alpha>=beta) return best;
	                }
	            }
	        }
	        return best;
	    }
	 
	    // If this minimizer's move
	    else
	    {
	        int best = Integer.MAX_VALUE;
	 
	        for (int i = 0; i<10; i++)
	        {
	            for (int j = 0; j<10; j++)
	            {
	                if (board[i][j]==0 &&(hasAdjacent(i, j, 2, humanPlayer)==true||hasAdjacent(i, j, 2, aiPlayer)==true))
	                {
	                    board[i][j] = humanPlayer;
	 
	                    best = Math.min(best,
	                           minimax(board, depth+1, true,alpha,beta));
	                    
	                   /* System.out.println("best:"+ best);*/
	 
	                    if(best<beta)beta = best;
	                    board[i][j] = 0;
	                    if(alpha>=beta)return best;
	                }
	            }
	        }
	        return best;
	    }
	}
	
	boolean isMovesLeft(int board[][])
	{
	    for (int i = 0; i<10; i++)
	        for (int j = 0; j<10; j++)
	            if (board[i][j]==0)
	                return true;
	    return false;
	}
	
	public boolean check(int turn) {
			
		if(checkLeftToRight(turn)) return true;
		else if(checkTopToBottom(turn)) return true;
		else if(checkDiagonalFromLeft(turn)) return true;	
		else if(checkDiagonalFromRight(turn)) return true;
		return false;
	}
	
	private boolean checkLeftToRight(int turn) {
		
		for(int i=0;i<10;i++) {
			for(int j=0;j<6;j++) {
				
				if(board[i][j]==0) {
					continue;
				}		

				if(board[i][j]==board[i][j+1] && 
				   board[i][j+1]==board[i][j+2] &&
				   board[i][j+2]==board[i][j+3] &&
				   board[i][j+3]==board[i][j+4]
				  ) 
				{	
					if(board[i][j]==turn) {
						for(int k=j;k<j+5;k++) {
							board[i][k]=turn+2;
						}
						return true;
						
					}
				}
			}
		}
		return false;
	}
	
	private boolean checkTopToBottom(int turn) {
		
		for(int i=0;i<10;i++) {
			for(int j=0;j<6;j++) {
				
				if(board[j][i]==0) {
					continue;
				}		
				
				if(board[j][i]==board[j+1][i] && 
				   board[j+1][i]==board[j+2][i] &&
				   board[j+2][i]==board[j+3][i] &&
				   board[j+3][i]==board[j+4][i]
				  ) 
				{	
					if(board[j][i]== turn) {
						for(int k=j;k<j+5;k++) {
							board[k][i]=turn+2;
						}
						System.out.println("win  "+turn);
						return true;
						
					}
					
				}
			}
		}
		return false;
	}
	
	private boolean checkDiagonalFromLeft(int turn) {
		
		for(int i=0;i<6;i++) {
			for(int j=0;j<6;j++) {
				
				if(board[i][j]==0) {
					continue;
				}		

				if(board[i][j]==board[i+1][j+1] && 
				   board[i+1][j+1]==board[i+2][j+2] &&
				   board[i+2][j+2]==board[i+3][j+3] &&
				   board[i+3][j+3]==board[i+4][j+4]
				  ) 
				{	
					if(board[i][j]==turn) {
						for(int k=0;k<5;k++) {
							board[i+k][j+k]=turn+2;
						}
						System.out.println("win  "+turn);
						return true;
						
					}
				}
			}
		}
		return false;	
	}
	
	private boolean checkDiagonalFromRight(int turn) {
		
		for(int i=0;i<6;i++) {
			for(int j=4;j<10;j++) {
				
				if(board[i][j]==0) {
					continue;
				}		

				if(board[i][j]==board[i+1][j-1] && 
				   board[i+1][j-1]==board[i+2][j-2] &&
				   board[i+2][j-2]==board[i+3][j-3] &&
				   board[i+3][j-3]==board[i+4][j-4]
				  ) 
				{	
					if(board[i][j]==turn) {
						for(int k=0;k<5;k++) {
							board[i+k][j-k]=turn+2;
						}
						System.out.println("win  "+turn);
						return true;
						
					}
				}
			}
		}
		return false;	
	}
	
	public Move getBestMove(){
		Move move=new Move();
		return move;
	}
	
	
}
