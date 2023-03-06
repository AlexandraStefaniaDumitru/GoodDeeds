package com.example.faptebune.domain.validators;

import com.example.faptebune.domain.exceptions.EmptyStringException;
import com.example.faptebune.domain.exceptions.NegativeNumberException;

public interface Validator<T> {
    void validate(T entity) throws ValidationException, EmptyStringException, NegativeNumberException;
}