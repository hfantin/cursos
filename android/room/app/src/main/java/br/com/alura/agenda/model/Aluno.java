package br.com.alura.agenda.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(tableName = "aluno")
public class Aluno implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String nome;
//    private String sobrenome;
    private String telefone;
    private String email;

    private LocalDateTime momentoCadastro = LocalDateTime.now();

    public Aluno() {

    }

    public LocalDateTime getMomentoCadastro() {
        return momentoCadastro;
    }

    public void setMomentoCadastro(LocalDateTime momentoCadastro) {
        this.momentoCadastro = momentoCadastro;
    }

    @Ignore
    public Aluno(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    @NonNull
    @Override
    public String toString() {
        return nome + " - " + telefone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean temIdValido() {
        return id > 0;
    }
//
//    public String getSobrenome() {
//        return sobrenome;
//    }
//
//    public void setSobrenome(String sobrenome) {
//        this.sobrenome = sobrenome;
//    }

//    public String getNomeCompleto() {
//        return nome + " " + sobrenome;
//    }

//    public String dataFormatada() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        return formatter.format(momentoCadastro);
//    }
}
