package file_converter;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;


@SuppressWarnings("serial")

public class DollarCode_Converter extends JFrame {

    // 변수준비
    JPanel jp1, jp2, jp3, jp4, jp5;				// 패널
    JLabel jl1, jl2, jl3, jl4;					// 라벨
    JButton jb1, jb2, jb3, jb4, jbExit;			// 버튼
    ActionListener ja1, ja2, ja3, ja4, jaExit;	// 버튼리스너
    GridBagConstraints gbc;                     // gbc 레이어 내부정렬기
    JFileChooser c;								// 파일선택기
    String f;									// 내가고르는 파일이름

    // 컨버터 교환정보 DB. 순서주의! 가장 긴것부터 차례대로 넣어줘야 꼬임 안생김.
    static final String[][] replaceDB = {
		{ "\\$.pn", "System.out.println" },
		{ "\\$.pf", "System.out.printf"  },
		{ "\\$.pr", "System.out.print"   }
    };

    // 생성자
    private DollarCode_Converter() { // 이게 싱글톤인가?
        // 창 생성
    	// 부모생성자에 String 넘겨주면 타이틀임
        super("DollarCode Converter");
        readyElements();  // 엘리먼트 준비
        readySettings();  // 환경설정 준비
        readyContexts();  // 내용물 준비
        readyFuncs();     // 각종 기능 준비
        setVisible(true); // 릴리즈
    }

    // 엘리먼트 생성: 패널, 라벨, 버튼
    private void readyElements() {
        // 패널 생성: 패널 생성하고, JFrame에 추가
        jp1		= new JPanel(); 	add(jp1);
        jp2		= new JPanel(); 	add(jp2);
        jp3		= new JPanel(); 	add(jp3);
        jp4		= new JPanel(); 	add(jp4);
        jp5		= new JPanel();		add(jp5);
        // 엘리먼트 생성: 각 패널에 라벨 및 버튼 추가
        jl1		= new JLabel();		jp1.add(jl1);		// 패널 1 - 라벨 1
        jl2		= new JLabel();		jp2.add(jl2);		// 패널 2 - 라벨 2
        jl3		= new JLabel();		jp3.add(jl3);		// 패널 3 - 라벨 3
        jl4		= new JLabel();		jp4.add(jl4);		// 패널 4 - 라벨 4
        jb1		= new JButton();	jp1.add(jb1);		// 패널 1 - 버튼 1
        jb2		= new JButton();	jp2.add(jb2);		// 패널 2 - 버튼 2
        jb3		= new JButton();	jp3.add(jb3);		// 패널 3 - 버튼 3
        jb4		= new JButton();	jp4.add(jb4);		// 패널 4 - 버튼 4
        // 탈출버튼 생성: GBC 포함
        jbExit	= new JButton();
        jp5.add(jbExit);	// 패널 5 - 버튼 exit
    }

    // 환경설정 준비
    private void readySettings() {
        // 창 환경설정 - 아이콘(128x128 png권장)
    	setIconImage(Toolkit.getDefaultToolkit().getImage("res/icon.png"));
    	// 창 환경설정 - 사이즈, 레이아웃
        setSize(450, 600);
    	setLayout(new GridLayout(5, 1));

        // 패널 외곽선 설정
        jp1.setBorder(new TitledBorder(new LineBorder(new Color(0xddffdd)), "Menu 1."));
        jp2.setBorder(new TitledBorder(new LineBorder(new Color(0xddffdd)), "Menu 2."));
        jp3.setBorder(new TitledBorder(new LineBorder(new Color(0xb3c4e6)), "Menu 3."));
        jp4.setBorder(new TitledBorder(new LineBorder(new Color(0xb3c4e6)), "Menu 4."));

        // 패널 환경설정
        jp1.setLayout(new FlowLayout()); jp1.setBackground(new Color(0xb3c4e6)); // 패널 1 환경설정
        jp2.setLayout(new FlowLayout()); jp2.setBackground(new Color(0xb3c4e6)); // 패널 2 환경설정
        jp3.setLayout(new FlowLayout()); jp3.setBackground(new Color(0xddffdd)); // 패널 3 환경설정
        jp4.setLayout(new FlowLayout()); jp4.setBackground(new Color(0xddffdd)); // 패널 4 환경설정
        jp5.setBackground(new Color(0x444444)); // 패널 5 환경설정

        // 패널 5 세로정렬
        jp5.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 버튼 환경설정
    }

    // 라벨 및 버튼 내용 준비
    private void readyContexts() {
    	// 라벨
        jl1.setText("<HTML><font size=6 color=blue>Normal → $<br/></font>"
            + "<font size=5>Convert Normal Code to $.&nbsp;&nbsp;&nbsp;</font></HTML>");
        jl2.setText("<HTML><font size=6 color=blue>Normal → $<br/></font>"
            + "<font size=5>Convert Normal Code to $.&nbsp;&nbsp;&nbsp;<br/>"
            + "<font size=4 color=maroon>◆ OVERRWITE ORIGINAL MODE</font></HTML>");
        jl3.setText("<HTML><font size=6 color=green>$ → Normal<br/></font>"
        	+ "<font size=5>Convert $ Code to Normal.&nbsp;&nbsp;&nbsp;</font></HTML>");
        jl4.setText("<HTML><font size=6 color=green>$ → Normal<br/></font>"
        	+ "<font size=5>Convert $ Code to Normal.&nbsp;&nbsp;&nbsp;<br/>"
        	+ "<font size=4 color=maroon>◆ OVERRWITE ORIGINAL MODE</font></HTML>");
        // 버튼
        jb1.setText("<HTML><font size=5><b>Open File..</b></font></HTML>");
        jb2.setText("<HTML><font size=5><b>Open File..</b></font></HTML>");
        jb3.setText("<HTML><font size=5><b>Open File..</b></font></HTML>");
        jb4.setText("<HTML><font size=5><b>Open File..</b></font></HTML>");
        jbExit.setText("<HTML><font size=20><b>Exit</b></font></HTML>");
    }

