package bodl.sandbox.mongo.hellogrid.command;

import java.math.BigInteger;

/**
 * Created by IntelliJ IDEA.
 * User: akollegger
 * Date: Jun 13, 2010
 * Time: 6:33:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class BaseConverter {
  private static final String baseDigits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

  public static String toBase62( BigInteger decimalNumber ) {
      return fromDecimalToOtherBase(BigInteger.valueOf(62), decimalNumber );
  }

  public static String toBase36( BigInteger decimalNumber ) {
      return fromDecimalToOtherBase(BigInteger.valueOf(36), decimalNumber );
  }

  public static String toBase16( BigInteger decimalNumber ) {
      return fromDecimalToOtherBase(BigInteger.valueOf(16), decimalNumber );
  }

  public static String toBase8( BigInteger decimalNumber ) {
      return fromDecimalToOtherBase(BigInteger.valueOf(8), decimalNumber );
  }

  public static String toBase2( BigInteger decimalNumber ) {
      return fromDecimalToOtherBase(BigInteger.valueOf(2), decimalNumber );
  }

  public static BigInteger fromBase62( String base62Number ) {
      return fromOtherBaseToDecimal(BigInteger.valueOf(62), base62Number );
  }

  public static BigInteger fromBase36( String base36Number ) {
      return fromOtherBaseToDecimal(BigInteger.valueOf(36), base36Number );
  }

  public static BigInteger fromBase16( String base16Number ) {
      return fromOtherBaseToDecimal(BigInteger.valueOf(16), base16Number );
  }

  public static BigInteger fromBase8( String base8Number) {
      return fromOtherBaseToDecimal(BigInteger.valueOf(8), base8Number );
  }

  public static BigInteger fromBase2( String base2Number ) {
      return fromOtherBaseToDecimal(BigInteger.valueOf(2), base2Number );
  }

  private static String fromDecimalToOtherBase ( BigInteger base, BigInteger decimalNumber ) {
      String tempVal = (decimalNumber.compareTo(BigInteger.ZERO) == 0)  ? "0" : "";
      int mod = 0;

      while(decimalNumber.compareTo(BigInteger.ZERO) != 0 ) {
          mod = decimalNumber.mod(base).intValue();
          tempVal = baseDigits.substring( mod, mod+1) + tempVal;
          decimalNumber = decimalNumber.divide(base);
      }

      return tempVal;
  }

  private static BigInteger fromOtherBaseToDecimal( BigInteger base, String number ) {
      int iterator = number.length();
      BigInteger returnValue = BigInteger.ZERO;
      BigInteger multiplier = BigInteger.ONE;

      while( iterator > 0 ) {
          returnValue =
              (BigInteger.valueOf(baseDigits.indexOf( number.substring( iterator - 1, iterator ) ))
                  .multiply(multiplier).add(returnValue));
          multiplier = multiplier.multiply(base);
          --iterator;
      }
      return returnValue;
  }

}
