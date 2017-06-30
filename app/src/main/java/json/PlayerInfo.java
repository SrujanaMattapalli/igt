package json;

public class PlayerInfo {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    String balance;

    public String getAvatarLink() {
        return avatarLink;
    }

    public void setAvatarLink(String avatarLink) {
        this.avatarLink = avatarLink;
    }

    String avatarLink;

    public String getLastLogindate() {
        return lastLogindate;
    }

    public void setLastLogindate(String lastLogindate) {
        this.lastLogindate = lastLogindate;
    }

    String lastLogindate;
}
