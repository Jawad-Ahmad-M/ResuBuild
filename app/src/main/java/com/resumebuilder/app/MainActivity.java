package com.resumebuilder.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.resumebuilder.app.databinding.ActivityMainBinding;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final Set<Integer> visitedPages = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MainPageAdapter pagerAdapter = new MainPageAdapter(this);
        binding.viewPager.setAdapter(pagerAdapter);

        // Start with minimal offscreenPageLimit
        binding.viewPager.setOffscreenPageLimit(1);

        // Track visited pages and dynamically increase offscreenPageLimit
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                if (!visitedPages.contains(position)) {
                    visitedPages.add(position);
                    binding.viewPager.setOffscreenPageLimit(visitedPages.size());
                }

//                 Sync BottomNavigationView selection
                switch (position) {
                    case 0:
                        binding.navView.setSelectedItemId(R.id.nav_details);
                        break;
                    case 1:
                        binding.navView.setSelectedItemId(R.id.nav_pdf_view);
                        break;
                    case 2:
                        binding.navView.setSelectedItemId(R.id.nav_ats);
                        break;
                    case 3:
                        binding.navView.setSelectedItemId(R.id.nav_profile);
                        break;
                }
            }
        });

        setupBottomNav();

        // Restore last selected page if any
        if (savedInstanceState != null) {
            int lastPage = savedInstanceState.getInt("last_page", 0);
            binding.viewPager.setCurrentItem(lastPage, false);
            visitedPages.add(lastPage);
            binding.viewPager.setOffscreenPageLimit(visitedPages.size());
        }
    }

    private void setupBottomNav() {
        binding.navView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_details) {
                binding.viewPager.setCurrentItem(0, false);
                return true;
            }
            else if (itemId == R.id.nav_pdf_view){
                binding.viewPager.setCurrentItem(1, false);
                return true;
            }
            else if (itemId == R.id.nav_ats){
                binding.viewPager.setCurrentItem(2, false);
                return true;
            }
            else if (itemId == R.id.nav_profile){
                binding.viewPager.setCurrentItem(3, false);
                return true;
            }
            return false;
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("last_page", binding.viewPager.getCurrentItem());
        super.onSaveInstanceState(outState);
    }
}
