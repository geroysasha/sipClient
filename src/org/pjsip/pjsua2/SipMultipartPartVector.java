/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.0
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.pjsip.pjsua2;

public class SipMultipartPartVector {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected SipMultipartPartVector(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(SipMultipartPartVector obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pjsua2JNI.delete_SipMultipartPartVector(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public SipMultipartPartVector() {
    this(pjsua2JNI.new_SipMultipartPartVector__SWIG_0(), true);
  }

  public SipMultipartPartVector(long n) {
    this(pjsua2JNI.new_SipMultipartPartVector__SWIG_1(n), true);
  }

  public long size() {
    return pjsua2JNI.SipMultipartPartVector_size(swigCPtr, this);
  }

  public long capacity() {
    return pjsua2JNI.SipMultipartPartVector_capacity(swigCPtr, this);
  }

  public void reserve(long n) {
    pjsua2JNI.SipMultipartPartVector_reserve(swigCPtr, this, n);
  }

  public boolean isEmpty() {
    return pjsua2JNI.SipMultipartPartVector_isEmpty(swigCPtr, this);
  }

  public void clear() {
    pjsua2JNI.SipMultipartPartVector_clear(swigCPtr, this);
  }

  public void add(SipMultipartPart x) {
    pjsua2JNI.SipMultipartPartVector_add(swigCPtr, this, SipMultipartPart.getCPtr(x), x);
  }

  public SipMultipartPart get(int i) {
    return new SipMultipartPart(pjsua2JNI.SipMultipartPartVector_get(swigCPtr, this, i), false);
  }

  public void set(int i, SipMultipartPart val) {
    pjsua2JNI.SipMultipartPartVector_set(swigCPtr, this, i, SipMultipartPart.getCPtr(val), val);
  }

}
