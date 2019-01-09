public class NBody {
	public static double readRadius(String filename) {
		In in = new In(filename);
		int n = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String filename) {
		In in = new In(filename);
		int n = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[n];
		for (int i = 0; i < n; i++) {
			planets[i] = new Planet(in.readDouble(),in.readDouble(),
				in.readDouble(),in.readDouble(),in.readDouble(),
				in.readString());
		}
		return planets;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		//Drawing the background
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");

		//Drawing all the planets
		for (Planet p : planets) {
			p.draw();
		}

		//n number of planets
		int n = planets.length;

		//Creating an animation
		StdDraw.enableDoubleBuffering();
		for (double time = 0; time < T; time++) {
			double[] xForces = new double[n];
			double[] yForces = new double[n];
			for (int i = 0; i < n; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			} 
			for (int i = 0; i < n; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			//Draw background image
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (Planet p : planets) {
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
}
	}
}