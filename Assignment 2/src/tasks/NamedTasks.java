package tasks;

public class NamedTasks extends Tasks {
	private String[] names;

	//Init Constructor
	public NamedTasks(String[] names) {
		super(names.length);
		this.names = names.clone();
	}

	public boolean dependsOn(String task1, String task2) {
		int i, j;
		for (i = 0; i < names.length; i++) { //Loop to convert string task1 to int task1  
			if (names[i].equals(task1))
				break;
		}
		for (j = 0; j < names.length; j++) { //Loop to convert string task2 to int task2 
			if (names[j].equals(task2))
				break;
		}
		if (super.dependsOn(i, j) == true)
			return true;
		return false;
	}

	public String[] nameOrder() {
		String[] result = new String[names.length];
		int[] arr = new int[names.length];
		arr = super.order();
		if(arr==null) 
			return null;
		for (int i = 0; i < names.length; i++) { //Loop to put the string value of the int array in to the result string array
			result[i]=names[arr[i]];
		}
		return result;
	}
}
