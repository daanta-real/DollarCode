package $;

public class $ {

	// print
	public static void pr (Object o)                       { System.out.print(o);             }
	// println
	public static void pn ()                               { System.out.println();            }
	public static void pn (Object o)                       { System.out.println(o);           }
	// printf
	public static void pf (String format, Object ... args) { System.out.printf(format, args); }

	// print
	public static void er (Object o)                       { System.err.print(o);             }
	// println
	public static void en ()                               { System.err.println();            }
	public static void en (Object o)                       { System.err.println(o);           }
	// printf
	public static void ef (String format, Object ... args) { System.err.printf(format, args); }

	// returns ①, ②, ③, ... as you input 0, 1, 2, ...
	public static char n(int number) { return (char)(9312 + number); }

	//
	// 구글 번역 안내 달기 스트링
	public static final String getGoogleTR_str = "<style type=\"text/css\">\r\n"
		+ "<!--\r\n"
		+ "#goog-gt-tt {display:none !important;}\r\n"
		+ ".goog-te-banner-frame {display:none !important;}\r\n"
		+ ".goog-te-menu-value:hover {text-decoration:none !important;}\r\n"
		+ ".goog-te-gadget-icon {background-image:url(//gtranslate.net/flags/gt_logo_19x19.gif) !important;background-position:0 0 !important;}\r\n"
		+ "body {top:0 !important;}\r\n"
		+ "-->\r\n"
		+ "</style>\r\n"
		+ "<div id=\"google_translate_element\"></div>\r\n"
		+ "<script type=\"text/javascript\">\r\n"
		+ "function googleTranslateElementInit() {new google.translate.TranslateElement({pageLanguage: 'ko', layout: google.translate.TranslateElement.InlineLayout.SIMPLE,autoDisplay: false, includedLanguages: ''}, 'google_translate_element');}\r\n"
		+ "</script><script type=\"text/javascript\" src=\"https://translate.google.com/translate_a/element.js?cb=googleTranslateElementInit\"></script>";


}