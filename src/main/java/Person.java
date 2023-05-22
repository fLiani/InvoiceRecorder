public class Person
{
    private String date;
    private int invNo;
    private String nameDetails;
    private double amount;
    private double total;
    private double vat;
    private double net;
    public Person(String date, int invNo, String nameDetails, double amount, double total, double vat, double net)
    {
        this.date = date;
        this.invNo = invNo;
        this.nameDetails = nameDetails;
        this.amount = amount;
        this.total = total;
        this.vat = vat;
        this.net = net;
    }

    public String getDate() {
        return date;
    }

    public int getInvNo() {
        return invNo;
    }

    public String getNameDetails() {
        return nameDetails;
    }

    public double getAmount() {
        return amount;
    }

    public double getTotal() {
        return total;
    }

    public double getVat() {
        return vat;
    }

    public double getNet() {
        return net;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setInvNo(int invNo) {
        this.invNo = invNo;
    }

    public void setNameDetails(String nameDetails) {
        this.nameDetails = nameDetails;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public void setNet(double net) {
        this.net = net;
    }
}
