package entities;

public final class Bed {
    private String type;
    public Bed(String type){
        this.type = type;
    }
    @Override
    public String toString(){
        return this.type;
    }

}
