package $;

public class $ {

	// print
	public static void pr (Object o)                       { System.out.print(o);             }

	// println
	public static void pn ()                               { System.out.println();            }
	public static void pn (Object o)                       { System.out.println(o);           }

	// printf
	public static void pf (String format, Object ... args) { System.out.printf(format, args); }

	// returns ①, ②, ③, ... as you input 0, 1, 2, ...
	public static char n(int number) { return (char)(9312 + number); }
}