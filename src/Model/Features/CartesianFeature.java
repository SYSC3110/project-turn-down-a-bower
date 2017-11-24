package Model.Features;

public class CartesianFeature extends GenericFeature {

	/* This constructor requires a String and two integers.  The values are then stored into a
	 * two element array as a x-value and a y-value, with the String being the feature name stored locally.
	 * 
	 * @author Logan MacGillivray, Ethan Morrll
	 */
	public CartesianFeature(String name, int x, int y){
		super(name, new int[] {x, y});
	}

	public String toString() {
		int[] value = (int[]) this.value;
		return this.name + " (Cartesian): X = " + value[0] + ", Y = " + value[1];
	}
}
