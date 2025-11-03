public class ProjectileMotion {
    private final double initialVelocity;
    private final double angle;
    private final double initialHeight;
    private final double mass;
    private final double crossSectionalArea;
    private final double dragCoefficient;
    private final double g = 9.81;
    private final double airDensity = 1.225;

    public ProjectileMotion(double initialVelocity, double angle, double initialHeight,
                            double mass, double crossSectionalArea, double dragCoefficient) {
        this.initialVelocity = initialVelocity;
        this.angle = Math.toRadians(angle);
        this.initialHeight = initialHeight;
        this.mass = mass;
        this.crossSectionalArea = crossSectionalArea;
        this.dragCoefficient = dragCoefficient;
    }

    private double calculateDragForce(double v) {
        return 0.5 * airDensity * v * v * dragCoefficient * crossSectionalArea;
    }

    private double[] calculateAcceleration(double vx, double vy) {
        double v = Math.sqrt(vx * vx + vy * vy);
        double dragForce = calculateDragForce(v);
        double forceX = -(dragForce * (vx / v));
        double forceY = -(dragForce * (vy / v)) - (mass * g);
        double ax = forceX / mass;
        double ay = forceY / mass;
        return new double[]{ax, ay};
    }

    public void simulateTrajectory(double dt) {
        double t = 0;
        double s = 0, h = initialHeight;
        double vx = initialVelocity * Math.sin(angle);
        double vy = initialVelocity * Math.cos(angle);

        System.out.println("Time (s)\tX (m)\t\tY (m)\t\tVx (m/s)\tVy (m/s)");

        while (h >= 0) {
            System.out.printf("%.2f\t\t%.2f\t\t%.2f\t\t%.2f\t\t%.2f%n", t, s, h, vx, vy);

            double[] acceleration = calculateAcceleration(vx, vy);
            double ax = acceleration[0];
            double ay = acceleration[1];

            vx += ax * dt;
            vy += ay * dt;
            s += vx * dt;
            h += vy * dt;
            t += dt;
        }
        System.out.println("Result: \t\tX (m)\t\tY (m)");
        System.out.printf("\t\t%.6f\t\t%.6f%n", s, h);
    }
}