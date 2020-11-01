package tasks;

public class Tasks {
	private int num;
	private int[][] depends;

//Init Constructor
	public Tasks(int num) {
		this.num = num;
		depends = new int[num][num];
	}

	public boolean dependsOn(int task1, int task2) {
		if (task1 > num - 1 || task2 > num - 1 || task1 < 0 || task2 < 0)//Check if the tasks are legal tasks
			return false;
		depends[task2][task1] = 1;
		return true;
	}

	public int[] order() {
		int[] result = new int[num];
		int count = 0;
		//******************************************FIRST LOOP*******************************************************//
		int j, i, l, k, flag = 0, flag2 = 0;
		for (k = 0; k < num; k++) {
			//******************************************SECOND LOOP*******************************************************//
			for (i = 0; i < num; i++) {
				//******************************************THIRD LOOP*******************************************************//
				for (j = 0; j < num; j++) {
					if (depends[j][i] == 1) {
						flag = 1;
						break;
					}
				}//******************************************END OF LOOP THREE*******************************************************//
				if (flag == 0) { //In case we need to insert the task to the array
					for (l = 0; l < count; l++) { //Loop to check if the is already in the order array 
						if (result[l] == i) {
							flag2 = 1; //If the task is already in the order array
							break;
						}
					}
					if (flag2 == 1)
						flag2 = 0;
					else { //is the task is not yet in the order array, put it in the array
						result[count] = i;
						count++;
						for (int m = 0; m < num; m++) {
							depends[i][m] = 0;
						}
					}
				}
				flag = 0;
			}//******************************************END OF LOOP TWO*******************************************************//	
			
		}//******************************************END OF LOOP ONE*******************************************************//
		if (count != num)
			return null;
		return result;
	}
}
