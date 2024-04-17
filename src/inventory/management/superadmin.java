package inventory.management;


import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PrinterException;
import java.beans.Statement;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author OJT-21
 */
public class superadmin extends javax.swing.JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rst = null;
public superadmin() {
        initComponents();
        conn = LogIn.connectDB();
        setExtendedState(superadmin.MAXIMIZED_BOTH); 

        //Display the total quantity from the database
        displayTotalDataCount();
        displayReleaseCount();
        displayCheckingCount();
        displayRepairCount();
        displayReturnCount();
        displayDisposalCount();
        displayAccountTypeCounts();
        alignUser(tblAccounts);
        alignDataInfo(tblDataInfo);
        alignStock(tblStock);
        alignDataInfo(tblDataInfo);
        alignRelease(tblRelease);
        alignChecking(tblChecking);
        alignReturn(tblReturn);
        alignRepair(tblRepair);
        alignDisposal(tblDisposal);
        btnSaveUser.setEnabled(false);
        btnCancel.setEnabled(false);
        btnSaveItem.setEnabled(false);
        btnCanceled.setEnabled(false); 
         // Attach key listener to txtEmployeeNum
    txtEmployeeNum.addKeyListener(createNumericDashKeyListener());
    // Attach key listener to txtQty
    txtQty.addKeyListener(createNumericKeyListener());
    txtFirstName.addKeyListener(createLetterKeyListener());
    txtLastName.addKeyListener(createLetterKeyListener());

    }
    private static final int[] dataAlignment = {SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER,
                                              SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER,
                                              SwingConstants.CENTER, SwingConstants.CENTER};
    private static final int[] dataWidths = {15, 40, 80, 80, 80, 50, 70, 15};
    
    private static final int[] stockAlignment = {SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER,
                                              SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER,
                                              SwingConstants.CENTER, SwingConstants.CENTER};
    private static final int[] minWidths = {15, 40, 120, 130, 220, 50, 60, 15};
    //
    private static final int[] alignment = {SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER,
                                              SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER,
                                              SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER};
    private static final int[] Widths = {95, 70, 80, 130, 100, 150, 90, 90, 15, 100};
    
    private static final int[] userAlignment = {SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER,
                                              SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER,
                                              SwingConstants.CENTER};
    private static final int[] userWidths = {10, 100, 100, 50, 50, 50, 100};
    private static final int[] releaseAlign = {SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER,
                                              SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER,
                                              SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER};
    private static final int[] releaseWidth = {60, 15, 80, 130, 100, 170, 60, 90, 15, 90};
    
    public static void alignUser(JTable tblAccounts) {
        DefaultTableCellRenderer renderer;
        JTableHeader header = tblAccounts.getTableHeader();
        TableCellRenderer headerRenderer = header.getDefaultRenderer();

        // Set alignment for header
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        
        // Set font and bold for header
        Font headerFont = header.getFont();
        header.setFont(headerFont.deriveFont(Font.BOLD, 15));

        for (int i = 0; i < tblAccounts.getColumnCount() && i < userAlignment.length; i++) {
            int align = userAlignment[i];
            int minWidth = userWidths[i];
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(align);
            tblAccounts.getColumnModel().getColumn(i).setCellRenderer(renderer);
            tblAccounts.getColumnModel().getColumn(i).setMinWidth(minWidth); // Set minimum width for cell

            // Set minimum width for header
            tblAccounts.getTableHeader().getColumnModel().getColumn(i).setMinWidth(minWidth);
        }
    }
    public static void alignDataInfo(JTable tblDataInfo) {
        DefaultTableCellRenderer renderer;
        JTableHeader header = tblDataInfo.getTableHeader();
        TableCellRenderer headerRenderer = header.getDefaultRenderer();

        // Set alignment for header
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        
        // Set font and bold for header
        Font headerFont = header.getFont();
        header.setFont(headerFont.deriveFont(Font.BOLD, 14));

        for (int i = 0; i < tblDataInfo.getColumnCount() && i < dataAlignment.length; i++) {
            int align = dataAlignment[i];
            int minWidth = dataWidths[i];
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(align);
            tblDataInfo.getColumnModel().getColumn(i).setCellRenderer(renderer);
            tblDataInfo.getColumnModel().getColumn(i).setMinWidth(minWidth); // Set minimum width for cell

            // Set minimum width for header
            tblDataInfo.getTableHeader().getColumnModel().getColumn(i).setMinWidth(minWidth);
        }
    }
    public static void alignStock(JTable tblStock) {
        DefaultTableCellRenderer renderer;
        JTableHeader header = tblStock.getTableHeader();
        TableCellRenderer headerRenderer = header.getDefaultRenderer();

        // Set alignment for header
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        
        // Set font and bold for header
        Font headerFont = header.getFont();
        header.setFont(headerFont.deriveFont(Font.BOLD, 15));

        for (int i = 0; i < tblStock.getColumnCount() && i < stockAlignment.length; i++) {
            int align = stockAlignment[i];
            int minWidth = minWidths[i];
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(align);
            tblStock.getColumnModel().getColumn(i).setCellRenderer(renderer);
            tblStock.getColumnModel().getColumn(i).setMinWidth(minWidth); // Set minimum width for cell

            // Set minimum width for header
            tblStock.getTableHeader().getColumnModel().getColumn(i).setMinWidth(minWidth);
        }
    }
    public static void alignRelease(JTable tblRelease) {
        DefaultTableCellRenderer renderer;
        JTableHeader header = tblRelease.getTableHeader();
        TableCellRenderer headerRenderer = header.getDefaultRenderer();

        // Set alignment for header
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        
        // Set font and bold for header
        Font headerFont = header.getFont();
        header.setFont(headerFont.deriveFont(Font.BOLD, 15)); // Set font size to 18

        for (int i = 0; i < tblRelease.getColumnCount() && i < releaseAlign.length; i++) {
            int align = releaseAlign[i];
            int width = releaseWidth[i];
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(align);
            tblRelease.getColumnModel().getColumn(i).setCellRenderer(renderer);
            tblRelease.getColumnModel().getColumn(i).setMinWidth(width); // Set minimum width for cell

            // Set minimum width for header
            tblRelease.getTableHeader().getColumnModel().getColumn(i).setMinWidth(width);
        }
    }
    public static void alignChecking(JTable tblChecking) {
        DefaultTableCellRenderer renderer;
        JTableHeader header = tblChecking.getTableHeader();
        TableCellRenderer headerRenderer = header.getDefaultRenderer();

        // Set alignment for header
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        
        // Set font and bold for header
        Font headerFont = header.getFont();
        header.setFont(headerFont.deriveFont(Font.BOLD, 15)); // Set font size to 18

        for (int i = 0; i < tblChecking.getColumnCount() && i < alignment.length; i++) {
            int align = alignment[i];
            int width = Widths[i];
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(align);
            tblChecking.getColumnModel().getColumn(i).setCellRenderer(renderer);
            tblChecking.getColumnModel().getColumn(i).setMinWidth(width); // Set minimum width for cell

            // Set minimum width for header
            tblChecking.getTableHeader().getColumnModel().getColumn(i).setMinWidth(width);
        }
    }
    public static void alignReturn(JTable tblReturn) {
        DefaultTableCellRenderer renderer;
        JTableHeader header = tblReturn.getTableHeader();
        TableCellRenderer headerRenderer = header.getDefaultRenderer();

        // Set alignment for header
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        
        // Set font and bold for header
        Font headerFont = header.getFont();
        header.setFont(headerFont.deriveFont(Font.BOLD, 15)); // Set font size to 18

        for (int i = 0; i < tblReturn.getColumnCount() && i < alignment.length; i++) {
            int align = alignment[i];
            int width = Widths[i];
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(align);
            tblReturn.getColumnModel().getColumn(i).setCellRenderer(renderer);
            tblReturn.getColumnModel().getColumn(i).setMinWidth(width); // Set minimum width for cell

            // Set minimum width for header
            tblReturn.getTableHeader().getColumnModel().getColumn(i).setMinWidth(width);
        }
    }
    public static void alignRepair(JTable tblRepair) {
        DefaultTableCellRenderer renderer;
        JTableHeader header = tblRepair.getTableHeader();
        TableCellRenderer headerRenderer = header.getDefaultRenderer();

        // Set alignment for header
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        
        // Set font and bold for header
        Font headerFont = header.getFont();
        header.setFont(headerFont.deriveFont(Font.BOLD, 15)); // Set font size to 18

        for (int i = 0; i < tblRepair.getColumnCount() && i < alignment.length; i++) {
            int align = alignment[i];
            int width = Widths[i];
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(align);
            tblRepair.getColumnModel().getColumn(i).setCellRenderer(renderer);
            tblRepair.getColumnModel().getColumn(i).setMinWidth(width); // Set minimum width for cell

            // Set minimum width for header
            tblRepair.getTableHeader().getColumnModel().getColumn(i).setMinWidth(width);
        }
    }
    public static void alignDisposal(JTable tblDisposal) {
        DefaultTableCellRenderer renderer;
        JTableHeader header = tblDisposal.getTableHeader();
        TableCellRenderer headerRenderer = header.getDefaultRenderer();

        // Set alignment for header
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        
        // Set font and bold for header
        Font headerFont = header.getFont();
        header.setFont(headerFont.deriveFont(Font.BOLD, 15)); // Set font size to 18

        for (int i = 0; i < tblDisposal.getColumnCount() && i < alignment.length; i++) {
            int align = alignment[i];
            int width = Widths[i];
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(align);
            tblDisposal.getColumnModel().getColumn(i).setCellRenderer(renderer);
            tblDisposal.getColumnModel().getColumn(i).setMinWidth(width); // Set minimum width for cell

            // Set minimum width for header
            tblDisposal.getTableHeader().getColumnModel().getColumn(i).setMinWidth(width);
        }
    }
   
    //End-------------------------------------------------------------------------------------------------------------End
