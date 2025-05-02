/*
*/
import java.util.*;
 import java.time.LocalDateTime;
 import java.time.format.DateTimeFormatter;
 
 public class StockTradingPlatform {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        Market market = new Market();
        Portfolio portfolio = new Portfolio();
        TransactionHistory history = new TransactionHistory();
        System.out.println("===============================================");
         System.out.println("|      JAVA STOCK TRADING SIMULATOR            |");
         System.out.println("|          Powered by Console Market           |");
         System.out.println("|    Version 1.0   |  By sabinaya mahapatra    |");
         System.out.println("===============================================\n");
         while (true) {
            System.out.println("\n======== MAIN MENU ========");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stocks");
            System.out.println("3. Sell Stocks");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Transaction History");
            System.out.println("6. Reset Portfolio");
            System.out.println("7. Clear Transaction History");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    market.displayMarket();
                    break;
                case 2:
                    System.out.print("Enter stock symbol to buy: ");
                    String buySymbol = scanner.nextLine().toUpperCase();
                    if (market.stockExists(buySymbol)) {
                        System.out.print("Enter quantity: ");
                        int qty = scanner.nextInt();
                        scanner.nextLine(); // clear buffer
                        Stock stock = market.getStock(buySymbol);
                        double total = stock.getPrice() * qty;
                        portfolio.addStock(buySymbol, qty);
                        history.addTransaction(new Transaction(TransactionType.BUY, buySymbol, qty, stock.getPrice()));
                        System.out.println("✅ Bought " + qty + " shares of " + buySymbol + " for ₹" + total);
                    } else {
                        System.out.println("❌ Stock not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter stock symbol to sell: ");
                    String sellSymbol = scanner.nextLine().toUpperCase();
                    if (portfolio.ownsStock(sellSymbol)) {
                        System.out.print("Enter quantity: ");
                        int qty = scanner.nextInt();
                        scanner.nextLine(); // clear buffer
                        Stock stock = market.getStock(sellSymbol);
                        double total = stock.getPrice() * qty;
                        portfolio.removeStock(sellSymbol, qty);
                        history.addTransaction(new Transaction(TransactionType.SELL, sellSymbol, qty, stock.getPrice()));
                        System.out.println("✅ Sold " + qty + " shares of " + sellSymbol + " for ₹" + total);
                    } else {
                        System.out.println("❌ You don't own this stock.");
                    }
                    break;
                case 4:
                    portfolio.displayPortfolio();
                    break;
                case 5:
                    history.displayHistory();
                    break;
                case 6:
                    portfolio.resetPortfolio();
                    break;
                case 7:
                    history.clearHistory();
                    break;
                case 8:
                    System.out.println("👋 Exiting... Thank you!");
                    return;
                default:
                    System.out.println(" Invalid choice.");
            }
        }
    }
}

class Market {
    private final Map<String, Stock> stocks = new HashMap<>();

    public Market() {
        addStock(new Stock("AAPL", "Apple Inc.", 175.00));
        addStock(new Stock("GOOG", "Alphabet Inc.", 2800.00));
        addStock(new Stock("TSLA", "Tesla Inc.", 750.00));
        addStock(new Stock("AMZN", "Amazon.com", 3300.00));
        addStock(new Stock("MSFT", "Microsoft Corp.", 310.00));
    }

    public void addStock(Stock stock) {
        stocks.put(stock.getSymbol(), stock);
    }

    public boolean stockExists(String symbol) {
        return stocks.containsKey(symbol.toUpperCase());
    }

    public Stock getStock(String symbol) {
        return stocks.get(symbol.toUpperCase());
    }

    public void displayMarket() {
        System.out.println("\n--- Market Prices ---");
        for (Stock stock : stocks.values()) {
            System.out.println(stock);
        }
    }
}

class Portfolio {
    private final Map<String, Integer> holdings = new HashMap<>();

    public void addStock(String symbol, int qty) {
        holdings.put(symbol, holdings.getOrDefault(symbol, 0) + qty);
    }

    public void removeStock(String symbol, int qty) {
        if (!ownsStock(symbol)) return;
        int currentQty = holdings.get(symbol);
        if (qty >= currentQty) {
            holdings.remove(symbol);
        } else {
            holdings.put(symbol, currentQty - qty);
        }
    }

    public boolean ownsStock(String symbol) {
        return holdings.containsKey(symbol) && holdings.get(symbol) > 0;
    }

    public void displayPortfolio() {
        if (holdings.isEmpty()) {
            System.out.println("Portfolio is empty.");
        } else {
            System.out.println("Your Portfolio:");
            for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
                System.out.println("- " + entry.getKey() + ": " + entry.getValue() + " shares");
            }
        }
    }

    public void resetPortfolio() {
        holdings.clear();
        System.out.println("Portfolio reset.");
    }
}

class Stock {
    private final String symbol;
    private final String name;
    private double price;

    public Stock(String symbol, String name, double price) {
        this.symbol = symbol.toUpperCase();
        this.name = name;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - ₹%.2f", symbol, name, price);
    }
}

class Transaction {
    private final TransactionType type;
    private final String stockSymbol;
    private final int quantity;
    private final double pricePerShare;
    private final String timestamp;

    public Transaction(TransactionType type, String symbol, int quantity, double price) {
        this.type = type;
        this.stockSymbol = symbol.toUpperCase();
        this.quantity = quantity;
        this.pricePerShare = price;
        this.timestamp = generateTimestamp();
    }

    private String generateTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public String toString() {
        return String.format("[%s] %s %d shares of %s @ ₹%.2f", timestamp, type, quantity, stockSymbol, pricePerShare);
    }
}

class TransactionHistory {
    private final List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void displayHistory() {
        System.out.println("\n Transaction History:");
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (Transaction tx : transactions) {
                System.out.println(tx);
            }
        }
    }

    public void clearHistory() {
        transactions.clear();
        System.out.println("Transaction history cleared.");
    }
}

enum TransactionType {
    BUY, SELL
}