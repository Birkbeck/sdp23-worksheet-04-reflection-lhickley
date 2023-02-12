package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

public class InstantiateClass {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (args.length < 1) {
            System.out.println("Usage: <Class name> <constructorArgs>");
            return;
        }


        // TODO: implement the functionality for Question 2


        Class classToReflect = Class.forName(args[0]);

        String[] constructorArgs = Arrays.copyOfRange(args, 1, args.length);

        Constructor finalConstructor;

        Class[] parameterTypes = new Class[constructorArgs.length];

        for (int i = 0; i < constructorArgs.length; i++) {
            parameterTypes[i] = String.class;
        }

        finalConstructor = classToReflect.getConstructor(parameterTypes);

        finalConstructor.newInstance(constructorArgs);

    }
}
