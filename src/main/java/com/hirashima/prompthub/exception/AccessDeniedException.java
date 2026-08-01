package com.hirashima.prompthub.exception;

public class AccessDeniedException extends RuntimeException {
	
//	編集権限
    public AccessDeniedException(String message) {
        super(message);
    }
}