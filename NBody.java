import java.util.Scanner;

public class NBody {

    public static void main(String args[]){
        Scanner reader = new Scanner(System.in);
        String sT = reader.next();
        double T = Double.parseDouble(sT);
        String sdt = reader.next();
        double dt = Double.parseDouble(sdt);
        String filename = reader.next();

        double uniRadius = readRadius(filename);
        Planet[] allPlanets = readPlanets(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.clear();
        StdDraw.setScale(-uniRadius,uniRadius);

        for (double t=0; t<T; t+=dt){
            double[] xForce = new double[allPlanets.length];
            double[] yForce = new double[allPlanets.length];
            for (int i=0; i< allPlanets.length; i++){
                xForce[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                yForce[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
            }
            for (int i=0; i< allPlanets.length; i++) {
                allPlanets[i].update(t, xForce[i], yForce[i]);
            }
            StdDraw.picture(0,0,"images/starfield.jpg");
            for (Planet p : allPlanets){
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(5);
        }
        StdOut.printf("%d\n", allPlanets.length);
        StdOut.printf("%.2e\n", uniRadius);
        for (int i = 0; i < allPlanets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                    allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);
        }
    }

    public static double readRadius(String fileName){
        In in = new In(fileName);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String fileName){
        In in = new In(fileName);
        int total = in.readInt();
        Planet[] allPlanets = new Planet[total];
        in.readDouble();
        for (int i=0; i<total; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            allPlanets[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
        }
        return allPlanets;
    }

}
