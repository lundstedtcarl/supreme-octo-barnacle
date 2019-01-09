public class TestPlanet {
	public static void main(String[] args) {
	Planet aldh = new Planet(1, 3, 4, 6, 1, "aldh");
	Planet brea = new Planet(49000, 6e21, 5, 7, 2, "brea");
	System.out.println("X force is " + 
		aldh.calcForceExertedByX(brea) + ", Y force is " + 
		aldh.calcForceExertedByY(brea));
	}
}