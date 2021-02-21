import javax.swing.*;

public class Menu extends JFrame{

    private JPanel PanelMain;
    private JButton manageCustomersButton;
    private JButton reportsButton;
    private JButton restoreDatabaseButton;
    private JButton createBackupButton;
    private JButton updateAccountButton;
    private JButton createAccountButton;
    private JButton viewActiveJobsButton;
    private JButton createCustomerButton;
    private JButton viewAllJobsButton;
    private JButton updateJobStatusButton;
    private JButton takePaymentButton;
    private JButton placeJobButton;
    private JButton logoutButton;


    public static void main(String[] args){
        JFrame frame = new JFrame("Bapers System");
        frame.setContentPane(new Menu().PanelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
