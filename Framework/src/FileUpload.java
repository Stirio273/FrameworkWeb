package etu1832.framework;

public class FileUpload {
    String path;
    String name;
    byte[] donnees;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getDonnees() {
        return donnees;
    }

    public void setDonnees(byte[] donnees) {
        this.donnees = donnees;
    }

    public FileUpload() {
    }

    public FileUpload(String name, byte[] donnees) {
        this.name = name;
        this.donnees = donnees;
    }

    public FileUpload(String path, String name, byte[] donnees) {
        this.path = path;
        this.name = name;
        this.donnees = donnees;
    }
}
