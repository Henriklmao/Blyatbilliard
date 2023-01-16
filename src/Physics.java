public class Physics {

    public static int getReflection(boolean vcol, double Ain) {

        if (vcol) {
            return (int) (-Ain);
        } else {
            return (int) (180-Ain);
        }
    }
}