package com.smartfoxserver.v2.exceptions;

public class SFSException extends Exception {

   public SFSException() {
      super();
   }

   public SFSException(String message) {
      super(message);
   }

   public SFSException(Throwable t) {
      super(t);
   }

}