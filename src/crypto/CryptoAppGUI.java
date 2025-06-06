/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crypto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CryptoAppGUI extends JFrame {
    private JTabbedPane tabbedPane;
    
    // Komponen untuk MD5/SHA Hash
    private JTextArea md5InputText;
    private JTextArea md5OutputText;
    private JButton md5HashButton;
    private JButton md5FileButton;
    private JTextField md5FileField;
    
    // Komponen untuk DES
    private JTextArea desInputText;
    private JTextArea desOutputText;
    private JButton desEncryptButton;
    private JButton desDecryptButton;
    private JButton desKeyGenButton;
    private JTextField desKeyField;
    
    // Komponen untuk RSA
    private JTextArea rsaInputText;
    private JTextArea rsaOutputText;
    private JButton rsaEncryptButton;
    private JButton rsaDecryptButton;
    private JButton rsaKeyGenButton;
    private JTextField rsaPubKeyField;
    private JTextField rsaPrivKeyField;
    
    // Komponen untuk Login
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    
    public CryptoAppGUI() {
        setTitle("Aplikasi Kriptografi");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        tabbedPane = new JTabbedPane();
        
        // Tambahkan tab-tab
        tabbedPane.addTab("MD5/SHA Hash", createHashPanel());
        tabbedPane.addTab("DES", createDESPanel());
        tabbedPane.addTab("RSA", createRSAPanel());
        tabbedPane.addTab("Login", createLoginPanel());
        
        add(tabbedPane);
    }
    
    private JPanel createHashPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Panel input
        JPanel inputPanel = new JPanel(new BorderLayout());
        md5InputText = new JTextArea(10, 40);
        inputPanel.add(new JScrollPane(md5InputText), BorderLayout.CENTER);
        
        JPanel filePanel = new JPanel(new BorderLayout());
        md5FileField = new JTextField(30);
        md5FileField.setEditable(false);
        JButton browseButton = new JButton("Browse");
        browseButton.addActionListener(e -> browseFile(md5FileField));
        
        filePanel.add(md5FileField, BorderLayout.CENTER);
        filePanel.add(browseButton, BorderLayout.EAST);
        inputPanel.add(filePanel, BorderLayout.SOUTH);
        
        // Panel tombol
        JPanel buttonPanel = new JPanel(new FlowLayout());
        md5HashButton = new JButton("Hash Text");
        md5HashButton.addActionListener(e -> hashText());
        
        md5FileButton = new JButton("Hash File");
        md5FileButton.addActionListener(e -> hashFile());
        
        buttonPanel.add(md5HashButton);
        buttonPanel.add(md5FileButton);
        
        // Panel output
        md5OutputText = new JTextArea(10, 40);
        md5OutputText.setEditable(false);
        
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(new JScrollPane(md5OutputText), BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createDESPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Panel input
        desInputText = new JTextArea(10, 40);
        
        // Panel key
        JPanel keyPanel = new JPanel(new BorderLayout());
        desKeyField = new JTextField(30);
        desKeyField.setEditable(false);
        desKeyGenButton = new JButton("Generate Key");
        desKeyGenButton.addActionListener(e -> generateDESKey());
        
        keyPanel.add(desKeyField, BorderLayout.CENTER);
        keyPanel.add(desKeyGenButton, BorderLayout.EAST);
        
        // Panel tombol
        JPanel buttonPanel = new JPanel(new FlowLayout());
        desEncryptButton = new JButton("Encrypt");
        desEncryptButton.addActionListener(e -> encryptDES());
        
        desDecryptButton = new JButton("Decrypt");
        desDecryptButton.addActionListener(e -> decryptDES());
        
        buttonPanel.add(desEncryptButton);
        buttonPanel.add(desDecryptButton);
        
        // Panel output
        desOutputText = new JTextArea(10, 40);
        desOutputText.setEditable(false);
        
        panel.add(new JScrollPane(desInputText), BorderLayout.NORTH);
        panel.add(keyPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(new JScrollPane(desOutputText), BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createRSAPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Panel input
        rsaInputText = new JTextArea(10, 40);
        
        // Panel key
        JPanel keyPanel = new JPanel(new GridLayout(2, 2));
        rsaPubKeyField = new JTextField(30);
        rsaPubKeyField.setEditable(false);
        rsaPrivKeyField = new JTextField(30);
        rsaPrivKeyField.setEditable(false);
        rsaKeyGenButton = new JButton("Generate Key Pair");
        rsaKeyGenButton.addActionListener(e -> generateRSAKeys());
        
        keyPanel.add(new JLabel("Public Key:"));
        keyPanel.add(rsaPubKeyField);
        keyPanel.add(new JLabel("Private Key:"));
        keyPanel.add(rsaPrivKeyField);
        
        // Panel tombol
        JPanel buttonPanel = new JPanel(new FlowLayout());
        rsaEncryptButton = new JButton("Encrypt");
        rsaEncryptButton.addActionListener(e -> encryptRSA());
        
        rsaDecryptButton = new JButton("Decrypt");
        rsaDecryptButton.addActionListener(e -> decryptRSA());
        
        buttonPanel.add(rsaKeyGenButton);
        buttonPanel.add(rsaEncryptButton);
        buttonPanel.add(rsaDecryptButton);
        
        // Panel output
        rsaOutputText = new JTextArea(10, 40);
        rsaOutputText.setEditable(false);
        
        panel.add(new JScrollPane(rsaInputText), BorderLayout.NORTH);
        panel.add(keyPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(new JScrollPane(rsaOutputText), BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        loginButton.addActionListener(e -> authenticateUser());
        
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel(""));
        panel.add(loginButton);
        
        return panel;
    }
    
    private void browseFile(JTextField targetField) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            targetField.setText(selectedFile.getAbsolutePath());
        }
    }
    
    // Implementasi fungsi-fungsi kriptografi
    private void hashText() {
        String text = md5InputText.getText();
        String hashed = MD5Hash.hash(text);
        // Tambahkan salt custom
        hashed = customHashWithSalt(hashed, "MY_SECRET_SALT");
        md5OutputText.setText(hashed);
    }
    
    private void hashFile() {
        String filePath = md5FileField.getText();
        if (filePath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih file terlebih dahulu");
            return;
        }
        
        String hashed = MD5HashOfFile.hashFile(filePath);
        // Tambahkan salt custom
        hashed = customHashWithSalt(hashed, "MY_SECRET_SALT");
        md5OutputText.setText(hashed);
    }
    
    private void generateDESKey() {
        String path = System.getProperty("user.dir") + File.separator + "DES_KEY.key";
        DESEncryptDecriptUsingFileKey.generateKey(path);
        desKeyField.setText(path);
    }
    
    private void encryptDES() {
        String text = desInputText.getText();
        String keyPath = desKeyField.getText();
        
        if (keyPath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Generate key terlebih dahulu");
            return;
        }
        
        byte[] key = DESEncryptDecriptUsingFileKey.readKeyFile(keyPath);
        byte[] encrypted = DESEncryptDecriptUsingFileKey.Encript(text, key);
        desOutputText.setText(MyStringUtils.getHexString(encrypted));
    }
    
    private void decryptDES() {
        String text = desInputText.getText();
        String keyPath = desKeyField.getText();
        
        if (keyPath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Generate key terlebih dahulu");
            return;
        }
        
        byte[] key = DESEncryptDecriptUsingFileKey.readKeyFile(keyPath);
        byte[] decrypted = DESEncryptDecriptUsingFileKey.Decrypt(hexStringToByteArray(text), key);
        desOutputText.setText(new String(decrypted));
    }
    
    private void generateRSAKeys() {
        String path = System.getProperty("user.dir") + File.separator;
        String privateFile = path + "PRIVATE.cer";
        String publicFile = path + "PUBLIC.cer";
        
        AsymetricRSA.generateKey(privateFile, publicFile);
        rsaPubKeyField.setText(publicFile);
        rsaPrivKeyField.setText(privateFile);
    }
    
    private void encryptRSA() {
        String text = rsaInputText.getText();
        String keyPath = rsaPubKeyField.getText();
        
        if (keyPath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Generate key pair terlebih dahulu");
            return;
        }
        
        PublicKey pubKey = AsymetricRSA.getPublicKey(keyPath);
        byte[] encrypted = AsymetricRSA.encrypt(text, pubKey);
        rsaOutputText.setText(MyStringUtils.getHexString(encrypted));
    }
    
    private void decryptRSA() {
        String text = rsaInputText.getText();
        String keyPath = rsaPrivKeyField.getText();
        
        if (keyPath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Generate key pair terlebih dahulu");
            return;
        }
        
        PrivateKey privKey = AsymetricRSA.getPrivateKey(keyPath);
        String decrypted = AsymetricRSA.decrypt(hexStringToByteArray(text), privKey);
        rsaOutputText.setText(decrypted);
    }
    
    private void authenticateUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        // Hash password dengan MD5 + salt
        String hashedPassword = customHashWithSalt(MD5Hash.hash(password), "LOGIN_SALT");
        
        // Contoh autentikasi sederhana
        if (username.equals("admin") && hashedPassword.equals(customHashWithSalt(MD5Hash.hash("admin123"), "LOGIN_SALT"))) {
            JOptionPane.showMessageDialog(this, "Login berhasil!");
        } else {
            JOptionPane.showMessageDialog(this, "Username atau password salah", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Custom hash dengan salt
    private String customHashWithSalt(String hash, String salt) {
        // Implementasi custom hashing dengan salt
        return MD5Hash.hash(hash + salt);
    }
    
    // Utility untuk konversi hex string ke byte array
    private byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                             + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CryptoAppGUI app = new CryptoAppGUI();
            app.setVisible(true);
        });
    }
}