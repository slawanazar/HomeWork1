public class Primes {
    public static boolean isPrime(int n) {
        for (int i = 0; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
