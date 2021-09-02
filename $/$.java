package $;

public class $ {

	// print
	public static void pr (Object o)                       { System.out.print(o);             }

	// println
	public static void pn ()                               { System.out.println();            }
	public static void pn (Object o)                       { System.out.println(o);           }

	// printf
	public static void pf (String format, Object ... args) { System.out.printf(format, args); }

}