package fvtc.edu.browserdemo;

import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import fvtc.edu.browserdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private WebView webView;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        navView.setOnItemSelectedListener(onItemSelectedListener);
        webView = findViewById(R.id.webView);

    }

    private NavigationBarView.OnItemSelectedListener onItemSelectedListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();
                if(id == R.id.navigation_dashboard) {
                    webView.setWebViewClient(new WebViewClient());
                    webView.loadUrl("https://foote.azurewebsites.net/Home");
                }
                else if(id == R.id.navigation_notifications){

                    String html = "<html><body style='-color: cornflowerblue;'>";
                    html += "<table border='1'>";
                    html += "<tr><td>Fred</td></tr>";
                    html += "<tr><td>Barney</td></tr>";
                    html += "<tr><td>Wilma</td></tr>";
                    html += "<tr><td>Betty</td></tr>";
                    html += "</table></body></html>";

                    webView.loadData(html, "text/html", "UTF-8");

            }
            return true;
        }
    };
}