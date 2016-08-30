package com.acme.edu.decorators;

import java.util.Objects;

/**
 * Interface which you should implement to yor own Decorator.
 * Created by anton on 25.08.16.
 */
public class Decorator {
    protected String prefix;
    protected String postfix;

    private String setSpaceToFix(StringBuilder fix, int position) {
        if ((fix.length() > 0) && (fix.charAt(position) != ' ')) {
            return fix.insert(position + (position == 0 ? 0 : 1), ' ').toString();
        }
        return fix.toString();
    }

    public Decorator(String prefix, String postfix) {
        this.prefix = setSpaceToFix(new StringBuilder(prefix), prefix.length() - 1);
        this.postfix = setSpaceToFix(new StringBuilder(postfix), 0);
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
