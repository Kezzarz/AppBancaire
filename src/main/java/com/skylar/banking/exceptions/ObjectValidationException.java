package com.skylar.banking.exceptions;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class ObjectValidationException extends RuntimeException {

    private final Set<String> violations;

    private final String violationSource;
}
