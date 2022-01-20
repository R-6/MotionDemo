package com.elon.motiondemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.animation.*
import com.google.android.material.transition.platform.MaterialArcMotion
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        parseShareTransition()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    fun parseShareTransition(){

        // Enable Activity Transitions. Optionally enable Activity transitions in your
        // theme with <item name=”android:windowActivityTransitions”>true</item>.
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

        // Set the transition name, which matches Activity A’s start view transition name, on
        // the root view.
        findViewById<View>(android.R.id.content).transitionName = "shared_element_container"

        // Attach a callback used to receive the shared elements from Activity A to be
        // used by the container transform transition.
        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())

        // Set this Activity’s enter and return transition to a MaterialContainerTransform
        window.sharedElementEnterTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            fadeMode = MaterialContainerTransform.FADE_MODE_IN
            pathMotion = MaterialArcMotion()
//            interpolator = BounceInterpolator()
            duration = 350L
        }
        window.sharedElementReturnTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            pathMotion = MaterialArcMotion()
//            interpolator = BounceInterpolator()
            duration = 250L
        }

    }
}