/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.0
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.pjsip.pjsua2;

public class CallVidSetStreamParam {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected CallVidSetStreamParam(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(CallVidSetStreamParam obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pjsua2JNI.delete_CallVidSetStreamParam(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setMedIdx(int value) {
    pjsua2JNI.CallVidSetStreamParam_medIdx_set(swigCPtr, this, value);
  }

  public int getMedIdx() {
    return pjsua2JNI.CallVidSetStreamParam_medIdx_get(swigCPtr, this);
  }

  public void setDir(pjmedia_dir value) {
    pjsua2JNI.CallVidSetStreamParam_dir_set(swigCPtr, this, value.swigValue());
  }

  public pjmedia_dir getDir() {
    return pjmedia_dir.swigToEnum(pjsua2JNI.CallVidSetStreamParam_dir_get(swigCPtr, this));
  }

  public void setCapDev(int value) {
    pjsua2JNI.CallVidSetStreamParam_capDev_set(swigCPtr, this, value);
  }

  public int getCapDev() {
    return pjsua2JNI.CallVidSetStreamParam_capDev_get(swigCPtr, this);
  }

  public CallVidSetStreamParam() {
    this(pjsua2JNI.new_CallVidSetStreamParam(), true);
  }

}
