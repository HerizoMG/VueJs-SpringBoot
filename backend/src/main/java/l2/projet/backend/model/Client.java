package l2.projet.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "numCompte")
    private Long numCompte;
    @Column(name = "nom", nullable = false, length = 200)
    private String nom;
    @Column(name = "solde")
    private Long solde;

    public Client(String nom, Long solde) {
        this.nom = nom;
        this.solde = solde;
    }

    public Client() {

    }

    public Long getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(Long numCompte) {
        this.numCompte = numCompte;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getSolde() {
        return solde;
    }

    public void setSolde(Long balance) {
        this.solde = balance;
    }
}
