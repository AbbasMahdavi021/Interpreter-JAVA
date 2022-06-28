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

    private BufferedReader byteSource;
    
    /**
     * Constructor Simply creates a buffered reader.
     * YOU ARE NOT ALLOWED TO READ FILE CONTENTS HERE
     * THIS NEEDS TO HAPPEN IN loadCodes.
     */
    public ByteCodeLoader(String file) throws IOException {
        this.byteSource = new BufferedReader(new FileReader(file));
    }
    /**
     * This function should read one line of source code at a time.
     * For each read line:
     *      Tokenize string to break it into parts. Can also use the split function in the String class.
     *      Grab THE correct class name for the given ByteCode from CodeTable
     *      Create an instance of the ByteCode class name returned from code table.
     *      Parse any additional arguments for the given ByteCode and send them to
     *      the newly created ByteCode instance via the init function.
     *      Then add newly created and initialize ByteCode to the program
     */
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
