package reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;

public class Autograder {

    static final String CLASS_NAME = "java.lang.Integer"; // TODO: insert the class name to be tested

    @Test
    public void testMoreThanFourFields() throws ClassNotFoundException {
        Class cls = Class.forName(CLASS_NAME);
        Field[] fields = cls.getFields();
        //Arrays.stream(fields).sequential().forEach(System.out::println);
        assert fields.length < 5;
    }

    @Test
    public void testNonPrivateFields() throws ClassNotFoundException {
        Class cls = Class.forName(CLASS_NAME);
        Field[] fields = cls.getFields();
        HashSet<String> uniqueModifiers = new HashSet<>();
        for (Field field : fields) {
            String[] modifiers = Modifier.toString(field.getModifiers()).split(" ");
            uniqueModifiers.addAll(Arrays.asList(modifiers));
        }
        assert !uniqueModifiers.contains("private");
    }

    // TODO: add a test for each of the remaining items of Question 3

    @Test
    public void testNoArrayList() throws ClassNotFoundException {
        Class cls = Class.forName(CLASS_NAME);
        Field[] fields = cls.getFields();
        HashSet<String> types = new HashSet<>();
        for (Field field : fields) {
            types.add(String.valueOf(field.getType()));
        }
        assert !types.contains("java.util.ArrayList");
    }

    @Test
    public void testHelperMethodLessThan2() throws ClassNotFoundException {
        Class cls = Class.forName(CLASS_NAME);
        Method[] methods = cls.getMethods();
        Integer privateCount = 0;
        for (Method method : methods) {
            HashSet<String> modifiers = new HashSet<>(Arrays.asList(Modifier.toString(method.getModifiers()).split(" ")));
            if (modifiers.contains("private")) {
                privateCount++;
            }
        }
        assert privateCount > 1;
    }

    @Test
    public void noThrowsOnMethod() throws ClassNotFoundException {
        Class cls = Class.forName(CLASS_NAME);
        Method[] methods = cls.getMethods();
        int countThrows = 0;
        for (Method method : methods) {
            countThrows += method.getExceptionTypes().length;
        }
        assert countThrows == 0;
    }

    @Test
    public void noIntReturns() throws ClassNotFoundException {
        Class cls = Class.forName(CLASS_NAME);
        Method[] methods = cls.getMethods();
        int intReturn = 0;
        for (Method method : methods) {
            if (method.getGenericReturnType() == int.class) {
                intReturn++;
            }
        }
        assert intReturn == 0;
    }

    @Test
    public void nullValueConstructor() throws ClassNotFoundException {
        Class cls = Class.forName(CLASS_NAME);
        Constructor constructor;
        try {
            constructor = cls.getConstructor();
            assert true;
        } catch (NoSuchMethodException e) {
            System.out.println(e);
            assert false;
        }
    }

}
