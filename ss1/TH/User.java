package ss1.TH;

public class User {
    private String username;
    private double balance;
    private boolean isActive;
    private String area;

    public User(String username, double balance, boolean isActive, String area) {
        this.username = username;
        this.balance = balance;
        this.isActive = isActive;
        this.area = area;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getUsername() {
        return username;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
