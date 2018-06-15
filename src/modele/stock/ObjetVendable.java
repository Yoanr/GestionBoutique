package modele.stock;

public abstract class ObjetVendable {
    protected String nom;
    protected String reference;
    protected String marque;

    //prix public
    protected double prix;

    public ObjetVendable(String reference) {
        this.nom = nom;
        this.reference = reference;

    }

    public String getNom() {
        return nom;
    }

    public String getReference() {
        return reference;
    }

    public double getPrix() {
        return prix;
    }

    public String getMarque() {
        return marque;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ObjetVendable)) return false;

        ObjetVendable that = (ObjetVendable) o;

        return getReference().equals(that.getReference());
    }

    @Override
    public int hashCode() {
        return getReference().hashCode();
    }
}


