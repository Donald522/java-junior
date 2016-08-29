package com.acme.edu.moduleTest;

import com.acme.edu.decorators.Decorator;
import com.acme.edu.loggers.StringLogger;
import org.junit.Test;
import org.omg.CORBA.Object;

import java.util.Arrays;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Created by Dmitriy on 30.08.2016.
 */
public class DecoratorTest {
    @Test
    public void shouldAddSpaceInPrefixInConstructor() {
        Decorator decorator;

        decorator = new Decorator("prefix without space", " postfix");

        assertEquals("prefix without space  postfix", decorator.decorate(""));
    }
    @Test
    public void shouldAddSpaceInPostfixInConstructor() {
        Decorator decorator;

        decorator = new Decorator("prefix ", "postfix without space");

        assertEquals("prefix  postfix without space", decorator.decorate(""));
    }
    @Test
    public void shouldCheckEquals() {
        Decorator decorator1, decorator2;

        decorator1 = new Decorator("prefix", "postfix");
        decorator2 = new Decorator("prefix", "postfix");

        assertEquals(true, decorator1.equals(decorator2));
    }
    @Test
    public void shouldReturnHashCode() {
        Decorator decorator = new Decorator("prefix", "postfix");

        int hash = decorator.hashCode();

        String[] arrString = {"prefix ", " postfix"};
        assertEquals(Arrays.hashCode(arrString), hash);
    }
}
