package reflection;

import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.*;

public class DescribeClass {

    public static <T> void printlnIndentLoop(T[] arrayIn) {
        for (T ele : arrayIn) {
            System.out.println("    " + ele);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        if (args.length != 1) {
            System.out.println("Usage: Please supply the class name");
            return;
        }// TODO: describe how to use the utility

        // TODO: implement the functionality for Question 1
        String classToReflectName = args[0];
        System.out.println("We are reflecting data about the " + classToReflectName + " class.");

        Class classToReflect = Class.forName(classToReflectName);

        System.out.println("The name of the class is: " + classToReflect.getName());

        if (classToReflect.isInterface()) {
            System.out.println("This is an interface");
        } else {
            System.out.println("This is a class");
        }

        Constructor[] constructors = classToReflect.getConstructors();

        System.out.println("The Constructors of the class are:");

        printlnIndentLoop(constructors);

        Method[] methods = classToReflect.getMethods();

        System.out.println("The Methods of the class are:");

        printlnIndentLoop(methods);

        Field[] fields = classToReflect.getFields();

        printlnIndentLoop(fields);

    }
}
