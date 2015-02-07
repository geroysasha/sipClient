/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.0
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.pjsip.pjsua2;

public class UserEvent {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected UserEvent(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(UserEvent obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pjsua2JNI.delete_UserEvent(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setUser1(SWIGTYPE_p_void value) {
    pjsua2JNI.UserEvent_user1_set(swigCPtr, this, SWIGTYPE_p_void.getCPtr(value));
  }

  public SWIGTYPE_p_void getUser1() {
    long cPtr = pjsua2JNI.UserEvent_user1_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_void(cPtr, false);
  }

  public void setUser2(SWIGTYPE_p_void value) {
    pjsua2JNI.UserEvent_user2_set(swigCPtr, this, SWIGTYPE_p_void.getCPtr(value));
  }

  public SWIGTYPE_p_void getUser2() {
    long cPtr = pjsua2JNI.UserEvent_user2_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_void(cPtr, false);
  }

  public void setUser3(SWIGTYPE_p_void value) {
    pjsua2JNI.UserEvent_user3_set(swigCPtr, this, SWIGTYPE_p_void.getCPtr(value));
  }

  public SWIGTYPE_p_void getUser3() {
    long cPtr = pjsua2JNI.UserEvent_user3_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_void(cPtr, false);
  }

  public void setUser4(SWIGTYPE_p_void value) {
    pjsua2JNI.UserEvent_user4_set(swigCPtr, this, SWIGTYPE_p_void.getCPtr(value));
  }

  public SWIGTYPE_p_void getUser4() {
    long cPtr = pjsua2JNI.UserEvent_user4_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_void(cPtr, false);
  }

  public UserEvent() {
    this(pjsua2JNI.new_UserEvent(), true);
  }

}
