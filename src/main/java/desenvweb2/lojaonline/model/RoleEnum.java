package desenvweb2.lojaonline.model;

public enum RoleEnum {
    ADMIN("ADMIN"),
    USER("USER"),
    LOJA("LOJA");

    private String role;

    RoleEnum(String role){
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }
}
