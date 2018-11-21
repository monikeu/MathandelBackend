package mathandel.backend.model.client;

public class ResultTO {

    private Long id;
    private UserTO sender;
    private UserTO receiver;
    private ItemTO item;
    private RateTO rate;

    public Long getId() {
        return id;
    }

    public ResultTO setId(Long id) {
        this.id = id;
        return this;
    }

    public UserTO getSender() {
        return sender;
    }

    public ResultTO setSender(UserTO sender) {
        this.sender = sender;
        return this;
    }

    public UserTO getReceiver() {
        return receiver;
    }

    public ResultTO setReceiver(UserTO receiver) {
        this.receiver = receiver;
        return this;
    }

    public ItemTO getItem() {
        return item;
    }

    public ResultTO setItem(ItemTO item) {
        this.item = item;
        return this;
    }

    public RateTO getRate() {
        return rate;
    }

    public ResultTO setRate(RateTO rate) {
        this.rate = rate;
        return this;
    }
}
