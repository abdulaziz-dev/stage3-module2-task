package com.mjc.school;

import com.mjc.school.controller.Operations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;
@Component
public class Menu {
    private final OperationHandler operationHandler;

    @Autowired
    public Menu(OperationHandler operationHandler) {
        this.operationHandler = operationHandler;
    }

    public void printMenu(){

            while (true){
                try {
                    for (Operations s: Operations.values()){
                        System.out.println(s.getOperationWithNumber());
                    }
                    System.out.println("Enter the number of operation:");
                    Scanner sc = new Scanner(System.in);

                    try {
                        int command = sc.nextInt();
                        if (command == 0) System.exit(0);
                        if (command > 0 && command <= 10){
                            operationHandler.executeOperation(command);
                        }
                    } catch (InputMismatchException e){
                        System.out.println("Command not found.");
                    }

                } catch (RuntimeException e){
                    System.out.println(e.getMessage());
                }
            }
    }
}
