package org.philipp.fun.pathmodelbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Pathmodel Spring Boot application.
 */
@SpringBootApplication
@SuppressWarnings("HideUtilityClassConstructor")
public class PathmodelBackendApplication {

    /**
     * Starts the Spring Boot application.
     *
     * @param args command-line arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(PathmodelBackendApplication.class, args);
    }

}
