package org.philipp.fun.pathmodelbackend;

/**
 * A simple greeter that produces greeting messages.
 *
 * <p>This class serves as an example of a well-documented, testable component
 * within the Pathmodel application.</p>
 */
public final class Greeter {

    /** Prefix prepended to every greeting. */
    private static final String GREETING_PREFIX = "Hello, ";

    /** Suffix appended to every greeting. */
    private static final String GREETING_SUFFIX = "!";

    /** Name used when no explicit name is provided. */
    private static final String DEFAULT_NAME = "World";

    /**
     * Returns a greeting message for the given name.
     *
     * @param name the name to greet; must not be {@code null}
     * @return the greeting message
     */
    public String greet(final String name) {
        return GREETING_PREFIX + name + GREETING_SUFFIX;
    }

    /**
     * Returns a greeting message using the default name.
     *
     * @return the greeting message
     */
    public String greetDefault() {
        return greet(DEFAULT_NAME);
    }

}
