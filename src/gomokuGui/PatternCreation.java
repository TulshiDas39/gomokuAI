package gomokuGui;

import java.util.ArrayList;

public class PatternCreation {

	private static final ArrayList<Pattern> threes = new ArrayList<>();
	private static final ArrayList<Pattern> fours = new ArrayList<>();
	private static final ArrayList<Pattern> solution = new ArrayList<>();

	public PatternCreation() {
		threes.add(new Pattern(new int[] { 0, 1, 1, 1, 0, 0 }, new int[] { 0, 4, 5 }));
		threes.add(new Pattern(new int[] { 0, 0, 1, 1, 1, 0 }, new int[] { 0, 1, 5 }));
		threes.add(new Pattern(new int[] { 0, 1, 0, 1, 1, 0 }, new int[] { 0, 2, 5 }));
		threes.add(new Pattern(new int[] { 0, 1, 1, 0, 1, 0 }, new int[] { 0, 3, 5 }));
		/*threes.add(new Pattern(new int[] { 1, 1, 1, 0, 0, 0 }, new int[] { 3, 4, 5 }));*/
		/*threes.add(new Pattern(new int[] { 0, 0, 0, 1, 1, 1 }, new int[] { 0, 1, 2 }));*/
		fours.add(new Pattern(new int[] { 1, 1, 1, 1, 0 }, new int[] { 4 }));
		fours.add(new Pattern(new int[] { 1, 1, 1, 0, 1 }, new int[] { 3 }));
		fours.add(new Pattern(new int[] { 1, 1, 0, 1, 1 }, new int[] { 2 }));
		fours.add(new Pattern(new int[] { 1, 0, 1, 1, 1 }, new int[] { 1 }));
		fours.add(new Pattern(new int[] { 0, 1, 1, 1, 1 }, new int[] { 0 }));
		solution.add(new Pattern(new int[] { 1, 1, 1, 0, 0 }, new int[] { 3, 4 }));
		/*solution.add(new Pattern(new int[] { 1, 0, 1, 1, 0 }, new int[] { 1, 4 }));*/
		/*solution.add(new Pattern(new int[] { 1, 1, 0, 1, 0 }, new int[] { 2, 4 }));*/
		solution.add(new Pattern(new int[] { 1, 1, 0, 0, 1 }, new int[] { 2, 3 }));
		solution.add(new Pattern(new int[] { 1, 0, 0, 1, 1 }, new int[] { 1, 2 }));
		/*solution.add(new Pattern(new int[] { 0, 1, 1, 1, 0 }, new int[] { 0, 4 }));*/
		solution.add(new Pattern(new int[] { 0, 0, 1, 1, 1 }, new int[] { 0, 1 }));

	}

	public ArrayList<Move> getThreesForPlayer(int posx, int posy, int playerIndex) {
		/* System.out.println(threes.size()+"  thress"); */
		return getMovesForPlayer(threes, posx, posy, playerIndex);
	}

	public ArrayList<Move> getFoursForPlayer(int posx, int posy, int playerIndex) {
		/* System.out.println(fours.size()+"  thress"); */
		return getMovesForPlayer(fours, posx, posy, playerIndex);
	}

	public ArrayList<Move> getsolutionForPlayer(int posx, int posy, int playerIndex) {
		return getMovesForPlayer(solution, posx, posy, playerIndex);
	}

	private ArrayList<Move> getMovesForPlayer(ArrayList<Pattern> patternList, int posx, int posy, int playerIndex) {
		ArrayList<Move> threatMoves = new ArrayList<>();

		for (int i = 0; i < 4; i++) {

			for (Pattern pattern : patternList) {
				/* System.out.println(pattern.toString()+"joy"); */
				int patternIndex = matchPattern(pattern.getPattern(playerIndex), i, posx, posy);
				if (patternIndex != -1) {
                    int t[]=pattern.getPattern(playerIndex);
                    for(int n=0;n<t.length;n++)
                     System.out.print("for pattern: "+t[n]+" ");
                    
                    System.out.println();
					for (int Patternsolution : pattern.getsolutions()) {
						System.out.println("solution: "+Patternsolution+"  index: "+patternIndex);
						int f = patternIndex + Patternsolution;
						
						if (i == 0) {
							if (f < 4){
								int x=posx - (4 - f);
								int y=posy - (4- f);
								if((x>=0 && x<10) && (y>=0 &&y<10))
								threatMoves.add(new Move(x,y));
							}
							else if (f > 4)
							{
								int x=posx + (f - 4);
								int y=posy + (f - 4);
								if((x>=0 && x<10) && (y>=0 &&y<10))
									threatMoves.add(new Move(x,y));
							}	
							else
								threatMoves.add(new Move(posx, posy));

						} else if (i == 1) {
							if (f < 4)
							{
								int x=posx - (4 - f);
								int y= posy + (4 - f);
								if((x>=0 && x<10) && (y>=0 &&y<10))
									threatMoves.add(new Move(x,y));
							}	
							else if (f > 4)
							{
								int x=posx + (f - 4);
								int y=posy - (f - 4);
								if((x>=0 && x<10) && (y>=0 &&y<10))
									threatMoves.add(new Move(x,y));
							}	
							else
								threatMoves.add(new Move(posx, posy));
						} else if (i == 2) {

							if (f < 4)
							{
								int x=posx;
								int y=posy - (4 - f);
								if((x>=0 && x<10) && (y>=0 &&y<10))
									threatMoves.add(new Move(x,y));
							}	
							else if (f > 4)
							{
								int x=posx;
								int y=posy + (f - 4);
								if((x>=0 && x<10) && (y>=0 &&y<10))
									threatMoves.add(new Move(x,y));
							}	
							else
								threatMoves.add(new Move(posx, posy));
						} else {
							
							if (f < 4)
							{
								int x=posx - (4 - f);
								int y=posy;
								if((x>=0 && x<10) && (y>=0 &&y<10))
									threatMoves.add(new Move(x,y));
							}
							else if (f > 4)
							{	
								int x=posx + (f - 4);
								int y=posy;
								if((x>=0 && x<10) && (y>=0 &&y<10))
									threatMoves.add(new Move(x,y));
							}	
							else
								threatMoves.add(new Move(posx, posy));
						}
					}
				}
			}
		}
		return threatMoves;
	}

	private  int matchPattern(int[] pattern, int i, int posx, int posy) {
		// TODO Auto-generated method stub
		GomukuAI gm =new GomukuAI();
		/*System.out.println(gm.adjacent[posx][posy][i][4]);*/
		
		int[] f=gm.adjacent[posx][posy][i];
		/*int outofBound=0;*/
	/*	System.out.println("array length: "+f.length);*/

		 for (int j = 0; j < f.length; j++) {
			 
			 if(j+(pattern.length-1)<f.length){
			int count = 0;
			for (int k = 0; k < pattern.length; k++) {
				if (f[j+k] == pattern[k])
					count++;
				else
					break;
			}
			if (count == pattern.length)
			{
			  System.out.println("milce");
			  return j;
			}
		}
		 
			 else
				 break;
		 }
		 
		 
		return -1;
	}

}
