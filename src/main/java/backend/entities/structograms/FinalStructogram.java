package backend.entities.structograms;

import backend.entities.statements.varoperations.declaration.Declaration;
import backend.enums.FuncType;

import java.util.ArrayList;

public class FinalStructogram extends Structogram {
    /**
     * Constructor for Structogram entity
     *
     * @param fileName name of the file
     * @param functionName name of the structogram
     * @param funcType type of the structogram
     */
    public FinalStructogram(String fileName, String functionName, FuncType funcType) {
        super(fileName, functionName, funcType);
    }

    /**
     * Constructor for Structogram entity
     *
     * @param fileName name of the file
     * @param functionName name of the structogram
     * @param funcType type of the structogram
     * @param parameterList parameter's of the structogram
     */
    public FinalStructogram(String fileName, String functionName, FuncType funcType, ArrayList<Declaration> parameterList) {
        super(fileName, functionName, funcType, parameterList);
    }

    /**
     * Constructor for Structogram entity
     *
     * @param fileName name of the file
     * @param functionName name of the structogram
     * @param funcType type of the structogram
     * @param precondition that condition must be true at the beginning of the function
     * @param postcondition that condition must be true at the, when the program exits from the function
     */
    public FinalStructogram(String fileName, String functionName, FuncType funcType, String precondition, String postcondition) {
        super(fileName, functionName, funcType, precondition, postcondition);
    }

    /**
     * Constructor for Structogram entity
     *
     * @param fileName name of the file
     * @param functionName name of the structogram
     * @param funcType type of the structogram
     * @param precondition that condition must be true at the beginning of the function
     * @param postcondition that condition what must be true at the, when the program exits from the function
     * @param parameterList parameter's of the function structogram
     */
    public FinalStructogram(String fileName, String functionName, FuncType funcType, String precondition, String postcondition, ArrayList<Declaration> parameterList) {
        super(fileName, functionName, funcType, precondition, postcondition, parameterList);
    }
}
