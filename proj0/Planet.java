public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static final double g = 6.67e-11;

	public Planet(double xP, double yP, double xV, 
		double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		double dx = p.xxPos-this.xxPos;
		double dy = p.yyPos-this.yyPos;
		return Math.sqrt(dx*dx+dy*dy);
	}

	public double calcForceExertedBy(Planet p) {
		double r = this.calcDistance(p);
		double force = g * p.mass * this.mass / (r * r);
		return force;
	}

	public double calcForceExertedByX(Planet p) {
		double fx = this.calcForceExertedBy(p) * 
		(p.xxPos - this.xxPos) / this.calcDistance(p);
		return fx;
	}

	public double calcForceExertedByY(Planet p) {
		double fy = this.calcForceExertedBy(p) * 
		(p.yyPos - this.yyPos) / this.calcDistance(p);
		return fy;
	}

	public double calcNetForceExertedByX(Planet[] planets) {
		double netx = 0;
		for (Planet p : planets) {
			if (this.equals(p)) {
				continue;
			}
			netx += this.calcForceExertedByX(p);
		}
		return netx;
	}

	public double calcNetForceExertedByY(Planet[] planets) {
		double nety = 0;
		for (Planet p : planets) {
			if (this.equals(p)) {
				continue;
			}
			nety += this.calcForceExertedByY(p);
		}
		return nety;
	}

	public void update(double dt, double fX, double fY) {
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		xxVel = xxVel + dt * aX;
		yyVel = yyVel + dt * aY;
		xxPos = xxPos + dt * xxVel;
		yyPos = yyPos + dt * yyVel;
	}

	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + imgFileName);
	}
}