/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.0
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.pjsip.pjsua2;

public class Version {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected Version(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(Version obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pjsua2JNI.delete_Version(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setMajor(int value) {
    pjsua2JNI.Version_major_set(swigCPtr, this, value);
  }

  public int getMajor() {
    return pjsua2JNI.Version_major_get(swigCPtr, this);
  }

  public void setMinor(int value) {
    pjsua2JNI.Version_minor_set(swigCPtr, this, value);
  }

  public int getMinor() {
    return pjsua2JNI.Version_minor_get(swigCPtr, this);
  }

  public void setRev(int value) {
    pjsua2JNI.Version_rev_set(swigCPtr, this, value);
  }

  public int getRev() {
    return pjsua2JNI.Version_rev_get(swigCPtr, this);
  }

  public void setSuffix(String value) {
    pjsua2JNI.Version_suffix_set(swigCPtr, this, value);
  }

  public String getSuffix() {
    return pjsua2JNI.Version_suffix_get(swigCPtr, this);
  }

  public void setFull(String value) {
    pjsua2JNI.Version_full_set(swigCPtr, this, value);
  }

  public String getFull() {
    return pjsua2JNI.Version_full_get(swigCPtr, this);
  }

  public void setNumeric(long value) {
    pjsua2JNI.Version_numeric_set(swigCPtr, this, value);
  }

  public long getNumeric() {
    return pjsua2JNI.Version_numeric_get(swigCPtr, this);
  }

  public Version() {
    this(pjsua2JNI.new_Version(), true);
  }

}
