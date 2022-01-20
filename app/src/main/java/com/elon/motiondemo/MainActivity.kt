package com.elon.motiondemo

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.transformation.TransformationChildCard
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.google.android.material.transition.platform.MaterialElevationScale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        // Enable Activity Transitions. Optionally enable Activity transitions in your
        // theme with <item name=”android:windowActivityTransitions”>true</item>.
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

        // Attach a callback used to capture the shared elements from this Activity to be used
        // by the container transform transition
        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())

        // Keep system bars (status bar, navigation bar) persistent throughout the transition.
        window.sharedElementsUseOverlay = false

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val exitTransition = MaterialElevationScale(/* growing= */ false)
        val reenterTransition = MaterialElevationScale(/* growing= */ true)

        initView()
    }


    private fun initView(){
        val cardView = findViewById<TransformationChildCard>(R.id.transition_card)
        val startView = findViewById<ConstraintLayout>(R.id.constraint_content)
        cardView.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)

            val options = ActivityOptions.makeSceneTransitionAnimation(
                this,
                startView,
                "shared_element_container" // The transition name to be matched in Activity B.
            )
            startActivity(intent, options.toBundle())
        }

        val floatBtn = findViewById<View>(R.id.floating_btn)
        floatBtn.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)

            val options = ActivityOptions.makeSceneTransitionAnimation(
                this,
                floatBtn,
                "shared_element_container" // The transition name to be matched in Activity B.
            )
            startActivity(intent, options.toBundle())
        }

    }

}