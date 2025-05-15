import java.util.ArrayList;
import java.util.Scanner;

class Room {
    int roomNumber;
    String category; // Standard, Deluxe, Suite
    double pricePerNight;
    boolean isBooked;

    Room(int roomNumber, String category, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.pricePerNight = pricePerNight;
        this.isBooked = false;
    }
}

class Booking {
    String customerName;
    Room room;
    int nights;
    double totalPayment;

    Booking(String customerName, Room room, int nights) {
        this.customerName = customerName;
        this.room = room;
        this.nights = nights;
        this.totalPayment = room.pricePerNight * nights;
    }
}


public class main {

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        initializeRooms();
        boolean running = true;

        while (running) {
            System.out.println("\n--- Welcome to Hotel Reservation System ---");
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Bookings");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    searchRooms();
                    break;
                case 2:
                    makeReservation();
                    break;
                case 3:
                    viewBookings();
                    break;
                case 4:
                    running = false;
                    System.out.println("Thank you for using the Hotel Reservation System!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        
    }

    static void initializeRooms() {
        rooms.add(new Room(101, "Standard", 100));
        rooms.add(new Room(102, "Standard", 100));
        rooms.add(new Room(201, "Deluxe", 150));
        rooms.add(new Room(202, "Deluxe", 150));
        rooms.add(new Room(301, "Suite", 250));
    }

    static void searchRooms() {
        System.out.println("\nAvailable Rooms:");
        boolean found = false;
        for (Room room : rooms) {
            if (!room.isBooked) {
                System.out.println("Room " + room.roomNumber + " - " + room.category + " - $" + room.pricePerNight + " per night");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No rooms available.");
        }
    }

    static void makeReservation() {
        searchRooms();
        System.out.print("\nEnter room number to book: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); 

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && !room.isBooked) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Invalid or unavailable room selected.");
            return;
        }

        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter number of nights: ");
        int nights = scanner.nextInt();
        scanner.nextLine();

        double totalCost = selectedRoom.pricePerNight * nights;
        System.out.printf("Total cost: $%.2f\n", totalCost);
        System.out.print("Proceed to payment? (yes/no): ");
        String proceed = scanner.nextLine();

        if (proceed.equalsIgnoreCase("yes")) {
            selectedRoom.isBooked = true;
            Booking newBooking = new Booking(customerName, selectedRoom, nights);
            bookings.add(newBooking);
            System.out.println("Booking successful!");
        } 
        else {
            System.out.println("Booking cancelled.");
        }
    }

    static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
            return;
        }

        System.out.println("\nAll Bookings:");
        for (Booking booking : bookings) {
            System.out.println("Customer: " + booking.customerName +
                    " | Room: " + booking.room.roomNumber +
                    " (" + booking.room.category + ")" +
                    " | Nights: " + booking.nights +
                    " | Total Paid: $" + booking.totalPayment);
        }

    }

}