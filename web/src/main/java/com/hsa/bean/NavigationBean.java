@Named
@SessionScoped
public class NavigationBean implements Serializable {

    private String vueActive = "dashboard";

    public void changerVue(String vue) {
        this.vueActive = vue;
    }

    public String getVueActive() {
        return vueActive;
    }

    public void setVueActive(String vueActive) {
        this.vueActive = vueActive;
    }

    public String getPageCourante() {

    switch(vueActive) {

        case "dashboard":
            return "/dashboard.xhtml";

        case "patients-all":
            return "/patients/all.xhtml";

        case "patients-add":
            return "/patients/add.xhtml";

        case "patients-edit":
            return "/patients/edit.xhtml";

        default:
            return "/dashboard.xhtml";
    }
}
}