/*
Snail Sort

Given an n x n array, return the array elements arranged from outermost elements to the middle element, traveling clockwise.

array = [[1,2,3],
         [4,5,6],
         [7,8,9]]
snail(array) #=> [1,2,3,6,9,8,7,4,5]

For better understanding, please follow the numbers of the next array consecutively:

array = [[1,2,3],
         [8,9,4],
         [7,6,5]]
snail(array) #=> [1,2,3,4,5,6,7,8,9]

This image will illustrate things more clearly:

NOTE: The idea is not sort the elements from the lowest value to the highest; the idea is to traverse the 2-d array in a clockwise snailshell pattern.

NOTE 2: The 0x0 (empty matrix) is represented as [[]]

*/

import java.util.ArrayList;

class Snail {
	public static void main(String[] argv) {
		int val = 18;
		int[][] path = new int[val][val];
		for(int i = 0; i < val;i++){
			for(int j = 0;j < val;j++){
				path[i][j] = 12;
			}
		}
		int[][] array
                = {{584, 343, 598, 484, 18, 82, 478, 702, 885, 461, 242, 274, 728},
				{694, 147, 278, 527, 813, 284, 247, 557, 190, 442, 280, 928, 199},
				{375, 906, 777, 378, 592, 377, 560, 301, 144, 468, 888, 538, 132},
				{11, 504, 542, 1, 370, 28, 428, 557, 460, 5, 409, 921, 577},
				{611, 194, 661, 1000, 213, 53, 617, 311, 613, 815, 66, 569, 191},
				{782, 739, 755, 918, 607, 722, 769, 124, 545, 29, 456, 800, 725},
				{630, 946, 537, 360, 452, 843, 321, 415, 423, 295, 900, 662, 896},
				{273, 220, 885, 384, 68, 736, 571, 237, 710, 784, 312, 784, 95},
				{88, 934, 827, 450, 972, 459, 417, 476, 841, 406, 840, 24, 988},
				{830, 668, 842, 241, 929, 346, 870, 439, 272, 55, 791, 936, 768},
				{842, 222, 220, 668, 128, 827, 646, 884, 930, 260, 28, 53, 167},
				{981, 424, 414, 411, 461, 98, 689, 349, 468, 109, 257, 897, 94},
				{670, 765, 919, 300, 710, 223, 245, 795, 236, 186, 608, 716, 528}};
		findPath(array);
	}
	public static void findPath(int[][] array) {
		//System.out.println("arraylen"+array[0].length/2);
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(array.length == 1) {
			if(array[0].length == 0){
				
			}
			System.out.println(array[0][0]);
			System.exit(0);
		}
		int global_row =  array.length;
		int index_row = 0, index_col = 0;
		while(true) {
			if(index_row >= array[0].length/2) {
				if(array.length % 2 != 0){
				list.add(array[index_row][index_col]);
				}
				break;
			}
			list.addAll(listPath(array,index_row,index_col,global_row));
			index_row++;
			index_col++;
			global_row -= 2;
		}
		//System.out.println(list.size());
		for(int i : list) System.out.print(i+"\t");
		//int[] a = list.stream().mapToInt(i -> i).toArray();
	}
	public static ArrayList<Integer> listPath(int[][] array, int index_row, int index_col, int global_row) {
		int total_itr = (global_row - 1) * 4;
		int ini_row = index_row,ini_col = index_col;
		global_row += index_row;
		boolean col_add = false, row_add = false, col_sub = false;
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0;i < total_itr;i++) {
			//System.out.println(array[index_row][index_col]);
			list.add(array[index_row][index_col]);
			if(index_col < global_row && !col_add) {
				index_col++;
				if(index_col == global_row - 1) col_add = true; continue;
			}
			//System.out.println(index_col+""+col_add+""+row_add);
			if(index_row < global_row && col_add && !row_add){
				index_row++;
				//System.out.println(index_row+"r");
				if(index_row == global_row - 1) row_add = true; continue;
			}
			if(index_col > ini_col && row_add && !col_sub) {
				index_col--;
				if(index_col == ini_col) col_sub = true; continue;
			}
			if(index_row > ini_row && col_sub) {
				index_row--;
				if(index_row == ini_row) break;
			}
		}//end for
		return list;
	}
	
/*
Best practice 

    public static int[] snail(int[][] array) {
      if (array[0].length == 0) return new int[0];
      int n = array.length;
      int[] answer = new int[n*n];
      int index=0;
      for (int i = 0; i<n/2; i++){
        for (int j = i; j < n-i; j++) answer[index++] = array[i][j];
        for (int j = i+1; j < n-i; j++) answer[index++] = array[j][n-i-1];
        for (int j = i+1; j < n-i; j++) answer[index++] = array[n-i-1][n-j-1];
        for (int j = i+1; j < n-i-1; j++) answer[index++] = array[n-j-1][i];
      }
      if (n%2 != 0) answer[index++] = array[n/2][n/2];
      return answer;
    } 
	
*/	
	
}