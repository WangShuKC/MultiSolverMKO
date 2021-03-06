package com.deu.multisolvermko.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.deu.multisolvermko.R;
import com.deu.multisolvermko.homepage.HomepageActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {

    EditText emailTextSignIn,passwordTextSignIn;
    Button signInButton;
    ImageView imageView;
    public FirebaseAuth firebaseAuth;
    TextView signUp, areYouNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        firebaseAuth = FirebaseAuth.getInstance();
        emailTextSignIn = findViewById(R.id.emailTextSignIn);
        passwordTextSignIn = findViewById(R.id.passwordSignIn);
        signUp = findViewById(R.id.signUpText);
        signInButton = findViewById(R.id.signInButton);
        areYouNew = findViewById(R.id.areYouNewText);
        imageView = findViewById(R.id.imageView);

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null){
            Intent intent = new Intent(getApplicationContext(), HomepageActivity.class);
            startActivity(intent);
            finish();
        }

        signUp.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
            startActivity(intent);
        });

        animationStart();
        animationStart2();
        animationStart3();
        animationStart4();
    }

    public void signInClick(View view){

        if (emailTextSignIn.getText().toString().equals("") && passwordTextSignIn.getText().toString().equals("")){
            showEmailPasswordToast();
            passwordTextSignIn.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.round_border_red,null));
            emailTextSignIn.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.round_border_red,null));
            onShakeBoth();
        }else if (passwordTextSignIn.getText().toString().equals("")){
            showPasswordToast();
            passwordTextSignIn.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.round_border_red,null));
            emailTextSignIn.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.round_border,null));
            onShakePassword();
        }else if (emailTextSignIn.getText().toString().equals("")){
            showEmailToast();
            emailTextSignIn.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.round_border_red,null));
            passwordTextSignIn.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.round_border,null));
            onShakeEmail();
        }else{
            String email = emailTextSignIn.getText().toString();
            String password = passwordTextSignIn.getText().toString();

            firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(authResult -> {
                passwordTextSignIn.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.round_border,null));
                emailTextSignIn.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.round_border,null));
                Intent intent = new Intent(getApplicationContext(), HomepageActivity.class);
                startActivity(intent);
                finish();
            }).addOnFailureListener(e -> {
                showFirebaseError();
                passwordTextSignIn.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.round_border_red,null));
                emailTextSignIn.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.round_border_red,null));
                onShakeBoth();
                onShakeSignText();
            });
        }
    }

    public void animationStart(){

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim);
        signUp.startAnimation(animation);
        emailTextSignIn.startAnimation(animation);
        passwordTextSignIn.startAnimation(animation);
        signInButton.startAnimation(animation);
    }
    public void animationStart2(){

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim2);
        areYouNew.startAnimation(animation);
    }
    public void animationStart3(){

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim3);
        signUp.startAnimation(animation);
    }
    public void animationStart4(){

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim5);
        imageView.startAnimation(animation);
    }
    public void onShakeEmail() {
        Animation shake;
        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
        emailTextSignIn.startAnimation(shake);
    }
    public void onShakePassword() {
        Animation shake;
        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
        passwordTextSignIn.startAnimation(shake);
    }
    public void onShakeBoth() {
        Animation shake;
        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
        emailTextSignIn.startAnimation(shake);
        passwordTextSignIn.startAnimation(shake);
    }
    public void onShakeSignText(){
        Animation shake;
        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
        signUp.startAnimation(shake);
    }
    public void showEmailToast(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout_email, findViewById(R.id.toast_root));
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM,0,50);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
    public void showPasswordToast(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout_password, findViewById(R.id.toast_root));
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM,0,50);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
    public void showEmailPasswordToast(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout_email_password, findViewById(R.id.toast_root));
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM,0,50);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
    public void showFirebaseError(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout_firebase_error, findViewById(R.id.toast_root));
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM,0,50);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}