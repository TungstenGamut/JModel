class Program {
    public static void main() {
        try {
            Program obj = new Program();
            obj.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() throws Exception {
        double initialVelocity = 5.205;
        double angle = 43.0;
        double initialHeight = 0.648;
        double mass = 0.0333;
        double crossSectionalArea = 0.0001131;
        double dragCoefficient = 0.82;
        ProjectileMotion proj = new ProjectileMotion(initialVelocity, angle, initialHeight, mass, crossSectionalArea, dragCoefficient);
        proj.simulateTrajectory(0.001);
    }
}