//Keyboard listener
private KeyListener createNumericDashKeyListener() {
    return new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == '-' || c == KeyEvent.VK_MINUS)) {
                e.consume(); // Ignore the event
            }
        }
    };
}
private KeyListener createNumericKeyListener() {
    return new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE)) {
                e.consume(); // Ignore the event
            }
        }
    };
}
private KeyListener createLetterKeyListener() {
    return new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isLetter(c) && c != KeyEvent.VK_BACK_SPACE) {
                e.consume(); // Ignore the event
            }
        }
    };
}
//End------------------------------------------------------------------------End
//Display Count in Dashboard
public void displayTotalDataCount() {
    try {
        // Execute a SQL query to sum the total quantity in the "Qty" column of the "Stock" table
        String sumSql = "SELECT SUM(Qty) AS total FROM Stock";
        try (PreparedStatement sumPst = conn.prepareStatement(sumSql);
             ResultSet rs = sumPst.executeQuery()) {

            if (rs.next()) {
                int totalQuantity = rs.getInt("total");

                // Update the label with the total quantity
                lblTotalqty.setText(Integer.toString(totalQuantity));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving total quantity.");
    }
}
public void displayReleaseCount() {
    try {
        String sumSql = "SELECT SUM(Qty) AS total FROM Release";
        try (PreparedStatement sumPst = conn.prepareStatement(sumSql);
             ResultSet rs = sumPst.executeQuery()) {

            if (rs.next()) {
                int totalQuantity = rs.getInt("total");

                // Update the label with the total quantity
                lblRelease.setText(Integer.toString(totalQuantity));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving total quantity.");
    }
}
public void displayCheckingCount() {
    try {
        String sumSql = "SELECT SUM(Qty) AS total FROM Checking";
        try (PreparedStatement sumPst = conn.prepareStatement(sumSql);
             ResultSet rs = sumPst.executeQuery()) {

            if (rs.next()) {
                int totalQuantity = rs.getInt("total");

                // Update the label with the total quantity
                lblChecking.setText(Integer.toString(totalQuantity));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving total quantity.");
    }
}
public void displayRepairCount() {
    try {
        // Execute a SQL query to sum the total quantity in the "Qty" column of the "Stock" table
        String sumSql = "SELECT SUM(Qty) AS total FROM Repair";
        try (PreparedStatement sumPst = conn.prepareStatement(sumSql);
             ResultSet rs = sumPst.executeQuery()) {

            if (rs.next()) {
                int totalQuantity = rs.getInt("total");

                // Update the label with the total quantity
                lblRepair.setText(Integer.toString(totalQuantity));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving total quantity.");
    }
}
public void displayReturnCount() {
    try {
        // Execute a SQL query to sum the total quantity in the "Qty" column of the "Stock" table
        String sumSql = "SELECT SUM(Qty) AS total FROM Return";
        try (PreparedStatement sumPst = conn.prepareStatement(sumSql);
             ResultSet rs = sumPst.executeQuery()) {

            if (rs.next()) {
                int totalQuantity = rs.getInt("total");

                // Update the label with the total quantity
                lblReturn.setText(Integer.toString(totalQuantity));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving total quantity.");
    }
}
public void displayDisposalCount() {
    try {
        // Execute a SQL query to sum the total quantity in the "Qty" column of the "Stock" table
        String sumSql = "SELECT SUM(Qty) AS total FROM Disposal";
        try (PreparedStatement sumPst = conn.prepareStatement(sumSql);
             ResultSet rs = sumPst.executeQuery()) {

            if (rs.next()) {
                int totalQuantity = rs.getInt("total");

                // Update the label with the total quantity
                lblDisposal.setText(Integer.toString(totalQuantity));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving total quantity.");
    }
}
public void displayAccountTypeCounts() {
    try {
        // Execute a SQL query to count the number of each account type
        String countSql = "SELECT AccountType, COUNT(*) AS count FROM Accounts GROUP BY AccountType";
        try (PreparedStatement countPst = conn.prepareStatement(countSql);
             ResultSet rs = countPst.executeQuery()) {

            // Loop through the result set
            while (rs.next()) {
                String accountType = rs.getString("AccountType");
                int count = rs.getInt("count");

                // Update the appropriate label based on the account type
                switch (accountType) {
                    case "User":
                        lblUser.setText(Integer.toString(count));
                        break;
                    case "Admin":
                        lblAdmin.setText(Integer.toString(count));
                        break;
                    case "SuperAdmin":
                        lblSuperAd.setText(Integer.toString(count));
                        break;
                    default:
                        // Handle unexpected account types
                        break;
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving account type counts.");
    }
}
//End------------------------------------------------------------------------End
//Search Functionality
public void searchItems(String searchText) {
    DefaultTableModel model = (DefaultTableModel) tblStock.getModel();
    model.setRowCount(0); 

    try {
        String sql = "SELECT * FROM Stock WHERE ItemName LIKE ? OR SerialNo LIKE ? OR Model LIKE ? OR Category LIKE ? OR Brand LIKE ?";
        pst = conn.prepareStatement(sql);
        pst.setString(1, "%" + searchText + "%"); // Search itemName
        pst.setString(2, "%" + searchText + "%"); // Search Serial No
        pst.setString(3, "%" + searchText + "%"); // Search Category
        pst.setString(4, "%" + searchText + "%"); // Search Status
        pst.setString(5, "%" + searchText + "%"); // Search Brand
        rst = pst.executeQuery();

        while (rst.next()) {
            int ItemID = rst.getInt("ItemID");
            String serialNumber = rst.getString("SerialNo");
            String itemName = rst.getString("ItemName");
            String modelValue = rst.getString("Model");
            String specification = rst.getString("Specification");
            String category = rst.getString("Category");
            String brand = rst.getString("Brand");
            int quantity = rst.getInt("Qty");

            // Map database column names to JTable column names
            model.addRow(new Object[]{ItemID, serialNumber, itemName, modelValue, specification, category, brand, quantity});
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving items from the database.");
    } finally {
        try {
            if (rst != null) {
                rst.close();
            }
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
//ADD item and Delete item fucntionality
public void insertItem() {
    try {
        // Validate fields before proceeding
        if (!validateFields()) {
            return; // Stop execution if fields are not filled up
        }

        // Get the values from the text fields
        String serialNum = txtSerial.getText();
        String itemName = txtItemName.getText();
        String model = txtModel.getText();
        String specification = txtSpecification.getText();
        String category = cbSCategory.getSelectedItem().toString();
        String brand = txtBrand.getText();
        String qtyText = txtQty.getText();

        // Validate integer field (Quantity)
        int qty;
        try {
            qty = Integer.parseInt(qtyText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer value for Quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if the item already exists in the database
        String existingItemQuery = "SELECT Qty FROM Stock WHERE SerialNo = ? AND ItemName = ? AND Model = ? AND Specification = ? AND Category = ? AND Brand = ?";
        pst = conn.prepareStatement(existingItemQuery);
        pst.setString(1, serialNum);
        pst.setString(2, itemName);
        pst.setString(3, model);
        pst.setString(4, specification);
        pst.setString(5, category);
        pst.setString(6, brand);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            // Item already exists, update quantity
            int existingQty = rs.getInt("Qty");
            int newQty = existingQty + qty;

            // Update quantity for existing item
            String updateQuery = "UPDATE Stock SET Qty = ? WHERE SerialNo = ? AND ItemName = ? AND Model = ? AND Specification = ? AND Category = ? AND Brand = ?";
            pst = conn.prepareStatement(updateQuery);
            pst.setInt(1, newQty);
            pst.setString(2, serialNum);
            pst.setString(3, itemName);
            pst.setString(4, model);
            pst.setString(5, specification);
            pst.setString(6, category);
            pst.setString(7, brand);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Item quantity updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            clear();
            displayDataItems();
        } else {
            // Item does not exist, insert new item
            String message = "Are you sure you want to add this item?";
            int option = JOptionPane.showConfirmDialog(this, message, "Confirmation", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                String insertQuery = "INSERT INTO Stock (SerialNo, ItemName, Model, Specification, Category, Brand, Qty) VALUES (?, ?, ?, ?, ?, ?, ?)";
                pst = conn.prepareStatement(insertQuery);
                pst.setString(1, serialNum);
                pst.setString(2, itemName);
                pst.setString(3, model);
                pst.setString(4, specification);
                pst.setString(5, category);
                pst.setString(6, brand);
                pst.setInt(7, qty);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(this, "Item added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                clear();
                displayDataItems();
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error adding/updating item to the database.");
    } finally {
        try {
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

public void deleteDataInfo() {
    try {
        int selectedRow = tblDataInfo.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int itemID = (int) tblDataInfo.getValueAt(selectedRow, 0);

        // Check if the item is associated with records in other tables
        if (isItemInProcess(itemID)) {
            JOptionPane.showMessageDialog(this, "The item cannot be deleted because it is saved in other areas.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String message = "Are you sure you want to delete this item?";
        int option = JOptionPane.showConfirmDialog(this, message, "Confirmation", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM Stock WHERE ItemID = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, itemID);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Item deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            displayDataItems(); // Refresh the JTable after deletion
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error deleting item from the database.");
    } finally {
        try {
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

public void delete_tblStock() {
    try {
        int selectedRow = tblStock.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int itemID = (int) tblStock.getValueAt(selectedRow, 0);

        // Check if the item is associated with records in other tables
        if (isItemInProcess(itemID)) {
            JOptionPane.showMessageDialog(this, "The item cannot be deleted because it is saved in other areas.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String message = "Are you sure you want to delete this item?";
        int option = JOptionPane.showConfirmDialog(this, message, "Confirmation", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM Stock WHERE ItemID = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, itemID);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Item deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            displayDataItems();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error deleting item from the database.");
    } finally {
        try {
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

// Method to check if the item is associated with records in other tables
private boolean isItemInProcess(int itemID) throws SQLException {
    String[] tables = {"Release", "Checking", "Return", "Repair", "Disposal"};

    for (String table : tables) {
        String query = "SELECT COUNT(*) FROM " + table + " WHERE ItemID = ?";
        pst = conn.prepareStatement(query);
        pst.setInt(1, itemID);
        ResultSet rs = pst.executeQuery();
        if (rs.next() && rs.getInt(1) > 0) {
            return true; // Item is associated with records in the current table
        }
    }

    return false; // Item is not associated with records in any table
}
//End------------------------------------------------------------------------End
//Edit | Save Functionality
private void editItemTable() {
    int selectedRow = tblDataInfo.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select an item to edit.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Retrieve data from the selected row
    String serialNum = (String) tblDataInfo.getValueAt(selectedRow, 1);
    String itemName = (String) tblDataInfo.getValueAt(selectedRow, 2);
    String model = (String) tblDataInfo.getValueAt(selectedRow, 3);
    String specification = (String) tblDataInfo.getValueAt(selectedRow, 4);
    String category = (String) tblDataInfo.getValueAt(selectedRow, 5);
    String brand = (String) tblDataInfo.getValueAt(selectedRow, 6);
    int qty = (int) tblDataInfo.getValueAt(selectedRow, 7);

    // Prompt for confirmation before editing
    int option = JOptionPane.showConfirmDialog(this, "Do you want to edit this item?", "Confirmation", JOptionPane.YES_NO_OPTION);
    if (option != JOptionPane.YES_OPTION) {
        return; // User selected No, so return without editing the item
    }

    // Populate data into input fields
    txtSerial.setText(serialNum);
    txtItemName.setText(itemName);
    txtModel.setText(model);
    txtSpecification.setText(specification);
    cbSCategory.setSelectedItem(category);
    txtBrand.setText(brand);
    txtQty.setText(String.valueOf(qty));

    // Enable the "Save" button for confirming changes
    btnSaveItem.setEnabled(true);
    btnEdit.setEnabled(false);
    btnInsert.setEnabled(false);
    btnDelete.setEnabled(false);
    btnCanceled.setEnabled(true);
}
private void saveItem() {
    // Validate integer fields
   
    // Retrieve edited data from input fields
    String serialNum = txtSerial.getText();
    String itemName = txtItemName.getText();
    String model = txtModel.getText();
    String specification = txtSpecification.getText();
    String category = (String) cbSCategory.getSelectedItem();
    String brand = txtBrand.getText();
    int qty = Integer.parseInt(txtQty.getText());
    
    int selectedRow = tblDataInfo.getSelectedRow();
    
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select an item to edit.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Prompt for confirmation before editing
    int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to edit this item?", "Confirmation", JOptionPane.YES_NO_OPTION);
    if (option != JOptionPane.YES_OPTION) {
        return; // User selected No, so return without editing the item
    }

    // Update data in the selected row of tblDataInfo table
    tblDataInfo.setValueAt(serialNum, selectedRow, 1);
    tblDataInfo.setValueAt(itemName, selectedRow, 2);
    tblDataInfo.setValueAt(model, selectedRow, 3);
    tblDataInfo.setValueAt(specification, selectedRow, 4);
    tblDataInfo.setValueAt(category, selectedRow, 5);
    tblDataInfo.setValueAt(brand, selectedRow, 6);
    tblDataInfo.setValueAt(qty, selectedRow, 7);

    // Update the database
    try {
        String sql = "UPDATE Stock SET SerialNo=?, ItemName=?, Model=?, Specification=?, Category=?, Brand=?, Qty=? WHERE ItemID=?";
        pst = conn.prepareStatement(sql);
        pst.setString(1, serialNum);
        pst.setString(2, itemName);
        pst.setString(3, model);
        pst.setString(4, specification);
        pst.setString(5, category);
        pst.setString(6, brand);
        pst.setInt(7, qty);

        // Assuming your ItemID is stored in a hidden column in the table
        int itemID = (int) tblDataInfo.getValueAt(selectedRow, 0); // Get the ItemID from the table
        pst.setInt(8, itemID);

        int rowsUpdated = pst.executeUpdate();
        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(this, "Item updated successfully in the database.", "Success", JOptionPane.INFORMATION_MESSAGE);
            clear();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update item in the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error updating item in the database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Close the preparedStatement if it's open
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error closing prepared statement: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    btnSaveItem.setEnabled(false);
    btnInsert.setEnabled(true);
    btnDelete.setEnabled(true);
    btnCanceled.setEnabled(false);
    btnEdit.setEnabled(true);
}
//End------------------------------------------------------------------------End
//Method to get each column in the stock
private String getItemSerialNumber(int itemID) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");
        String sql = "SELECT SerialNo FROM Stock WHERE ItemID = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, itemID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("SerialNo");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving item serial number.");
    }
    return null;
}
private String getItemName(int itemID) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");
        String sql = "SELECT ItemName FROM Stock WHERE ItemID = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, itemID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("ItemName");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving item name.");
    }
    return null;
}
private String getItemModel(int itemID) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");
        String sql = "SELECT Model FROM Stock WHERE ItemID = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, itemID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Model");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving item name.");
    }
    return null;
}
private String getItemSpecification(int itemID) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");
        String sql = "SELECT Specification FROM Stock WHERE ItemID = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, itemID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Specification");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving item name.");
    }
    return null;
}
private String getCategory(int itemID) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");
        String sql = "SELECT Category FROM Stock WHERE ItemID = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, itemID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Category");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving item category.");
    }
    return null;
}
private String getBrand(int itemID) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");
        String sql = "SELECT Brand FROM Stock WHERE ItemID = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, itemID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Brand");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving item brand.");
    }
    return null;
}
//End------------------------------------------------------------------------End
//Transfer Functionalities(Stock|Release|Checking|Return|Repair|Disposal)
public void transferSelectedItem() {
    try {
        int selectedRow = tblStock.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to transfer.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int itemID = (int) tblStock.getValueAt(selectedRow, 0);
        int currentQty = (int) tblStock.getValueAt(selectedRow, 7);

        String quantityStr = JOptionPane.showInputDialog(this, "Enter quantity to transfer:", "For Release", JOptionPane.PLAIN_MESSAGE);

        if (quantityStr == null || quantityStr.trim().isEmpty()) {
            return; // User canceled or entered an empty quantity
        }

        int transferQty;
        try {
            transferQty = Integer.parseInt(quantityStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid quantity. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (transferQty <= 0 || transferQty > currentQty) {
            JOptionPane.showMessageDialog(this, "Invalid quantity to transfer.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Combo box for selecting the destination area
        JComboBox<String> areaComboBox = new JComboBox<>(new String[]{"Release", "Checking", "Return", "Repair", "Disposal"});
        JLabel areaLabel = new JLabel("Select the destination area:");
        Object[] areaDialog = {areaLabel, areaComboBox};

        int areaOption = JOptionPane.showConfirmDialog(this, areaDialog, "Select Destination Area", JOptionPane.OK_CANCEL_OPTION);

        if (areaOption == JOptionPane.CANCEL_OPTION) {
            return; // User canceled
        }

        int selectedAreaIndex = areaComboBox.getSelectedIndex();
        String selectedArea = getDestinationTableName(selectedAreaIndex);

        // Prompt for the location of the item only if it's for Release
        String location = null;
        if (selectedArea.equals("Release")) {
            location = JOptionPane.showInputDialog(this, "Enter the location of the item:", "Item Location", JOptionPane.PLAIN_MESSAGE);
            if (location == null || location.trim().isEmpty()) {
                return; // User canceled or entered an empty location
            }
        }

        // Initialize existingQty variable
        int existingQty = 0;

        // Input dialog for the transfer date
        SpinnerDateModel dateModel = new SpinnerDateModel();
        JSpinner dateSpinner = new JSpinner(dateModel);
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));
        JLabel dateLabel = new JLabel("Select the transfer date:");
        Object[] dateDialog = {dateLabel, dateSpinner};

        int dateOption = JOptionPane.showConfirmDialog(this, dateDialog, "Select Transfer Date", JOptionPane.OK_CANCEL_OPTION);

        if (dateOption == JOptionPane.CANCEL_OPTION) {
            return; // User canceled
        }

        java.sql.Date transferDate = new java.sql.Date(((java.util.Date) dateSpinner.getValue()).getTime());

        String message = "Are you sure you want to transfer " + transferQty + " items to " + selectedArea + "?";
        int option = JOptionPane.showConfirmDialog(this, message, "Confirmation", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            // Update quantity in the Stock table
            String updateSql = "UPDATE Stock SET Qty = ? WHERE ItemID = ?";
            try (PreparedStatement updatePst = conn.prepareStatement(updateSql)) {
                updatePst.setInt(1, currentQty - transferQty);
                updatePst.setInt(2, itemID);
                updatePst.executeUpdate();
            }

            // Insert or update record in the destination table
            String insertOrUpdateSql;
            if (selectedArea.equals("Release")) {
                // Check if the location already exists in the Release table
                String selectLocationSql = "SELECT Qty FROM Release WHERE Location = ?";
                try (PreparedStatement selectLocationPst = conn.prepareStatement(selectLocationSql)) {
                    selectLocationPst.setString(1, location);
                    try (ResultSet resultSet = selectLocationPst.executeQuery()) {
                        if (resultSet.next()) {
                            existingQty = resultSet.getInt("Qty");
                        }
                    }
                }

                // Insert a new record into the Release table or update existing quantity
                if (existingQty > 0) {
                    insertOrUpdateSql = "UPDATE Release SET Qty = ? WHERE Location = ?";
                } else {
                    insertOrUpdateSql = "INSERT INTO Release (ItemID, Location, Qty, Date) VALUES (?, ?, ?, ?)";
                }
            } else {
                // Check if the item already exists in the selected area
                String selectAreaSql = "SELECT Qty FROM " + selectedArea + " WHERE ItemID = ?";
                try (PreparedStatement selectAreaPst = conn.prepareStatement(selectAreaSql)) {
                    selectAreaPst.setInt(1, itemID);
                    try (ResultSet resultSet = selectAreaPst.executeQuery()) {
                        if (resultSet.next()) {
                            existingQty = resultSet.getInt("Qty");
                        }
                    }
                }

                // Insert a new record into the selected area table or update existing quantity
                if (existingQty > 0) {
                    insertOrUpdateSql = "UPDATE " + selectedArea + " SET Qty = ? WHERE ItemID = ?";
                } else {
                    insertOrUpdateSql = "INSERT INTO " + selectedArea + " (ItemID, Qty, Date) VALUES (?, ?, ?)";
                }
            }

            try (PreparedStatement insertOrUpdatePst = conn.prepareStatement(insertOrUpdateSql)) {
                if (selectedArea.equals("Release")) {
                    if (existingQty > 0) {
                        insertOrUpdatePst.setInt(1, existingQty + transferQty);
                        insertOrUpdatePst.setString(2, location);
                    } else {
                        insertOrUpdatePst.setInt(1, itemID);
                        insertOrUpdatePst.setString(2, location);
                        insertOrUpdatePst.setInt(3, transferQty);
                        insertOrUpdatePst.setDate(4, transferDate);
                    }
                } else {
                    if (existingQty > 0) {
                        insertOrUpdatePst.setInt(1, existingQty + transferQty);
                        insertOrUpdatePst.setInt(2, itemID);
                    } else {
                        insertOrUpdatePst.setInt(1, itemID);
                        insertOrUpdatePst.setInt(2, transferQty);
                        insertOrUpdatePst.setDate(3, transferDate);
                    }
                }
                insertOrUpdatePst.executeUpdate();
            }

            JOptionPane.showMessageDialog(this, "Transfer completed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            displayCombinedData(); // Refresh the JTable after transfer
            displayCheckingData(); // Display Checking data
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error during item transfer.");
    }
}
//Transfer from Checking area > Destination: Return|Repair|Disposal
public void transferCheckingItem(String sourceTable) {
    Connection conn = null;
    try {
        int selectedRow = tblChecking.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to transfer from the " + sourceTable + " table.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int itemID = -1;
        int checkedQty = -1;

        switch (sourceTable) {
            case "Checking":
                itemID = (int) tblChecking.getValueAt(selectedRow, 1);
                checkedQty = (int) tblChecking.getValueAt(selectedRow, 8);
                break;

        }

        // Combo box for selecting the destination area
        JComboBox<String> areaComboBox = new JComboBox<>(new String[]{"Return", "Repair", "Disposal"});
        JLabel areaLabel = new JLabel("Select the destination area:");
        Object[] areaDialog = {areaLabel, areaComboBox};

        int areaOption = JOptionPane.showConfirmDialog(this, areaDialog, "Select Destination Area", JOptionPane.OK_CANCEL_OPTION);

        if (areaOption == JOptionPane.CANCEL_OPTION) {
            return; // User canceled
        }

        String selectedArea = (String) areaComboBox.getSelectedItem();

        // Input dialog for the quantity to transfer
        String transferQtyString = JOptionPane.showInputDialog(this, "Enter the quantity to transfer:", "Transfer Quantity", JOptionPane.QUESTION_MESSAGE);
        try {
            int transferQty = Integer.parseInt(transferQtyString);

            // Check if the transfer quantity is a positive integer
            if (transferQty <= 0) {
                JOptionPane.showMessageDialog(this, "Invalid! Please enter a number", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if the transfer quantity exceeds the checked quantity
            if (transferQty > checkedQty) {
                JOptionPane.showMessageDialog(this, "Transfer quantity exceeds the quantity.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Adjust the checked quantity
            checkedQty -= transferQty;

            // Input dialog for the return date
            SpinnerDateModel dateModel = new SpinnerDateModel();
            JSpinner dateSpinner = new JSpinner(dateModel);
            dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));
            JLabel dateLabel = new JLabel("Select the return date:");
            Object[] dateDialog = {dateLabel, dateSpinner};

            int dateOption = JOptionPane.showConfirmDialog(this, dateDialog, "Select Return Date", JOptionPane.OK_CANCEL_OPTION);

            if (dateOption == JOptionPane.CANCEL_OPTION) {
                return; // User canceled
            }

            java.sql.Date returnDate = new java.sql.Date(((java.util.Date) dateSpinner.getValue()).getTime());

            String message = "Are you sure you want to transfer " + transferQty + " item(s) from the " + sourceTable + " table to the " + selectedArea + " table?";
            int option = JOptionPane.showConfirmDialog(this, message, "Confirmation", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                // Check if the item already exists in the destination area
                int existingQty = 0;
                String selectExistingSql = "SELECT Qty FROM " + selectedArea + " WHERE ItemID = ?";
                conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");
                try (PreparedStatement selectExistingPst = conn.prepareStatement(selectExistingSql)) {
                    selectExistingPst.setInt(1, itemID);
                    ResultSet existingRs = selectExistingPst.executeQuery();
                    if (existingRs.next()) {
                        existingQty = existingRs.getInt("Qty");
                    }
                }

                // Update quantity in the destination table
                int newQty = existingQty + transferQty;
                String updateSql = "UPDATE " + selectedArea + " SET Qty = ? WHERE ItemID = ?";
                try (PreparedStatement updatePst = conn.prepareStatement(updateSql)) {
                    updatePst.setInt(1, newQty);
                    updatePst.setInt(2, itemID);
                    updatePst.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.err.println("Error updating quantity in the destination table.");
                }

                // Insert a new record into the selected destination table if the item does not exist
                if (existingQty == 0) {
                    String insertSql = "";
                    switch (selectedArea) {
                        case "Return":
                            insertSql = "INSERT INTO Return (ItemID, Qty, Date) VALUES (?, ?, ?)";
                            break;
                        case "Repair":
                            insertSql = "INSERT INTO Repair (ItemID, Qty, Date) VALUES (?, ?, ?)";
                            break;
                        case "Disposal":
                            insertSql = "INSERT INTO Disposal (ItemID, Qty, Date) VALUES (?, ?, ?)";
                            break;
                        // Add cases for other destination tables if needed
                    }

                    try (PreparedStatement insertPst = conn.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                        insertPst.setInt(1, itemID);
                        insertPst.setInt(2, transferQty); // Insert transfer quantity
                        insertPst.setDate(3, returnDate);
                        insertPst.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.err.println("Error during item transfer.");
                    }
                }

                // Display information
                JOptionPane.showMessageDialog(this, "Transfer completed successfully.\n\n"
                        + "ItemID: " + itemID + "\n"
                        + "Transferred Quantity: " + transferQty, "Success", JOptionPane.INFORMATION_MESSAGE);

                // Update the checked quantity in the source table
                String updateCheckedQtySql = "UPDATE " + sourceTable + " SET Qty = ? WHERE ItemID = ?";
                try (PreparedStatement updateCheckedQtyPst = conn.prepareStatement(updateCheckedQtySql)) {
                    updateCheckedQtyPst.setInt(1, checkedQty);
                    updateCheckedQtyPst.setInt(2, itemID);
                    updateCheckedQtyPst.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.err.println("Error updating checked quantity in the source table.");
                }

                // Delete the record if the quantity reaches zero
                if (checkedQty == 0) {
                    String deleteSql = "DELETE FROM " + sourceTable + " WHERE ItemID = ?";
                    try (PreparedStatement deletePst = conn.prepareStatement(deleteSql)) {
                        deletePst.setInt(1, itemID);
                        deletePst.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.err.println("Error deleting item record from the source table.");
                    }
                }

                // Refresh the corresponding table after transfer
                switch (sourceTable) {
                    case "Checking":
                        displayCheckingData();
                        break;
                    case "Return":
                        displayReturn();
                        break;
                    case "Disposal":
                        displayDisposal();
                        break;    
                    // Add cases for other source tables if needed
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid transfer quantity. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error during item transfer.");
    } finally
    {if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
//Transfer from Release area > Destination: Checking
public void transferReleaseItem() {
    Connection conn = null;
    try {
        int selectedRow = tblRelease.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to transfer from the Release table.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int itemID = -1;
        String location = null;
        int checkedQty = -1;

        switch ("Release") {
            case "Release":
                itemID = (int) tblRelease.getValueAt(selectedRow, 1);
                location = (String) tblRelease.getValueAt(selectedRow, 2); // Assuming location is in column index 2
                checkedQty = (int) tblRelease.getValueAt(selectedRow, 9);
                break;
        }

        // Combo box for selecting the destination area
        String selectedArea = "Checking";

        // Input dialog for the quantity to transfer
        String transferQtyString = JOptionPane.showInputDialog(this, "Enter the quantity to transfer:", "Transfer Quantity", JOptionPane.QUESTION_MESSAGE);
        try {
            int transferQty = Integer.parseInt(transferQtyString);

            // Check if the transfer quantity is a positive integer
            if (transferQty <= 0) {
                JOptionPane.showMessageDialog(this, "Invalid! Please enter a number", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if the transfer quantity exceeds the checked quantity
            if (transferQty > checkedQty) {
                JOptionPane.showMessageDialog(this, "Transfer quantity exceeds the quantity.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Adjust the checked quantity
            checkedQty -= transferQty;

            // Input dialog for the return date
            SpinnerDateModel dateModel = new SpinnerDateModel();
            JSpinner dateSpinner = new JSpinner(dateModel);
            dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));
            JLabel dateLabel = new JLabel("Select the return date:");
            Object[] dateDialog = {dateLabel, dateSpinner};

            int dateOption = JOptionPane.showConfirmDialog(this, dateDialog, "Select Return Date", JOptionPane.OK_CANCEL_OPTION);

            if (dateOption == JOptionPane.CANCEL_OPTION) {
                return; // User canceled
            }

            java.sql.Date returnDate = new java.sql.Date(((java.util.Date) dateSpinner.getValue()).getTime());

            String message = "Are you sure you want to transfer " + transferQty + " item(s) from the Release table to the Checking table?";
            int option = JOptionPane.showConfirmDialog(this, message, "Confirmation", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                // Check if the item already exists in the destination area with the same location
                int existingQty = 0;
                String selectExistingSql = "SELECT Qty FROM " + selectedArea + " WHERE ItemID = ?";
                conn = DriverManager.getConnection("jdbc:sqlite:ITEMS.db");
                try (PreparedStatement selectExistingPst = conn.prepareStatement(selectExistingSql)) {
                    selectExistingPst.setInt(1, itemID);
                    ResultSet existingRs = selectExistingPst.executeQuery();
                    if (existingRs.next()) {
                        existingQty = existingRs.getInt("Qty");
                    }
                }

                // Update quantity in the destination table
                int newQty = existingQty + transferQty;
                String updateSql = "UPDATE " + selectedArea + " SET Qty = ? WHERE ItemID = ?";
                try (PreparedStatement updatePst = conn.prepareStatement(updateSql)) {
                    updatePst.setInt(1, newQty);
                    updatePst.setInt(2, itemID);
                    updatePst.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.err.println("Error updating quantity in the destination table.");
                }

                // Insert a new record into the selected destination table if the item does not exist
                if (existingQty == 0) {
                    String insertSql = "INSERT INTO " + selectedArea + " (ItemID, Qty, Date) VALUES (?, ?, ?)";
                    try (PreparedStatement insertPst = conn.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                        insertPst.setInt(1, itemID);
                        insertPst.setInt(2, transferQty); // Insert transfer quantity
                        insertPst.setDate(3, returnDate);
                        insertPst.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.err.println("Error during item transfer.");
                    }
                }

                // Display information
                JOptionPane.showMessageDialog(this, "Transfer completed successfully.\n\n"
                        + "ItemID: " + itemID + "\n"
                        + "Transferred Quantity: " + transferQty, "Success", JOptionPane.INFORMATION_MESSAGE);

                // Update the checked quantity in the Release table
                String updateCheckedQtySql = "UPDATE Release SET Qty = ? WHERE ItemID = ? AND Location = ?";
                try (PreparedStatement updateCheckedQtyPst = conn.prepareStatement(updateCheckedQtySql)) {
                    updateCheckedQtyPst.setInt(1, checkedQty);
                    updateCheckedQtyPst.setInt(2, itemID);
                    updateCheckedQtyPst.setString(3, location);
                    updateCheckedQtyPst.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.err.println("Error updating checked quantity in the Release table.");
                }

                // Delete the record if the quantity reaches zero
                if (checkedQty == 0) {
                    String deleteSql = "DELETE FROM Release WHERE ItemID = ? AND Location = ?";
                    try (PreparedStatement deletePst = conn.prepareStatement(deleteSql)) {
                        deletePst.setInt(1, itemID);
                        deletePst.setString(2, location);
                        deletePst.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.err.println("Error deleting item record from the Release table.");
                    }
                }

                // Refresh the Release table after transfer
                displayReleaseData();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid transfer quantity. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error during item transfer.");
    } finally {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
//Transfer from Return area > Destination: Stock|
public void transferReturnItem() {
    Connection conn = null; // Declaring the connection outside the try-catch block
    try {
        int selectedRow = tblReturn.getSelectedRow(); // Assuming you have a JTable for the "Return" table

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to transfer into the Stock.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int itemID = -1;
        int originalQty = -1;
        // Assuming different tables have different column indices for ItemID and Qty
        itemID = (int) tblReturn.getValueAt(selectedRow, 1); // Assuming ItemID is at index 0 in Return table
        originalQty = (int) tblReturn.getValueAt(selectedRow, 8); // Assuming Qty is at index 1 in Return table

        // Input dialog for the quantity to transfer
        String transferQtyString = JOptionPane.showInputDialog(this, "Enter the quantity to transfer:", "Transfer to Stock", JOptionPane.QUESTION_MESSAGE);
        try {
            int transferQty = Integer.parseInt(transferQtyString);

            // Check if the transfer quantity is a positive integer
            if (transferQty <= 0) {
                JOptionPane.showMessageDialog(this, "Invalid! Please enter a number you want to transfer.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if the transfer quantity exceeds the original quantity
            if (transferQty > originalQty) {
                JOptionPane.showMessageDialog(this, "Transfer quantity exceeds the original quantity.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Combo box for selecting the destination area
            String selectedArea = "Stock";

            // Get existing quantity in the Stock table for the item
            int existingQty = 0;
            conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");
            String selectSql = "SELECT Qty FROM Stock WHERE ItemID = ?";
            try (PreparedStatement selectPst = conn.prepareStatement(selectSql)) {
                selectPst.setInt(1, itemID);
                ResultSet rs = selectPst.executeQuery();

                if (rs.next()) {
                    existingQty = rs.getInt("Qty");
                }
            }

            // Update quantity in the Stock table
            int newQty = existingQty + transferQty;
            String updateSql = "UPDATE Stock SET Qty = ? WHERE ItemID = ?";
            try (PreparedStatement updatePst = conn.prepareStatement(updateSql)) {
                updatePst.setInt(1, newQty);
                updatePst.setInt(2, itemID);
                updatePst.executeUpdate();
            }

            // Deduct the transferred quantity from the original quantity in the Return table
            originalQty -= transferQty;
            String updateReturnSql = "UPDATE Return SET Qty = ? WHERE ItemID = ?";
            try (PreparedStatement updateReturnPst = conn.prepareStatement(updateReturnSql)) {
                updateReturnPst.setInt(1, originalQty);
                updateReturnPst.setInt(2, itemID);
                updateReturnPst.executeUpdate();
            }

            // Delete the record if the quantity reaches zero
            if (originalQty == 0) {
                String deleteSql = "DELETE FROM Return WHERE ItemID = ?";
                try (PreparedStatement deletePst = conn.prepareStatement(deleteSql)) {
                    deletePst.setInt(1, itemID);
                    deletePst.executeUpdate();
                }
            }

            // Display information
            JOptionPane.showMessageDialog(this, "Transfer completed successfully.\n\n"
                    + "ItemID: " + itemID + "\n"
                    + "Transferred Quantity: " + transferQty, "Success", JOptionPane.INFORMATION_MESSAGE);

            // Refresh the Return table after transfer
            displayReturn();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid transfer quantity. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error during item transfer.");
    } finally {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
//Transfer from Repair area > Destination: Return|Disposal
public void transferRepairItem() {
    Connection conn = null;
    try {
        int selectedRow = tblRepair.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to transfer from the Repair table.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int itemID = -1;
        int checkedQty = -1;

        switch ("Repair") {
            case "Repair":
                itemID = (int) tblRepair.getValueAt(selectedRow, 1);
                checkedQty = (int) tblRepair.getValueAt(selectedRow, 8);
                break;
        }

        // Combo box for selecting the destination area
        JComboBox<String> areaComboBox = new JComboBox<>(new String[]{"Return", "Disposal"});
        JLabel areaLabel = new JLabel("Select the destination area:");
        Object[] areaDialog = {areaLabel, areaComboBox};

        int areaOption = JOptionPane.showConfirmDialog(this, areaDialog, "Select Destination Area", JOptionPane.OK_CANCEL_OPTION);

        if (areaOption == JOptionPane.CANCEL_OPTION) {
            return; // User canceled
        }

        String selectedArea = (String) areaComboBox.getSelectedItem();

        // Input dialog for the quantity to transfer
        String transferQtyString = JOptionPane.showInputDialog(this, "Enter the quantity to transfer:", "Transfer Quantity", JOptionPane.QUESTION_MESSAGE);
        try {
            int transferQty = Integer.parseInt(transferQtyString);

            // Check if the transfer quantity is a positive integer
            if (transferQty <= 0) {
                JOptionPane.showMessageDialog(this, "Invalid! Please enter a number", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if the transfer quantity exceeds the checked quantity
            if (transferQty > checkedQty) {
                JOptionPane.showMessageDialog(this, "Transfer quantity exceeds the quantity.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Adjust the checked quantity
            checkedQty -= transferQty;

            // Input dialog for the return date
            SpinnerDateModel dateModel = new SpinnerDateModel();
            JSpinner dateSpinner = new JSpinner(dateModel);
            dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));
            JLabel dateLabel = new JLabel("Select the return date:");
            Object[] dateDialog = {dateLabel, dateSpinner};

            int dateOption = JOptionPane.showConfirmDialog(this, dateDialog, "Select Return Date", JOptionPane.OK_CANCEL_OPTION);

            if (dateOption == JOptionPane.CANCEL_OPTION) {
                return; // User canceled
            }

            java.sql.Date returnDate = new java.sql.Date(((java.util.Date) dateSpinner.getValue()).getTime());

            String message = "Are you sure you want to transfer " + transferQty + " item(s) from the Repair table to the " + selectedArea + " table?";
            int option = JOptionPane.showConfirmDialog(this, message, "Confirmation", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                // Check if the item already exists in the destination area
                int existingQty = 0;
                String selectExistingSql = "SELECT Qty FROM " + selectedArea + " WHERE ItemID = ?";
                conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");
                try (PreparedStatement selectExistingPst = conn.prepareStatement(selectExistingSql)) {
                    selectExistingPst.setInt(1, itemID);
                    ResultSet existingRs = selectExistingPst.executeQuery();
                    if (existingRs.next()) {
                        existingQty = existingRs.getInt("Qty");
                    }
                }

                // Update quantity in the destination table
                int newQty = existingQty + transferQty;
                String updateSql = "UPDATE " + selectedArea + " SET Qty = ? WHERE ItemID = ?";
                try (PreparedStatement updatePst = conn.prepareStatement(updateSql)) {
                    updatePst.setInt(1, newQty);
                    updatePst.setInt(2, itemID);
                    updatePst.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.err.println("Error updating quantity in the destination table.");
                }

                // Insert a new record into the selected destination table if the item does not exist
                if (existingQty == 0) {
                    String insertSql = "";
                    switch (selectedArea) {
                        case "Return":
                            insertSql = "INSERT INTO Return (ItemID, Qty, Date) VALUES (?, ?, ?)";
                            break;
                        case "Disposal":
                            insertSql = "INSERT INTO Disposal (ItemID, Qty, Date) VALUES (?, ?, ?)";
                            break;
                        // Add cases for other destination tables if needed
                    }

                    try (PreparedStatement insertPst = conn.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                        insertPst.setInt(1, itemID);
                        insertPst.setInt(2, transferQty); // Insert transfer quantity
                        insertPst.setDate(3, returnDate);
                        insertPst.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.err.println("Error during item transfer.");
                    }
                }

                // Display information
                JOptionPane.showMessageDialog(this, "Transfer completed successfully.\n\n"
                        + "ItemID: " + itemID + "\n"
                        + "Transferred Quantity: " + transferQty, "Success", JOptionPane.INFORMATION_MESSAGE);

                // Update the checked quantity in the Repair table
                String updateCheckedQtySql = "UPDATE Repair SET Qty = ? WHERE ItemID = ?";
                try (PreparedStatement updateCheckedQtyPst = conn.prepareStatement(updateCheckedQtySql)) {
                    updateCheckedQtyPst.setInt(1, checkedQty);
                    updateCheckedQtyPst.setInt(2, itemID);
                    updateCheckedQtyPst.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.err.println("Error updating checked quantity in the Repair table.");
                }

                // Delete the record if the quantity reaches zero
                if (checkedQty == 0) {
                    String deleteSql = "DELETE FROM Repair WHERE ItemID = ?";
                    try (PreparedStatement deletePst = conn.prepareStatement(deleteSql)) {
                        deletePst.setInt(1, itemID);
                        deletePst.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.err.println("Error deleting item record from the Repair table.");
                    }
                }

                // Refresh the Checking table after transfer
                displayRepair();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid transfer quantity. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error during item transfer.");
    } finally {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
//End------------------------------------------------------------------------End
//Display the information from the database
public void displayReturn() {
    DefaultTableModel returnModel = (DefaultTableModel) tblReturn.getModel();
    returnModel.setRowCount(0); // Clear existing rows

    try {
        String sql = "SELECT r.ReturnID, r.ItemID, s.SerialNo, s.ItemName, s.Model, s.Specification,  s.Category, s.Brand, r.Qty AS ReturnedQty, r.Date " +
                     "FROM Return r " +
                     "LEFT JOIN Stock s ON r.ItemID = s.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int returnID = rst.getInt("ReturnID");
                int itemID = rst.getInt("ItemID");
                String serialNo = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String model = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int returnedQty = rst.getInt("ReturnedQty");
                Date date = rst.getDate("Date");

                // Add a row to the Return table with all the information
                returnModel.addRow(new Object[]{returnID, itemID, serialNo, itemName, model, specification, category, brand, returnedQty, date});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving Return data from the database.");
    }
}
public void displayRepair() {
    DefaultTableModel repairModel = (DefaultTableModel) tblRepair.getModel();
    repairModel.setRowCount(0); // Clear existing rows

    try {
        // Modify the SQL query for Repair area
        String sql = "SELECT rep.RepairID, rep.ItemID, s.SerialNo, s.Model, s.Specification, s.Category, s.Brand, rep.Qty AS RepairedQty, rep.Date " +
                     "FROM Repair rep " +
                     "LEFT JOIN Stock s ON rep.ItemID = s.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int repairID = rst.getInt("RepairID");
                int itemID = rst.getInt("ItemID");
                String serialNo = getItemSerialNumber(itemID);
                String itemName = getItemName(itemID); // Utilize getItemName method
                String model = getItemModel(itemID); // Utilize getItemModel method
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int repairedQty = rst.getInt("RepairedQty");
                Date date = rst.getDate("Date");

                // Add a row to the Repair table with all the information
                repairModel.addRow(new Object[]{repairID, itemID, serialNo, itemName, model, specification, category, brand, repairedQty, date});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving Repair data from the database.");
    }
}
public void displayDisposal() {
    DefaultTableModel disposalModel = (DefaultTableModel) tblDisposal.getModel();
    disposalModel.setRowCount(0); // Clear existing rows

    try {
        // Modify the SQL query for Disposal area
        String sql = "SELECT d.DisposalID, d.ItemID, s.SerialNo, s.ItemName, s.Model, s.Specification, s.Category, s.Brand, d.Qty AS DisposedQty, d.Date " +
                     "FROM Disposal d " +
                     "LEFT JOIN Stock s ON d.ItemID = s.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int disposalID = rst.getInt("DisposalID");
                int itemID = rst.getInt("ItemID");
                String serialNo = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String model = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int disposedQty = rst.getInt("DisposedQty");
                Date date = rst.getDate("Date");

                // Add a row to the Disposal table with all the information
                disposalModel.addRow(new Object[]{disposalID, itemID, serialNo, itemName, model, specification, category, brand, disposedQty, date});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving Disposal data from the database.");
    }
}
public void displayCheckingData() {
    DefaultTableModel checkingModel = (DefaultTableModel) tblChecking.getModel();
    checkingModel.setRowCount(0); // Clear existing rows

    try {
        // Modify the SQL query for Disposal area
        String sql = "SELECT c.CheckingID, c.ItemID, s.SerialNo, s.ItemName, s.Model, s.Specification, s.Category, s.Brand, c.Qty AS CheckedQty, c.Date " +
                     "FROM Checking c " +
                     "LEFT JOIN Stock s ON c.ItemID = s.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int disposalID = rst.getInt("CheckingID");
                int itemID = rst.getInt("ItemID");
                String serialNo = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String model = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int checkedQty = rst.getInt("CheckedQty");
                Date date = rst.getDate("Date");

                // Add a row to the Disposal table with all the information
                checkingModel.addRow(new Object[]{disposalID, itemID, serialNo, itemName, model, specification, category, brand, checkedQty, date});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving Disposal data from the database.");
    }
}
public void displayReturnData() {
    DefaultTableModel model = (DefaultTableModel) tblReturn.getModel();
    model.setRowCount(0); // Clear existing rows

    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");

        String sql = "SELECT r.ReturnID, r.ItemID, s.SerialNo, s.ItemName, s.Model, s.Specification, s.Category, s.Brand, c.Qty AS CheckedQty, r.Qty AS ReturnedQty, r.Date " +
                     "FROM Return r " +
                     "INNER JOIN Checking c ON r.ItemID = c.ItemID " +
                     "INNER JOIN Stock s ON r.ItemID = s.ItemID";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int returnID = rst.getInt("ReturnID");
                int itemID = rst.getInt("ItemID");
                String serialNumber = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String modelValue = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int checkedQty = rst.getInt("CheckedQty");
                int returnedQty = rst.getInt("ReturnedQty");
                Date returnDate = rst.getDate("Date");

                // Add a row to the table with all the information
                model.addRow(new Object[]{returnID, itemID, serialNumber, itemName, modelValue, specification,  category, brand, checkedQty, returnedQty, returnDate});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving return data from the database.");
    }
}
public void displayReleaseData() {
    DefaultTableModel releaseModel = (DefaultTableModel) tblRelease.getModel();
    releaseModel.setRowCount(0); // Clear existing rows
    
    try {
        String sql = "SELECT r.ReleaseID, r.ItemID, r.Location, s.SerialNo, s.ItemName, s.Model, s.Specification, s.Category, s.Brand, r.Qty AS ReleasedQty, r.Date " +
                     "FROM Release r " +
                     "LEFT JOIN Stock s ON r.ItemID = s.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int releaseID = rst.getInt("ReleaseID");
                int itemID = rst.getInt("ItemID");
                String location = rst.getString("Location");
                String serialNo = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String model = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int releasedQty = rst.getInt("ReleasedQty");
                Date date = rst.getDate("Date");

                // Add a row to the Release table with all the information
                releaseModel.addRow(new Object[]{releaseID, itemID, location, serialNo, itemName, model, specification, category, brand, releasedQty, date});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving Release data from the database.");
    }
}
public void displayCombinedData() {
    DefaultTableModel model = (DefaultTableModel) tblChecking.getModel();
    model.setRowCount(0); // Clear existing rows

    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");

        String sql = "SELECT s.ItemID, s.SerialNo, s.ItemName, s.Model, s.Category, s.Specification, s.Brand, s.Qty, " +
                     "c.Date AS CheckingDate " +
                     "FROM Stock s " +
                     "LEFT JOIN Checking c ON s.ItemID = c.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {    
                int itemID = rst.getInt("ItemID");
                String serialNumber = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String modelValue = rst.getString("Model");
                String category = rst.getString("Category");
                String specification = rst.getString("Specification");
                String brand = rst.getString("Brand");
                int quantity = rst.getInt("Qty");

                // Retrieve dates from different tables
                Date checkingDate = rst.getDate("CheckingDate");

                // Add a row to the table with all the information
                model.addRow(new Object[]{itemID, serialNumber, itemName, modelValue, category, specification, brand, quantity, checkingDate});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving combined data from the database.");
    }
}
public void displayChecking() {
    DefaultTableModel model = (DefaultTableModel) tblChecking.getModel();
    model.setRowCount(0); // Clear existing rows

    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");

        String sql = "SELECT s.ItemID, s.SerialNo, s.ItemName, s.Model, s.Category, s.Specification, s.Brand, s.Qty, " +
                     "c.Date AS CheckingDate " +
                     "FROM Stock s " +
                     "LEFT JOIN Checking c ON s.ItemID = c.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int itemID = rst.getInt("ItemID");
                String serialNumber = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String modelValue = rst.getString("Model");
                String category = rst.getString("Category");
                String specification = rst.getString("Specification");
                String brand = rst.getString("Brand");
                int quantity = rst.getInt("Qty");

                // Retrieve dates from different tables
                Date Date = rst.getDate("CheckingDate");

                // Add a row to the table with all the information
                model.addRow(new Object[]{itemID, serialNumber, itemName, modelValue, category, specification, brand, quantity, Date});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving combined data from the database.");
    }
}
//End------------------------------------------------------------------------End
//Handler for attributes
private void transferItems(int itemID, int currentQty, int transferQty, String sourceTable, String sourceArea, String destinationTable, String destinationArea, java.sql.Date transferDate) {
    try {
        // Update quantity in the source table
        String updateSourceSql = "UPDATE " + sourceTable + " SET Qty = ? WHERE ItemID = ? AND Area = ?";
        try (PreparedStatement updatePst = conn.prepareStatement(updateSourceSql)) {
            updatePst.setInt(1, currentQty - transferQty);
            updatePst.setInt(2, itemID);
            updatePst.setString(3, sourceArea);
            updatePst.executeUpdate();
        }

        // Insert a new record into the destination table
        String insertDestinationSql = "INSERT INTO " + destinationTable + " (ItemID, Qty, Area, Date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement insertPst = conn.prepareStatement(insertDestinationSql)) {
            insertPst.setInt(1, itemID);
            insertPst.setInt(2, transferQty);
            insertPst.setString(3, destinationArea);
            insertPst.setDate(4, transferDate);
            insertPst.executeUpdate();
        }

        JOptionPane.showMessageDialog(this, "Transfer completed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        // Refresh the JTables after transfer based on your specific requirements
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error during item transfer.");
    }
}
// Helper method to get the destination table name based on the user's selection
private String getDestinationTableName(int destinationTableIndex) {
    switch (destinationTableIndex) {
        case 0:
            return "Release";
        case 1:
            return "Checking";
        case 2:
            return "Return";
        case 3:
            return "Repair";
        case 4:
            return "Disposal";
        default:
            throw new IllegalArgumentException("Invalid destination table index");
    }
}
//Validation method
private boolean validateFields() {
        if (txtSerial.getText().isEmpty() || txtItemName.getText().isEmpty() ||
            txtModel.getText().isEmpty() || txtSpecification.getText().isEmpty() ||
            txtBrand.getText().isEmpty() || txtQty.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Please fill in all the required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return false; // Validation failed
        }

        return true; // Validation passed
    }
//Clear funtionality
public void clear(){
        txtSerial.setText("");
        txtItemName.setText("");
        txtModel.setText("");
        txtSpecification.setText("");
        cbSCategory.setSelectedIndex(0);
        txtBrand.setText("");
        txtQty.setText("");
        txtSearchItem.setText("");
    } 
//Display data from database to ADD form table
public void displayDataItems() {
    DefaultTableModel model = (DefaultTableModel) tblDataInfo.getModel();
    model.setRowCount(0); // Clear existing rows

    try {
        String sql = "SELECT * FROM Stock";
        pst = conn.prepareStatement(sql);
        rst = pst.executeQuery();

        while (rst.next()) {
            int ItemID = rst.getInt("ItemID");
            String serialNumber = rst.getString("SerialNo");
            String itemName = rst.getString("ItemName");
            String modelValue = rst.getString("Model");
            String specification = rst.getString("Specification");
            String category = rst.getString("Category");
            String brand = rst.getString("Brand");
            int quantity = rst.getInt("Qty");

            // Map database column names to JTable column names
            model.addRow(new Object[]{ItemID, serialNumber, itemName, modelValue, specification, category, brand, quantity});
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving items from the database.");
    } finally {
        try {
            if (rst != null) {
                rst.close();
            }
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
//Display Stock Records
public void displayStockItems() {
    DefaultTableModel model = (DefaultTableModel) tblStock.getModel();
    model.setRowCount(0); // Clear existing rows

    try {
        String sql = "SELECT * FROM Stock";
        pst = conn.prepareStatement(sql);
        rst = pst.executeQuery();

        while (rst.next()) {
            int ItemID = rst.getInt("ItemID");
            String serialNumber = rst.getString("SerialNo");
            String itemName = rst.getString("ItemName");
            String modelValue = rst.getString("Model");
            String specification = rst.getString("Specification");
            String category = rst.getString("Category");
            String brand = rst.getString("Brand");
            int quantity = rst.getInt("Qty");

            // Map database column names to JTable column names
            model.addRow(new Object[]{ItemID, serialNumber, itemName, modelValue, specification, category, brand, quantity});
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving items from the database.");
    } finally {
        try {
            if (rst != null) {
                rst.close();
            }
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
//Add user and delete functionality
/*private void addUser() {
    String employeeNum = txtEmployeeNum.getText(); // Treat employee number as a string
    
    String firstName = txtFirstName.getText();
    String lastName = txtLastName.getText();
    String username = txtUserName.getText();
    String password = new String(txtPass.getPassword());
    String accountType = (String) cbAccountType.getSelectedItem();

    // Validation for empty fields
    if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(this, "All fields must be filled.", "Warning", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Confirmation dialog
    int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to add this user?", "Confirmation", JOptionPane.YES_NO_OPTION);
    if (option != JOptionPane.YES_OPTION) {
        return; // User selected No, so return without adding the user
    }

    // Hash the password
    String hashedPassword = hashPassword(password);

    // Database interaction (integrate this with your existing connection code)
    try {
        String query = "INSERT INTO Accounts (EmployeeNo, FirstName, LastName, Username, Password, AccountType) VALUES (?, ?, ?, ?, ?, ?)";
        pst = conn.prepareStatement(query);
        pst.setString(1, employeeNum);
        pst.setString(2, firstName);
        pst.setString(3, lastName);
        pst.setString(4, username);
        pst.setString(5, hashedPassword); // Store hashed password
        pst.setString(6, accountType);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(this, "User added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        displayAccountsData();
        // Reset fields after successful addition
        txtEmployeeNum.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtUserName.setText("");
        txtPass.setText("");
        cbAccountType.setSelectedIndex(0);
        chkPass.setSelected(false);

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error adding user.", "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Close resources in a finally block
        try {
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}*/
private void addUser() {
    String employeeNum = txtEmployeeNum.getText(); // Treat employee number as a string
    
    String firstName = txtFirstName.getText();
    String lastName = txtLastName.getText();
    String username = txtUserName.getText();
    String password = new String(txtPass.getPassword());
    String accountType = (String) cbAccountType.getSelectedItem();

    // Validation for empty fields
    if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(this, "All fields must be filled.", "Warning", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Confirmation dialog
    int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to add this user?", "Confirmation", JOptionPane.YES_NO_OPTION);
    if (option != JOptionPane.YES_OPTION) {
        return; // User selected No, so return without adding the user
    }

    // Hash the password
    String hashedPassword = hashPassword(password);

    // Check if user with the same Employee number or Username already exists
    try {
        String queryCheck = "SELECT * FROM Accounts WHERE EmployeeNo = ? OR Username = ?";
        pst = conn.prepareStatement(queryCheck);
        pst.setString(1, employeeNum);
        pst.setString(2, username);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            JOptionPane.showMessageDialog(this, "User credentials already exist!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error checking existing user.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Database interaction (integrate this with your existing connection code)
    try {
        String query = "INSERT INTO Accounts (EmployeeNo, FirstName, LastName, Username, Password, AccountType) VALUES (?, ?, ?, ?, ?, ?)";
        pst = conn.prepareStatement(query);
        pst.setString(1, employeeNum);
        pst.setString(2, firstName);
        pst.setString(3, lastName);
        pst.setString(4, username);
        pst.setString(5, hashedPassword); // Store hashed password
        pst.setString(6, accountType);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(this, "User added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        displayAccountsData();
        // Reset fields after successful addition
        txtEmployeeNum.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtUserName.setText("");
        txtPass.setText("");
        cbAccountType.setSelectedIndex(0);
        chkPass.setSelected(false);

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error adding user.", "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Close resources in a finally block
        try {
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
private void deleteUser() {
    int selectedRow = tblAccounts.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a user to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int userId = (int) tblAccounts.getValueAt(selectedRow, 0); // ID from the table

    // Confirmation dialog
    int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user?", "Confirmation", JOptionPane.YES_NO_OPTION);
    if (option != JOptionPane.YES_OPTION) {
        return; // User selected No, so return without deleting
    }

    // Database interaction to delete user data
    try {
        String query = "DELETE FROM Accounts WHERE ID=?";
        pst = conn.prepareStatement(query);
        pst.setInt(1, userId);
        int rowsAffected = pst.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this, "User deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Remove the selected row from the JTable
            DefaultTableModel model = (DefaultTableModel) tblAccounts.getModel();
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete user.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error deleting user.", "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Close resources in a finally block
        try {
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
//End------------------------------------------------------------------------End
//Hashing functionality
private String hashPassword(String password) {
    try {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(password.getBytes());

        // Convert bytes to hexadecimal representation
        StringBuilder hexString = new StringBuilder();
        for (byte hashByte : hashBytes) {
            String hex = Integer.toHexString(0xff & hashByte);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
        return null;
    }
}
//Edit and save the user functionality
private void editUser() {
    // Confirmation dialog before displaying data
    int confirmOption = JOptionPane.showConfirmDialog(this, "Are you sure you want to edit this user?", "Confirmation", JOptionPane.YES_NO_OPTION);
    if (confirmOption != JOptionPane.YES_OPTION) {
        return; // User selected No, so return without editing
    }

    // Get the selected row index
    int selectedRowIndex = tblAccounts.getSelectedRow();

    // Check if a row is selected
    if (selectedRowIndex == -1) {
        JOptionPane.showMessageDialog(this, "Please select a user to edit.", "Warning", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Fetch data from the selected row in the table
    String employeeNo = tblAccounts.getValueAt(selectedRowIndex, 1).toString(); // Assuming EmployeeNo is in the second column
    String firstName = tblAccounts.getValueAt(selectedRowIndex, 2).toString(); // Assuming FirstName is in the third column
    String lastName = tblAccounts.getValueAt(selectedRowIndex, 3).toString(); // Assuming LastName is in the fourth column
    String username = tblAccounts.getValueAt(selectedRowIndex, 4).toString(); // Assuming Username is in the fifth column
    String accountType = tblAccounts.getValueAt(selectedRowIndex, 6).toString(); // Assuming AccountType is in the seventh column
    String storedHashedPassword = tblAccounts.getValueAt(selectedRowIndex, 5).toString(); // Assuming Password is in the sixth column

    // Populate fields with fetched data
    txtEmployeeNum.setText(employeeNo);
    txtFirstName.setText(firstName);
    txtLastName.setText(lastName);
    txtUserName.setText(username);
    cbAccountType.setSelectedItem(accountType);
    txtPass.setText(storedHashedPassword); // Display hashed password

    // Reset button states
    btnUserEdit.setEnabled(false);
    btnUserDelete.setEnabled(false);
    btnAddU.setEnabled(false);
    btnSaveUser.setEnabled(true);
    btnCancel.setEnabled(true);  
}
private void saveUserChanges() {
    // Retrieve edited data from input fields
    String employeeNum = txtEmployeeNum.getText();
    String firstName = txtFirstName.getText();
    String lastName = txtLastName.getText();
    String username = txtUserName.getText();
    String newPassword = new String(txtPass.getPassword()); // New password input
    String accountType = (String) cbAccountType.getSelectedItem();

    // Validation for empty fields
    if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty()) {
        JOptionPane.showMessageDialog(this, "All fields must be filled.", "Warning", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Confirmation dialog
    int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to save changes?", "Confirmation", JOptionPane.YES_NO_OPTION);
    if (option != JOptionPane.YES_OPTION) {
        return; // User selected No, so return without saving changes
    }

    // Retrieve existing hashed password from the database
    String storedHashedPassword = null;
    try {
        String query = "SELECT PassWord FROM Accounts WHERE ID=?";
        pst = conn.prepareStatement(query);
        pst.setInt(1, (int) tblAccounts.getValueAt(tblAccounts.getSelectedRow(), 0)); // Get the ID from the table
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            storedHashedPassword = rs.getString("PassWord");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error retrieving password from the database.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    } finally {
        // Close resources in a finally block
        try {
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Determine if the password needs to be updated
    String hashedPassword;
    if (newPassword.isEmpty() || newPassword.equals(storedHashedPassword)) {
        hashedPassword = storedHashedPassword; // Use the existing hashed password
    } else {
        hashedPassword = hashPassword(newPassword); // Hash the new password
    }

    // Update the database with the new data
    try {
        String query = "UPDATE Accounts SET EmployeeNo=?, FirstName=?, LastName=?, UserName=?, PassWord=?, AccountType=? WHERE ID=?";
        pst = conn.prepareStatement(query);
        pst.setString(1, employeeNum); // Updated employee number
        pst.setString(2, firstName);
        pst.setString(3, lastName);
        pst.setString(4, username);
        pst.setString(5, hashedPassword); // Use the new or existing hashed password
        pst.setString(6, accountType);
        
        // Assuming your ID is stored in a hidden column in the table
        int userID = (int) tblAccounts.getValueAt(tblAccounts.getSelectedRow(), 0); // Get the ID from the table
        pst.setInt(7, userID);

        pst.executeUpdate();

        JOptionPane.showMessageDialog(this, "Changes saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        displayAccountsData(); // Refresh table after saving changes
        clearUserFields();

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error saving changes.", "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Close resources in a finally block
        try {
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Reset button states
    btnUserEdit.setEnabled(true);
    btnUserDelete.setEnabled(true);
    btnAddU.setEnabled(true);
    btnCanceled.setEnabled(true);
    btnSaveUser.setEnabled(false);
    btnCancel.setEnabled(false);
}

//End------------------------------------------------------------------------End
private void displayAccountsData() {
    DefaultTableModel model = new DefaultTableModel(); // Create a table model
    model.addColumn("ID");
    model.addColumn("Employee No");
    model.addColumn("First Name");
    model.addColumn("Last Name");
    model.addColumn("Username");
    model.addColumn("Password");
    model.addColumn("Account Type");

    try {
        // Query to fetch data from the accounts table
        String query = "SELECT ID, EmployeeNo, FirstName, LastName, UserName,PassWord, AccountType FROM Accounts";
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        // Loop through the result set and add rows to the table model
        while (rs.next()) {
            Object[] row = {
                rs.getInt("ID"),
                rs.getString("EmployeeNo"),
                rs.getString("FirstName"),
                rs.getString("LastName"),
                rs.getString("UserName"),
                rs.getString("PassWord"),
                rs.getString("AccountType")
            };
            model.addRow(row);
        }

        // Close resources
        pst.close();
        rs.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error fetching accounts data.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Set the table model to the JTable for display
    tblAccounts.setModel(model);
}
//Clear the Add User Fields
private void clearUserFields() {
    txtEmployeeNum.setText("");
    txtFirstName.setText("");
    txtLastName.setText("");
    txtUserName.setText("");
    txtPass.setText("");
    cbAccountType.setSelectedIndex(0);
}
//Print functionality
public static void printStock(JTable tblStock) {
        // Get the table model
        TableModel model = tblStock.getModel();

        try {
            // Print the table
            boolean complete = tblStock.print();

            if (complete) {
                System.out.println("Print successful");
            } else {
                System.out.println("Print canceled");
            }
        } catch (PrinterException pe) {
            System.out.println("Print failed: " + pe.getMessage());
        }
    }
public static void printRelease(JTable tblRelease) {
        // Get the table model
        TableModel model = tblRelease.getModel();

        try {
            // Print the table
            boolean complete = tblRelease.print();

            if (complete) {
                System.out.println("Print successful");
            } else {
                System.out.println("Print canceled");
            }
        } catch (PrinterException pe) {
            System.out.println("Print failed: " + pe.getMessage());
        }
    }
public static void printChecking(JTable tblChecking) {
        // Get the table model
        TableModel model = tblChecking.getModel();

        try {
            // Print the table
            boolean complete = tblChecking.print();

            if (complete) {
                System.out.println("Print successful");
            } else {
                System.out.println("Print canceled");
            }
        } catch (PrinterException pe) {
            System.out.println("Print failed: " + pe.getMessage());
        }
    }
public static void printReturn(JTable tblReturn) {
        // Get the table model
        TableModel model = tblReturn.getModel();

        try {
            // Print the table
            boolean complete = tblReturn.print();

            if (complete) {
                System.out.println("Print successful");
            } else {
                System.out.println("Print canceled");
            }
        } catch (PrinterException pe) {
            System.out.println("Print failed: " + pe.getMessage());
        }
    }
public static void printRepair(JTable tblRepair) {
        // Get the table model
        TableModel model = tblRepair.getModel();

        try {
            // Print the table
            boolean complete = tblRepair.print();

            if (complete) {
                System.out.println("Print successful");
            } else {
                System.out.println("Print canceled");
            }
        } catch (PrinterException pe) {
            System.out.println("Print failed: " + pe.getMessage());
        }
    }
public static void printDisposal(JTable tblDisposal) {
        // Get the table model
        TableModel model = tblDisposal.getModel();

        try {
            // Print the table
            boolean complete = tblDisposal.print();

            if (complete) {
                System.out.println("Print successful");
            } else {
                System.out.println("Print canceled");
            }
        } catch (PrinterException pe) {
            System.out.println("Print failed: " + pe.getMessage());
        }
    }
//End------------------------------------------------------------------------End

       

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JButton();
        tabPane = new javax.swing.JTabbedPane();
        dashboard = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblTotalqty = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        lblChecking = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        lblRepair = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        lblDisposal = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        lblReturn = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        lblSuperAd = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        lblAdmin = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        lblUser = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        lblRelease = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtItemName = new javax.swing.JTextField();
        txtModel = new javax.swing.JTextField();
        txtQty = new javax.swing.JTextField();
        txtBrand = new javax.swing.JTextField();
        btnInsert = new javax.swing.JButton();
        txtClear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDataInfo = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        txtSpecification = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cbSCategory = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        txtSerial = new javax.swing.JTextField();
        btnSaveItem = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnCanceled = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        txtSearchItem = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        btnViewUser3 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbAccountType = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        btnAddU = new javax.swing.JButton();
        txtUserName = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        chkPass = new javax.swing.JCheckBox();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblAccounts = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        txtFirstName = new javax.swing.JTextField();
        txtEmployeeNum = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        btnUserEdit = new javax.swing.JButton();
        btnUserDelete = new javax.swing.JButton();
        btnClearUser = new javax.swing.JButton();
        btnSaveUser = new javax.swing.JButton();
        txtSearchUser = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnViewUser2 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        tabSuperadmin = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblStock = new javax.swing.JTable();
        btnDeleteStock = new javax.swing.JButton();
        txtSearchStock = new javax.swing.JTextField();
        btnTransferStock = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        btnViewUser4 = new javax.swing.JButton();
        btnPrint_stock = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblRelease = new javax.swing.JTable();
        btnTransferRelease = new javax.swing.JButton();
        btnPrint_release = new javax.swing.JButton();
        txtSearchRelease = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        btnViewUser5 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblChecking = new javax.swing.JTable();
        btnTransferChk = new javax.swing.JButton();
        btnPrint_checking = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        btnViewUser6 = new javax.swing.JButton();
        txtSearchChecking = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblReturn = new javax.swing.JTable();
        btnTransferRtn = new javax.swing.JButton();
        btnPrint_Return = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        btnViewUser7 = new javax.swing.JButton();
        txtSearchReturn = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblRepair = new javax.swing.JTable();
        btnTransferRpr = new javax.swing.JButton();
        btnPrint_repair = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        btnViewUser8 = new javax.swing.JButton();
        txtSearchRepair = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblDisposal = new javax.swing.JTable();
        btnPrint_disposal = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        btnViewUser9 = new javax.swing.JButton();
        txtSearchDisposal = new javax.swing.JTextField();
        btnStock = new javax.swing.JButton();
        btnChecking = new javax.swing.JButton();
        btnReturn = new javax.swing.JButton();
        btnRepair = new javax.swing.JButton();
        btnDisposal = new javax.swing.JButton();
        btnChecking1 = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnRecords = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnAddUser = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/logos_small_67RB7KTLSNXPWKW6NAG7-56a62ad1-removebg-preview (1) (3).png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        btnDashboard.setFont(new java.awt.Font("Rockwell", 1, 32)); // NOI18N
        btnDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/80x70 dashboard.png"))); // NOI18N
        btnDashboard.setText("  Dashboard");
        btnDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashboardActionPerformed(evt);
            }
        });
        jPanel1.add(btnDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 310, 80));

        dashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1749, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );

        dashboard.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, -1, 130));

        jPanel2.setBackground(new java.awt.Color(255, 232, 163));
        jPanel2.setPreferredSize(new java.awt.Dimension(231, 156));

        lblTotalqty.setFont(new java.awt.Font("Rockwell", 1, 50)); // NOI18N
        lblTotalqty.setText("0");

        jLabel28.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel28.setText("Total");

        jLabel29.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel29.setText("Stock Items");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTotalqty, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(108, 108, 108)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(147, 147, 147)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addComponent(lblTotalqty, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel29))
        );

        dashboard.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 400, 220));

        jPanel12.setBackground(new java.awt.Color(211, 109, 109));
        jPanel12.setPreferredSize(new java.awt.Dimension(241, 104));

        lblChecking.setFont(new java.awt.Font("Rockwell", 1, 50)); // NOI18N
        lblChecking.setText("0");

        jLabel31.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel31.setText("Checking Items");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Checking.png"))); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(72, 72, 72)
                        .addComponent(lblChecking, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblChecking, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(jLabel31)
                .addGap(16, 16, 16))
        );

        dashboard.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 200, 400, 220));

        jPanel14.setBackground(new java.awt.Color(106, 177, 135));
        jPanel14.setPreferredSize(new java.awt.Dimension(241, 104));

        lblRepair.setFont(new java.awt.Font("Rockwell", 1, 50)); // NOI18N
        lblRepair.setText("0");

        jLabel33.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel33.setText("Repair Items");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Repair.png"))); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(0, 103, Short.MAX_VALUE)
                        .addComponent(jLabel33))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblRepair, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblRepair)
                        .addGap(39, 39, 39))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)))
                .addComponent(jLabel33)
                .addGap(13, 13, 13))
        );

        dashboard.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 200, 380, 220));

        jPanel20.setBackground(new java.awt.Color(190, 159, 191));
        jPanel20.setPreferredSize(new java.awt.Dimension(241, 104));

        lblDisposal.setFont(new java.awt.Font("Rockwell", 1, 50)); // NOI18N
        lblDisposal.setText("0");

        jLabel39.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel39.setText("Disposal Items");

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Disposal.png"))); // NOI18N

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(93, Short.MAX_VALUE)
                .addComponent(jLabel39)
                .addGap(19, 19, 19))
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDisposal, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(lblDisposal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jLabel39)
                .addGap(20, 20, 20))
        );

        dashboard.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 450, 380, 220));

        jPanel21.setBackground(new java.awt.Color(102, 102, 154));
        jPanel21.setPreferredSize(new java.awt.Dimension(241, 104));

        lblReturn.setFont(new java.awt.Font("Rockwell", 1, 50)); // NOI18N
        lblReturn.setText("0");

        jLabel37.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel37.setText("Return Items");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Return.png"))); // NOI18N

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap(137, Short.MAX_VALUE)
                .addComponent(jLabel37)
                .addGap(29, 29, 29))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addComponent(lblReturn)
                        .addGap(44, 44, 44)))
                .addComponent(jLabel37)
                .addGap(22, 22, 22))
        );

        dashboard.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 450, 400, 220));

        jPanel22.setBackground(new java.awt.Color(68, 67, 67));
        jPanel22.setPreferredSize(new java.awt.Dimension(241, 104));

        lblSuperAd.setFont(new java.awt.Font("Rockwell", 1, 50)); // NOI18N
        lblSuperAd.setForeground(new java.awt.Color(255, 255, 255));
        lblSuperAd.setText("0");

        jLabel41.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Super Admin");

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Super admin.png"))); // NOI18N

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(lblSuperAd, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel41)
                .addGap(56, 56, 56))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblSuperAd)
                        .addGap(38, 38, 38))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel19)))
                .addComponent(jLabel41)
                .addGap(17, 17, 17))
        );

        dashboard.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 700, 400, 230));

        jPanel23.setBackground(new java.awt.Color(68, 67, 67));
        jPanel23.setPreferredSize(new java.awt.Dimension(241, 104));

        lblAdmin.setFont(new java.awt.Font("Rockwell", 1, 50)); // NOI18N
        lblAdmin.setForeground(new java.awt.Color(255, 255, 255));
        lblAdmin.setText("0");

        jLabel45.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Admin");

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Admin.png"))); // NOI18N

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addComponent(lblAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblAdmin)
                .addGap(32, 32, 32)
                .addComponent(jLabel45)
                .addGap(19, 19, 19))
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(jLabel24)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        dashboard.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 700, 400, 230));

        jPanel24.setBackground(new java.awt.Color(68, 67, 67));
        jPanel24.setPreferredSize(new java.awt.Dimension(241, 104));

        lblUser.setFont(new java.awt.Font("Rockwell", 1, 50)); // NOI18N
        lblUser.setForeground(new java.awt.Color(255, 255, 255));
        lblUser.setText("0");

        jLabel43.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("User");

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/User.png"))); // NOI18N

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                        .addComponent(lblUser, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addComponent(lblUser)
                .addGap(30, 30, 30)
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel30)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dashboard.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 700, 380, 230));

        jLabel47.setFont(new java.awt.Font("Rockwell", 1, 55)); // NOI18N
        jLabel47.setText("DASHBOARD");
        dashboard.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 120, -1, -1));

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        dashboard.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, -1, -1));

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        dashboard.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, -1, -1));

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        dashboard.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, -1, -1));

        jPanel28.setBackground(new java.awt.Color(251, 202, 126));

        lblRelease.setFont(new java.awt.Font("Rockwell", 1, 50)); // NOI18N
        lblRelease.setText("0");

        jLabel38.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel38.setText("Release Items");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Release.png"))); // NOI18N

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(lblRelease, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(lblRelease)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                        .addContainerGap(26, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        dashboard.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 450, 400, 220));

        tabPane.addTab("tab1", dashboard);

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 103, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel5.setText("Item Name:");

        jLabel6.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel6.setText("Model:");

        jLabel7.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel7.setText("Category:");

        jLabel8.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel8.setText("Brand:");

        jLabel10.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel10.setText("Quantity:");

        txtItemName.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N

        txtModel.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        txtModel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtModelActionPerformed(evt);
            }
        });

        txtQty.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        txtQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQtyActionPerformed(evt);
            }
        });

        txtBrand.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        txtBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBrandActionPerformed(evt);
            }
        });

        btnInsert.setBackground(new java.awt.Color(93, 190, 163));
        btnInsert.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnInsert.setText("Insert");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        txtClear.setBackground(new java.awt.Color(93, 190, 163));
        txtClear.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        txtClear.setText("Clear");
        txtClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClearActionPerformed(evt);
            }
        });

        tblDataInfo.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tblDataInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "SerialNo", "ItemName", "Model", "Specification", "Category", "Brand", "Qty"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDataInfo);
        if (tblDataInfo.getColumnModel().getColumnCount() > 0) {
            tblDataInfo.getColumnModel().getColumn(0).setResizable(false);
            tblDataInfo.getColumnModel().getColumn(1).setResizable(false);
            tblDataInfo.getColumnModel().getColumn(2).setResizable(false);
            tblDataInfo.getColumnModel().getColumn(3).setResizable(false);
            tblDataInfo.getColumnModel().getColumn(4).setResizable(false);
            tblDataInfo.getColumnModel().getColumn(5).setResizable(false);
            tblDataInfo.getColumnModel().getColumn(6).setResizable(false);
            tblDataInfo.getColumnModel().getColumn(7).setResizable(false);
        }

        jLabel13.setFont(new java.awt.Font("Rockwell", 1, 55)); // NOI18N
        jLabel13.setText("ADD ITEM FORM");

        txtSpecification.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel15.setText("Specification:");

        cbSCategory.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbSCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hardware", "Software" }));

        jLabel18.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel18.setText("Serial No:");

        txtSerial.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        txtSerial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSerialActionPerformed(evt);
            }
        });

        btnSaveItem.setBackground(new java.awt.Color(93, 190, 163));
        btnSaveItem.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnSaveItem.setText("Save");
        btnSaveItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveItemActionPerformed(evt);
            }
        });

        btnEdit.setBackground(new java.awt.Color(93, 190, 163));
        btnEdit.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnCanceled.setBackground(new java.awt.Color(93, 190, 163));
        btnCanceled.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnCanceled.setText("Cancel");
        btnCanceled.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCanceledActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(93, 190, 163));
        btnDelete.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        txtSearchItem.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        txtSearchItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchItemActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jLabel25.setText("Search:");

        btnViewUser3.setBackground(new java.awt.Color(0, 153, 153));
        btnViewUser3.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnViewUser3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh .png"))); // NOI18N
        btnViewUser3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewUser3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 2, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(54, 54, 54)
                                        .addComponent(txtClear, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(24, 24, 24))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(cbSCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtSpecification, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtModel, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(30, 30, 30))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel8))
                                .addGap(311, 311, 311))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSerial, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(btnSaveItem, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(btnCanceled)
                            .addGap(20, 20, 20)
                            .addComponent(btnDelete))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 997, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(220, 220, 220)
                        .addComponent(jLabel25)
                        .addGap(2, 2, 2)
                        .addComponent(txtSearchItem, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(btnViewUser3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtSerial, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel6))
                            .addComponent(txtModel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtSpecification, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel7))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbSCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtClear, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtSearchItem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel25))
                                        .addGap(1, 1, 1))
                                    .addComponent(btnViewUser3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSaveItem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCanceled, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        tabPane.addTab("tab2", jPanel3);

        jPanel10.setBackground(new java.awt.Color(51, 51, 51));

        jLabel11.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel11.setText("Username");

        jLabel12.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel12.setText("Password");

        cbAccountType.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbAccountType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SuperAdmin", "Admin", "User" }));
        cbAccountType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAccountTypeActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel16.setText("Account Type");

        btnAddU.setBackground(new java.awt.Color(93, 190, 163));
        btnAddU.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnAddU.setText("Add");
        btnAddU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUActionPerformed(evt);
            }
        });

        txtUserName.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        txtUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserNameActionPerformed(evt);
            }
        });

        txtPass.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N

        chkPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPassActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel20.setText("ShowPassword");

        tblAccounts.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tblAccounts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "EmployeeNo", "FirstName", "LastName", "Username", "Password", "AccountType"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tblAccounts);
        if (tblAccounts.getColumnModel().getColumnCount() > 0) {
            tblAccounts.getColumnModel().getColumn(0).setResizable(false);
            tblAccounts.getColumnModel().getColumn(1).setResizable(false);
            tblAccounts.getColumnModel().getColumn(2).setResizable(false);
            tblAccounts.getColumnModel().getColumn(3).setResizable(false);
            tblAccounts.getColumnModel().getColumn(4).setResizable(false);
            tblAccounts.getColumnModel().getColumn(5).setResizable(false);
            tblAccounts.getColumnModel().getColumn(6).setResizable(false);
        }

        jLabel21.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel21.setText("Employee No.");

        jLabel22.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel22.setText("First Name");

        txtLastName.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        txtLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLastNameActionPerformed(evt);
            }
        });

        txtFirstName.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N

        txtEmployeeNum.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel23.setText("Last Name");

        btnUserEdit.setBackground(new java.awt.Color(93, 190, 163));
        btnUserEdit.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnUserEdit.setText("Edit");
        btnUserEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserEditActionPerformed(evt);
            }
        });

        btnUserDelete.setBackground(new java.awt.Color(93, 190, 163));
        btnUserDelete.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnUserDelete.setText("Delete");
        btnUserDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserDeleteActionPerformed(evt);
            }
        });

        btnClearUser.setBackground(new java.awt.Color(93, 190, 163));
        btnClearUser.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnClearUser.setText("Clear");
        btnClearUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearUserActionPerformed(evt);
            }
        });

        btnSaveUser.setBackground(new java.awt.Color(93, 190, 163));
        btnSaveUser.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnSaveUser.setText("Save");
        btnSaveUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveUserActionPerformed(evt);
            }
        });

        txtSearchUser.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        txtSearchUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchUserActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jLabel26.setText("Search");

        btnCancel.setBackground(new java.awt.Color(93, 190, 163));
        btnCancel.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnViewUser2.setBackground(new java.awt.Color(93, 190, 163));
        btnViewUser2.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnViewUser2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh .png"))); // NOI18N
        btnViewUser2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewUser2ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Rockwell", 1, 50)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("ADD USER FORM");

        jPanel32.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(0, 0, 0)
                                .addComponent(txtSearchUser, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(btnViewUser2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel16)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(chkPass, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel20))
                                    .addComponent(jLabel21)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(txtEmployeeNum, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(cbAccountType, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                        .addComponent(btnAddU, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(56, 56, 56)
                                        .addComponent(btnClearUser, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(btnSaveUser, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)
                                        .addComponent(btnUserEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)
                                        .addComponent(btnCancel)
                                        .addGap(20, 20, 20)
                                        .addComponent(btnUserDelete))
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1050, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(512, 512, 512)
                        .addComponent(jLabel17)))
                .addContainerGap(30, Short.MAX_VALUE))
            .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnViewUser2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearchUser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(5, 5, 5)
                        .addComponent(txtEmployeeNum, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22)
                        .addGap(5, 5, 5)
                        .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23)
                        .addGap(5, 5, 5)
                        .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addGap(5, 5, 5)
                        .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addGap(5, 5, 5)
                        .addComponent(cbAccountType, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chkPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnClearUser, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddU, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUserDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUserEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSaveUser, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabPane.addTab("tab3", jPanel8);

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2780, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 625, Short.MAX_VALUE)
        );

        tabSuperadmin.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        tblStock.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tblStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Serial No.", "ItemName", "Model", "Specification", "Category", "Brand", "Qty."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblStock);
        if (tblStock.getColumnModel().getColumnCount() > 0) {
            tblStock.getColumnModel().getColumn(0).setResizable(false);
            tblStock.getColumnModel().getColumn(1).setResizable(false);
            tblStock.getColumnModel().getColumn(2).setResizable(false);
            tblStock.getColumnModel().getColumn(3).setResizable(false);
            tblStock.getColumnModel().getColumn(4).setResizable(false);
            tblStock.getColumnModel().getColumn(5).setResizable(false);
            tblStock.getColumnModel().getColumn(6).setResizable(false);
            tblStock.getColumnModel().getColumn(7).setResizable(false);
        }

        btnDeleteStock.setBackground(new java.awt.Color(93, 190, 163));
        btnDeleteStock.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnDeleteStock.setText("Delete");
        btnDeleteStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteStockActionPerformed(evt);
            }
        });

        txtSearchStock.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        txtSearchStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchStockActionPerformed(evt);
            }
        });

        btnTransferStock.setBackground(new java.awt.Color(93, 190, 163));
        btnTransferStock.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnTransferStock.setText("Transfer");
        btnTransferStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferStockActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jLabel27.setText("Search:");

        btnViewUser4.setBackground(new java.awt.Color(93, 190, 163));
        btnViewUser4.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnViewUser4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh .png"))); // NOI18N
        btnViewUser4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewUser4ActionPerformed(evt);
            }
        });

        btnPrint_stock.setBackground(new java.awt.Color(93, 190, 163));
        btnPrint_stock.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnPrint_stock.setText("Print");
        btnPrint_stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint_stockActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(btnTransferStock)
                        .addGap(2, 2, 2)
                        .addComponent(btnDeleteStock)
                        .addGap(2, 2, 2)
                        .addComponent(btnPrint_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(895, 895, 895)
                        .addComponent(jLabel27)
                        .addGap(3, 3, 3)
                        .addComponent(txtSearchStock, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(btnViewUser4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSearchStock, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewUser4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDeleteStock)
                        .addComponent(btnTransferStock)
                        .addComponent(jLabel27)
                        .addComponent(btnPrint_stock)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        txtSearchStock.getAccessibleContext().setAccessibleName("");

        tabSuperadmin.addTab("tab1", jPanel13);

        tblRelease.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tblRelease.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ReleaseID", "ItemID", "Location", "SerialNo", "ItemName", "Model", "Specification", "Category", "Brand", "Qty", "ReleaseDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(tblRelease);
        if (tblRelease.getColumnModel().getColumnCount() > 0) {
            tblRelease.getColumnModel().getColumn(0).setResizable(false);
            tblRelease.getColumnModel().getColumn(1).setResizable(false);
            tblRelease.getColumnModel().getColumn(2).setResizable(false);
            tblRelease.getColumnModel().getColumn(3).setResizable(false);
            tblRelease.getColumnModel().getColumn(4).setResizable(false);
            tblRelease.getColumnModel().getColumn(5).setResizable(false);
            tblRelease.getColumnModel().getColumn(6).setResizable(false);
            tblRelease.getColumnModel().getColumn(7).setResizable(false);
            tblRelease.getColumnModel().getColumn(8).setResizable(false);
            tblRelease.getColumnModel().getColumn(9).setResizable(false);
            tblRelease.getColumnModel().getColumn(10).setResizable(false);
        }

        btnTransferRelease.setBackground(new java.awt.Color(93, 190, 163));
        btnTransferRelease.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnTransferRelease.setText("Transfer");
        btnTransferRelease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferReleaseActionPerformed(evt);
            }
        });

        btnPrint_release.setBackground(new java.awt.Color(93, 190, 163));
        btnPrint_release.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnPrint_release.setText("Print");
        btnPrint_release.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint_releaseActionPerformed(evt);
            }
        });

        txtSearchRelease.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        txtSearchRelease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchReleaseActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jLabel32.setText("Search:");

        btnViewUser5.setBackground(new java.awt.Color(93, 190, 163));
        btnViewUser5.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnViewUser5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh .png"))); // NOI18N
        btnViewUser5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewUser5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 1451, Short.MAX_VALUE)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(btnTransferRelease)
                        .addGap(2, 2, 2)
                        .addComponent(btnPrint_release)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel32)
                        .addGap(3, 3, 3)
                        .addComponent(txtSearchRelease, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(btnViewUser5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTransferRelease)
                        .addComponent(btnPrint_release))
                    .addComponent(btnViewUser5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSearchRelease, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabSuperadmin.addTab("tab6", jPanel19);

        tblChecking.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tblChecking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "CheckingID", "ItemID", "Serial No.", "Name", "Model", "Specification", "Category", "Brand", "Qty.", "CheckDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChecking.setToolTipText("");
        tblChecking.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblChecking);
        if (tblChecking.getColumnModel().getColumnCount() > 0) {
            tblChecking.getColumnModel().getColumn(0).setResizable(false);
            tblChecking.getColumnModel().getColumn(1).setResizable(false);
            tblChecking.getColumnModel().getColumn(2).setResizable(false);
            tblChecking.getColumnModel().getColumn(3).setResizable(false);
            tblChecking.getColumnModel().getColumn(4).setResizable(false);
            tblChecking.getColumnModel().getColumn(5).setResizable(false);
            tblChecking.getColumnModel().getColumn(6).setResizable(false);
            tblChecking.getColumnModel().getColumn(7).setResizable(false);
            tblChecking.getColumnModel().getColumn(8).setResizable(false);
            tblChecking.getColumnModel().getColumn(9).setResizable(false);
        }

        btnTransferChk.setBackground(new java.awt.Color(93, 190, 163));
        btnTransferChk.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnTransferChk.setText("Transfer");
        btnTransferChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferChkActionPerformed(evt);
            }
        });

        btnPrint_checking.setBackground(new java.awt.Color(93, 190, 163));
        btnPrint_checking.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnPrint_checking.setText("Print");
        btnPrint_checking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint_checkingActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jLabel34.setText("Search:");

        btnViewUser6.setBackground(new java.awt.Color(93, 190, 163));
        btnViewUser6.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnViewUser6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh .png"))); // NOI18N
        btnViewUser6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewUser6ActionPerformed(evt);
            }
        });

        txtSearchChecking.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        txtSearchChecking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchCheckingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1456, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(btnTransferChk)
                        .addGap(2, 2, 2)
                        .addComponent(btnPrint_checking)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel34)
                        .addGap(3, 3, 3)
                        .addComponent(txtSearchChecking, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(btnViewUser6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTransferChk)
                        .addComponent(btnPrint_checking))
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnViewUser6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSearchChecking, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 717, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabSuperadmin.addTab("tab2", jPanel15);

        tblReturn.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tblReturn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ReturnID", "ItemID", "SerialNo.", "Name", "Model", "Specification", "Category", "Brand", "Qty.", "ReturnDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblReturn);
        if (tblReturn.getColumnModel().getColumnCount() > 0) {
            tblReturn.getColumnModel().getColumn(0).setResizable(false);
            tblReturn.getColumnModel().getColumn(1).setResizable(false);
            tblReturn.getColumnModel().getColumn(2).setResizable(false);
            tblReturn.getColumnModel().getColumn(3).setResizable(false);
            tblReturn.getColumnModel().getColumn(4).setResizable(false);
            tblReturn.getColumnModel().getColumn(5).setResizable(false);
            tblReturn.getColumnModel().getColumn(6).setResizable(false);
            tblReturn.getColumnModel().getColumn(7).setResizable(false);
            tblReturn.getColumnModel().getColumn(8).setResizable(false);
            tblReturn.getColumnModel().getColumn(9).setResizable(false);
        }

        btnTransferRtn.setBackground(new java.awt.Color(93, 190, 163));
        btnTransferRtn.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnTransferRtn.setText("Transfer");
        btnTransferRtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferRtnActionPerformed(evt);
            }
        });

        btnPrint_Return.setBackground(new java.awt.Color(93, 190, 163));
        btnPrint_Return.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnPrint_Return.setText("Print");
        btnPrint_Return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint_ReturnActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jLabel35.setText("Search:");

        btnViewUser7.setBackground(new java.awt.Color(93, 190, 163));
        btnViewUser7.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnViewUser7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh .png"))); // NOI18N
        btnViewUser7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewUser7ActionPerformed(evt);
            }
        });

        txtSearchReturn.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        txtSearchReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchReturnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1451, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(btnTransferRtn)
                        .addGap(2, 2, 2)
                        .addComponent(btnPrint_Return)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel35)
                        .addGap(3, 3, 3)
                        .addComponent(txtSearchReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(btnViewUser7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnTransferRtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPrint_Return, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnViewUser7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSearchReturn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        tabSuperadmin.addTab("tab3", jPanel16);

        tblRepair.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "RepairID", "ItemID", "Serial No.", "Name", "Model", "Specification", "Category", "Brand", "Qty", "RepairDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tblRepair);
        if (tblRepair.getColumnModel().getColumnCount() > 0) {
            tblRepair.getColumnModel().getColumn(0).setResizable(false);
            tblRepair.getColumnModel().getColumn(1).setResizable(false);
            tblRepair.getColumnModel().getColumn(2).setResizable(false);
            tblRepair.getColumnModel().getColumn(3).setResizable(false);
            tblRepair.getColumnModel().getColumn(4).setResizable(false);
            tblRepair.getColumnModel().getColumn(5).setResizable(false);
            tblRepair.getColumnModel().getColumn(6).setResizable(false);
            tblRepair.getColumnModel().getColumn(7).setResizable(false);
            tblRepair.getColumnModel().getColumn(8).setResizable(false);
            tblRepair.getColumnModel().getColumn(9).setResizable(false);
        }

        btnTransferRpr.setBackground(new java.awt.Color(93, 190, 163));
        btnTransferRpr.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnTransferRpr.setText("Transfer");
        btnTransferRpr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferRprActionPerformed(evt);
            }
        });

        btnPrint_repair.setBackground(new java.awt.Color(93, 190, 163));
        btnPrint_repair.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnPrint_repair.setText("Print");
        btnPrint_repair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint_repairActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jLabel36.setText("Search:");

        btnViewUser8.setBackground(new java.awt.Color(93, 190, 163));
        btnViewUser8.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnViewUser8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh .png"))); // NOI18N
        btnViewUser8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewUser8ActionPerformed(evt);
            }
        });

        txtSearchRepair.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        txtSearchRepair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchRepairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1451, Short.MAX_VALUE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(btnTransferRpr)
                        .addGap(2, 2, 2)
                        .addComponent(btnPrint_repair)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel36)
                        .addGap(3, 3, 3)
                        .addComponent(txtSearchRepair, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(btnViewUser8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnPrint_repair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTransferRpr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnViewUser8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSearchRepair, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 717, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabSuperadmin.addTab("tab4", jPanel17);

        tblDisposal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "DisposalID", "ItemID", "Serial No.", "Name", "Model", "Specification", "Category", "Brand", "Qty.", "DisposalDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tblDisposal);
        if (tblDisposal.getColumnModel().getColumnCount() > 0) {
            tblDisposal.getColumnModel().getColumn(0).setResizable(false);
            tblDisposal.getColumnModel().getColumn(1).setResizable(false);
            tblDisposal.getColumnModel().getColumn(2).setResizable(false);
            tblDisposal.getColumnModel().getColumn(3).setResizable(false);
            tblDisposal.getColumnModel().getColumn(4).setResizable(false);
            tblDisposal.getColumnModel().getColumn(5).setResizable(false);
            tblDisposal.getColumnModel().getColumn(6).setResizable(false);
            tblDisposal.getColumnModel().getColumn(7).setResizable(false);
            tblDisposal.getColumnModel().getColumn(8).setResizable(false);
            tblDisposal.getColumnModel().getColumn(9).setResizable(false);
        }

        btnPrint_disposal.setBackground(new java.awt.Color(93, 190, 163));
        btnPrint_disposal.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnPrint_disposal.setText("Print");
        btnPrint_disposal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint_disposalActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jLabel40.setText("Search:");

        btnViewUser9.setBackground(new java.awt.Color(93, 190, 163));
        btnViewUser9.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnViewUser9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh .png"))); // NOI18N
        btnViewUser9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewUser9ActionPerformed(evt);
            }
        });

        txtSearchDisposal.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        txtSearchDisposal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchDisposalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(btnPrint_disposal, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1109, Short.MAX_VALUE)
                        .addComponent(jLabel40)
                        .addGap(3, 3, 3)
                        .addComponent(txtSearchDisposal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(btnViewUser9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPrint_disposal)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnViewUser9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSearchDisposal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        tabSuperadmin.addTab("tab5", jPanel18);

        btnStock.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnStock.setText("Stock");
        btnStock.setPreferredSize(new java.awt.Dimension(98, 25));
        btnStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStockActionPerformed(evt);
            }
        });

        btnChecking.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnChecking.setText("Checking");
        btnChecking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckingActionPerformed(evt);
            }
        });

        btnReturn.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnReturn.setText("Return");
        btnReturn.setPreferredSize(new java.awt.Dimension(98, 25));
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });

        btnRepair.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnRepair.setText("Repair");
        btnRepair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRepairActionPerformed(evt);
            }
        });

        btnDisposal.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnDisposal.setText("Disposal");
        btnDisposal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisposalActionPerformed(evt);
            }
        });

        btnChecking1.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnChecking1.setText("Release");
        btnChecking1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChecking1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btnStock, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnChecking1)
                        .addGap(2, 2, 2)
                        .addComponent(btnChecking)
                        .addGap(2, 2, 2)
                        .addComponent(btnReturn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(btnRepair)
                        .addGap(2, 2, 2)
                        .addComponent(btnDisposal))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tabSuperadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 1463, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDisposal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRepair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReturn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnChecking, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnChecking1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(tabSuperadmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 831, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabPane.addTab("tab4", jPanel4);

        jPanel1.add(tabPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(359, -40, 1490, 980));

        btnAdd.setFont(new java.awt.Font("Rockwell", 1, 32)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/80x70 item.png"))); // NOI18N
        btnAdd.setText("  Items");
        btnAdd.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel1.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 310, 80));

        btnRecords.setFont(new java.awt.Font("Rockwell", 1, 32)); // NOI18N
        btnRecords.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/80x70 records.png"))); // NOI18N
        btnRecords.setText("  Records");
        btnRecords.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRecords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecordsActionPerformed(evt);
            }
        });
        jPanel1.add(btnRecords, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 310, 80));

        btnLogout.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        btnLogout.setText("LOG OUT");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 850, 220, 60));

        btnAddUser.setFont(new java.awt.Font("Rockwell", 1, 32)); // NOI18N
        btnAddUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/80x70 user.png"))); // NOI18N
        btnAddUser.setText("  Users");
        btnAddUser.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });
        jPanel1.add(btnAddUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 310, 80));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        // TODO add your handling code here:
        displayTotalDataCount();
        displayReleaseCount();
        displayCheckingCount();
        displayRepairCount();
        displayReturnCount();
        displayDisposalCount();
        displayAccountTypeCounts();
        tabPane.setSelectedIndex(0);
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
          tabPane.setSelectedIndex(1);
          displayDataItems();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecordsActionPerformed
        // TODO add your handling code here:
          tabPane.setSelectedIndex(3);
          displayStockItems();
    }//GEN-LAST:event_btnRecordsActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {

            JOptionPane.showMessageDialog(null, "Logout Successful");
             new LogIn().setVisible(true);
            dispose();
        } else {

        }
          
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void txtBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBrandActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBrandActionPerformed

    private void txtQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQtyActionPerformed

    private void txtModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtModelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModelActionPerformed

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        // TODO add your handling code here:
        tabPane.setSelectedIndex(2);
        
        displayAccountsData();
    }//GEN-LAST:event_btnAddUserActionPerformed

    private void txtClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_txtClearActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
       editItemTable();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        // TODO add your handling code here:
        insertItem();
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnCanceledActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCanceledActionPerformed
        // TODO add your handling code here:
        clear();
        btnEdit.setEnabled(true);
        btnDelete.setEnabled(true);
        btnInsert.setEnabled(true);
        btnSaveItem.setEnabled(false);
        btnCanceled.setEnabled(false);
        displayDataItems();
        displayStockItems();
    }//GEN-LAST:event_btnCanceledActionPerformed

    private void btnDeleteStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteStockActionPerformed
        // TODO add your handling code here:
        delete_tblStock();
        displayStockItems();
    }//GEN-LAST:event_btnDeleteStockActionPerformed

    private void btnTransferStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferStockActionPerformed
        // TODO add your handling code here:
        transferSelectedItem();
        displayStockItems();
    }//GEN-LAST:event_btnTransferStockActionPerformed

    private void btnTransferChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferChkActionPerformed
        // TODO add your handling code here:
        transferCheckingItem("Checking");
        

    }//GEN-LAST:event_btnTransferChkActionPerformed

    private void btnTransferRtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferRtnActionPerformed
        // TODO add your handling code here:
        //transferCheckingItem("Return");
        transferReturnItem();
        
    }//GEN-LAST:event_btnTransferRtnActionPerformed

    private void btnTransferRprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferRprActionPerformed
        // TODO add your handling code here:
        //transferCheckingItem("Repair");
        transferRepairItem();
    }//GEN-LAST:event_btnTransferRprActionPerformed

    private void btnStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockActionPerformed
        // TODO add your handling code here:
        tabSuperadmin.setSelectedIndex(0);
        displayStockItems();
    }//GEN-LAST:event_btnStockActionPerformed

    private void btnCheckingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckingActionPerformed
        // TODO add your handling code here:
        tabSuperadmin.setSelectedIndex(2);
        displayCheckingData();

    }//GEN-LAST:event_btnCheckingActionPerformed

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        // TODO add your handling code here:
        tabSuperadmin.setSelectedIndex(3);
        displayReturn();

    }//GEN-LAST:event_btnReturnActionPerformed

    private void btnRepairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRepairActionPerformed
        // TODO add your handling code here:
        tabSuperadmin.setSelectedIndex(4);
        displayRepair();

    }//GEN-LAST:event_btnRepairActionPerformed

    private void btnDisposalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisposalActionPerformed
        // TODO add your handling code here:
        tabSuperadmin.setSelectedIndex(5);
        displayDisposal();
        
    }//GEN-LAST:event_btnDisposalActionPerformed

    private void txtSearchStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchStockActionPerformed
        // TODO add your handling code here:
        String searchText = txtSearchStock.getText().trim();

        DefaultTableModel model = (DefaultTableModel) tblStock.getModel();
        model.setRowCount(0); // Clear existing rows

        try {
            String sql = "SELECT * FROM Stock WHERE SerialNo LIKE ? OR ItemName LIKE ? OR Model LIKE ? OR Category LIKE ? OR Brand LIKE ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + searchText + "%");
            pst.setString(2, "%" + searchText + "%");
            pst.setString(3, "%" + searchText + "%");
            pst.setString(4, "%" + searchText + "%");
            pst.setString(5, "%" + searchText + "%");
            rst = pst.executeQuery();

            while (rst.next()) {
                int ItemID = rst.getInt("ItemID");
                String serialNumber = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String modelValue = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int quantity = rst.getInt("Qty");

                // Map database column names to JTable column names
                model.addRow(new Object[]{ItemID, serialNumber, itemName, modelValue, specification, category, brand, quantity});
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Error searching items in the database.");
        } finally {
            try {
                if (rst != null) {
                    rst.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    
    }//GEN-LAST:event_txtSearchStockActionPerformed

    private void btnSaveItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveItemActionPerformed
        // TODO add your handling code here:
       saveItem();
    }//GEN-LAST:event_btnSaveItemActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        deleteDataInfo();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtSearchItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchItemActionPerformed
        // TODO add your handling code here:
        String searchText = txtSearchItem.getText().trim();

        DefaultTableModel model = (DefaultTableModel) tblDataInfo.getModel();
        model.setRowCount(0); // Clear existing rows

        try {
            String sql = "SELECT * FROM Stock WHERE SerialNo LIKE ? OR ItemName LIKE ? OR Category LIKE ? OR Model LIKE? OR Brand LIKE ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + searchText + "%");
            pst.setString(2, "%" + searchText + "%");
            pst.setString(3, "%" + searchText + "%");
            pst.setString(4, "%" + searchText + "%");
            pst.setString(5, "%" + searchText + "%");
            rst = pst.executeQuery();

            while (rst.next()) {
                int ItemID = rst.getInt("ItemID");
                String serialNumber = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String modelValue = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int quantity = rst.getInt("Qty");

                // Map database column names to JTable column names
                model.addRow(new Object[]{ItemID, serialNumber, itemName, modelValue, specification, category, brand, quantity});
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Error searching items in the database.");
        } finally {
            try {
                if (rst != null) {
                    rst.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_txtSearchItemActionPerformed

    private void btnViewUser3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewUser3ActionPerformed
        // TODO add your handling code here:
        txtSearchItem.setText("");
        displayDataItems();
        clear();
        
    }//GEN-LAST:event_btnViewUser3ActionPerformed

    private void btnViewUser4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewUser4ActionPerformed
        // TODO add your handling code here:
        txtSearchStock.setText("");
        displayStockItems();
    }//GEN-LAST:event_btnViewUser4ActionPerformed

    private void btnChecking1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChecking1ActionPerformed
        // TODO add your handling code here:
        tabSuperadmin.setSelectedIndex(1);
        displayReleaseData();

    }//GEN-LAST:event_btnChecking1ActionPerformed

    private void btnTransferReleaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferReleaseActionPerformed
        // TODO add your handling code here:
        transferReleaseItem();
    }//GEN-LAST:event_btnTransferReleaseActionPerformed

    private void btnPrint_stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint_stockActionPerformed
        // TODO add your handling code here:
        printStock(tblStock);
    }//GEN-LAST:event_btnPrint_stockActionPerformed

    private void btnPrint_releaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint_releaseActionPerformed
        // TODO add your handling code here:
        printRelease(tblRelease);
    }//GEN-LAST:event_btnPrint_releaseActionPerformed

    private void btnPrint_disposalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint_disposalActionPerformed
        // TODO add your handling code here:
        printDisposal(tblDisposal);
    }//GEN-LAST:event_btnPrint_disposalActionPerformed

    private void btnPrint_repairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint_repairActionPerformed
        // TODO add your handling code here:
        printRepair(tblRepair);
    }//GEN-LAST:event_btnPrint_repairActionPerformed

    private void btnPrint_ReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint_ReturnActionPerformed
        // TODO add your handling code here:
        printReturn(tblReturn);
    }//GEN-LAST:event_btnPrint_ReturnActionPerformed

    private void btnPrint_checkingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint_checkingActionPerformed
        // TODO add your handling code here:
        printChecking(tblChecking);
    }//GEN-LAST:event_btnPrint_checkingActionPerformed

    private void txtSerialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSerialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSerialActionPerformed

    private void btnViewUser2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewUser2ActionPerformed
        // TODO add your handling code here:
        txtSearchUser.setText("");
        clearUserFields();
        displayAccountsData();
    }//GEN-LAST:event_btnViewUser2ActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        clearUserFields();
        btnSaveUser.setEnabled(false);
        btnAddU.setEnabled(true);
        btnUserDelete.setEnabled(true);
        btnCancel.setEnabled(false);
        btnUserEdit.setEnabled(true);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void txtSearchUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchUserActionPerformed
        // TODO add your handling code here:
        String searchUser = txtSearchUser.getText().trim();

        DefaultTableModel model = (DefaultTableModel) tblAccounts.getModel();
        model.setRowCount(0); // Clear existing rows

        try {
            String sql = "SELECT * FROM Accounts WHERE EmployeeNo LIKE ? OR FirstName LIKE ? OR LastName LIKE ? OR UserName LIKE ? OR AccountType LIKE ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + searchUser + "%");
            pst.setString(2, "%" + searchUser + "%");
            pst.setString(3, "%" + searchUser + "%");
            pst.setString(4, "%" + searchUser + "%");
            pst.setString(5, "%" + searchUser + "%");
            rst = pst.executeQuery();

            while (rst.next()) {
                int ID = rst.getInt("ID");
                String employeeNumber = rst.getString("EmployeeNo");
                String firstName = rst.getString("FirstName");
                String lastName = rst.getString("LastName");
                String userName = rst.getString("UserName");
                String passWord = rst.getString("Password");
                String accountType = rst.getString("AccountType");

                // Map database column names to JTable column names
                model.addRow(new Object[]{ID, employeeNumber, firstName, lastName, userName, passWord, accountType});
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Error searching items in the database.");
        } finally {
            try {
                if (rst != null) {
                    rst.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_txtSearchUserActionPerformed

    private void btnSaveUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveUserActionPerformed
        // TODO add your handling code here:
        saveUserChanges();
    }//GEN-LAST:event_btnSaveUserActionPerformed

    private void btnClearUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearUserActionPerformed
        // TODO add your handling code here:
        txtEmployeeNum.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtUserName.setText("");
        txtPass.setText("");
        cbAccountType.setSelectedIndex(0);
        chkPass.setSelected(false);
    }//GEN-LAST:event_btnClearUserActionPerformed

    private void btnUserDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserDeleteActionPerformed
        // TODO add your handling code here:
        deleteUser();
    }//GEN-LAST:event_btnUserDeleteActionPerformed

    private void btnUserEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserEditActionPerformed
        // TODO add your handling code here:

        editUser();
    }//GEN-LAST:event_btnUserEditActionPerformed

    private void txtLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLastNameActionPerformed

    private void chkPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPassActionPerformed
        // TODO add your handling code here:
        if(chkPass.isSelected()){
            txtPass.setEchoChar((char)0);
        }
        else{
            txtPass.setEchoChar('*');
        }
    }//GEN-LAST:event_chkPassActionPerformed

    private void txtUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserNameActionPerformed

    private void btnAddUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUActionPerformed
        // TODO add your handling code here:
        addUser();
        clearUserFields();
    }//GEN-LAST:event_btnAddUActionPerformed

    private void cbAccountTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAccountTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbAccountTypeActionPerformed

    private void txtSearchReleaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchReleaseActionPerformed
        // TODO add your handling code here:
        String searchText = txtSearchRelease.getText().trim();
        DefaultTableModel releaseModel = (DefaultTableModel) tblRelease.getModel();
        releaseModel.setRowCount(0); // Clear existing rows

        try {
            String sql = "SELECT rel.ReleaseID, rel.ItemID, rel.Location, s.SerialNo, s.ItemName, s.Model, s.Specification, s.Category, s.Brand, rel.Qty AS ReleasedQty, rel.Date " +
                         "FROM Release rel " +
                         "LEFT JOIN Stock s ON rel.ItemID = s.ItemID " +
                         "WHERE rel.Location LIKE? s.ItemName LIKE ? OR s.SerialNo LIKE ? OR s.Model LIKE ? OR s.Category LIKE ? OR s.Brand LIKE ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + searchText + "%"); // Search Location
            pst.setString(2, "%" + searchText + "%"); // Search ItemName
            pst.setString(3, "%" + searchText + "%"); // Search SerialNo
            pst.setString(4, "%" + searchText + "%"); // Search Model
            pst.setString(5, "%" + searchText + "%"); // Search Category
            pst.setString(6, "%" + searchText + "%"); // Search Brand
            rst = pst.executeQuery();

            while (rst.next()) {
                int releaseID = rst.getInt("ReleaseID");
                int itemID = rst.getInt("ItemID");
                String location = rst.getString("Location");
                String serialNo = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String model = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int releasedQty = rst.getInt("ReleasedQty");
                Date date = rst.getDate("Date");

                // Add a row to the Release table with all the information
                releaseModel.addRow(new Object[]{releaseID, itemID, location, serialNo, itemName, model, specification, category, brand, releasedQty, date});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error retrieving items from the database.");
        } finally {
            try {
                if (rst != null) {
                    rst.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_txtSearchReleaseActionPerformed

    private void btnViewUser5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewUser5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnViewUser5ActionPerformed

    private void btnViewUser6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewUser6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnViewUser6ActionPerformed

    private void txtSearchCheckingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchCheckingActionPerformed
        // TODO add your handling code here:
        String searchText = txtSearchChecking.getText().trim();    
        DefaultTableModel checkingModel = (DefaultTableModel) tblChecking.getModel();
        checkingModel.setRowCount(0); // Clear existing rows

        try {
            String sql = "SELECT c.CheckingID, c.ItemID, s.SerialNo, s.ItemName, s.Model, s.Specification, s.Category, s.Brand, c.Qty AS CheckingQty, c.Date " +
                         "FROM Checking c " +
                         "LEFT JOIN Stock s ON c.ItemID = s.ItemID " +
                         "WHERE s.ItemName LIKE ? OR s.SerialNo LIKE ? OR s.Model LIKE ? OR s.Category LIKE ? OR s.Brand LIKE ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + searchText + "%"); // Search ItemName
            pst.setString(2, "%" + searchText + "%"); // Search SerialNo
            pst.setString(3, "%" + searchText + "%"); // Search Model
            pst.setString(4, "%" + searchText + "%"); // Search Category
            pst.setString(5, "%" + searchText + "%"); // Search Brand
            rst = pst.executeQuery();

            while (rst.next()) {
                int checkingID = rst.getInt("CheckingID");
                int itemID = rst.getInt("ItemID");
                String serialNo = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String model = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int checkingQty = rst.getInt("CheckingQty");
                Date date = rst.getDate("Date");

                // Add a row to the Release table with all the information
                checkingModel.addRow(new Object[]{checkingID, itemID, serialNo, itemName, model, specification, category, brand, checkingQty, date});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error retrieving items from the database.");
        } finally {
            try {
                if (rst != null) {
                    rst.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_txtSearchCheckingActionPerformed

    private void btnViewUser7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewUser7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnViewUser7ActionPerformed

    private void txtSearchReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchReturnActionPerformed
        // TODO add your handling code here:
        String searchText = txtSearchReturn.getText().trim();
        DefaultTableModel returnModel = (DefaultTableModel) tblReturn.getModel();
        returnModel.setRowCount(0); // Clear existing rows

        try {
            String sql = "SELECT r.ReturnID, r.ItemID, s.SerialNo, s.ItemName, s.Model, s.Specification, s.Category, s.Brand, r.Qty AS returnQty, r.Date " +
                         "FROM Return r " +
                         "LEFT JOIN Stock s ON r.ItemID = s.ItemID " +
                         "WHERE s.ItemName LIKE ? OR s.SerialNo LIKE ? OR s.Model LIKE ? OR s.Category LIKE ? OR s.Brand LIKE ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + searchText + "%"); // Search ItemName
            pst.setString(2, "%" + searchText + "%"); // Search SerialNo
            pst.setString(3, "%" + searchText + "%"); // Search Model
            pst.setString(4, "%" + searchText + "%"); // Search Category
            pst.setString(5, "%" + searchText + "%"); // Search Brand
            rst = pst.executeQuery();

            while (rst.next()) {
                int returnID = rst.getInt("ReturnID");
                int itemID = rst.getInt("ItemID");
                String serialNo = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String model = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int returnQty = rst.getInt("ReturnQty");
                Date date = rst.getDate("Date");

                // Add a row to the Release table with all the information
                returnModel.addRow(new Object[]{returnID, itemID, serialNo, itemName, model, specification, category, brand, returnQty, date});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error retrieving items from the database.");
        } finally {
            try {
                if (rst != null) {
                    rst.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_txtSearchReturnActionPerformed

    private void btnViewUser8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewUser8ActionPerformed
        // TODO add your handling code here:
        String searchText = txtSearchRepair.getText().trim();
        DefaultTableModel repairModel = (DefaultTableModel) tblRepair.getModel();
        repairModel.setRowCount(0); // Clear existing rows

        try {
            String sql = "SELECT rep.RepairID, rep.ItemID, s.SerialNo, s.ItemName, s.Model, s.Specification, s.Category, s.Brand, rep.Qty AS repairQty, rep.Date " +
                         "FROM Repair rep " +
                         "LEFT JOIN Stock s ON rep.ItemID = s.ItemID " +
                         "WHERE s.ItemName LIKE ? OR s.SerialNo LIKE ? OR s.Model LIKE ? OR s.Category LIKE ? OR s.Brand LIKE ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + searchText + "%"); // Search ItemName
            pst.setString(2, "%" + searchText + "%"); // Search SerialNo
            pst.setString(3, "%" + searchText + "%"); // Search Model
            pst.setString(4, "%" + searchText + "%"); // Search Category
            pst.setString(5, "%" + searchText + "%"); // Search Brand
            rst = pst.executeQuery();

            while (rst.next()) {
                int repairID = rst.getInt("RepairID");
                int itemID = rst.getInt("ItemID");
                String serialNo = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String model = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int repairQty = rst.getInt("RepairQty");
                Date date = rst.getDate("Date");

                // Add a row to the Release table with all the information
                repairModel.addRow(new Object[]{repairID, itemID, serialNo, itemName, model, specification, category, brand, repairQty, date});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error retrieving items from the database.");
        } finally {
            try {
                if (rst != null) {
                    rst.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnViewUser8ActionPerformed

    private void txtSearchRepairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchRepairActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtSearchRepairActionPerformed

    private void btnViewUser9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewUser9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnViewUser9ActionPerformed

    private void txtSearchDisposalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchDisposalActionPerformed
        // TODO add your handling code here:
        String searchText = txtSearchDisposal.getText().trim();
        DefaultTableModel disposalModel = (DefaultTableModel) tblDisposal.getModel();
        disposalModel.setRowCount(0); // Clear existing rows

        try {
            String sql = "SELECT d.DisposalID, d.ItemID, s.SerialNo, s.ItemName, s.Model, s.Specification, s.Category, s.Brand, d.Qty AS DisposalQty, d.Date " +
                         "FROM Disposal d " +
                         "LEFT JOIN Stock s ON d.ItemID = s.ItemID " +
                         "WHERE s.ItemName LIKE ? OR s.SerialNo LIKE ? OR s.Model LIKE ? OR s.Category LIKE ? OR s.Brand LIKE ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + searchText + "%"); // Search ItemName
            pst.setString(2, "%" + searchText + "%"); // Search SerialNo
            pst.setString(3, "%" + searchText + "%"); // Search Model
            pst.setString(4, "%" + searchText + "%"); // Search Category
            pst.setString(5, "%" + searchText + "%"); // Search Brand
            rst = pst.executeQuery();

            while (rst.next()) {
                int disposalID = rst.getInt("DisposalID");
                int itemID = rst.getInt("ItemID");
                String serialNo = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String model = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int disposalQty = rst.getInt("DisposalQty");
                Date date = rst.getDate("Date");

                // Add a row to the Release table with all the information
                disposalModel.addRow(new Object[]{disposalID, itemID, serialNo, itemName, model, specification, category, brand, disposalQty, date});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error retrieving items from the database.");
        } finally {
            try {
                if (rst != null) {
                    rst.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_txtSearchDisposalActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(superadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(superadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(superadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(superadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new superadmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddU;
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCanceled;
    private javax.swing.JButton btnChecking;
    private javax.swing.JButton btnChecking1;
    private javax.swing.JButton btnClearUser;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeleteStock;
    private javax.swing.JButton btnDisposal;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPrint_Return;
    private javax.swing.JButton btnPrint_checking;
    private javax.swing.JButton btnPrint_disposal;
    private javax.swing.JButton btnPrint_release;
    private javax.swing.JButton btnPrint_repair;
    private javax.swing.JButton btnPrint_stock;
    private javax.swing.JButton btnRecords;
    private javax.swing.JButton btnRepair;
    private javax.swing.JButton btnReturn;
    private javax.swing.JButton btnSaveItem;
    private javax.swing.JButton btnSaveUser;
    private javax.swing.JButton btnStock;
    private javax.swing.JButton btnTransferChk;
    private javax.swing.JButton btnTransferRelease;
    private javax.swing.JButton btnTransferRpr;
    private javax.swing.JButton btnTransferRtn;
    private javax.swing.JButton btnTransferStock;
    private javax.swing.JButton btnUserDelete;
    private javax.swing.JButton btnUserEdit;
    private javax.swing.JButton btnViewUser2;
    private javax.swing.JButton btnViewUser3;
    private javax.swing.JButton btnViewUser4;
    private javax.swing.JButton btnViewUser5;
    private javax.swing.JButton btnViewUser6;
    private javax.swing.JButton btnViewUser7;
    private javax.swing.JButton btnViewUser8;
    private javax.swing.JButton btnViewUser9;
    private javax.swing.JComboBox<String> cbAccountType;
    private javax.swing.JComboBox<String> cbSCategory;
    private javax.swing.JCheckBox chkPass;
    private javax.swing.JPanel dashboard;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lblAdmin;
    private javax.swing.JLabel lblChecking;
    private javax.swing.JLabel lblDisposal;
    private javax.swing.JLabel lblRelease;
    private javax.swing.JLabel lblRepair;
    private javax.swing.JLabel lblReturn;
    private javax.swing.JLabel lblSuperAd;
    private javax.swing.JLabel lblTotalqty;
    private javax.swing.JLabel lblUser;
    private javax.swing.JTabbedPane tabPane;
    private javax.swing.JTabbedPane tabSuperadmin;
    private javax.swing.JTable tblAccounts;
    private javax.swing.JTable tblChecking;
    private javax.swing.JTable tblDataInfo;
    private javax.swing.JTable tblDisposal;
    private javax.swing.JTable tblRelease;
    private javax.swing.JTable tblRepair;
    private javax.swing.JTable tblReturn;
    private javax.swing.JTable tblStock;
    private javax.swing.JTextField txtBrand;
    private javax.swing.JButton txtClear;
    private javax.swing.JTextField txtEmployeeNum;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtModel;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtSearchChecking;
    private javax.swing.JTextField txtSearchDisposal;
    private javax.swing.JTextField txtSearchItem;
    private javax.swing.JTextField txtSearchRelease;
    private javax.swing.JTextField txtSearchRepair;
    private javax.swing.JTextField txtSearchReturn;
    private javax.swing.JTextField txtSearchStock;
    private javax.swing.JTextField txtSearchUser;
    private javax.swing.JTextField txtSerial;
    private javax.swing.JTextField txtSpecification;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
