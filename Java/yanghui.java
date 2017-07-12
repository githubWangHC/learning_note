public class yanghui {
	public static void main(String[] args){
		int maxLine = 15;
		int[][] result = new int[maxLine][maxLine];
		result[0][0] = 1;
		int i_row = 0, i_column = 0, up = 0, left = 0;
		for(i_row = 0; i_row < maxLine; i_row++){
			for(i_column = 0; i_column <= i_row; i_column++){
				if((i_row == 0) ||(i_column == i_row))
					up = 0;
				else
					up = result[i_row - 1][i_column];
				if(i_column == 0)
					left = 0;
				else
					left = result[i_row - 1][i_column - 1];
				if((i_column == 0) && (i_row == 0))
					result[i_row][i_column] = 1;
				else
					result[i_row][i_column] = up + left;
				System.out.printf("%d ", result[i_row][i_column]);
			}
			System.out.printf("\n");
		}
	}
}
