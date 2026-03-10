package org.philipp.fun.pathmodelbackend;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GreeterTest {

    @Test
    void shouldGreetWithProvidedName() {
        final Greeter greeter = new Greeter();
        assertEquals("Hello, Alice!", greeter.greet("Alice"));
    }

    @Test
    void shouldGreetWithDefaultName() {
        final Greeter greeter = new Greeter();
        assertEquals("Hello, World!", greeter.greetDefault());
    }

}
