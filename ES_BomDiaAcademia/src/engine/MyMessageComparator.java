package engine;

import java.util.Comparator;

public class MyMessageComparator implements Comparator<MyMessage> {

	@Override
	public int compare(MyMessage m1, MyMessage m2) {
		return m1.getTime().compareTo(m2.getTime());
	}

}
