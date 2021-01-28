public class Body {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public double G = 6.67e-11;
	public Body(double xP,double yP,double xV,double yV,double m,String img){
		this.xxPos=xP;
		this.yyPos=yP;
		this.xxVel=xV;
		this.yyVel=yV;
		this.mass=m;
		this.imgFileName=img;

	}
	public Body(Body b){
		this.xxPos=b.xxPos;
		this.yyPos=b.yyPos;
		this.xxVel=b.xxVel;
		this.yyVel=b.yyVel;
		this.mass=b.mass;
		this.imgFileName=b.imgFileName;
	}
	public double calcDistance (Body b){
		return Math.sqrt(Math.pow((b.xxPos-this.xxPos), 2)+Math.pow((b.yyPos-this.yyPos), 2));
	}
	public double calcForceExertedBy (Body b){
		return G*this.mass * b.mass / Math.pow ((this.calcDistance (b)),2);
	}
	public double calcForceExertedByX (Body b){
		return this.calcForceExertedBy (b)*(b.xxPos-this.xxPos)/ this.calcDistance (b);
	}
	public double calcForceExertedByY (Body b){
		return this.calcForceExertedBy (b)*(b.yyPos-this.yyPos)/ this.calcDistance (b);
	}
	public double calcNetForceExertedByX (Body[] allBodys){
		double sumX=0;
		for (Body b : allBodys){
			if (b.equals(this)){continue;}
			sumX +=calcForceExertedByX (b);
		}
		return sumX;
	}
	public double calcNetForceExertedByY (Body[] allBodys){
		double sumY=0;
		for (Body b : allBodys){
			if (b.equals(this)){continue;}
			sumY +=calcForceExertedByY (b);
		}
		return sumY;
	}
	public void update(double dt, double fX, double fY){
		this.xxVel+=fX/this.mass*dt;
		this.yyVel+=fY/this.mass*dt;
		this.xxPos+=this.xxVel*dt;
		this.yyPos+=this.yyVel*dt;
	}
	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
		}

















}