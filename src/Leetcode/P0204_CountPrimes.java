package Leetcode;

public class P0204_CountPrimes {

    // initial approach: brute force (TLE)
    // check if a number is a prime number for each number less than n

    public int countPrimes(int n) {
        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                cnt++;
            }
        }
        return cnt;
    }
    public boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // approach 2: Sieve_of_Eratosthenes
    // the basic idea: for each number, its multiples are not prime numbers. So for each number, we will
    // iteratively marking its multiples as not prime numbers. This is basically O(n) solution.
    // This gif shows the process:
    // https://assets.leetcode.com/static_assets/public/images/solutions/Sieve_of_Eratosthenes_animation.gif

    // time: O(n)
    // space: O(n)

    public int countPrimes_Sieve_of_Eratosthenes(int n) {
        boolean[] notPrime = new boolean[n];
        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]) cnt++;
            for (int j = i * i; j < n; j += i) {
                notPrime[j] = true;
            }
        }
        return cnt;
    }
}
