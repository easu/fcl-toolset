package wit.fcl.filehash;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainFrame {

	private JFrame frmFilehash;
	private JTextField textField_path_1;
	private JTextField textField_size_2;
	private JTextField textField_md5_3;
	private JTextField textField_sha1_4;

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	public void show() {
		frmFilehash.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFilehash = new JFrame();
		frmFilehash.setTitle("FileHash");
		frmFilehash.getContentPane().setBackground(SystemColor.menu);
		frmFilehash.setBounds(100, 100, 437, 300);
		frmFilehash.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFilehash.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setForeground(Color.BLACK);
		panel.setBounds(10, 130, 401, 122);
		frmFilehash.getContentPane().add(panel);
		panel.setLayout(null);

		new DropTarget(panel, DnDConstants.ACTION_COPY_OR_MOVE,
				new DropTargetAdapter() {

					@Override
					public void drop(DropTargetDropEvent dtde) {
						if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
							dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);// 接收拖拽来的数据
							List<File> list = null;
							try {
								list = (List<File>) (dtde.getTransferable()
										.getTransferData(DataFlavor.javaFileListFlavor));
							} catch (Exception e) {
								e.printStackTrace();
							}
							String filepath = (list.get(0).getAbsolutePath());
							textField_path_1.setText(filepath);
							File file = new File(filepath);
							textField_size_2.setText("" + file.length());
							try {
								textField_md5_3.setText(FileHashUtil
										.getFileMD5(file));
								textField_sha1_4.setText(FileHashUtil
										.getFileSha1(file));
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null,
										e.getMessage());
								e.printStackTrace();
							}
							dtde.dropComplete(true);
						} else {
							dtde.rejectDrop();
						}

					}
				});

		JLabel lblMoveFileTo = new JLabel("Move file to this area");
		lblMoveFileTo.setBounds(116, 53, 141, 15);
		panel.add(lblMoveFileTo);

		JLabel lblFilepath = new JLabel("filePath");
		lblFilepath.setBounds(10, 10, 54, 15);
		frmFilehash.getContentPane().add(lblFilepath);

		JLabel lblSizekb = new JLabel("Size(B)");
		lblSizekb.setBounds(10, 35, 54, 15);
		frmFilehash.getContentPane().add(lblSizekb);

		JLabel lblMd = new JLabel("MD5");
		lblMd.setBounds(10, 60, 54, 15);
		frmFilehash.getContentPane().add(lblMd);

		JLabel lblSha = new JLabel("sha1");
		lblSha.setBounds(10, 85, 54, 15);
		frmFilehash.getContentPane().add(lblSha);

		textField_path_1 = new JTextField();
		textField_path_1.setBounds(61, 7, 272, 21);
		frmFilehash.getContentPane().add(textField_path_1);
		textField_path_1.setColumns(10);

		textField_size_2 = new JTextField();
		textField_size_2.setBounds(61, 32, 272, 21);
		frmFilehash.getContentPane().add(textField_size_2);
		textField_size_2.setColumns(10);

		textField_md5_3 = new JTextField();
		textField_md5_3.setBounds(61, 57, 272, 21);
		frmFilehash.getContentPane().add(textField_md5_3);
		textField_md5_3.setColumns(10);

		textField_sha1_4 = new JTextField();
		textField_sha1_4.setBounds(61, 82, 272, 21);
		frmFilehash.getContentPane().add(textField_sha1_4);
		textField_sha1_4.setColumns(10);

		JButton btn_1 = new JButton("Copy");
		btn_1.setBounds(343, 7, 68, 23);
		frmFilehash.getContentPane().add(btn_1);

		JButton btn_2 = new JButton("Copy");
		btn_2.setBounds(343, 32, 68, 23);
		frmFilehash.getContentPane().add(btn_2);

		JButton btn_3 = new JButton("Copy");
		btn_3.setBounds(343, 57, 68, 23);
		frmFilehash.getContentPane().add(btn_3);

		JButton btn_4 = new JButton("Copy");
		btn_4.setBounds(343, 82, 68, 23);
		frmFilehash.getContentPane().add(btn_4);

		btn_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				copy(textField_sha1_4.getText());
			}
		});
		btn_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				copy(textField_md5_3.getText());
			}
		});
		btn_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				copy(textField_size_2.getText());
			}
		});
		btn_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				copy(textField_path_1.getText());
			}
		});
	}

	public void copy(String text) {
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable tText = new StringSelection(text);
		clip.setContents(tText, null);
	}
}
