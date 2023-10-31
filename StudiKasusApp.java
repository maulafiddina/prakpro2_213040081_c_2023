package studi_kasus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StudiKasusApp {
    private JFrame frame;
    private JTextField nameField;
    private JComboBox<String> genderComboBox;
    private JTextField phoneNumberField;
    private JTextArea addressArea;
    private JTable table;
    private DefaultTableModel tableModel;

    public StudiKasusApp() {
        frame = new JFrame("Biodata App");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(frame, "Apakah Anda yakin ingin keluar?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        nameField = new JTextField(20);
        genderComboBox = new JComboBox<>(new String[]{"Pria", "Wanita"});
        phoneNumberField = new JTextField(20);
        addressArea = new JTextArea(5, 20);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nama");
        tableModel.addColumn("Jenis Kelamin");
        tableModel.addColumn("Nomor HP");
        tableModel.addColumn("Alamat");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton saveButton = new JButton("Simpan");
        JButton deleteButton = new JButton("Hapus");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveBiodata();
            }
        });


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBiodata();
            }
        });

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        inputPanel.add(new JLabel("Nama:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Jenis Kelamin:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(genderComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Nomor HP:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(phoneNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Alamat:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(new JScrollPane(addressArea), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        inputPanel.add(saveButton, gbc);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);

        frame.setLayout(new BorderLayout());
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(tablePanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    private void saveBiodata() {
        String name = nameField.getText();
        String gender = (String) genderComboBox.getSelectedItem();
        String phoneNumber = phoneNumberField.getText();
        String address = addressArea.getText();

        if (name.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Semua input harus diisi", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        tableModel.addRow(new String[]{name, gender, phoneNumber, address});
        nameField.setText("");
        genderComboBox.setSelectedIndex(0);
        phoneNumberField.setText("");
        addressArea.setText("");
    }


    private void deleteBiodata() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih baris untuk dihapus", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudiKasusApp();
            }
        });
    }
}
