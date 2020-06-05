package ua.com.alevel.nix.experienceusingclass.data;

public abstract class AbstractData<T> {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
