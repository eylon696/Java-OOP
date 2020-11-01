package shop;


import java.util.ArrayList;
import java.util.List;

public class Shop {
	private List<Instrument> l = new ArrayList<>();// l is an array list of Instrument

	public void add(Instrument i) {
		l.add(i);
	}
//Returning the instrument with this specific serial number, null if does not exist
	public Instrument get(int serial) {
		for (Instrument i : l) {
			if (i.getSerial() == serial)
				return i;
		}
		return null;
	}
//Creating an array list of all the serial numbers
	public List<Integer> allSerials() {
		List<Integer> serials = new ArrayList<>();
		for (Instrument i : l) {
			serials.add(i.getSerial());
		}
		return serials;
	}
//Returning a array list of all the serial numbers belonging to a guitar
	public List<Integer> guitarsOfType(Type t) {// check guitar.type
		List<Integer> serials = new ArrayList<>();
		for (Instrument i : l) {
			if (i instanceof Guitar) {
				if (((Guitar) i).getType().equals(t))
					serials.add(i.getSerial());
			}
		}
		return serials;
	}

	public void sell(int serial) throws MusicShopException {
		int count = 0;
		int flag = 0;
		Instrument temp=null;
		for (Instrument i : l) {
			if (i instanceof Guitar)
				count++;//counting how many guitar are in the shop
			if (i.getSerial() == serial) {
				flag = 1;//the wanted serial number belongs to an instrument found in the shop
				temp=i;
			}
		}
		if (flag == 0)
			throw new MusicShopException("Serial number wasn't found");
		if (count == 1)
			throw new MusicShopException("Sorry there is only one guitar left in the shop");
		l.remove(l.indexOf(temp));//"selling" the wanted instrument
	}
//"sells" all the instruments with the matching serial number. returns the number of instruments that could not be sold
	public int sellAll(int[] serials) {
		int count=0;
		for(int i=0;i<serials.length;i++) {
			try {
				sell(serials[i]);
			}
			catch(MusicShopException e) {
				count++;	
			}
		}
		return count;
	}
}
