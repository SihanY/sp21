public class Planet {

    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double x = xxPos - p.xxPos;
        double y = yyPos - p.yyPos;
        return Math.sqrt(x * x + y * y);
    }

    public double calcForceExertedBy(Planet p){
        double r = calcDistance(p);
        return 6.67 * Math.pow(10,-11) * mass * p.mass / r / r;
    }

    public double calcForceExertedByX(Planet p){
        double r = calcDistance(p);
        double f = calcForceExertedBy(p);
        double x = p.xxPos - xxPos;
        return f * x / r;
    }

    public double calcForceExertedByY(Planet p){
        double r = calcDistance(p);
        double f = calcForceExertedBy(p);
        double y = p.yyPos - yyPos;
        return f * y / r;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets){
        double totalFx = 0;
        for(Planet p : allPlanets){
            if (this.equals(p)){
                continue;
            }
            totalFx += calcForceExertedByX(p);
        }
        return totalFx;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets){
        double totalFy = 0;
        for(Planet p : allPlanets){
            if (this.equals(p)){
                continue;
            }
            totalFy += calcForceExertedByY(p);
        }
        return totalFy;
    }

    public void update(double dt, double fX, double fY){
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel = xxVel + dt * aX;
        yyVel = yyVel + dt * aY;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/" + imgFileName);
        StdDraw.show();
    }

}
