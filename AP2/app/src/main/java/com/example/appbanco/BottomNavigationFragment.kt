package com.example.appbanco // Replace with your actual package name

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appbanco.HomeActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
// Import your activities
import com.example.appbanco.InvestirActivity
import com.example.appbanco.R // Your app's R file

class BottomNavigationFragment : Fragment() {

    private lateinit var bottomNavigationView: BottomNavigationView // Declare it here

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_navigation, container, false)
        bottomNavigationView = view.findViewById(R.id.bottom_navigation_view) // Initialize here

        // Initial setup of selected item (still useful for first load)
        updateSelectedItem() // Call the new helper method

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    if (activity !is HomeActivity) {
                        val intent = Intent(activity, HomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                        startActivity(intent)
                        activity?.overridePendingTransition(0, 0)
                        // It's good practice to call finish() on the current activity
                        // if you want to remove it from the back stack when navigating via bottom nav.
                        // However, this depends on your desired navigation flow.
                        // activity?.finish()
                    }
                    true
                }
                R.id.nav_invest -> {
                    if (activity !is InvestirActivity) {
                        val intent = Intent(activity, InvestirActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                        startActivity(intent)
                        activity?.overridePendingTransition(0, 0)
                        // activity?.finish()
                    }
                    true
                }
                else -> false
            }
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        // Update the selected item when the fragment resumes
        // This handles cases like pressing the back button
        updateSelectedItem()
    }

    private fun updateSelectedItem() {
        // Ensure bottomNavigationView has been initialized
        if (!::bottomNavigationView.isInitialized) return

        when (activity) {
            is HomeActivity -> bottomNavigationView.selectedItemId = R.id.nav_home
            is InvestirActivity -> bottomNavigationView.selectedItemId = R.id.nav_invest
            // Add other cases here if you have more activities with this bottom nav
        }
    }
}