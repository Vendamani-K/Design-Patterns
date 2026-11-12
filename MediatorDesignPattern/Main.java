package MediatorDesignPattern;


import java.util.ArrayList;
import java.util.List;

// 1. MEDIATOR INTERFACE
interface ChatMediator {
    void sendMessage(String message, User sender);
    void addUser(User user);
}

// 2. COLLEAGUE INTERFACE
interface ChatUser {
    void send(String message);
    void receive(String message, String senderName);
}

// 3. CONCRETE MEDIATOR
class ChatRoom implements ChatMediator {

    private List<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void sendMessage(String message, User sender) {

        System.out.println(
            "\n" + sender.getName() + " sends: " + message
        );

        // Broadcast message to all other users
        for (User user : users) {

            if (user != sender) {
                user.receive(message, sender.getName());
            }
        }
    }
}

// 4. CONCRETE COLLEAGUE
class User implements ChatUser {

    private String name;
    private ChatMediator mediator;

    public User(String name, ChatMediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public String getName() {
        return name;
    }

    @Override
    public void send(String message) {
        mediator.sendMessage(message, this);
    }

    @Override
    public void receive(String message, String senderName) {

        System.out.println(
            name + " received from " + senderName + ": " + message
        );
    }
}

// 5. MAIN CLASS
public class Main {

    public static void main(String[] args) {

        // Create chat room (Mediator)
        ChatMediator chatRoom = new ChatRoom();

        // Create users
        User user1 = new User("Alice", chatRoom);
        User user2 = new User("Bob", chatRoom);
        User user3 = new User("Charlie", chatRoom);
        User user4 = new User("David", chatRoom);

        // Add users to chat room
        chatRoom.addUser(user1);
        chatRoom.addUser(user2);
        chatRoom.addUser(user3);
        chatRoom.addUser(user4);

        // Users send messages
        user1.send("Hello everyone!");

        user2.send("Hi Alice!");

        user3.send("Good morning everyone!");
    }
}

