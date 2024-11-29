package com.example.projetmobile.model;

public class User {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String username;
    private String password;
    private String telephone;
    private String cin;
    private String poste;
    private String adresseComplet;
    private String dateNaissance; // Changer LocalDate en String
    private String dateDebutTravail; // Changer LocalDate en String
    private String image; // Changer byte[] en String

    // Constructeurs, getters et setters
    public User() {
    }

    public User(Long id, String nom, String prenom, String email, String username, String password, String telephone, String cin, String poste, String adresseComplet, String dateNaissance, String dateDebutTravail, String image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.username = username;
        this.password = password;
        this.telephone = telephone;
        this.cin = cin;
        this.poste = poste;
        this.adresseComplet = adresseComplet;
        this.dateNaissance = dateNaissance;
        this.dateDebutTravail = dateDebutTravail;
        this.image = image;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getAdresseComplet() {
        return adresseComplet;
    }

    public void setAdresseComplet(String adresseComplet) {
        this.adresseComplet = adresseComplet;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getDateDebutTravail() {
        return dateDebutTravail;
    }

    public void setDateDebutTravail(String dateDebutTravail) {
        this.dateDebutTravail = dateDebutTravail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
