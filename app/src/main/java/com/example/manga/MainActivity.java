package com.example.Manga;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.Manga.BaseDHelper;

public class MainActivity extends AppCompatActivity {

    // Déclaration des variables pour l'interface utilisateur et la base de données
    private BaseDHelper baseDHelper;
    private EditText titreInput, auteurInput, genreInput, chapitresInput;
    private Button ajouterMangaButton;

    @Override  // Ajoute l'annotation @Override ici pour signaler que tu réécris une méthode de la superclasse
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Lier le fichier XML à cette activité

        // Initialisation de la base de données
        baseDHelper = new BaseDHelper(this);

        // Lier les composants de l'interface utilisateur avec les variables Java
        titreInput = findViewById(R.id.titreInput);
        auteurInput = findViewById(R.id.auteurInput);
        genreInput = findViewById(R.id.genreInput);
        chapitresInput = findViewById(R.id.chapitresInput);
        ajouterMangaButton = findViewById(R.id.ajouterMangaButton);

        // Ajouter une action lorsque l'utilisateur clique sur le bouton "Ajouter Manga"
        ajouterMangaButton.setOnClickListener(new View.OnClickListener() {
            @Override  // L'annotation @Override est nécessaire ici aussi
            public void onClick(View v) {
                // Récupérer les données saisies par l'utilisateur
                String titre = titreInput.getText().toString();
                String auteur = auteurInput.getText().toString();
                String genre = genreInput.getText().toString();
                int chapitres;

                // Validation : Si chapitres n'est pas un entier valide, on l'affiche en Toast
                try {
                    chapitres = Integer.parseInt(chapitresInput.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Veuillez entrer un nombre valide pour les chapitres", Toast.LENGTH_SHORT).show();
                    return;  // Sortir de la méthode en cas d'erreur de saisie
                }

                // Ajouter le manga à la base de données
                boolean isInserted = baseDHelper.ajouterManga(titre, auteur, genre, chapitres);
                if (isInserted) {
                    Toast.makeText(MainActivity.this, "Manga ajouté avec succès", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Erreur lors de l'ajout", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
