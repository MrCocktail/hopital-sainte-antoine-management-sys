package com.hsa.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class NavigationBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String vueActive = "dashboard";
    private String menuOuvert;

    public void changerVue(String vue) {
        this.vueActive = vue;
        if (vue != null && vue.startsWith("patients-")) {
            this.menuOuvert = "patients";
        }
    }

    public void toggleMenu(String menu) {
        if (menu != null && menu.equals(menuOuvert)) {
            menuOuvert = null;
        } else {
            menuOuvert = menu;
        }
    }

    public String getVueActive() {
        return vueActive;
    }

    public void setVueActive(String vueActive) {
        this.vueActive = vueActive;
    }

    public String getMenuOuvert() {
        return menuOuvert;
    }

    public void setMenuOuvert(String menuOuvert) {
        this.menuOuvert = menuOuvert;
    }

    public String getPageCourante() {
        switch (vueActive) {
            case "dashboard":
                return "/dashboard.xhtml";
            case "patients-all":
                return "/patients/all.xhtml";
            case "patients-add":
                return "/patients/add.xhtml";
            case "patients-edit":
                return "/patients/edit.xhtml";
            case "medecins-all":
                return "/medecins/all.xhtml";
            case "medecins-add":
                return "/medecins/add.xhtml";
            case "medecins-edit":
                return "/medecins/edit.xhtml";
            case "employees-all":
                return "/employees/all.xhtml";
            case "employees-add":
                return "/employees/add.xhtml";
            case "employees-edit":
                return "/employees/edit.xhtml";
            case "payroll":
                return null;
            default:
                return "/dashboard.xhtml";
        }
    }
}
