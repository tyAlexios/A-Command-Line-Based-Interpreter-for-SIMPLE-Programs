# A-Command-Line-Based-Interpreter-for-SIMPLE-Programs

The goal of this project is to develop a command-line-based interpreter for programs written in the Simple programming language. 
Simple is a programming language similar to Java, but it supports a very limited number of data types, operators, and statement types, and the interpreter will enable users to define and interpret Simple programs.

In a nutshell, Simple supports only two data types, namely bool and int: Values of type bool include only true and false, while values of type int range between -99999 and 99999, both inclusive. That is, calculation results of int type that are larger than 99999 or smaller than -99999 are always rounded to 99999 and -99999, respectively. In total, 16 different operators, namely %, +, -, *, /, #, ~, >, >=, <, <=, ==, !=, &&, ||, and !, and seven types of statements, namely variable definitions, skips, assignments, conditionals, loops, prints, and blocks, are supported in Simple; Each statement in a Simple program is identified by a unique label, while each variable or expression is identified by a unique name.

1. Command: vardef lab typ varName expRef
   
   Effect: 
     Defines a new variable definition statement with a unique label lab. When this variable definition statement is executed, a variable with the unique name varName      and of type typ is defined and initialized to the value of the expression reference expRef, where typ is either bool or int. An expression reference is either a        literal, a variable name, or an expression name.
   
   Example: 
     vardef vardef1 int x 100
   
   Note: 
     All variable/expression names and statement labels in Simple programs are identifiers, which 1) may contain only English letters and digits, 2) cannot start with      digits, and 3) may contain at most eight characters; int, bool, true, false, and all command names (e.g., vardef and execute) are Simple keywords, and  they            cannot be used as variable/expression names or statement labels.
 
 
 2. Command: binexpr expName expRef1 bop expRef2
    
    Effect: 
      Defines a new binary expression with a unique name expName. The new expression’s left operand is an expression reference expRef1, its binary operator is
      bop, and its right operand is an expression reference expRef2. The following 11 binary operators can be applied to int expression references: %, +, -, *, /, >,         >=, <, <=, ==, !=; The following four binary operators can be applied to bool expression references: &&, ||, ==, and !=.
    
    Example: binexpr exp1 x * 20

3. Command: unexpr expName uop expRef1
   
   Effect: 
     efines a new unary expression with a unique name expName. The new expression’s unary operator is uop, and its operand is an expression reference expRef1. The          following two unary operators can be applied to int values: # and ~ (equivalent to unary + and - in Java). The following one unary operator can be applied to bool      values: !.
   
   Example: unexpr exp2 ~ exp1


4. Command: assign lab varName expRef
   
   Effect: 
     Defines a new assignment statement with a unique label lab. When this assignment statement is executed, the value of expression reference expRef will be assigned      to the variable with name varName. The variable should have been defined by a vardef statement.
   
   Example: assign assign1 x exp2


5. Command: print lab expRef
   
   Effect: 
      Defines a new print statement with a unique label lab. When this print statement is executed, the value of the expression reference expRef will be printed             between a pair of square brackets.
   
   Example: print print1 exp2
  
  
6. Command: skip lab
   
   Effect:
     Defines a new skip statement with a unique label lab. When executed, a skip statement does nothing.
  
   Example: skip skip1


7. Command: block lab statementLab1 ... statementLabn
   
   Effect: 
     Defines a new block statement with a unique label lab. The block statement contains a list of statements with labels statementLab1, ..., statementLabn (n > 0).        When this block statement is executed, the statements in the block statement are executed in sequence.
   
   Example: block block1 assign1 skip1


8. Command: if lab expRef statementLab1 statementLab2
   
   Effect: 
     Defines a new conditional statement with a unique label lab. When this conditional statement is executed, the statement with label statementLab1 is executed if        the expression reference expRef evaluates to true; Otherwise, the statement with label statementLab2 is executed.
   
   Example: if if1 exp5 block1 print1
   
9. Command: while lab expRef statementLab1
   
   Effect: 
     Defines a new loop statement with a unique label lab. When this loop statement is executed, the value of the expression reference expRef is evaluated repeatedly:      If it evaluates to true, the statement with label statementLab1 is executed; Otherwise, the loop terminates.
   
   Example: while while1 true block1 (note that the statement with label block1 will be repeatedly executed for ever when this while statement is executed)
   
   
10. Command: program programName statementLab
    
    Effect: 
      Defines a new Simple program with a unique name programName, and the program has the statement labeled statementLab as its body. When the program
      is executed, the statement in its body is executed accordingly.
    
    Example: program program1 while1


11. Command: execute programName
    
    Effect: Executes the defined program named programName.
    
    Example: execute program1
    
    
12. Command: list programName
    
    Effect: Prints out the list of commands that defines the program with name programName.
    
    Example: list program1


13. Command: store programName path
    
    Effect: Stores the defined program with name programName into the file at path.
    
    Example: store program1 d:\prog1.simple
    
    
14. Command: load path programName
    
    Effect: Loads the defined program from path and names it as programName.
    
    Example: load d:\prog1.simple program1


15. Command: quit
    
    Effect: Terminates the execution of the interpreter.


16. Command: debug programName
    
    Effect: Starts/Continues executing the program named programName in debug mode till completion or a breakpoint.
    
    Example: debug program1


17. Command: togglebreakpoint programName statementLab
    
    Effect: 
      Sets/Removes a breakpoint at the statement labeled statementLab inside the program with name programName. During debugging, the execution of the program               will suspend when encountering the breakpoint, i.e., the statement with label statementLab becomes the next statement to execute.
    
    Example: togglebreakpoint program1 block1


18. Command: inspect programName varName
    
    Effect: 
      When the execution of program named programName is suspended during debugging, this command can be invoked to print out the value of the variable named varName         within a pair of angle brackets, if the variable is in scope.
    
    Example: inspect program1 x
    
    
19. Command: instrument programName statementLab pos expRef
    
    Effect: 
      Prints out the value of expRef in a pair of curly braces right before/after the statement with label statementLab from the program named programName is
      executed, where pos should be either before or after. Note that multiple instrument commands can be used to print out multiple strings at a single statement           location.
    
    Example: 
      instrument program1 block1 after 5 will cause the interpreter toprint out “{5}” after each time the statement labeled block1 from program named program1               is executed.






