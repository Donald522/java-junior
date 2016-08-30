package com.acme.edu.decorators;

import java.util.Objects;

/**
 * Interface which you should implement to yor own Decorator.
 * Created by anton on 25.08.16.
 */
public class Decorator {
    protected String prefix;
    protected String postfix;

    public Decorator(String prefix, String postfix) {
        if ((prefix.length() > 0) && (prefix.charAt(prefix.length() - 1) != ' ')) {
            prefix += " ";
        }
        if ((postfix.length() > 0) && (postfix.charAt(0) != ' ')) {
            postfix = " " + postfix;
        }
        this.prefix = prefix;
        this.postfix = postfix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Decorator decorator = (Decorator) o;
        return Objects.equals(prefix, decorator.prefix) &&
                Objects.equals(postfix, decorator.postfix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prefix, postfix);
    }

    public String decorate(String message) {
        return prefix + message + postfix;
    }

}
