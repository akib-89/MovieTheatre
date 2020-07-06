package sample.data;

public class Message {
    private final String from;
    private final String title;
    private final String message;
    private boolean seen;

    public Message(String from, String title, String message) {
        this.from = from;
        this.title = title;
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
