package com.example.chefskiss2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class Search extends AppCompatActivity{
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private MaterialToolbar toolbar;

    public Search() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        Account loggedInAcct = (Account) getIntent().getSerializableExtra("account");

        toolbar = (MaterialToolbar) findViewById(R.id.topAppbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                drawerLayout.openDrawer(GravityCompat.START);
                TextView text = findViewById(R.id.nav_menu_name);
                text.setText(loggedInAcct.getUsername());

            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                //drawerLayout.closeDrawer(GravityCompat.START);
                switch (id)
                {

                    case R.id.nav_home:
                        Toast.makeText(Search.this, "Home is Clicked",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Search.this, Homepage.class);
                        intent.putExtra("account", loggedInAcct);
                        startActivity(intent);
                        break;
                    case R.id.nav_search:
                        Toast.makeText(Search.this, "Home is Clicked",Toast.LENGTH_SHORT).show();
                        Intent intent5 = new Intent(Search.this, Search.class);
                        intent5.putExtra("account", loggedInAcct);
                        startActivity(intent5);
                        break;
                    case R.id.nav_saved_recipes:
                        Toast.makeText(Search.this, "Saved Recipes is Clicked",Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(Search.this, SavedRecipes.class);
                        intent1.putExtra("account", loggedInAcct);
                        startActivity(intent1);
                        break;
                    case R.id.nav_create_recipes:
                        Intent intent2 = new Intent(Search.this, CreateRecipe.class);
                        intent2.putExtra("account", loggedInAcct);
                        startActivity(intent2);
                        Toast.makeText(Search.this, "Create Recipes is Clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_account:
                        Intent intent3 = new Intent(Search.this, AccountInfoPage.class);
                        intent3.putExtra("account", loggedInAcct);
                        startActivity(intent3);
                        Toast.makeText(Search.this, "Account is Clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_log_out:
                        Intent intent4 = new Intent(Search.this, MainActivity.class);
                        startActivity(intent4);
                        finishAffinity();
                        break;
                    default:
                        return true;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

}
