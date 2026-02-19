package PackageService;

public class AIOrder {
    
    private String[][] order_specifications = new String[4][3]; // A.I. order specifications
    private double order_cost; // Order cost
    private int development_time; // Order development time

    public AIOrder(String[][] order_specifications, double order_cost, int development_time) {

        this.order_specifications = order_specifications;
        this.order_cost = order_cost;
        this.development_time = development_time;
    }

    public String[][] GetOrderSpecifications() {

        return (this.order_specifications);
    }

    public double GetOrderCost() {

        return (this.order_cost);
    }

    public int GetDevelopmentTime() {

        return (this.development_time);
    }
}
