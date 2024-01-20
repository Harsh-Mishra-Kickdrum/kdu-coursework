package org.example;

import java.lang.reflect.Method;

public class ExceptionAspect {

    public void handleException(Method method, HandleError handleErrorAnnotation, Exception exception) {
        Class<? extends Exception>[] exceptionTypes = handleErrorAnnotation.value();

        // Handle exceptions based on the specified types
        for (Class<? extends Exception> exceptionType : exceptionTypes) {
            if (exceptionType.isInstance(exception)) {
                Logging.getmsg().error("Exception handled in method " + method.getName() + ": " + exception.getMessage());
                // Add additional logic for handling the exception if needed
                break;
            }
        }
    }
}
