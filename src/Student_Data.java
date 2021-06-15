

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.json.simple.JSONObject;

//import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.PrintWriter;

public class Student_Data {

	private JFrame frame;
	private JTextField jtxtStudent_name;
	private JTextField jtxtMajor;
	private static JTextField jtxtComp;
	//private static JTextField jtxtBool;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student_Data window = new Student_Data();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection =null;
	private JTextField jtxt_studentID;
	private static JTextField jtxtAlgebra;

	public Student_Data() {
		connection = DbConnection.dbConnector();
		initialize();
	}
	
	

	public class my_update{

		void  my_db_update(String str1, String str2,String str3,String str4 ,double str5) {
			try{
				Class.forName("org.postgresql.Driver");
				// database is my_tutorial, userid =root, password //
				// Update your usrid, password and database name //
				Connection con= DriverManager.getConnection(
						"jdbc:postgresql://localhost:5432/StudentData","postgres","baraza");
				Statement st=con.createStatement();
				//int mark = Integer.parseInt(str3); // Mark is an integer
				// Adding record
				String query1="INSERT INTO student_info"
						+ " (student_name, reg_no, major, grade , average)"
						+ "VALUES('"+str1+"','"+str2+"','"+str3+"','"+str4+"','"+computeAverage()+"')";
				st.executeUpdate(query1); // record added.

				con.close();

			}catch(Exception e){ System.out.println(e);}

		}
	}
	
