package com.nagalakshmi.democamel.util;

import java.util.function.Predicate;

public class PhNumValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        return (s !=null && !s.equalsIgnoreCase("") && s.startsWith("+91") && s.length() == 13);
    }
}
