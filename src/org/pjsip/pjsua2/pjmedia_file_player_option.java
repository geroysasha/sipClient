/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.0
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.pjsip.pjsua2;

public final class pjmedia_file_player_option {
  public final static pjmedia_file_player_option PJMEDIA_FILE_NO_LOOP = new pjmedia_file_player_option("PJMEDIA_FILE_NO_LOOP", pjsua2JNI.PJMEDIA_FILE_NO_LOOP_get());

  public final int swigValue() {
    return swigValue;
  }

  public String toString() {
    return swigName;
  }

  public static pjmedia_file_player_option swigToEnum(int swigValue) {
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue)
        return swigValues[i];
    throw new IllegalArgumentException("No enum " + pjmedia_file_player_option.class + " with value " + swigValue);
  }

  private pjmedia_file_player_option(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  private pjmedia_file_player_option(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue+1;
  }

  private pjmedia_file_player_option(String swigName, pjmedia_file_player_option swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue+1;
  }

  private static pjmedia_file_player_option[] swigValues = { PJMEDIA_FILE_NO_LOOP };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}

