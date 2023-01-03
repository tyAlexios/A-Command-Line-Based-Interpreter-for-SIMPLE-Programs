package hk.edu.polyu.comp.comp2021.simple.controller;

import hk.edu.polyu.comp.comp2021.simple.model.execution.Simple;
import hk.edu.polyu.comp.comp2021.simple.model.initialize.initialize;
import hk.edu.polyu.comp.comp2021.simple.view.menu;

import java.util.Objects;
import java.util.Scanner;

/**
 * Controller: Please run this java code to begi
 * */
public class Application
{
    /**
     * @param args : args
     * */
    public static void main(String[] args)
    {
        Simple simple = new Simple();
        initialize.Memory.clear();
        Scanner input = new Scanner(System.in);
        menu.welcomePage();
        while(true)
        {
            String line = input.nextLine();
            if(Objects.equals(line,"quit"))
            {
                System.out.println("Bye~~");
                return;
            }
            simple.run(line);
        }
    }
}


/*
vardef vardef1 int x 0
binexpr exp1 x % 2
binexpr exp2 exp1 == 0
print print1 x
skip skip1
if if1 exp2 print1 skip1
binexpr exp3 x + 1
assign assign1 x exp3
block block1 if1 assign1
binexpr exp4 x <= 10
while while1 exp4 block1
block block2 vardef1 while1
program program1 block2
execute program1

togglebreakpoint program1 assign1
debug program1
inspect program1 x

instrument program1 exp4 before 1
execute program1

program program2 vardef1
instrument program2 vardef1 before 2
execute program2
execute program1
 */
