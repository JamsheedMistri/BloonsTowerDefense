
public class Projectile {
	int x, y, xTarget, yTarget;
	double xVelocity, yVelocity;
	
	public Projectile(int xStart, int yStart, int xTarget, int yTarget, double velocity) {
		this.x = xStart;
		this.y = yStart;
		this.xTarget = xTarget;
		this.yTarget = yTarget;
		
		int xDistance = xTarget - xStart;
		int yDistance = yTarget - yStart;
		double distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);
		this.xVelocity = velocity * xDistance / distance;
		this.yVelocity = velocity * yDistance / distance;
	}
	
	public void move() {
		x += xVelocity;
		y += yVelocity;
	}
	
	public boolean reachedTarget() {
		if (xVelocity < 0) {
			if (x < xTarget) return false;
		} else if (x > xTarget) return false;
		
		if (yVelocity < 0) {
			if (y < yTarget) return false;
		} else if (y > yTarget) return false;
		
		return true;
	}
}
