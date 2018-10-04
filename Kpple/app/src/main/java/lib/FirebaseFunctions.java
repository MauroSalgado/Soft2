package lib;

import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import entities.Community;

public class FirebaseFunctions {

    private FirebaseAuth mAuth ;
    private FirebaseDatabase mDatabase;
    private FirebaseStorage mStorage;
    // Constructor
    public FirebaseFunctions() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mStorage = FirebaseStorage.getInstance();
    }

    // Éste método consigue el Id del usuario actual
    public String getIdUsuarioFire() {
        return mAuth.getCurrentUser().getUid();
    }

    // Éste método consigue la uri del usuario actual
    public Uri GetImageUrlFire() {
        Uri uri = mAuth.getCurrentUser().getPhotoUrl();
        return uri;
    }

    // Éste método consigue la referencia de POST en firebase enviándole el id de usuario por parámetro
    public DatabaseReference getReferencePostByUserId(String id) {
        String url = "Post/"+id;
        DatabaseReference refUser = mDatabase.getReference(url);
        return refUser;
    }

    // Éste método consigue la referencia de POST en firebase del usuario actual
    public DatabaseReference getReferencePostByUserId() {
        String url = "Post/"+this.getIdUsuarioFire();
        DatabaseReference refPostUser = mDatabase.getReference(url);
        return refPostUser;
    }

    // Éste método consigue la referencia de USER en firebase
    public DatabaseReference getReferenceUserCollection(){
        DatabaseReference refUserCollection = mDatabase.getReference("User");
        return refUserCollection;
    }

    // Éste método consigue la referencia de USER en firebase del usuario actual
    public DatabaseReference getReferenceUserById(){
        String url = "User/"+this.getIdUsuarioFire();
        DatabaseReference refUser = mDatabase.getReference(url);
        return refUser;
    }

    // Éste método consigue la referencia de USER en firebase enviándole el id de usuario por parámetro
    public DatabaseReference getReferenceUserById(String id){
        String url = "User/"+id;
        DatabaseReference refUser = mDatabase.getReference(url);
        return refUser;
    }

    // Éste método consigue la referencia de INTEREST en firebase
    public DatabaseReference getReferenceInterestCollection(){
        DatabaseReference refInterestCollection = mDatabase.getReference("Interest");
        return refInterestCollection;
    }

    // Éste método consigue la referencia de INTEREST en firebase enviándole el nombre de interest parámetro
    public DatabaseReference getReferenceInterestByName(String name){
        String url = "Interest/"+name;
        DatabaseReference refInterestByname= mDatabase.getReference(url);
        return refInterestByname;
    }

    // Éste método consigue la referencia de HOBBIES
    public DatabaseReference getReferenceHobbiesCollection(){
        DatabaseReference refHobbiesCollection = mDatabase.getReference("Hobbies");
        return refHobbiesCollection;
    }

    // Éste método consigue la referencia de HOBBIES en firebase enviándole el nombre de interest parámetro
    public DatabaseReference getReferenceHobbiesByName(String name){
        String url = "Hobbies/"+name;
        DatabaseReference refHobbiesByname= mDatabase.getReference(url);
        return refHobbiesByname;
    }

    public DatabaseReference getReferenceCommunity(){
        String url = "Community/";
        DatabaseReference refCommunity = mDatabase.getReference(url);
        return refCommunity;
    }

    public DatabaseReference getReferenceCommunityByID(String id){
        String url = "Community/"+id;
        DatabaseReference refCommunity = mDatabase.getReference(url);
        return refCommunity;
    }

    public StorageReference getStorageRefPostPicturesByUser(){
        StorageReference profImgref = mStorage.getReference("/" + this.getIdUsuarioFire()+ "/post");
        return profImgref;
    }

    public void InsertarComunity(Community community){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mguiaReference = database.getReference("Community");
        /*DatabaseReference comunityref = this.getReferenceCommunity();*/
        mguiaReference.push().setValue(community);
    }

}
