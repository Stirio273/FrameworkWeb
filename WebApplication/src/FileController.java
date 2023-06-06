package controller;

import etu1832.framework.FileUpload;
import etu1832.framework.ModelView;
import etu1832.framework.annotation.RequestMapping;

public class FileController {
    FileUpload fichier;

    public FileUpload getFichier() {
        return fichier;
    }

    public void setFichier(FileUpload fichier) {
        this.fichier = fichier;
    }

    @RequestMapping(path = "/file-add")
    public ModelView getForm() {
        return new ModelView("file.jsp");
    }

    @RequestMapping(path = "/file-upload")
    public ModelView setFile() {
        // System.out.println(this.fichier.getName());
        return this.getForm();
    }
}
