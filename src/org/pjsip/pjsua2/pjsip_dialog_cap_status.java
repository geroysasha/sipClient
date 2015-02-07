/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.0
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.pjsip.pjsua2;

public final class pjsip_dialog_cap_status {
  public final static pjsip_dialog_cap_status PJSIP_DIALOG_CAP_UNSUPPORTED = new pjsip_dialog_cap_status("PJSIP_DIALOG_CAP_UNSUPPORTED", pjsua2JNI.PJSIP_DIALOG_CAP_UNSUPPORTED_get());
  public final static pjsip_dialog_cap_status PJSIP_DIALOG_CAP_SUPPORTED = new pjsip_dialog_cap_status("PJSIP_DIALOG_CAP_SUPPORTED", pjsua2JNI.PJSIP_DIALOG_CAP_SUPPORTED_get());
  public final static pjsip_dialog_cap_status PJSIP_DIALOG_CAP_UNKNOWN = new pjsip_dialog_cap_status("PJSIP_DIALOG_CAP_UNKNOWN", pjsua2JNI.PJSIP_DIALOG_CAP_UNKNOWN_get());

  public final int swigValue() {
    return swigValue;
  }

  public String toString() {
    return swigName;
  }

  public static pjsip_dialog_cap_status swigToEnum(int swigValue) {
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue)
        return swigValues[i];
    throw new IllegalArgumentException("No enum " + pjsip_dialog_cap_status.class + " with value " + swigValue);
  }

  private pjsip_dialog_cap_status(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  private pjsip_dialog_cap_status(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue+1;
  }

  private pjsip_dialog_cap_status(String swigName, pjsip_dialog_cap_status swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue+1;
  }

  private static pjsip_dialog_cap_status[] swigValues = { PJSIP_DIALOG_CAP_UNSUPPORTED, PJSIP_DIALOG_CAP_SUPPORTED, PJSIP_DIALOG_CAP_UNKNOWN };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}

