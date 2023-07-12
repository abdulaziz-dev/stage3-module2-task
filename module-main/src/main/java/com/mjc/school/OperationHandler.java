package com.mjc.school;

import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.ControllerHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

@Component
public class OperationHandler {
    private final ControllerHandler controllerHandler;

    public OperationHandler(ControllerHandler controllerHandler) {
        this.controllerHandler = controllerHandler;
    }

    void executeOperation(int operation){
        Method[] methods = ControllerHandler.class.getDeclaredMethods();
        Method method = Arrays.stream(methods).filter(x -> x.isAnnotationPresent(CommandHandler.class))
                .filter(x -> x.getAnnotation(CommandHandler.class).command() == operation).findFirst().orElse(null);
        try {
            method.invoke(controllerHandler);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
