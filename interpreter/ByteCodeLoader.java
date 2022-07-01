package interpreter;

import interpreter.bytecode.ByteCode;
import interpreter.virtualmachine.Program;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;


public final class ByteCodeLoader {

    private final BufferedReader byteSource;

    public ByteCodeLoader(String file) throws IOException {
        this.byteSource = new BufferedReader(new FileReader(file));
    }

    public Program loadCodes(){

        ArrayList<ByteCode> byteCodes = new ArrayList<>();
        String line;
        String className;
        Class c;
        ByteCode bc;

        Program program = new Program(byteCodes);

        try {
            while ((line = byteSource.readLine()) != null) {
                ArrayList<String> items = new ArrayList<>(Arrays.asList(line.split("\\s+")));
                className = CodeTable.getClassName(items.get(0));
                c = Class.forName("interpreter.bytecode." + className);
                bc = (ByteCode) c.getDeclaredConstructor().newInstance();

                bc.init(items);

                byteCodes.add(bc);
            }
        } catch (IOException | ClassNotFoundException | NoSuchMethodException
                 | IllegalAccessException | InstantiationException | InvocationTargetException e){
            e.printStackTrace();
            System.err.println(e);
            System.exit(-2);
        }

        program.resolveAddress();
        return program;
    }

}
