package tetris;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;



//与俄罗斯方块游戏相关的矩阵运算的实用程序类。
public class MatrixOperations {
	//检查给定的砖块是否与现有矩阵在指定位置相交。
	public static boolean intersects(int[][] matrix, int[][] brick , int x, int y) {
		for(int i=0; i < brick.length; i++) {
			for(int j = 0; j < brick[i].length; j++) {
				int tragetX = x + i;
				int tragetY = y + j;
				if(brick[j][i] != 0 && 
				  (outOfBounds(matrix, tragetX, tragetY) || matrix[tragetY][tragetX] != 0)) {
					return true;
				}
			}
		}
		//占位符返回值；需要实际执行。
		return false;
	}
	
	//17
	public static int[][]  merge(int[][] filledFields, int[][] brick, int x, int y){
		int[][] copy = copy(filledFields);
		
		for(int i = 0; i < brick.length; i++) {
			for(int j = 0; j < brick[i].length; j++) {
				int tragetX = x + i;
				int tragetY = y + j;
				//边界
				if (tragetY >= 1 && tragetY <= copy.length && tragetX >= 0 && tragetX <= copy[0].length) {
					 if(brick[j][i] != 0) {
						 copy[tragetY - 1][tragetX] = brick[j][i];
					 }					 
				 }
			}
		}
		
		
		return copy;
	}
	
	//17 
	public static int[][] copy(int[][] original) {
		int[][] myInt = new int[original.length][];
		for(int i = 0; i < original.length; i++) {
			int[] aMatrix = original[i];
			int aLength = aMatrix.length;
			myInt[i] = new int[aLength];
			System.arraycopy(aMatrix, 0, myInt[i], 0, aLength);
		}
		
		return myInt;
	}

	private static boolean outOfBounds(int[][] matrix, int tragetX, int tragetY) {
		if(tragetX >= 0 && tragetY < matrix.length && tragetX < matrix[tragetY].length) {
			return false;
		}
				
		return true;
	}

	//26
	//
	public static ClearRow checkRemoveing(int[][] matrix) {
		int [][] tmp = new int[matrix.length][matrix[0].length];
		
		List<Integer> clearedRows = new ArrayList<>();
		Deque<int[]> newRows = new ArrayDeque<int[]>();
		
		for(int i = 0; i < matrix.length; i++) {
			int[] tmpRow = new int[matrix[i].length];
			boolean rowToClear = true;
			for(int j = 0; j < matrix[0].length; j++) {
				if(matrix[i][j] == 0) {
					rowToClear = false;
				}
				tmpRow[j] = matrix[i][j]; 
			}
			
			if(rowToClear) {
				clearedRows.add(i);
			}else {
				newRows.add(tmpRow);
			}
		}
		
		for(int i = matrix.length - 1; i >= 0; i--) {
			int[] row = newRows.pollLast();
			if(row != null) {
				tmp[i] = row;
			}else {
				break;
			}
		}
		//计算清除行的分数
		int scoreBonus = 30 *clearedRows.size();
		
		
		return new ClearRow(clearedRows.size(), tmp, scoreBonus);
	}
}
