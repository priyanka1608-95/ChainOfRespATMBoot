package com.app.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class DispenserResponse {

    private Map<String, Integer> noteMap = new HashMap<>();

    private String message;
    private String status;
    private String errorCode;
}
