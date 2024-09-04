public class RatNum {
    int denominator; //täljare
    int numerator; //nämnare
    /**
     * A utility method to calculate the greatest common divisor (GCD) of two integers.
     *
     * @param m The first integer.
     * @param n The second integer.
     * @return The GCD of the two integers.
     * @throws IllegalArgumentException if both integers are zero.
     */
    public static int gcd(int m, int n) {
        int gcd = 0;
        //System.out.println("m:" + m + " n:" + n);
        if (m == 0 && n == 0) {
            throw new IllegalArgumentException("Both faktors can not be zero");
        }
        else if(m==0 && n!=0){
            gcd = n;
        }
        else if(n==0 && m!=0){
            gcd = m;
        }
        else {
            m = java.lang.Math.abs(m);
            n = java.lang.Math.abs(n);
            for (int i = 1; i <= m && i <= n; i++) {
                if (n % i == 0 && m % i == 0) {
                    gcd = i;
                }
            }
        }
        //System.out.println("gcd:" + gcd);
        return gcd;
    }
    /**
     * Constructs a Rational Number object with the given numerator and denominator.
     *
     * @param numerator   The numerator of the rational number.
     * @param denominator The denominator of the rational number.
     * @throws NumberFormatException if the denominator is zero.
     */
    //Steg 2
    public RatNum(int numerator, int denominator) {
        if (denominator == 0) {
            throw  new  NumberFormatException("Denominator = 0");
        }
        int gcd = gcd(numerator, denominator);
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
        if (this.denominator < 0 && this.numerator<0) {
            this.numerator *= -1;
            this.denominator *= -1;
        }
        else if(this.denominator < 0 && this.numerator>0) {
            this.numerator *= -1;
            this.denominator *= -1;
        }
        //System.out.println(this.numerator + "/" + this.denominator);
    }


    /**
     * Constructs a Rational Number object initialized to 0/1.
     */
    public RatNum() { //coment for me: ALT: this(0, 1);
        this.numerator = 0;
        this.denominator = 1;
    }

    /**
     * Constructs a Rational Number object initialized to 'a'/1.
     *
     * @param a The integer value for the numerator.
     */
    public RatNum(int a) { //coment for me: ALT: this(a, 1);
        this.numerator = a;
        this.denominator = 1;
    }

    /**
     * Constructs a copy of the given Rational Number object.
     *
     * @param r The Rational Number to be copied.
     */
    public RatNum(RatNum r) { //ALT:this(r.numerator, r.denominator);
        this.numerator = r.numerator;
        this.denominator = r.denominator;
    }

    /**
     * Retrieves the numerator of the Rational Number.
     *
     * @return The numerator.
     */
    public int getNumerator() {
        return this.numerator;
    }

    /**
     * Retrieves the denominator of the Rational Number.
     *
     * @return The denominator.
     */
    public int getDenominator() {
        return this.denominator;
    }
    //Steg 3
    /**
     * Converts the Rational Number to a string representation.
     *
     * @return The string representation of the Rational Number.
     */
    public String toString() {
        if (denominator == 1) {
            return Integer.toString(numerator);
        } else {
            return numerator + "/" + denominator;
        }
    }

    /**
     * Parses a string to create a Rational Number object.
     *
     * @param s The input string to parse.
     * @return The Rational Number object.
     * @throws NumberFormatException if the input string has an invalid format.
     */
    public static RatNum parse(String s) {
        if (s.matches("-?\\d+/-?\\d+")) { //For me: ALT:if (s.contains("/"))
            String[] parts = s.split("/");
            int num = Integer.parseInt(parts[0]);
            int den = Integer.parseInt(parts[1]);
            return new RatNum(num, den);
        } else if (s.matches("-?\\d+")) {
            int num = Integer.parseInt(s);
            return new RatNum(num);
        } else {
            throw new NumberFormatException("Invalid format: " + s);
        }
    }
    /**
     * Constructs a Rational Number object from a string representation.
     *
     * @param s The string representation of the Rational Number.
     * @throws NumberFormatException if the input string has an invalid format.
     */
    public RatNum(String s) {
        this(parse(s));
    }

    /**
     * Checks if two Rational Number objects are equal.
     *
     * @param obj The object to compare.
     * @return true if the objects are equal, false otherwise.
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RatNum other = (RatNum) obj;
        return numerator == other.numerator && denominator == other.denominator;
    }


    /**
     * Checks if the current Rational Number is less than another Rational Number.
     *
     * @param r The Rational Number to compare.
     * @return true if the current number is less than the given number, false otherwise.
     */
    public boolean lessThan(RatNum r) {
        return (numerator * r.denominator) < (r.numerator * denominator);
    }

    /**
     * Adds two Rational Number objects.
     *
     * @param r The Rational Number to add.
     * @return The result of the addition as a new Rational Number object.
     */
    public RatNum add(RatNum r) {
        int newNum = (numerator * r.denominator) + (r.numerator * denominator);
        int newDen = denominator * r.denominator;
        return new RatNum(newNum, newDen);
    }

    /**
     * Subtracts two Rational Number objects.
     *
     * @param r The Rational Number to subtract.
     * @return The result of the subtraction as a new Rational Number object.
     */
    public RatNum sub(RatNum r) {
        int newNum = (numerator * r.denominator) - (r.numerator * denominator);
        int newDen = denominator * r.denominator;
        return new RatNum(newNum, newDen);
    }

    /**
     * Multiplies two Rational Number objects.
     *
     * @param r The Rational Number to multiply with.
     * @return The result of the multiplication as a new Rational Number object.
     */
    public RatNum mul(RatNum r) {
        int newNum = numerator * r.numerator;
        int newDen = denominator * r.denominator;
        return new RatNum(newNum, newDen);
    }

    /**
     * Divides two Rational Number objects.
     *
     * @param r The Rational Number to divide by.
     * @return The result of the division as a new Rational Number object.
     */
    public RatNum div(RatNum r) {
        int newNum = numerator * r.denominator;
        int newDen = denominator * r.numerator;
        return new RatNum(newNum, newDen);
    }

    /**
     * Rounds and returns the Rational Number as an integer string.
     *
     * @return The rounded integer string representation of the Rational Number.
     */
    public String toIntString() {
        int result = numerator / denominator;
        return Integer.toString(result);
    }

}
