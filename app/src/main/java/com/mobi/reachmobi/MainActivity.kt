package com.mobi.reachmobi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.mobi.reachmobi.utils.BaseActivity
import com.mobi.reachmobi.utils.checkNull

class MainActivity : BaseActivity(), NavController.OnDestinationChangedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var currentDestination: NavDestination
    private lateinit var navController: NavController
    private val hostId = R.id.mainNav

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(hostId)
        val navMainFragment = supportFragmentManager.findFragmentById(hostId) as NavHostFragment?
        navController = navMainFragment?.navController!!
        appBarConfiguration = AppBarConfiguration(navController.graph)
    }

    /**
     * Callback for when the [currentDestination] or its arguments change.
     * This navigation may be to a destination that has not been seen before, or one that
     * was previously on the back stack. This method is called after navigation is complete,
     * but associated transitions may still be playing.
     *
     * @param controller the controller that navigated
     * @param destination the new destination
     * @param arguments the arguments passed to the destination
     */
    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        currentDestination = destination
        currentDestination.label?.let { setToolbar(it) }
    }

    private fun setToolbar(it: CharSequence) {
        supportActionBar?.apply {
            title = it.toString().checkNull()
            setDisplayHomeAsUpEnabled(false)
        }
    }

    override fun onStart() {
        super.onStart()
        navController.addOnDestinationChangedListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        if (!navController.navigateUp(appBarConfiguration)) {
            onBackPressed()
        }
        return navController.navigateUp(appBarConfiguration)
    }
}