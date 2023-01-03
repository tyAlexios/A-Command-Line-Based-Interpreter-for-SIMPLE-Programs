package hk.edu.polyu.comp.comp2021.simple.view;

/**
 * Command format and some help
 */
public class menu
{
    /**
     * welcomePage
     */
    public static void welcomePage()
    {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Welcome to Command-Line-Based Interpreter for Simple Programs");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("The Simple Program supports only two data types, namely [bool] and [int]");
        System.out.println("[bool] only contain \"true\" and \"false\"");
        System.out.println("[int] range between -99999 and 99999 (it will round if out of range)");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Warning: Each statement in a Simple program is identified by a unique label");
        System.out.println("Warning: Each variable or expression is identified by a unique name");
        System.out.println("Enter \"help\" for search all command");
        System.out.println("Enter command after \">>>\"");
        System.out.println("----------------------------------------------------------------------------");

    }

    /**
     * definition command format help page
     */
    public static void defineCommandHelpPage() {
        System.out.println("[Variables]: \"vardef lab typ varName expRef\"");
        System.out.println("[Binary Expressions]: \"binexpr expName expRef1 bop expRef2\"");
        System.out.println("[Unary Expressions]: \"unexpr expName uop expRef1\"");
        System.out.println("[Assignment Statements]: \"assign lab varName expRef\"");
        System.out.println("[Print Statements]: \"print lab expRef\"");
        System.out.println("[Skip Statements]: \"skip lab\"");
        System.out.println("[Block Statements]: \"block lab statementLab1 ... statementLabn\"");
        System.out.println("[Conditional Statements]: \"if lab expRef statementLab1 statementLab2\"");
        System.out.println("[Loop Statements]: \"while lab expRef statementLab1\"");
        System.out.println("[Simple Programs]: \"program programName statementLab\"");
    }

    /**
     * execution command help page
     */
    public static void executeCommandHelpPage() {
        System.out.println("[Execute Program]: \"execute programName\"");
        System.out.println("[List Program]: \"list programName\"");
        System.out.println("[Store Program]: \"store programName path\"");
        System.out.println("[Load Program]: \"load path programName\"");
        System.out.println("[Quit]: \"quit\"");
        System.out.println("[Debug Program]: \"debug programName\"");
        System.out.println("[Set/Remove Breakpoint]: \"togglebreakpoint programName statementLab\"");
        System.out.println("[Inspect]: \"inspect programName varName\"");
        System.out.println("[Instrument Programs]: \"instrument programName statementLab pos expRef\"");
    }

    /**
     * all help pages
     */
    public static void mainHelpPage()
    {
        System.out.println("------------------------------------Help------------------------------------");
        System.out.println("The Define help list:");
        defineCommandHelpPage();
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("The Execute help list:");
        executeCommandHelpPage();
        System.out.println("----------------------------------------------------------------------------");

    }



}
