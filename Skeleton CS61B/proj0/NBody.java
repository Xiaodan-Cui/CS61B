public class NBody {
	public static double readRadius (String filename){
		In in = new In(filename);
		int number = in.readInt();
		double radius=in.readDouble();
		return radius ;
	}
	public static Body[] readBodies (String filename){
		In in = new In(filename);
		int N = in.readInt();
		double radius=in.readDouble();
		Body[] Bodies=new Body[N];
		for (int i = 0; i<N; i++){
			double xP=in.readDouble();
			double yP=in.readDouble();
			double xV=in.readDouble();
			double yV=in.readDouble();
			double m=in.readDouble();
			String img=in.readString();
			Bodies[i]=new Body(xP,yP,xV,yV,m,img);
		}
		return Bodies ;
	}
	public static void main(String[] args){
		double T =Double.parseDouble(args[0]);
		double dt =Double.parseDouble(args[1]);
		String filename = args[2];
		double radius=readRadius(filename);
		Body[] Bodies = readBodies(filename);
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");
		for (Body b:Bodies){
			b.draw();
		}
		StdDraw.show();
		StdDraw.enableDoubleBuffering();
		double time=0;
		while(time<=T){
			int N =Bodies.length;
			double[] ForceX=new double[N];
			double[] ForceY=new double[N];
			for (int i = 0; i<N; i++){
				ForceX[i]= Bodies[i].calcNetForceExertedByX (Bodies);
				ForceY[i]= Bodies[i].calcNetForceExertedByY (Bodies);
			}
			for (int i = 0; i<N; i++){
				Bodies[i].update(dt, ForceX[i], ForceY[i]);
			}
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (int i = 0; i<N; i++){
				Bodies[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time+=dt;
		}
		StdOut.printf("%d\n",Bodies.length);
		StdOut.printf("%.2e\n",radius);
		for (int i = 0; i<Bodies.length; i++){
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
				Bodies[i].xxPos,Bodies[i].yyPos,Bodies[i].xxVel,
				Bodies[i].yyVel,Bodies[i].mass,Bodies[i].imgFileName);
		}

	}
}