    // 기능연결
    private void readyFuncs() {
    	// 버튼
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X 버튼 클릭시 종료
        ja1 = new ActionListener()		{ @Override public void actionPerformed(ActionEvent ae) { buttonExecution(true,  true);  } };
        ja2 = new ActionListener()		{ @Override public void actionPerformed(ActionEvent ae) { buttonExecution(true,  false); } };
        ja3 = new ActionListener()		{ @Override public void actionPerformed(ActionEvent ae) { buttonExecution(false, true);  } };
        ja4 = new ActionListener()		{ @Override public void actionPerformed(ActionEvent ae) { buttonExecution(false, false); } };
        jaExit = new ActionListener()	{ @Override public void actionPerformed(ActionEvent ae) { System.exit(0); } };
        jb1.addActionListener(ja1);
        jb2.addActionListener(ja2);
        jb3.addActionListener(ja3);
        jb4.addActionListener(ja4);
        jbExit.addActionListener(jaExit);
        // ESC 키를 누르면 어플 자체를 종료하도록 함
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel");
        getRootPane().getActionMap().put("Cancel", new AbstractAction(){
            @Override public void actionPerformed(ActionEvent e) { dispose(); }
        });
    }

    // 각 상세 메소드

    // 파일탐색기열어주고 파일골라서 파일명 리턴해주는 함수
    // 저장용도로도 사용
    private String getFileName(boolean isOpening) {
    	// 파일선택기 생성
    	c = new JFileChooser();
        // 파일탐색기 기본경로 설정
        c.setCurrentDirectory(
        	new File (
    			System.getProperty("user.dir") /*+ System.getProperty("file.separator") + "Music" */
			)
		);
    	// 파일선택기 필터 설정
    	c.setFileFilter(new FileNameExtensionFilter(".java files", "java"));
        // 파일 다이얼로그 출력
        int ret = isOpening ? c.showOpenDialog(null) : c.showSaveDialog(null);
        // 창의 조작 결과에 따른 대응
        // 1. 창을 끄거나 취소누르면, null값 리턴해서 파일골라주세요 메세지 뽑게함
        if(ret != JFileChooser.APPROVE_OPTION) return null;
        // 2. 사용자가 파일을 선택하고 "열기" 버튼을 누른 경우, 파일 경로명 성공적으로 리턴
        else {
        	String filename = c.getSelectedFile().getPath();
            if (!filename .endsWith(".java"))
                filename += ".java";
            return filename;
        }
    }

    // 파일이름을 넣으면 파일내용을 String으로 돌려주는 메소드
    private String getFileContents(String fileName) {
    	StringBuilder sb = null;
    	String result = "";
        try {
        	// 파일 읽기 준비
        	// 파일 객체 및 입력 스트림 생성
        	File file = new File(fileName);
        	FileReader frdr = new FileReader(file);

        	// 파일 읽기
        	// 스트링빌더 제작
        	sb = new StringBuilder();
        	// 커서 0에 놓고 모든 문자 읽기
        	for(int curr = 0; (curr = frdr.read()) != -1; ) sb.append((char)curr);
        	frdr.close();

        	// 파일 읽기 종료
        	result = sb.toString();
        }
        catch (FileNotFoundException e) { e.getStackTrace(); return null; }
        catch (IOException e) { e.getStackTrace(); return null; }
        return result;
    }

    // 원하는 String을 넣어 경고창 출력
    private void alert(String msg) { JOptionPane.showMessageDialog(
		null, msg, "Warning", JOptionPane.WARNING_MESSAGE
	); }

    // 일반 코드 ↔ $ 코드 상호변환기
    private String dollarCodeReplacer(boolean isZip, String str) {
    	String[][] selected_db = replaceDB;
    	for(String[] db: selected_db) str = str.replaceAll(
    		isZip ? db[1] : db[0],
			isZip ? db[0] : db[1]
		);
    	return str;
    }

    // 파일 저장
    private void fileSave(String target, String contents) {
    	while(target == null) { alert("Please Choose the file."); return; }
    	try {
    		OutputStream output = new FileOutputStream(target);
    		byte[] bytes = contents.getBytes();
    		output.write(bytes);
    		output.close();
    	} catch (Exception e) { return; }
    }

    // 버튼 눌렀을 시 실행 코드
    private void buttonExecution(boolean isZip, boolean saveOther) {
    	System.out.println("저장모드: "
			+ (isZip ? "Zip (Normal → $)" : "Unzip ($ → Normal)")
			+ " / " + (saveOther ? "다른 곳에 저장" : "덮어쓰기")
		);
    	// 파일 불러오기
    	String fileName = getFileName(true);
    	if(fileName == null) { alert("Please Choose the file."); return; }
    	String str = getFileContents(fileName);
    	// 컨버팅
    	str = dollarCodeReplacer(isZip, str);
    	alert("Finished converting.");
    	// 저장하기
    	if(saveOther) fileName = getFileName(false);
    	fileSave(fileName, str);
    	alert("Finished saving.");
    }

    // 주 실행 함수
    public static void main(String[] args) {
        new DollarCode_Converter();
    }

}
