package se.chalmers.tda367_4.app;

public class ApplicationImage {
    private String path;

    public ApplicationImage(String path){
        String tmp = "res/";
        this.path = tmp + path;
    }
    public String getPath(){
        return path;
    }
}