	   public static String getJsonData(){


	        JSONObject list = new JSONObject();

	        try{
	            list.put("Computer Graphics",jtxtComp.getText()); 
	            list.put("Boolean Algebra",jtxtAlgebra.getText()); 
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        return list.toString();
	    }
	   
	   public static double computeAverage() {
		   
		   String subject1 = jtxtComp.getText();
		   String subject2 = jtxtAlgebra.getText();
		    double result=0;
		    double str1 = Double.parseDouble(subject1);
		    double str2 = Double.parseDouble(subject2);
		    double sum = 0.0;
		    double avg = 0.0;
		    
		    sum = str1 + str2;
		     result = sum/2;
		    
		
			return  result;
	   }
	
	 
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1400, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		panel.setBorder(new MatteBorder(14, 14, 14, 14, (Color) Color.GRAY));
		panel.setBounds(0, -15, 1390, 700);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		panel_1.setLayout(null);
		panel_1.setBorder(new MatteBorder(14, 14, 14, 14, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(30, 40, 1308, 105);
		panel.add(panel_1);
		
		JLabel lblStudentsInfo = new JLabel("STUDENTS INFO");
		lblStudentsInfo.setFont(new Font("Dialog", Font.BOLD, 33));
		lblStudentsInfo.setBounds(437, 27, 442, 47);
		panel_1.add(lblStudentsInfo);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setBorder(new MatteBorder(14, 14, 14, 14, (Color) new Color(0, 0, 0)));
		panel_1_1_1.setBounds(430, 212, 908, 339);
		panel.add(panel_1_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			
//			public void tableMouseClicked(java.awt.event.MouseEvent evt) {
//				int index = table.getSelectedRow();
//				TableModel model = table.getModel();
//				jtxtStudent_name.setText(model.getValueAt(index,1).toString());
//				jtxt_studentID .setText(model.getValueAt(index,2).toString());
//				jtxtMajor.setText(model.getValueAt(index,3).toString());
//				jtxtComp.setText(model.getValueAt(index,4).toString());
//				
//			}
		});
		scrollPane.setBounds(12, 12, 854, 318);
		panel_1_1_1.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = table.getSelectedRow();
				TableModel model = table.getModel();
				jtxtStudent_name.setText(model.getValueAt(index,0).toString());
				jtxt_studentID .setText(model.getValueAt(index,1).toString());
				jtxtMajor.setText(model.getValueAt(index,2).toString());
				jtxtComp.setText(model.getValueAt(index,3).toString());
				
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STUDENT NAME", "STUDENT ID", "MAJOR", "GRADES", "AVERAGE"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(162);
		table.getColumnModel().getColumn(2).setPreferredWidth(158);
		table.getColumnModel().getColumn(3).setPreferredWidth(268);
		scrollPane.setViewportView(table);
		
		JPanel jtxtBool = new JPanel();
		jtxtBool.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		jtxtBool.setLayout(null);
		jtxtBool.setBorder(new MatteBorder(14, 14, 14, 14, (Color) new Color(0, 0, 0)));
		jtxtBool.setBounds(30, 174, 346, 377);
		panel.add(jtxtBool);
		
		JLabel lblNewLabel = new JLabel("STUDENT NAME");
		lblNewLabel.setBounds(23, 64, 117, 32);
		jtxtBool.add(lblNewLabel);
		
		JLabel lblMajor = new JLabel("STUDENT ID");
		lblMajor.setBounds(23, 113, 117, 32);
		jtxtBool.add(lblMajor);
		
		JLabel lblMajor_1 = new JLabel("MAJOR");
		lblMajor_1.setBounds(23, 165, 117, 32);
		jtxtBool.add(lblMajor_1);
		
		JLabel lblMajor_1_1 = new JLabel("MARKS");
		lblMajor_1_1.setBounds(151, 201, 117, 32);
		jtxtBool.add(lblMajor_1_1);
		
		jtxtStudent_name = new JTextField();
		jtxtStudent_name.setBounds(141, 69, 178, 24);
		jtxtBool.add(jtxtStudent_name);
		jtxtStudent_name.setColumns(10);
		
		jtxtMajor = new JTextField();
		jtxtMajor.setColumns(10);
		jtxtMajor.setBounds(141, 156, 178, 24);
		jtxtBool.add(jtxtMajor);
		
		jtxtComp = new JTextField();
		jtxtComp.setColumns(10);
		jtxtComp.setBounds(232, 245, 64, 24);
		jtxtBool.add(jtxtComp);
		
		jtxt_studentID = new JTextField();
		jtxt_studentID.setColumns(10);
		jtxt_studentID.setBounds(141, 120, 178, 24);
		jtxtBool.add(jtxt_studentID);
		
		JLabel lblMajor_1_1_1 = new JLabel("COMPUTER GRAPHICS");
		lblMajor_1_1_1.setBounds(34, 245, 178, 32);
		jtxtBool.add(lblMajor_1_1_1);
		
		jtxtAlgebra = new JTextField();
		jtxtAlgebra.setBounds(232, 287, 64, 19);
		jtxtBool.add(jtxtAlgebra);
		jtxtAlgebra.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("BOOLEAN ALGEBRA");
		lblNewLabel_1.setBounds(55, 283, 132, 15);
		jtxtBool.add(lblNewLabel_1);
		
		JPanel panel_1_1_2_1 = new JPanel();
		panel_1_1_2_1.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		panel_1_1_2_1.setLayout(null);
		panel_1_1_2_1.setBorder(new MatteBorder(14, 14, 14, 14, (Color) new Color(0, 0, 0)));
		panel_1_1_2_1.setBounds(30, 563, 1308, 105);
		panel.add(panel_1_1_2_1);
		
		JButton btnNewButton = new JButton("ADD STUDENT");
		btnNewButton.addActionListener(new ActionListener() {
		  public  void actionPerformed(ActionEvent arg0){
			  String student_name=jtxtStudent_name.getText();
			  String reg_no=jtxt_studentID.getText();
			  String major=jtxtMajor.getText();
			  String grade=getJsonData();
			  double average=computeAverage();
			  //String grade_json = getJsonData();
			  
			  // creating one object
			  my_update obj=new my_update();
			  obj.my_db_update(student_name, reg_no, major, grade, average );
				jtxtStudent_name.setText("");
				jtxt_studentID.setText(""); 
				jtxtMajor.setText("");
				jtxtComp.setText("");
				jtxtAlgebra.setText("");
			  
		  }
		});
		btnNewButton.setBounds(59, 29, 166, 52);
		panel_1_1_2_1.add(btnNewButton);
		
		JButton btnNewButton_1_2 = new JButton("EXIT");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame= new JFrame();
				if(JOptionPane.showConfirmDialog(frame, "Do You Want To Exit" )==JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				} 
			}
		});
		btnNewButton_1_2.setBounds(1053, 29, 166, 52);
		panel_1_1_2_1.add(btnNewButton_1_2);
		
		JButton btnNewButton_1_1_1 = new JButton("EDIT/UPDATE");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				connection = DbConnection.dbConnector();
				
				try {
					
					String value1 = jtxtStudent_name.getText();
                    String value2 = jtxt_studentID.getText();
					String value3 = jtxtMajor.getText();
					String value4 = jtxtComp.getText();
					String value5 = jtxtAlgebra.getText();
					
					
					String sql = "UPDATE student_info SET grade= '"+value4+"' where rej_no='"+value2+"'";
					PreparedStatement pst =connection.prepareStatement(sql);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Grade is Updated");
					
					
					
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
		});
		btnNewButton_1_1_1.setBounds(751, 29, 166, 52);
		panel_1_1_2_1.add(btnNewButton_1_1_1);
		
		JButton btnNewButton_1 = new JButton("RESET");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				jtxtStudent_name.setText("");
				jtxt_studentID.setText(""); 
				jtxtMajor.setText("");
				jtxtComp.setText("");
				jtxtAlgebra.setText("");
			}
		});
		btnNewButton_1.setBounds(350, 29, 117, 52);
		panel_1_1_2_1.add(btnNewButton_1);
		
		JButton jtxtLoad = new JButton("LOAD STUDENTS INFO");
		jtxtLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Class.forName("org.postgresql.Driver");
					String url= "jdbc:postgresql://localhost:5432/StudentData";
					Connection con =DriverManager.getConnection(url, "postgres" ,"baraza");
					Statement st = con.createStatement();
					String sql = "select * from student_info";
					ResultSet rs = st.executeQuery(sql);
					
					while(rs.next()) {
						
						String student_name = rs.getString("student_name");
						String student_id= rs.getString("reg_no");
						String major = rs.getString("major");
						String marks = rs.getString("grade");
						String average =rs.getString("average");
						
						
						
						String tbData[] = {student_name, student_id, major,marks, average};
						   DefaultTableModel tblModel =(DefaultTableModel)table.getModel();
						   
						   tblModel.addRow(tbData);
					}
					
					
					 
					con.close();
					
				    }catch(Exception e) {
				    	System.out.println(e.getMessage());
				    }
				
				
				
				
			}
		});
		jtxtLoad.setBounds(705, 157, 231, 52);
		panel.add(jtxtLoad);
		
		JButton btnClearList = new JButton("CLEAR  LIST");
		btnClearList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				 ((DefaultTableModel)table.getModel()).setRowCount(0);/////////////////////////////////////////////////////////////////////////////
			}
		});
		btnClearList.setBounds(947, 157, 231, 52);
		panel.add(btnClearList);
	}
}
