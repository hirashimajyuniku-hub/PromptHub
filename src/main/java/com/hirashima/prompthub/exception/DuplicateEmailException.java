package com.hirashima.prompthub.exception;

public class DuplicateEmailException
extends RuntimeException {

//	このメールアドレスは既に登録されています
public DuplicateEmailException(
    String message) {

super(message);
}
}