package model;

public class User {

	private String id;
    private String name;
    private String email;
    
    /**
     * @param id
     * @param name
     * @param email
     */
    public User(String id, String name, String email) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
    }
    
    

    public User(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}



	/**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
