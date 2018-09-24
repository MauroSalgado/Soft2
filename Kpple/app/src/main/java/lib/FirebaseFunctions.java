package lib;

import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseFunctions {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseDatabase mDatabase;

    // Constructor
    public FirebaseFunctions() {
        mUser = mAuth.getCurrentUser();
    }

    // Éste método consigue el Id del usuario actual
    public String GetIdUsuarioFire() {
        return mUser.getUid();
    }

    // Éste método consigue la uri del usuario actual
    public Uri GetImageUrlFire() {
        Uri uri = mUser.getPhotoUrl();
        return uri;
    }

    // Éste método consigue la referencia de POST en firebase enviándole el id de usuario por parámetro
    public DatabaseReference getReferencePostByUserId(String id) {
        String url = "Post/"+id;
        DatabaseReference refUser = mDatabase.getInstance().getReference(url);
        return refUser;
    }

    // Éste método consigue la referencia de POST en firebase del usuario actual
    public DatabaseReference getReferencePostByUserId() {
        String url = "Post/"+this.GetIdUsuarioFire();
        DatabaseReference refPostUser = mDatabase.getInstance().getReference(url);
        return refPostUser;
    }

    // Éste método consigue la referencia de USER en firebase
    public DatabaseReference getReferenceUserCollection(){
        DatabaseReference refUserCollection = mDatabase.getInstance().getReference("User");
        return refUserCollection;
    }

    // Éste método consigue la referencia de USER en firebase del usuario actual
    public DatabaseReference getReferenceUserById(){
        String url = "User/"+this.GetIdUsuarioFire();
        DatabaseReference refUser = mDatabase.getInstance().getReference(url);
        return refUser;
    }

    // Éste método consigue la referencia de USER en firebase enviándole el id de usuario por parámetro
    public DatabaseReference getReferenceUserById(String id){
        String url = "User/"+id;
        DatabaseReference refUser = mDatabase.getInstance().getReference(url);
        return refUser;
    }

    // Éste método consigue la referencia de INTEREST en firebase
    public DatabaseReference getReferenceInterestCollection(){
        DatabaseReference refInterestCollection = mDatabase.getInstance().getReference("Interest");
        return refInterestCollection;
    }

    // Éste método consigue la referencia de INTEREST en firebase enviándole el nombre de interest parámetro
    public DatabaseReference getReferenceInterestByName(String name){
        String url = "Interest/"+name;
        DatabaseReference refInterestByname= mDatabase.getInstance().getReference(url);
        return refInterestByname;
    }

    // Éste método consigue la referencia de HOBBIES
    public DatabaseReference getReferenceHobbiesCollection(){
        DatabaseReference refHobbiesCollection = mDatabase.getInstance().getReference("Hobbies");
        return refHobbiesCollection;
    }

    // Éste método consigue la referencia de HOBBIES en firebase enviándole el nombre de interest parámetro
    public DatabaseReference getReferenceHobbiesByName(String name){
        String url = "Hobbies/"+name;
        DatabaseReference refHobbiesByname= mDatabase.getInstance().getReference(url);
        return refHobbiesByname;
    }


}
