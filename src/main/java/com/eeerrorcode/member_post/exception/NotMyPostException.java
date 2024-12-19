package com.eeerrorcode.member_post.exception;

public class NotMyPostException extends RuntimeException{
  public NotMyPostException(String msg) {
    super(msg);
  }
}
